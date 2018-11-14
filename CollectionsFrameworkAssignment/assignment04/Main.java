package assignment04;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        File test = new File("assignment04/data.txt");
        InputParser parser = new InputParser();
        List<Person> result = parser.parse(test);

        PersonDatabase database = new PersonDatabase(result);
        System.out.println(database.sortedByBirthdate());
        System.out.println(database.sortedByFirstName());
        System.out.println(database.sortedBySurnameFirstNameAndBirthdate());
        System.out.println(database.bornOnDay(formatter.parse("1879-03-14 00:00:00")));
    }
}