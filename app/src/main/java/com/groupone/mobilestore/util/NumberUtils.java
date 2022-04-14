package com.groupone.mobilestore.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class NumberUtils {

    //Convert long number to vietnam dong
    public static String convertPrice(long price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price) + "₫";
    }

    //Convert int number to (number)
    public static String convertParentheses(int count) {
        return "(" + count + ")";
    }

    public static String convertDateType1(int dayOfMonth, int month, int year){
        return "Ngày " + dayOfMonth + " tháng " + month + " năm " + year;
    }

    public static String convertDateType2(int dayOfMonth, int month, int year){
        Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }
}
