package com.manish.crypto.marketdata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class CoinmarketcapPriceFetcher implements CryptoPriceFetcher {

    // IMPORTANT : Put your API Key here else this pricer and unit tests for this won't work
    // If you don't have one, go to coinmarketcap.com and generate free of cost
    private static final String API_KEY = "dummy-key-here";
    // Replace with your CoinMarketCap API Key
    private static final String COIN_MARKET_CAP_API_URL = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest";
    @Override
    public Map<String, Double> fetchCryptoPrices(Set<CharSequence> cryptoCurrencies) throws IOException, JSONException {
        Map<String, Double> prices = new HashMap<>();

        StringBuilder urlBuilder = new StringBuilder(COIN_MARKET_CAP_API_URL);
        urlBuilder.append("?symbol=");
        for (var currency : cryptoCurrencies) {
            urlBuilder.append(currency);
            urlBuilder.append(",");
        }
        urlBuilder.append("&convert=GBP"); // Fetch prices in GBP

        URL url = new URL(urlBuilder.toString());
        System.out.println("Trying cmd " + urlBuilder.toString());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-CMC_PRO_API_KEY", API_KEY); // Set your API key

        if (connection.getResponseCode() != 200) {
            throw new IOException("Failed to fetch data from API (status code: " + connection.getResponseCode() + ")");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        connection.disconnect();

        JSONObject jsonObject = new JSONObject(response.toString());

        System.out.println(jsonObject.toString());
        JSONObject dataObject = jsonObject.getJSONObject("data");

        for (CharSequence currency : cryptoCurrencies) {
            if (dataObject.has((String) currency)) {
                JSONObject priceObject = dataObject.getJSONObject((String) currency).getJSONObject("quote").getJSONObject("GBP");
                double price = priceObject.getDouble("price");
                prices.put((String) currency, price);
            }
        }

        return prices;
    }
}
