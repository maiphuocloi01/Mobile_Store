package com.groupone.mobilestore.util;

import java.text.DecimalFormat;

public class ConvertString {

    public static String convertPrice(long price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price) + "₫";
    }

    public static String convertCountReview(int count) {
        return "(" + count + ")";
    }
}
