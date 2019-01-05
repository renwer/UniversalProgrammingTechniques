package com.company.model;

import java.util.Locale;

public enum Nationality {
    POLISH("Polish", "pl-PL"),
    UKRAINIAN("Ukrainian", "uk-UA"),
    BELARUSSIAN("Belarussian", "be-BY"),
    SLOVAK("Slovak", "sk-SK"),
    LITHUANIAN("Lithuanian", "lt-LT"),
    LATVIAN("Latvian", "lv-LV"),
    BRITISH("British", "en-GB"),
    INDIAN("Indian", "hi-IN"),
    CHINESE("Chinese", "zh-CN"),
    VIETNAMESE("Vietnamese", "vi-VN");

    private final String name;
    private final Locale locale;


    Nationality(String name, String localeString) {
        this.name = name;
        this.locale = Locale.forLanguageTag(localeString);
    }

    public String getName() {
        return name;
    }

    public Locale getLocale() {
        return locale;
    }
}
