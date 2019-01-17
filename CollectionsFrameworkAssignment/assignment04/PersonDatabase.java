package assignment04;

import assignment04.comparators.BirthdateComparator;
import assignment04.comparators.FirstNameComparator;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

    public void serialize() {

        System.out.println("Serializing the db...");
        try {
            FileOutputStream out = new FileOutputStream("db");

            DataOutputStream outputStream = new DataOutputStream(out);

            for(Person p: sortedByFirstName()) {
                outputStream.writeUTF(p.get_firstName());
                outputStream.writeUTF(p.get_surname());
                outputStream.writeLong(p.get_birthdate().getTime());
                outputStream.writeUTF("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Done.");
    }

    public void deserialize(String path) {

        System.out.println("Recovering the database from the file: " + path);
        
        List<Person> result = new ArrayList<>();

        try {
            File file = new File(path);
            DataInputStream inputStream = new DataInputStream(new FileInputStream(file));

            while (inputStream.available() > 0) {
                String firstName = inputStream.readUTF();
                String lastName = inputStream.readUTF();
                Date birthdate = new Date(inputStream.readLong());
                //skipping the new line character
                inputStream.readUTF();

                System.out.println(firstName + " " + lastName + " " + birthdate);

                result.add(new Person(firstName, lastName, birthdate));
            }


            PersonDatabase deserializedDB = new PersonDatabase(result);
            this._sortedByBirthDate = deserializedDB._sortedByBirthDate;
            this._sortedByFirstName = deserializedDB._sortedByFirstName;
            this._sortedByFirstNameAndBirthdate = deserializedDB._sortedByFirstNameAndBirthdate;
            this._peopleAndDates = deserializedDB._peopleAndDates;

            System.out.println("Done.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}