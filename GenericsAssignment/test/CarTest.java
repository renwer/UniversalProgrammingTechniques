import com.company.Car;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarTest {

    private Car testedCar;

    @Before
    public void init() {
        testedCar = new Car(2000, "Grey", "Renault");
    }

    @Test
    public void assureDeepCloneCreatesEqualCar() {
        Car copy = testedCar.deepClone();
        Assert.assertEquals(copy, testedCar);
    }

    @Test
    public void assureAggregatingTwoCarsYieldsProperSum() {
        Car copy = testedCar.deepClone();
        Car anotherOne = new Car(1000, "Black", "Honda");
        int intermediateResult = 0;

        intermediateResult = copy.aggregate(intermediateResult);
        intermediateResult = anotherOne.aggregate(intermediateResult);

        Assert.assertEquals(3000, intermediateResult);
    }

    @Test(expected = RuntimeException.class)
    public void assureCreatingCarWithInvalidArgumentsYieldsException() {
        //just by having the price of 0 it should throw an exception, as well as for empty color or make strings
        Car invalidCar = new Car(0, "NotImportant", "Whatever");
    }



}
