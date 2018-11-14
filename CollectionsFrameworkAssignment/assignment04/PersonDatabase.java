package assignment04;

import assignment04.comparators.BirthdateComparator;
import assignment04.comparators.FirstNameComparator;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class PersonDatabase {

	private List<Person> _sortedByFirstName;
	private List<Person> _sortedByFirstNameAndBirthdate;
	private List<Person> _sortedByBirthDate;
	private Map<Person, Date> _peopleAndDates;

	public PersonDatabase(List<Person> unsortedList) {

		this._sortedByFirstName = unsortedList.parallelStream()
		.sorted(FirstNameComparator::compare)
		.collect(Collectors.toList());

		this._sortedByFirstNameAndBirthdate = unsortedList.parallelStream()
		.sorted(Person::compareTo)
		.collect(Collectors.toList());

		this._sortedByBirthDate = unsortedList.parallelStream()
		.sorted(BirthdateComparator::compare)
		.collect(Collectors.toList());

		this._peopleAndDates = unsortedList.parallelStream()
				.collect(Collectors.toMap(Person::getSelf, Person::get_birthdate));
	}

	public List<Person> sortedByFirstName() {
		return this._sortedByFirstName;
	}
	
	public List<Person> sortedBySurnameFirstNameAndBirthdate() {
		return this._sortedByFirstNameAndBirthdate;
	}
	
	public List<Person> sortedByBirthdate() {
		return this._sortedByBirthDate;
	}
	
	public List<Person> bornOnDay(Date date) {
		return this._peopleAndDates.entrySet().parallelStream()
				.filter(e -> e.getValue().getDay() == date.getDate())
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());
	}
}