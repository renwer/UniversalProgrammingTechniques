package com.company.extents;

import com.company.model.Department;

import java.util.HashSet;
import java.util.Set;

public class Departaments {

    private Set<Department> departments;

    public Departaments() {
        this.departments = new HashSet<>();
    }

    public void add(Department department) {
        this.departments.add(department);
    }
}
