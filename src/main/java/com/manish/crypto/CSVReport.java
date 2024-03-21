package com.manish.crypto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class CSVReport implements Report {
    private final String CSVFileName;

    public CSVReport(String CSVFileName) {
        this.CSVFileName = CSVFileName;
    }

    @Override
    public void print(Map<CharSequence, CryptoAsset> data) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(CSVFileName));

            writer.write(CryptoAsset.getHeader());

            double totalCost = 0, totalCurrentValue = 0, totalGainLoss = 0;

            for (Map.Entry<CharSequence, CryptoAsset> entry : data.entrySet()) {
                writer.write(entry.getValue().toCSVString() + "\n");
                totalCost += entry.getValue().getTotalCost();
                totalCurrentValue += entry.getValue().getCurrentValue();
                totalGainLoss += entry.getValue().getGainLoss();
            }

            String footer = "\nTotalCost, TotalCurrentValue, TotalGainLoss";
            footer += "\n" + totalCost + "," + totalCurrentValue + "," + totalGainLoss;
            writer.write(footer);

            System.out.println("Successfully wrote to the file: " + CSVFileName);

            writer.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
