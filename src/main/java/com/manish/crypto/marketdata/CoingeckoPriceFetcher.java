package com.manish.crypto.marketdata;

import com.manish.crypto.Utility;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class CoingeckoPriceFetcher implements CryptoPriceFetcher {
    private final String COIN_GECKO_API_URL = "https://api.coingecko.com/api/v3/simple/price";

    public Map<String, Double> fetchCryptoPrices(Set<CharSequence> cryptoCurrenciesSymbols) throws IOException, JSONException {
        Map<String, Double> prices = new HashMap<>();

        Set<CharSequence> cryptoCurrenciesIDs = cryptoCurrenciesSymbols.stream().map(c -> Utility.symbolToIdMapping.get(c)).collect(Collectors.toSet());

        StringBuilder urlBuilder = new StringBuilder(COIN_GECKO_API_URL);
        urlBuilder.append("?ids=");
        for (var currency : cryptoCurrenciesIDs) {
            // coingecko needs CryptoID like bitcoin and not BTC
            urlBuilder.append(currency);
            urlBuilder.append(",");
        }

        urlBuilder.append("&vs_currencies=gbp"); // Fetch prices in GBP

        System.out.println(urlBuilder.toString());
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

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
        for (CharSequence currency : cryptoCurrenciesIDs) {
            if (jsonObject.has((String) currency)) {
                JSONObject priceObject = jsonObject.getJSONObject((String) currency);
                double gbpPrice = priceObject.getDouble("gbp");
                prices.put((String) currency, gbpPrice);
            }
        }

        if ( prices.isEmpty()) {
            throw new JSONException("Error retrieving prices from Coingeckoapi");
        }
        for (Map.Entry<String, Double> entry : prices.entrySet()) {
            System.out.println(entry.getKey() + ": £" + entry.getValue());
        }
        return prices;
    }
}