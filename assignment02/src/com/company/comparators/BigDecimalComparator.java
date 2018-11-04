package com.company.comparators;


import java.math.BigDecimal;

public class BigDecimalComparator {
    public static int compare(BigDecimal first, BigDecimal second) {
        return second.compareTo(first);
    }
}
