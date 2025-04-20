package org.example.storage;

import java.util.Map;
import java.util.HashMap;


public class MonthHelper {

    public static final Map<Integer, String> MONTHS;

    static {
        MONTHS = new HashMap<>();
        MONTHS.put(1, "January");
        MONTHS.put(2, "February");
        MONTHS.put(3, "March");
        MONTHS.put(4, "April");
        MONTHS.put(5, "May");
        MONTHS.put(6, "June");
        MONTHS.put(7, "July");
        MONTHS.put(8, "August");
        MONTHS.put(9, "September");
        MONTHS.put(10, "October");
        MONTHS.put(11, "November");
        MONTHS.put(12, "December");
    }

    public static String getMonthName(int monthNumber) {
        return MONTHS.getOrDefault(monthNumber, "Неизвестный месяц");
    }
}
