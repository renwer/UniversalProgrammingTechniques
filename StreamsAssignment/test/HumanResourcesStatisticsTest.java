
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.company.HumanResourcesStatistics;
import com.company.employee.Employee;
import com.company.employee.Manager;
import com.company.employee.Trainee;
import com.company.employee.Worker;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HumanResourcesStatisticsTest {
	
	// Create a HR structure which resembles the below one:
	//
	// Director (an instance of Manager class (Director does not have a manager)
	//   |- Manager01
	//        |- Worker
	//        |- Worker
	//        |- Trainee
	//        |- ...
	//   |- Manager02
	//        |- ...
	//   |- ...
	//   |- Worker
	//   |- Worker
	//   |- Trainee


	private Manager director;
	private Manager manager1;
	private Worker worker1;
	private Worker worker2;
	private Worker worker5;
	private Worker worker6;
	private Worker worker7;
	private Worker worker8;
	private Trainee trainee1;
	private Trainee trainee2;
	private Worker worker9;
	private Worker worker10;
	private Worker worker11;
	private Worker worker12;
	private Worker worker13;
	private Worker worker14;

	private List<Employee> _allEmployees = new ArrayList<>();
	private HumanResourcesStatistics statistics = new HumanResourcesStatistics();

	@Before
	public void init() {
		director = new Manager("Mark", "Zuckerberg", new DateTime(1970, 1, 15, 0, 0), new DateTime(2005,1,2,  3, 4));
		director.setSalary(new BigDecimal(150_000));
		director.setBonus(new BigDecimal(20_000));
		//manager is left null for director

		manager1 = new Manager("Obi Van", "Kenoby", new DateTime(1960, 2, 12, 0, 0), new DateTime(2006,1,2,  3, 4));
		manager1.setSalary(new BigDecimal(100_000));
		manager1.setManager(director);

		worker1 = new Worker("Bender", "Rodriguez", new DateTime(1980, 1, 23, 0, 0), new DateTime(2007,1,2,  3, 4));
		worker1.setSalary(new BigDecimal(70_000));
		worker1.setManager(manager1);

		worker2 = new Worker("Thomas", "Anderson", new DateTime(1963, 3, 11, 0, 0), new DateTime(2010,1,2,  3, 4));
		worker2.setSalary(new BigDecimal(72_000));
		worker2.setBonus(new BigDecimal(1000));
		worker2.setManager(manager1);

		worker5 = new Worker("Gandalf", "White", new DateTime(1933, 2, 5, 0, 0), new DateTime(2011,1,2,  3, 4));
		worker5.setSalary(new BigDecimal(73_000));
		worker5.setManager(director);

		worker6 = new Worker("Albreht", "Durer", new DateTime(1977, 4, 18, 0, 0), new DateTime(2012,1,2,  3, 4));
		worker6.setSalary(new BigDecimal(73_000));
		worker6.setManager(manager1);


		worker7 = new Worker("Neil", "Armstrong", new DateTime(1968, 1, 4, 0, 0), new DateTime(2010,1,2,  3, 4));
		worker7.setSalary(new BigDecimal(71_000));
		worker7.setBonus(new BigDecimal(2000));
		worker7.setManager(manager1);

		worker8 = new Worker("Buzz", "Aldrin", new DateTime(1964, 4, 26, 0, 0), new DateTime(2007,1,2,  3, 4));
		worker8.setSalary(new BigDecimal(73_000));
		worker8.setManager(director);

		trainee1 = new Trainee("Luke", "Skywalker", new DateTime(1979, 10, 24, 0, 0), new DateTime(2014,1,2,  3, 4), 78);
		trainee1.setSalary(new BigDecimal(20_000));
		trainee1.setManager(manager1);

		worker9 = new Worker("John", "Loom", new DateTime(1980, 1, 23, 0, 0), new DateTime(2007,1,2,  3, 4));
		worker9.setSalary(new BigDecimal(75_000));
		worker9.setManager(manager1);

		worker10 = new Worker("Fred", "Wright", new DateTime(1963, 3, 11, 0, 0), new DateTime(2010,1,2,  3, 4));
		worker10.setSalary(new BigDecimal(73_000));
		worker10.setManager(director);

		worker11 = new Worker("Ron", "Whisley", new DateTime(1933, 2, 5, 0, 0), new DateTime(2011,1,2,  3, 4));
		worker11.setSalary(new BigDecimal(71_000));
		worker11.setManager(director);

		trainee2 = new Trainee("Ian", "Curtis", new DateTime(1979, 10, 24, 0, 0), new DateTime(2014,1,2,  3, 4), 75);
		trainee2.setSalary(new BigDecimal(20_000));
		trainee2.setManager(manager1);

		worker12 = new Worker("Alex", "Vincent", new DateTime(1963, 3, 11, 0, 0), new DateTime(2010,1,2,  3, 4));
		worker12.setSalary(new BigDecimal(74_000));
		worker12.setBonus(new BigDecimal(2000));
		worker12.setManager(manager1);


		worker13 = new Worker("Damien", "Kowalski", new DateTime(1933, 2, 5, 0, 0), new DateTime(2016,1,2,  3, 4));
		worker13.setSalary(new BigDecimal(71_000));
		worker13.setManager(director);

		worker14 = new Worker("Amanda", "Brown", new DateTime(1933, 2, 5, 0, 0), new DateTime(2006,1,2,  3, 4));
		worker14.setSalary(new BigDecimal(72_000));
		worker14.setManager(manager1);

		_allEmployees.addAll(Arrays.asList(worker1, worker2, worker5, worker6, worker7, worker8, worker9, worker10, worker11, worker12, worker13, worker14, director, manager1, trainee1, trainee2));
	}

	// all employees ---  i.e. Workers, Trainees and their Managers and top Director (also an instance of Manager class)


	@Test
	public void subordinatesPayroll() {
		System.out.println(statistics.subordinatesPayroll(director));
	}

	@Test
	public void theMostSeniorEmployee() {
		Assert.assertEquals(director, statistics.longestSeniorityEmployee(_allEmployees));
	}

	@Test
    public void hightstSalaryWithoutBonus() {
	    Assert.assertEquals(new BigDecimal(150_000), statistics.highestSalaryWithoutBonus(_allEmployees));
    }

    @Test
    public void highestSalaryWithBonus() {
	    Assert.assertEquals(new BigDecimal(170_000), statistics.highestSalaryWithBonus(_allEmployees));
    }

    @Test
    public void subordinatesStartingWithA() {
	    System.out.println(statistics.subordinatesStartingWithA(manager1));
    }

    @Test
    public void employeesWithSalaryGreaterThan75000() {
	    System.out.println(statistics.salaryGreaterThan75000(_allEmployees));
    }

    @Test
    public void sumOfBonusesTest() {
	    Assert.assertEquals(new BigDecimal(25000), statistics.bonusTotal(_allEmployees));
    }




    @Test
	public void olderThenAndEarnMoreTest() {
		System.out.println(statistics.olderThenAndEarnMore(_allEmployees, worker6));
	}

	@Test
	public void practiceLengthLongerThanTest() {
		System.out.println(statistics.practiceLengthLongerThan(_allEmployees, 76));
	}

	@Test
	public void seniorityLongerThanTest() {
		System.out.println(statistics.seniorityLongerThan(_allEmployees, 150));
	}

	@Test
	public void seniorityBetweenOneAndThreeYearsTest() {
		System.out.println(statistics.seniorityBetweenOneAndThreeYears(_allEmployees));
		Worker expected = (Worker)statistics.seniorityBetweenOneAndThreeYears(_allEmployees).get(0);
		Assert.assertEquals(expected, worker13);
	}

	@Test
	public void seniorityLongerAndSalaryLessThanEmployeesTest() {
		//System.out.println(statistics.seniorityLongerThan(_allEmployees, worker2));
		Worker expected = (Worker)statistics.seniorityLongerThan(_allEmployees, worker2).get(0);
		Assert.assertEquals(expected, worker1);

	}

	@Test
	public void seniorityBetweenTwoAndFourYearsAndAgeGreaterThanTest() {
		System.out.println(statistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(_allEmployees, 70));
		Worker expected = (Worker)statistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(_allEmployees, 70).get(0);
		Assert.assertEquals(expected, worker13);
	}

}