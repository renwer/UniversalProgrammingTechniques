package com.company.model;

import java.util.Set;

public class Subject {
    private String _name;
    private Department _supervisingDepartment;
    private Teacher _lecturer;
    private Set<Student> _attendingStudents;

    public Subject(String _name, Department _supervisingDepartment, Teacher _lecturer, Set<Student> _attendingStudents) {
        this._name = _name;
        this._supervisingDepartment = _supervisingDepartment;
        this._lecturer = _lecturer;
        this._attendingStudents = _attendingStudents;
    }
}
