package com.company.extents;

import com.company.model.Subject;

import java.util.HashSet;
import java.util.Set;

public class Subjects {

    private Set<Subject> subjects;

    public Subjects() {
        this.subjects = new HashSet<>();
    }

    public void add(Subject subject) {
        this.subjects.add(subject);
    }
}
