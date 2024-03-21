package com.manish.crypto;

import com.manish.crypto.marketdata.CryptoPriceFetcher;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class PortfolioManager {

    // A final report showing latest valuation of the portfolio
    private Report outputReport;
    private CryptoPriceFetcher cryptoPriceFetcher;
    private CSVParser csvParser;

    public PortfolioManager(Report outputReport, CryptoPriceFetcher cryptoPriceFetcher, CSVParser csvParser) {
        this.outputReport = outputReport;
        this.cryptoPriceFetcher = cryptoPriceFetcher;
        this.csvParser = csvParser;
    }

    public void evaluatePortfolio() throws IOException {

        Map<CharSequence, CryptoAsset> cryptosMap = csvParser.parse();

        List<CryptoAsset> cryptos = new ArrayList<>();

        for ( Map.Entry<CharSequence, CryptoAsset> entry : cryptosMap.entrySet()){
            boolean add = cryptos.add(entry.getValue());
        }

        var cryptoSymbols = cryptos.stream().map(c -> c.getSymbol()).collect(Collectors.toSet());

        Map<String, Double> cryptoPrice = cryptoPriceFetcher.fetchCryptoPrices(cryptoSymbols);

        try {
            for (var asset: cryptos) {

                asset.setCurrentPrice(cryptoPrice.getOrDefault( (String) asset.getCryptoId(), 0.0));
                asset.setCurrentValue(asset.getSize() * asset.getCurrentPrice());
                asset.setGainLoss(asset.getCurrentValue() + asset.getTotalCost());
                log.info(asset.toString());
                System.out.println(asset);
            }
        } catch (Exception e) {
            log.info(e.toString());
        }

        outputReport.print(cryptosMap);
    }

}
