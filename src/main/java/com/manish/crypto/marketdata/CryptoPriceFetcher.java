package com.manish.crypto.marketdata;

import org.json.JSONException;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface CryptoPriceFetcher {
    public Map<String, Double> fetchCryptoPrices(Set<CharSequence> cryptoCurrencies) throws IOException, JSONException;

}
