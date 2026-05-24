package com.aftab.kkdresses.util;

public class PriceDecoder {

    private static final String SECRET =
            "NOSIMCARDK";

    public static Long decodePrice(String code) {

        if (code == null || code.isEmpty()) {
            return null;
        }

        StringBuilder price =
                new StringBuilder();

        code = code.toUpperCase();

        for (char ch : code.toCharArray()) {

            int index =
                    SECRET.indexOf(ch);

            if (index == -1) {
                return null;
            }

            if (index == 9) {
                price.append("0");
            } else {
                price.append(index + 1);
            }
        }

        return Long.parseLong(
                price.toString()
        );
    }
}