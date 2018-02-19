package steps;

import api.Coinmarketcap;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by egorvas on 29.07.16.
 */
public class CoinmarketcapSteps extends Coinmarketcap{

    @Step(callNestedMethods=false)
    public static Response getRatesFromCoinmarketcap(String convert, int limit) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("convert",convert);
        params.put("limit",limit);
        return get("/ticker", params);
    }

}
