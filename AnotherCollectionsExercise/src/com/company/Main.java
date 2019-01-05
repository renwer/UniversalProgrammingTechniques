package com.company;

import com.company.generators.CommonGenerator;

public class Main {
    public static void main(String[] args) {
        CommonGenerator generator = new CommonGenerator();

        System.out.println(generator.groups);
        System.out.println(generator.students);
        System.out.println(generator.teachers);
        System.out.println(generator.departments);
    }
}
