package com.manish.crypto;

import com.manish.crypto.marketdata.CoingeckoPriceFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CoingeckoPriceFetcherTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void when_freeAPIisCalled_then_cryptoPriesAreRetriedved() throws IOException {
        Set<CharSequence> currencies = Set.of("BTC", "NMR", "SKL", "ETC", "ICP", "XLM", "ETH", "LINK", "LTC", "ADA");
        CoingeckoPriceFetcher coingeckoPriceFetcher = new CoingeckoPriceFetcher();
        Map<String, Double> prices = coingeckoPriceFetcher.fetchCryptoPrices(currencies);

        assertFalse(prices.isEmpty());
        assertEquals(prices.size(), currencies.size());

    }
}