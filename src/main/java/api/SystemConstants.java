package api;

/**
 * Created by egorvas on 27.07.16.
 */

public class SystemConstants {

    public final static String COINMARKETCAP_API_URL = getFailsafeProperty("coinmarketcap.url", "http://api.coinmarketcap.com/v1");
    public final static String ETHPLORER_API_URL = getFailsafeProperty("ethplorer.url", "https://ethplorer.io/service/service.php");
    public final static String EVEREX_API_URL = getFailsafeProperty("everex.url", "http://rates.everex.io");
    public final static int ERROR_PERCENT = getFailsafeProperty("error.percent", 10);


    private static String getFailsafeProperty(String key, String def) {
        if (System.getProperty(key) == null || System.getProperty(key).equals("")) {
            return def;
        }
        return System.getProperty(key);
    }

    private static int getFailsafeProperty(String key, int def) {
        if (System.getProperty(key) == null || System.getProperty(key).equals("")) {
            return def;
        }
        return Integer.parseInt(System.getProperty(key));
    }

}
