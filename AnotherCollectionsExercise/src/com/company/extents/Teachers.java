package com.company.extents;

import com.company.model.Teacher;

import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;

public class Teachers {

    public static Set<Teacher> teachers = new HashSet<>();

    public List<String> getSortedOnPolishCollation() {

        Collator collator = Collator.getInstance(Locale.forLanguageTag("pl-PL"));
        collator.setStrength(Collator.PRIMARY);

        List<String> sortedTeachersNames = teachers.stream()
                .map(e -> e.get_firstName() + " " + e.get_lastName())
                .collect(Collectors.toList());

        Collections.sort(sortedTeachersNames, collator);

        return sortedTeachersNames;
    }

    public List<String> getFilteredAndSortedOnNationality(Locale locale) {
            Collator collator = Collator.getInstance(locale);
            collator.setStrength(Collator.PRIMARY);

            return teachers.stream()
                    .filter(e -> e.getLocale().equals(locale))
                    .map(e -> e.get_firstName() + " " + e.get_lastName())
                    .sorted(collator)
                    .collect(Collectors.toList());
        }
    }
