package com.manish.crypto;

import com.manish.crypto.marketdata.CoinmarketcapPriceFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// Note : CoingmarketcapPriceFetcher test will only run once you configure your KEY in it's class
@SpringBootTest
class CoinmarketcapPriceFetcherTest {

    @Autowired
    CoinmarketcapPriceFetcher coinmarketcapPriceFetcher;
    @BeforeEach
    void setUp() {
    }

    @Test
    void when_freeAPIisCalled_then_cryptoPriesAreRetriedved() throws IOException {
        Set<CharSequence> currencies = Set.of("MATIC", "CELO", "CRV", "NU", "GRT", "UMA", "FIL", "ANKR", "ALGO", "1INCH", "BTC", "NMR", "SKL", "ETC", "ICP", "XLM", "ETH", "LINK", "LTC", "ADA");

        Map<String, Double> prices = coinmarketcapPriceFetcher.fetchCryptoPrices(currencies);

        assertFalse(prices.isEmpty());
        assertEquals(prices.size(), currencies.size());

    }
}