package com.company.comparators;

import com.company.employee.Employee;

import java.util.Comparator;

public class SeniorityComparator {


    public static int compare(Employee employee1, Employee employee2) {
        if (employee1.getSeniority().toDurationMillis() - employee2.getSeniority().toDurationMillis() > 0) {
            return -1;
        } else if (employee1.getSeniority().toDurationMillis() - employee2.getSeniority().toDurationMillis() < 0) {
            return 1;
        } else return 0;

    }
}
