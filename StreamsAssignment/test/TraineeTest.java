import com.company.employee.Trainee;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class TraineeTest {
    private Trainee trainee = new Trainee("Robert", "Smith", new DateTime(1999, 7, 20, 0, 0, 0, 0), new DateTime(2017, 1, 20, 0, 0, 0, 0), 57);

    @Test
    public void assureAgeIsCalculatedCorrectly() {
        Assert.assertEquals(19, trainee.getAge());
    }
}
