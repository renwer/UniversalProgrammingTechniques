package com.company.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Student extends Person {

    private static Long currentStudentBookNo = 0L;

    public Long _studentBookNumber;

    public Student(String _firstName, String _lastName, LocalDate _dateOfBirth, Nationality nationality) {
        super(_firstName, _lastName, _dateOfBirth, nationality);
        this._studentBookNumber = ++currentStudentBookNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(_studentBookNumber, student._studentBookNumber) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_studentBookNumber);
    }

    @Override
    public int compareTo(Person p) {
        return Long.compare(this._studentBookNumber, ((Student)p)._studentBookNumber);
    }

    public Long get_studentBookNumber() {
        return _studentBookNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "_studentBookNumber=" + _studentBookNumber +
                ", nationality=" + nationality +
                '}';
    }
}
