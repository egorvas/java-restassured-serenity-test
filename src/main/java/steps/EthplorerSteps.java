package steps;

import api.Ethplorer;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by egorvas on 29.07.16.
 */
public class EthplorerSteps extends Ethplorer{

    @Step
    public Response getTokenDataFromEthplorer(Object data) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("data",data);
        return get("/service/service.php",params);

    }

}
