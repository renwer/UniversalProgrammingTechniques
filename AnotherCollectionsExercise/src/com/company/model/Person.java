package com.company.model;

import com.company.generators.PeselGenerator;

import java.text.Collator;
import java.time.LocalDate;
import java.util.*;

public abstract class Person implements Comparable<Person> {
    public final String _pesel;
    private final String _firstName;
    private final String _lastName;
    private final LocalDate _dateOfBirth;
    private final int _sex;

    Nationality nationality;

    public Person(String _firstName, String _lastName, LocalDate _dateOfBirth, Nationality nationality) {
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._dateOfBirth = _dateOfBirth;
        this.nationality = nationality;
        this._pesel = PeselGenerator.generate_PESEL();
        this._sex = (int)Math.round(Math.random());
    }

    public Locale getLocale() {
        return this.nationality.getLocale();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(_pesel, person._pesel) &&
                Objects.equals(_firstName, person._firstName) &&
                Objects.equals(_lastName, person._lastName) &&
                Objects.equals(_dateOfBirth, person._dateOfBirth) &&
                nationality == person.nationality;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_pesel, _firstName, _lastName, _dateOfBirth, nationality);
    }

    @Override
    public int compareTo(Person person) {
        if (this._pesel.equals(person._pesel)) return 0;
        else return this._dateOfBirth.compareTo(person._dateOfBirth);
    }

    public String get_pesel() {
        return _pesel;
    }

    public String get_firstName() {
        return _firstName;
    }

    public String get_lastName() {
        return _lastName;
    }

    public LocalDate get_dateOfBirth() {
        return _dateOfBirth;
    }

    public Nationality getNationality() {
        return nationality;
    }

    @Override
    public String toString() {
        return "Person{" +
                "_pesel=" + _pesel +
                ", _firstName='" + _firstName + '\'' +
                ", _lastName='" + _lastName + '\'' +
                ", _dateOfBirth=" + _dateOfBirth +
                ", _sex=" + _sex +
                ", nationality=" + nationality +
                '}';
    }
}
