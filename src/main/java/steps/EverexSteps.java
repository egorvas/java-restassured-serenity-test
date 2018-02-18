package steps;

import api.Everex;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by egorvas on 29.07.16.
 */
public class EverexSteps extends Everex{

    @Step
    public Response getTokensWithPricesFromEverex() {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("id","id");
        params.put("method","getTokensWithPrices");
        params.put("params",new ArrayList<String>());
        return post(params);

    }

}
