package com.company.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Teacher extends Person {

    private AcademicDegree _degree;
    private final LocalDate _hiredate;

    public Teacher(String _firstName, String _lastName, LocalDate _dateOfBirth, Nationality nationality, AcademicDegree _degree, LocalDate _hiredate) {
        super(_firstName, _lastName, _dateOfBirth, nationality);
        this._degree = _degree;
        this._hiredate = _hiredate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return _degree == teacher._degree &&
                Objects.equals(_hiredate, teacher._hiredate) && super.equals(o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), _degree, _hiredate);
    }

    @Override
    public int compareTo(Person p) {
        if (this._degree.ordinal() - ((Teacher)p)._degree.ordinal() == 0) {
            return super.compareTo(p);
        } else return this._degree.ordinal() - ((Teacher)p)._degree.ordinal();
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "_degree=" + _degree +
                ", _hiredate=" + _hiredate +
                '}';
    }
}
