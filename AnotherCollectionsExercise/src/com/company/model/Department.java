package com.company.model;

import java.util.HashSet;
import java.util.Set;

public class Department {
    private String _name;
    private Set<Teacher> _employees;

    public Department(String _name) {
        this._name = _name;
        this._employees = new HashSet<>();
    }

    public void addTeacher(Teacher teacher) {
        this._employees.add(teacher);
    }

    public boolean removeTeacher(Teacher teacher) {
        return this._employees.remove(teacher);
    }

    public String get_name() {
        return _name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "_name='" + _name + '\'' +
                ", _employees=" + _employees +
                '}';
    }
}
