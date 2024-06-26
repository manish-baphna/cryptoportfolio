package com.manish.crypto;

import com.manish.crypto.marketdata.CoingeckoPriceFetcher;
import com.manish.crypto.marketdata.CoinmarketcapPriceFetcher;
import com.manish.crypto.marketdata.CryptoPriceFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

@SpringBootTest
class PortfolioManagerRealAPITest {
    @Autowired
    @Qualifier("CoingeckoPriceFetcher")
    CryptoPriceFetcher cryptoPriceFetcher;

    @Autowired
    @Qualifier("FullPortfolio")
    CSVParser csvParser;

    @Autowired
    @Qualifier("CoingeckoReport")
    Report outputCSVReport;

    @BeforeEach
    void setUp() {
    }

    @Test
    void when_portfolioMgrEvaluated_withCoingecko_then_resultsAreGenerated() throws IOException {

        PortfolioManager portfolioManager = new PortfolioManager(outputCSVReport, cryptoPriceFetcher, csvParser);
        portfolioManager.evaluatePortfolio();

    }

}