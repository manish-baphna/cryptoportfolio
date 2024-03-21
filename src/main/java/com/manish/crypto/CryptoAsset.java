package com.manish.crypto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CryptoAsset {
    CharSequence symbol;
    CharSequence cryptoId;
    String tradeDateTime;
    String side;
    double size;
    double tradePrice;
    double fees;
    double totalCost;
    double currentPrice;
    double currentValue;
    double gainLoss;

    static CryptoAsset merge(CryptoAsset v1, CryptoAsset v2) {
        v1.setTotalCost(v1.getTotalCost() + v2.getTotalCost());

        if ( v1.getSide().equalsIgnoreCase(v2.getSide())) {
            double newSize = v1.getSize() + v2.getSize();
            v1.setSize(newSize);
        } else {
            v1.setSize(Math.abs(v1.getSize() - v2.getSize()));
            if ( v1.getTotalCost() <= 0.0) {
                v1.setSide("BUY");
            } else {
                v1.setSide("SELL");
            }
        }

        v1.setTradePrice(v1.getTotalCost()/ v1.getSize());
        return v1;
    }

    static public String getHeader(){
        return "SYMBOL, ID, TRADE_DATE, SIZE, TRADE_PRICE,TOTAL_COST, CURRENT_PRICE, CURRENT_VALUE, GAIN_LOSS";
    }
    public String toCSVString() {
        return  symbol +
                "," + cryptoId +
                "," + tradeDateTime +
                "," + size +
                "," + tradePrice +
                "," + totalCost +
                "," + currentPrice +
                "," + currentValue +
                "," + gainLoss ;
    }
}
