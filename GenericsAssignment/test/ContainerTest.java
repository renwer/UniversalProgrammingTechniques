import com.company.Car;
import com.company.Container;
import com.company.Student;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class ContainerTest {

    private Container<Student, Integer> testedStudentContainer;

    @Before
    public void init() {
        testedStudentContainer = new Container<>();
    }

    @Test
    public void assureEmptyContainerReturnsEmptyList() {
        Assert.assertEquals(0, testedStudentContainer.elements().size());
    }

    @Test
    public void assureAddingElementsProperlyWorks() {
        testedStudentContainer.addAllElements(Arrays.asList(new Student("John"), new Student("Alex")));
        Assert.assertEquals(2, testedStudentContainer.elements().size());
    }

    @Test
    public void assureCloningAtIndexReturnsEqualElement() {
        Student s = new Student("John");
        testedStudentContainer.addAllElements(Arrays.asList(s, new Student("Alex")));
        Student clone = testedStudentContainer.cloneElementAtIndex(0);
        Assert.assertEquals(s, clone);
        Assert.assertNotSame(s, clone);
    }

    @Test
    public void assureAggregatingAllElementsYieldsProperResult() {

        testedStudentContainer.addElement(new Student("John"));
        testedStudentContainer.addElement(new Student("Alex"));

        testedStudentContainer.elements().get(0).addGrade(10);
        testedStudentContainer.elements().get(1).addGrade(15);
        Assert.assertEquals(25, (int)testedStudentContainer.aggregateAllElements());
    }

}
