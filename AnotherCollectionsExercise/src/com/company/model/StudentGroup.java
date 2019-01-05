package com.company.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class StudentGroup {
    private String _name;
    private Set<Student> _students;

    public StudentGroup(String name) {
        this._name = name;
        this._students = new HashSet<>();
    }

    public boolean addStudent(Student student) {
        if (this._students.size() < 10) {
            this._students.add(student);
            return true;
        } else return false;
    }

    public Set<Student> get_students() {
        return _students;
    }

    public boolean removeStudent(Student student) {
        return this._students.remove(student);
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroup that = (StudentGroup) o;
        return Objects.equals(_name, that._name) &&
                Objects.equals(_students, that._students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_name, _students);
    }

    public String get_name() {
        return _name;
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "_name='" + _name + '\'' +
                ", _students=" + _students +
                '}';
    }
}
