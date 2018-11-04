package com.company.comparators;

import com.company.payroll.PayrollEntry;

public class PayrollComparator {
    public static int compare(PayrollEntry first, PayrollEntry second) {
        return second.getSum().compareTo(first.getSum());
    }
}
