package com.manish.crypto;

import com.manish.crypto.marketdata.CoingeckoPriceFetcher;
import com.manish.crypto.marketdata.CoinmarketcapPriceFetcher;
import com.manish.crypto.marketdata.CryptoPriceFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class PortfolioManagerRealAPITest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void when_portfolioMgrEvaluated_withCoingecko_then_resultsAreGenerated() throws IOException {
        // TODO Remove these new with Spring Boot implementation
        CSVParser csvParser = new CSVParser("src\\test\\java\\resources\\fills.csv");
        CryptoPriceFetcher cryptoPriceFetcher = new CoingeckoPriceFetcher();

        Report outputCSVReport = new CSVReport("src\\test\\java\\resources\\CryptoReportCoingecko.csv");

        PortfolioManager portfolioManager = new PortfolioManager(outputCSVReport, cryptoPriceFetcher, csvParser);

        portfolioManager.evaluatePortfolio();

    }

    @Test
    void when_portfolioMgrEvaluated_withCoinmarketcap_then_resultsAreGenerated() throws IOException {
        // TODO Remove these new with Spring Boot implementation
        CSVParser csvParser = new CSVParser("src\\test\\java\\resources\\fillsNU.csv");
        CryptoPriceFetcher cryptoPriceFetcher = new CoinmarketcapPriceFetcher();

        Report outputCSVReport = new CSVReport("src\\test\\java\\resources\\CryptoReportCoinmarketcap.csv");

        PortfolioManager portfolioManager = new PortfolioManager(outputCSVReport, cryptoPriceFetcher, csvParser);

        portfolioManager.evaluatePortfolio();

    }
}