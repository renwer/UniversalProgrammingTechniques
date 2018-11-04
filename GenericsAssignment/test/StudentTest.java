import com.company.Car;
import com.company.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {

    private Student testedStudent;

    @Before
    public void init() {
        testedStudent = new Student("Robert Smith");
    }

    @Test
    public void assureDeepCloneCreatesEqualStudent() {
        Student copy = testedStudent.deepClone();
        Assert.assertEquals(copy, testedStudent);
    }

    @Test
    public void assureAggregationgTwoStudentsYieldsProperSum() {
        Student copy = testedStudent.deepClone();
        copy.addGrade(5);
        Student anotherOne = new Student("Jack");
        anotherOne.addGrade(1);

        int intermediateResult = 0;
        intermediateResult = copy.aggregate(intermediateResult);
        intermediateResult = anotherOne.aggregate(intermediateResult);

        Assert.assertEquals(6, intermediateResult);
    }

    @Test(expected = RuntimeException.class)
    public void assureGivingNegativeGradeThrowsException() {
        testedStudent.addGrade(-1);
    }


}
