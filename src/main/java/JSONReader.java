import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    //Get release date
    public static String getRelease() throws IOException, JSONException {
        //Initialize date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();

        JSONObject json = readJsonFromUrl("https://store.steampowered.com/api/appdetails/?appids=823500");
        String releaseDate = json.getJSONObject("823500").getJSONObject("data").getJSONObject("release_date").getString("date");
        System.out.println("[" + dtf.format(now) + "]" + "(LOG) Fetched game release date");
        return releaseDate;
    }

    //Get price
    public static String getGamePrice() throws IOException, JSONException {
        //Initialize date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();

        JSONObject json = readJsonFromUrl("https://store.steampowered.com/api/appdetails/?appids=823500");
        String gamePrice;
        if(!json.getJSONObject("823500").getJSONObject("data").isNull("price_overview")) {
            gamePrice = json.getJSONObject("823500").getJSONObject("data").getJSONObject("price_overview").getString("final_formatted");
        } else {
            gamePrice = json.getJSONObject("823500").getJSONObject("data").getString("is_free");
            if (gamePrice.equals("true")){
                gamePrice = "Free for Some Reason?";
            } else {
                gamePrice = "Not Free";
            }
        }
        System.out.println("[" + dtf.format(now) + "]" + "(LOG) Fetched game price");
        return gamePrice;
    }
}