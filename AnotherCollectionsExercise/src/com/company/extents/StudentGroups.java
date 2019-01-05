package com.company.extents;

import com.company.model.StudentGroup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentGroups {

    public static Set<StudentGroup> studentGroups = new HashSet<>();


    public void add(StudentGroup group) {
        studentGroups.add(group);
    }
}
