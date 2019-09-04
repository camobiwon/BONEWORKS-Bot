package org.camobiwon.boneworksbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONException;
import org.json.JSONObject;

class JSONReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    //JSONReader
    private static JSONObject readJsonFromUrl() throws IOException, JSONException {
        try (InputStream is = new URL("https://store.steampowered.com/api/appdetails/?appids=823500").openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    //Get release date
    static String getRelease() throws IOException, JSONException {
        JSONObject json = readJsonFromUrl();
        return json.getJSONObject("823500").getJSONObject("data").getJSONObject("release_date").getString("date");
    }

    //Get price
    static String getGamePrice() throws IOException, JSONException {
        JSONObject json = readJsonFromUrl();
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
        return gamePrice;
    }
}