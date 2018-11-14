package assignment04.comparators;

import assignment04.Person;

public final class BirthdateComparator {

	public static int compare(Person person1, Person person2) {
		if (person1.get_birthdate().after(person2.get_birthdate())) {
			return 1;
		} else if (person1.get_birthdate().before(person2.get_birthdate())) {
			return -1;
		} else return 0;
	}
}