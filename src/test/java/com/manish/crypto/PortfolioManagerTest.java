package com.manish.crypto;

import com.manish.crypto.marketdata.CoingeckoPriceFetcher;
import com.manish.crypto.marketdata.CryptoPriceFetcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class DummyReport implements Report {
    StringBuilder reportData;
    @Override
    public void print(Map<CharSequence, CryptoAsset> data) {
        reportData = new StringBuilder();

        //reportData.append(CryptoAsset.getHeader());

        double totalCost = 0, totalCurrentValue = 0, totalGainLoss = 0;

        for (Map.Entry<CharSequence, CryptoAsset> entry : data.entrySet()) {
            //reportData.append(entry.getValue().toCSVString());
            totalCost += entry.getValue().getTotalCost();
            totalCurrentValue += entry.getValue().getCurrentValue();
            totalGainLoss += entry.getValue().getGainLoss();
        }

        String footer = "TotalCost, TotalCurrentValue, TotalGainLoss,";
        footer += totalCost + "," + totalCurrentValue + "," + totalGainLoss;
        reportData.append(footer);
    }
}

@ExtendWith(MockitoExtension.class)
class PortfolioManagerTest {
    CSVParser csvParser;

    @Mock
    CryptoPriceFetcher mockCryptoPriceFetcher;
    Report outputReport;

    Map<String, Double> cryptoPrice;

    @BeforeEach
    void setUp() throws IOException {
        csvParser = new CSVParser("src\\test\\java\\resources\\fillsNU.csv");
        cryptoPrice = new HashMap<>() {{
            put("nucypher", 0.25);
        }};
        when(mockCryptoPriceFetcher.fetchCryptoPrices(any())).thenReturn(cryptoPrice);

        outputReport = new DummyReport();
    }

    @Test
    void when_portfolioMgrEvaluated_withMockAPI_then_resultsAreGenerated() throws IOException {
        // TODO Remove these new with Spring Boot implementation
        PortfolioManager portfolioManager = new PortfolioManager(outputReport, mockCryptoPriceFetcher, csvParser);

        portfolioManager.evaluatePortfolio();

        String actualOutput = ((DummyReport)outputReport).reportData.toString();
        System.out.println(actualOutput);

        String expectOutput = "TotalCost, TotalCurrentValue, TotalGainLoss," +
                "-502.2320058831315,477.49324593000006,-24.73875995313142";

        assertEquals(actualOutput, expectOutput);

    }

}