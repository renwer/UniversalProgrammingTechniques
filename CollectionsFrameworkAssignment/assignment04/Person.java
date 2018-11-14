package assignment04;

import java.util.Date;
import java.util.Objects;

public class Person implements Comparable<Person> {
	
	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;
	
	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}

	public Person getSelf() {
		return this;
	}

	@Override
	public int compareTo(Person otherPerson) {

		if (this._firstName.compareTo(otherPerson.get_firstName()) != 0) {
			return this._firstName.compareTo(otherPerson.get_firstName());
		}
		else if (this._surname.compareTo(otherPerson.get_surname()) != 0) {
			return this._surname.compareTo(otherPerson.get_surname());
		}
		else if (this._birthdate.before(otherPerson._birthdate)) {
			return 1;
		}
		else if (this._birthdate.after(otherPerson._birthdate)) {
			return -1;
		}
		else return 0;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return Objects.equals(_firstName, person._firstName) &&
				Objects.equals(_surname, person._surname) &&
				Objects.equals(_birthdate, person._birthdate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(_firstName, _surname, _birthdate);
	}

	@Override
	public String toString() {
		return _firstName + " " + _surname;
	}

	public String get_firstName() {
		return _firstName;
	}

	public String get_surname() {
		return _surname;
	}

	public Date get_birthdate() {
		return _birthdate;
	}
}