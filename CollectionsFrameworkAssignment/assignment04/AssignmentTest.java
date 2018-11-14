package assignment04;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class AssignmentTest {

    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private PersonDatabase database;
    InputParser parser;

    @Before
    public void init() {
        try {
            database = new PersonDatabase(parser.parse(new File("/home/lofi/Programming/PJATK/UTP/UniversalProgrammingTechniques/CollectionsFrameworkAssignment/assignment04/data.txt")));
            parser = new InputParser();
        } catch (Exception e) {
            //...
        }
    }

    @Test
    public void assureInvalidRecordsAreNotInTheDatabase() {
        Assert.assertEquals(5, database.sortedByBirthdate().size());
    }
    @Test
    public void assureSortingByFirstNameYieldsProperFirstPerson() {
        try {
            Assert.assertEquals(new Person("Ada", "Lovelace", formatter.parse("1815-11-10 00:00:00")), database.sortedByFirstName().get(0));
        } catch (Exception e) {
            //...
        }
    }
    @Test
    public void assureSortingByBirthdateYieldsProperFirstPerson() {
        try {
            Assert.assertEquals(new Person("George", "Boole", formatter.parse("1815-11-02 00:00:00")), database.sortedByBirthdate().get(0));
        } catch (Exception e) {
            //...
        }
    }

    @Test
    public void hecc() {
        try {
            System.out.println(database.bornOnDay(formatter.parse("1815-11-02 00:00:00")).size());
        } catch (Exception e) {
            //...
        }

    }

}
