package com.company.extents;

import com.company.model.Student;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Students {

    public static Set<Student> students = new HashSet<>();


    public void add(Student student) {
        this.students.add(student);
    }

    public List<String> getSortedOnPolishCollation() {
        Collator collator = Collator.getInstance(Locale.forLanguageTag("pl-PL"));
        collator.setStrength(Collator.PRIMARY);

        List<String> sortedStudentsNames = students.stream()
                .map(e -> e.get_firstName() + " " + e.get_lastName())
                .collect(Collectors.toList());

        Collections.sort(sortedStudentsNames, collator);

        return sortedStudentsNames;
    }

    public List<String> getFilteredAndSortedOnNationality(Locale locale) {

        Collator collator = Collator.getInstance(locale);
        collator.setStrength(Collator.PRIMARY);

        return students.stream()
                .filter(e -> e.getLocale().equals(locale))
                .map(e -> e.get_firstName() + " " + e.get_lastName())
                .sorted(collator)
                .collect(Collectors.toList());
    }
}
