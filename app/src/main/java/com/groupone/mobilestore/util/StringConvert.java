package com.groupone.mobilestore.util;

import java.text.Normalizer;

public class StringConvert {

    public static String removeDiacriticalMarks(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
