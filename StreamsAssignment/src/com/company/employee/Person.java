package com.company.employee;
import org.joda.time.*;

public abstract class Person {

	// To implement an attribute means that you provide a backing field and
	// getter or optionally setter for read-write properties/attributes
	// 
	// NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
	// THOSE SHOULD BE COMPUTED ON-LINE
	//
	// attributes:
	// * first name (read-only)
	// * surname (read-only)
	// * birth date (read-only) --- date MUST BE represented by an instance of
	// type designed for date representation (either Date or LocalDate)
	//
	// * age (derived --- computed based on birth date) --- implemented as a
	// getter calculating the difference between the current date and birth date

	private final String _firstName; // backing field
	private String _surname;
	private DateTime _birthDate;

	protected Person(String firstName, String _surname, DateTime birthDate) {
		_firstName = firstName;
		this._surname = _surname;
		this._birthDate = birthDate;
	}

	public String getFirstName() { // getter
		return _firstName;
	}

	//implemented getter for age
	public int getAge() {
		Years years = Years.yearsBetween(_birthDate, new Instant());
		return years.getYears();
	}

	@Override
	public String toString() {
		return _firstName + " " + _surname;
	}
}