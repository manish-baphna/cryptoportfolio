package com.manish.crypto;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class CSVParser {

    private final String csvFile;

    public CSVParser(String inputCSVFile) {
        this.csvFile = inputCSVFile;
    }

    public Map<CharSequence, CryptoAsset> parse(){

        Map<CharSequence, CryptoAsset> cryptos = new HashMap<CharSequence, CryptoAsset>();

        String delimiter = ","; // Change delimiter if needed (e.g., ';' or tab)

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            Scanner scanner = new Scanner(reader);
            scanner.useDelimiter(delimiter); // Set delimiter for the scanner

            // Skip header row (optional, adjust if your CSV has a header)
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter(delimiter);
                String fieldPortfolio = lineScanner.next();
                String fieldTradeId = lineScanner.next();
                String fieldProduct = lineScanner.next();
                String fieldSide = lineScanner.next();
                String fieldTradeDate = lineScanner.next();
                String fieldSize = lineScanner.next();
                String fieldSymbol = lineScanner.next();
                String fieldTradePrice = lineScanner.next();
                String fieldFees = lineScanner.next();
                String fieldTotal = lineScanner.next();

                CryptoAsset crypto = new CryptoAsset();
                crypto.setSymbol(fieldSymbol);
                crypto.setCryptoId(Utility.symbolToIdMapping.get(fieldSymbol).toLowerCase());
                if (fieldSide.contains("Earn"))
                    fieldSide = "BUY";
                crypto.setSide(fieldSide);
                crypto.setSize(Double.parseDouble(fieldSize));
                crypto.setTotalCost(Double.parseDouble(fieldTotal));
                crypto.setTradeDateTime(fieldTradeDate.replace('T', ' ').replace('Z', ' '));
                lineScanner.close(); // Close the inner scanner for each line
                cryptos.merge(crypto.getSymbol(), crypto, CryptoAsset::merge);
            }
            scanner.close(); // Close the main scanner
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cryptos;
    }
}
