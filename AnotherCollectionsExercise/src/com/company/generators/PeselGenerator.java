package com.company.generators;

import java.time.LocalDate;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class PeselGenerator {

    private static final Random RANDOM = new Random();

    public static String generate_PESEL(){

        long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2015, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        LocalDate birthDate = LocalDate.ofEpochDay(randomDay);

        String year = getLast2DigitsOfYear(birthDate);
        String month = get2DigitsMonth(birthDate);
        String day = get2digitsDay(birthDate);
        String id_and_sex = String.format("%04d", RANDOM.nextInt(1000));
        String PESEL = year+month+day+id_and_sex;
        String controlDigit = controlDigit(PESEL);
        PESEL = PESEL + controlDigit;

        return PESEL;

    }

    //private static final int ZERO = (int) '0';

    private static String controlDigit(String PESEL){
        int A = (int)PESEL.charAt(0);
        int B = (int)PESEL.charAt(1);
        int C = (int)PESEL.charAt(2);
        int D = (int)PESEL.charAt(3);
        int E = (int)PESEL.charAt(4);
        int F = (int)PESEL.charAt(5);
        int G = (int)PESEL.charAt(6);
        int H = (int)PESEL.charAt(7);
        int I = (int)PESEL.charAt(8);
        int J = (int)PESEL.charAt(9);

        int sum = A*1 + B*3 + C*7 + D*9 + E*1 + F*3 + G*7 + H*9 + I*1 + J*3;
        int mod = sum%10;
        int q = 10 - mod;
        if (mod == 0)
            q = 0;
        return q+"";
    }

    private static String getLast2DigitsOfYear(LocalDate birthDate) {
        return (birthDate.getYear()+"").substring(2);
    }
    private static String get2DigitsMonth(LocalDate birthDate) {
        int moth2digits = birthDate.getMonthValue();
        if (birthDate.getYear() > 2000 && birthDate.getYear() < 2999){
            moth2digits = birthDate.getMonthValue() + 20;
            return String.format("%02d", moth2digits);
        }
        else return String.format("%02d",moth2digits);
    }
    private static String get2digitsDay(LocalDate birthDate){
        return String.format("%02d",birthDate.getDayOfMonth());
    }

}