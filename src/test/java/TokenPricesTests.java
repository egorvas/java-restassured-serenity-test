import api.SystemConstants;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import steps.StepSets;

import java.util.List;

/**
 * Created by egorvas on 29.07.16.
 */

@RunWith(SerenityRunner.class)
public class TokenPricesTests extends StepSets{

    @Test
    public void tokenPricesTest() {

        Response coinmarketcapResponse = coinmarketcapSteps().getRatesFromCoinmarketcap("USD", 100000);
        Response everexApiResponse = everexSteps().getTokensWithPricesFromEverex();
        JsonParser jsonParser = new JsonParser();

        for (JSONObject object : (everexApiResponse.jsonPath().getList("result", JSONObject.class))) {
            if (!object.get("external_id").toString().equals("ETH")) {

                Response ethplorerApiResponse = ethplorerSteps().getTokenDataFromEthplorer(object.get("address").toString());
                JsonElement element = jsonParser.parse(ethplorerApiResponse.asString());
                if (element.getAsJsonObject().get("isContract").getAsBoolean()) {
                    if (element.getAsJsonObject().getAsJsonObject("token").get("price").isJsonObject()) {
                        if (element.getAsJsonObject().getAsJsonObject("token").getAsJsonObject("price").get("rate")!=null){
                            List<Double> prices;
                            if (object.containsKey("cmc")) {
                                prices = coinmarketcapResponse.jsonPath().
                                        getList("findAll { it.id == '" + object.get("cmc").
                                        toString() + "' }.price_usd", Double.class);

                            } else {
                                prices = coinmarketcapResponse.jsonPath().
                                        getList("findAll { it.symbol == '" + object.get("external_id").
                                        toString() + "' }.price_usd", Double.class);

                            }
                            if (prices.size() > 1) {
                                logSteps().message("Size of the tag more then 1: "+object.get("external_id"));
                            }
                            assertSteps().assertFieldCloseTo("Wrong price for token "+object.get("external_id")
                                            .toString(), element.getAsJsonObject().getAsJsonObject("token").
                                            getAsJsonObject("price").get("rate").getAsDouble(),prices.get(0),
                                    prices.get(0)* SystemConstants.ERROR_PERCENT / 100);
                            Assert.assertThat("Wrong price for token "+object.get("external_id").toString(),
                                    element.getAsJsonObject().getAsJsonObject("token").getAsJsonObject("price")
                                            .get("rate").getAsDouble(), Matchers.closeTo(prices.get(0),
                                    prices.get(0)* SystemConstants.ERROR_PERCENT / 100));
                        }else{
                            logSteps().message("Token without price: "+object.get("external_id"));
                        }
                    }else{
                        logSteps().message("Token with price false: "+object.get("external_id"));
                    }
                }
            }
        }
    }

}
