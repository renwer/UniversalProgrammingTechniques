package com.company;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.company.comparators.BigDecimalComparator;
import com.company.comparators.PayrollComparator;
import com.company.comparators.SeniorityComparator;
import com.company.employee.Employee;
import com.company.employee.Manager;
import com.company.employee.Trainee;
import com.company.employee.Worker;
import com.company.payroll.PayrollEntry;
import org.joda.time.DateTime;
import org.joda.time.Months;

public final class HumanResourcesStatistics {

	public List<PayrollEntry> payroll(List<Employee> employees) {

		List<PayrollEntry> result = employees
				.stream()
				.map(employee -> {
				//Set bonus to bonus value if employee is a worker, 0 otherwise
				BigDecimal bonus = employee instanceof Worker ? ((Worker) employee).getBonus() : new BigDecimal(0);
				PayrollEntry entry = new PayrollEntry(employee, employee.getSalary(), bonus);
				return entry;
		})
				.collect(Collectors.toList());
		return result;
	}

	public List<PayrollEntry> subordinatesPayroll(Manager manager) {
		System.out.println("Payroll for all " + manager + "'s subordinates:");
		List<PayrollEntry> result = manager.getAllSubordinates().stream()
				.map(employee -> {
					//Set bonus to bonus value if employee is a worker, 0 otherwise
					BigDecimal bonus = employee instanceof Worker ? ((Worker) employee).getBonus() : new BigDecimal(0);
					PayrollEntry entry = new PayrollEntry(employee, employee.getSalary(), bonus);
					return entry;
				})
					.collect(Collectors.toList());
		return result;
	}

	public Employee longestSeniorityEmployee(List<Employee> employees) {
	    Employee result = employees.stream()
                .sorted(SeniorityComparator::compare)
                .findFirst()
                .get();
	    return result;
    }

    public BigDecimal highestSalaryWithoutBonus(List<Employee> employees) {
	    BigDecimal result = employees.stream()
                .map(Employee::getSalary)
                .sorted(BigDecimalComparator::compare)
                .findFirst()
                .get();
	    return result;

    }

    public BigDecimal highestSalaryWithBonus(List<Employee> employees) {
	    List<PayrollEntry> payrolls = payroll(employees);

	    BigDecimal result = payrolls.stream()
                .sorted(PayrollComparator::compare)
                .findFirst()
                .get()
                .getSum();

	    return result;
    }

    public List<Employee> subordinatesStartingWithA(Manager manager) {
	    List<Employee> result = manager.getImmediateSubordinates().stream()
                .filter(e -> e.getFirstName().startsWith("A"))
                .collect(Collectors.toList());
	    return result;
    }

    public List<Employee> salaryGreaterThan75000(List<Employee> employees) {
        List<Employee> result = employees.stream()
                .filter(e -> e.getSalary().intValue() > 75000)
                .collect(Collectors.toList());

        return result;
    }

	public BigDecimal bonusTotal(List<Employee> employees) {
        List<PayrollEntry> payrolls = payroll(employees);

        BigDecimal result = payrolls.stream()
                .map(p -> p.get_bonus())
                .filter(Objects::nonNull)
                .reduce(BigDecimal::add)
                .get();

        return result;
	}

	// The best solution is to implement the below features as static methods having a list of Employee as the first input argument.
	// In addition the list of arguments may be augmented with parameters required for the given feature.
	// (assignment 03)
	// methods:
	//
	// * search for Employees older than given employee and earning less than him
	public static List<Employee> olderThenAndEarnMore(List<Employee> allEmployees, Employee employee) {
		 return allEmployees.stream()
				.filter(e -> (e.getAge() > employee.getAge() && e.getSalary().compareTo(employee.getSalary()) > 0))
				.collect(Collectors.toList());
	}

	//
	// * search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
	public static List<Employee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount) {

				return allEmployees.stream()
				.filter(e -> e instanceof Trainee)
				.filter(e -> ((Trainee) e).get_practiceLength() > daysCount)
				.peek(e -> {
					e.setSalary(e.getSalary().add(e.getSalary().multiply(new BigDecimal(0.1))));
				})
				.collect(Collectors.toList());
	}

	//
	// * search for Workers whose seniority is longer than given number of months and give them bonus of 300 if their bonus is smaller
	public static List<Employee> seniorityLongerThan(List<Employee> allEmployees, int monthCount) {
		return allEmployees.stream()
				.filter(e -> e instanceof Worker)
				.filter(e -> {
					DateTime now = new DateTime();
					Months m = Months.monthsBetween(e.get_hireDate(), now);
					return m.getMonths() > monthCount;
				})
				.peek(e -> ((Worker) e).setBonus(new BigDecimal(300)))
				.collect(Collectors.toList());
	}

	//
	// * search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
	public static List<Employee> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
		return allEmployees.stream()
				.filter(e -> e instanceof Worker)
				.filter(e -> {
					DateTime now = new DateTime();
					Months m = Months.monthsBetween(e.get_hireDate(), now);
					int years = m.getMonths()/12;
					return years > 0 && years <= 3;
				})
				.peek(e -> {
					BigDecimal newSalary = e.getSalary().add(e.getSalary().multiply(new BigDecimal(0.1)));
					e.setSalary(newSalary);
				})
				.collect(Collectors.toList());
	}

	//
	// * search for Workers whose seniority is longer than the seniority of a given employee and earn less than him and align their salary with the given employee
	//   nastêpnie zrównaj ich wynagrodzenie z wynagrodzeniem danego pracownika
	public static List<Employee> seniorityLongerThan(List<Employee> allEmployees, Employee employee) {
		return allEmployees.stream()
				.filter(e -> e instanceof Worker)
				.filter(e -> {
					return (e.getSeniority().toDurationMillis() > employee.getSeniority().toDurationMillis()) && (e.getSalary().compareTo(employee.getSalary()) < 0);
				})
				.peek(e -> e.setSalary(employee.getSalary()))
				.collect(Collectors.toList());
	}

	//
	// * search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
	public static List<Employee> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
		return allEmployees.stream()
				.filter(e -> e instanceof Worker)
				.filter(e -> {
					DateTime now = new DateTime();
					Months m = Months.monthsBetween(e.get_hireDate(), now);
					int seniorityYears = m.getMonths()/12;
					return (seniorityYears >= 2 && seniorityYears <= 4) && (e.getAge() > age);
				})
				.collect(Collectors.toList());
	}
}