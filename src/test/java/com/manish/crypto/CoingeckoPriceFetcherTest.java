package com.manish.crypto;

import com.manish.crypto.marketdata.CoingeckoPriceFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoingeckoPriceFetcherTest {

    @Autowired
    CoingeckoPriceFetcher coingeckoPriceFetcher;
    @BeforeEach
    void setUp() {
    }

    @Test
    void when_freeAPIisCalled_then_cryptoPriesAreRetriedved() throws IOException {
        Set<CharSequence> currencies = Set.of("BTC", "NMR", "SKL", "ETC", "ICP", "XLM", "ETH", "LINK", "LTC", "ADA");

        Map<String, Double> prices = coingeckoPriceFetcher.fetchCryptoPrices(currencies);

        assertFalse(prices.isEmpty());
        assertEquals(prices.size(), currencies.size());

    }
}