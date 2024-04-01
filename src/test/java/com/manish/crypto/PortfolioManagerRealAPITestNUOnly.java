package com.manish.crypto;

import com.manish.crypto.marketdata.CoingeckoPriceFetcher;
import com.manish.crypto.marketdata.CoinmarketcapPriceFetcher;
import com.manish.crypto.marketdata.CryptoPriceFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class PortfolioManagerRealAPITestNUOnly {

    @Autowired
    @Qualifier("CoinmarketcapPriceFetcher")
    CryptoPriceFetcher cryptoPriceFetcher;
    @Autowired
    @Qualifier("NUOnlyPortfolio")
    CSVParser csvParser;
    @Autowired
    @Qualifier("CoinmarketcapReport")
    Report outputCSVReport;

    @Autowired
    PortfolioManager portfolioManager;

    @BeforeEach
    void setUp() {
    }

    @Test
    void when_portfolioMgrEvaluated_withCoinmarketcap_then_resultsAreGenerated() throws IOException {

        PortfolioManager portfolioManager = new PortfolioManager(outputCSVReport, cryptoPriceFetcher, csvParser);
        portfolioManager.evaluatePortfolio();

    }
}