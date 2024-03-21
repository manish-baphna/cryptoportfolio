package com.manish.crypto;

import java.util.HashMap;
import java.util.Map;

public class Utility {

    // TODO move this mapping into Config file
    public static final Map<String, String> symbolToIdMapping = new HashMap<>() {{
        put("MATIC", "Polygon");
        put("CGLD", "celo");
        put("CRV", "curve-dao-token");
        put("NU", "nucypher");
        put("FIL", "filecoin");
        put("UMA", "uma");
        put("GRT", "the-graph");
        put("ANKR", "ANKR");
        put("ALGO", "algorand");
        put("1INCH", "1inch");
        put("BTC", "bitcoin");
        put("NMR", "numeraire");
        put("ETC", "ethereum-classic");
        put("SKL", "skale");
        put("ICP", "internet-computer");
        put("ETH", "ethereum");
        put("XLM", "stellar");
        put("LINK", "chainlink");
        put("LTC", "litecoin");
        put("ADA", "cardano");

    }};
}
