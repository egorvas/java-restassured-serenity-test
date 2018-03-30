import api.SystemConstants;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.junit.annotations.Concurrent;
import net.thucydides.junit.annotations.TestData;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.CoinmarketcapSteps;
import steps.EverexSteps;
import steps.StepSets;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by egorvas on 29.07.16.
 */

@RunWith(SerenityParameterizedRunner.class)
@Concurrent
public class TokenPricesTests extends StepSets{


    public TokenPricesTests(JSONObject object) {
        this.object = object;
    }

    private static JsonPath coinmarketcapResponse;
    private JSONObject object;

    @TestData
    public static Collection<Object[]> testData() {
        coinmarketcapResponse = CoinmarketcapSteps.getRatesFromCoinmarketcap("USD", 100000).jsonPath();
        Response everexApiResponse = EverexSteps.getTokensWithPricesFromEverex();
        List<JSONObject> everexList = everexApiResponse.jsonPath().getList("result", JSONObject.class);
        Object [][] objectArray = new Object[everexList.size()][1];
        for (int i=0; i<everexList.size(); i++){
            objectArray[i][0] = everexList.get(i);
        }

        return Arrays.asList(objectArray);
    }

    @Test
    public void tokenPricesTest() {
        if (!this.object.get("address").equals("0x000000000000000000000000000000000000000")) { // ignore ETH token
            String token = this.object.get("external_id").toString();
            logSteps().message(String.format("Token is %s", token));
            JsonParser jsonParser = new JsonParser();
            Response ethplorerApiResponse = ethplorerSteps().getTokenDataFromEthplorer(this.object.get("address").toString());
            JsonElement element = jsonParser.parse(ethplorerApiResponse.asString());
            if (element.getAsJsonObject().get("isContract").getAsBoolean()) {
                Assert.assertTrue(String.format("Check that object 'price' for token %s exists", token),
                        element.getAsJsonObject().getAsJsonObject("token").get("price").isJsonObject());
                Assert.assertTrue(String.format("Check that field 'rate' for token %s exists", token),
                        element.getAsJsonObject().getAsJsonObject("token").getAsJsonObject("price").get("rate") != null);
                Assert.assertTrue(String.format("Check that field 'rate' for token %s not null", token),
                        !element.getAsJsonObject().getAsJsonObject("token").getAsJsonObject("price").get("rate").isJsonNull());
                List<Double> prices;
                if (this.object.containsKey("cmc")) {
                    prices = coinmarketcapResponse.
                            getList("findAll { it.id == '" + this.object.get("cmc").
                                    toString() + "' }.price_usd", Double.class);

                } else {
                    prices = coinmarketcapResponse.
                            getList("findAll { it.symbol == '" + this.object.get("external_id").
                                    toString() + "' }.price_usd", Double.class);

                }
                Assert.assertTrue(String.format("Check that token %s exists in Coinmarketcap response", token),
                        prices.size() != 0);

                if (prices.size() > 1) {
                    logSteps().message(String.format("Number of the token %s in Coinmarketcap response is %d)"
                            , token, prices.size()));
                }

                Assert.assertThat(String.format("Wrong price for token %s", token), element.getAsJsonObject()
                                .getAsJsonObject("token").getAsJsonObject("price").get("rate").getAsDouble(),
                        Matchers.closeTo(prices.get(0), prices.get(0) * SystemConstants.ERROR_PERCENT / 100));

            } else {
                logSteps().message(String.format("isContract == false for Token %s", token));
            }

        }
    }

}
