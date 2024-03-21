package com.manish.crypto;

import com.manish.crypto.marketdata.CoinmarketcapPriceFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CoinmarketcapPriceFetcherTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void when_freeAPIisCalled_then_cryptoPriesAreRetriedved() throws IOException {
        Set<CharSequence> currencies = Set.of("MATIC", "CELO", "CRV", "NU", "GRT", "UMA", "FIL", "ANKR", "ALGO", "1INCH", "BTC", "NMR", "SKL", "ETC", "ICP", "XLM", "ETH", "LINK", "LTC", "ADA");
        CoinmarketcapPriceFetcher coinmarketcapPriceFetcher = new CoinmarketcapPriceFetcher();
        Map<String, Double> prices = coinmarketcapPriceFetcher.fetchCryptoPrices(currencies);

//        if (prices.isEmpty()) {
//            System.out.println("Error fetching prices.");
//        } else {
//            for (Map.Entry<String, Double> entry : prices.entrySet()) {
//                System.out.println(entry.getKey() + ": £" + entry.getValue());
//            }
//        }

        assertFalse(prices.isEmpty());
        assertEquals(prices.size(), currencies.size());

    }
}