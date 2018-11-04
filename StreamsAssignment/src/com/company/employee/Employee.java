package com.company.employee;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.Interval;

import java.math.BigDecimal;

public abstract class Employee extends Person {

	//
	// attributes:
	// * salary (use BigDecimal type for representing currency values)
	// * manager (empty if at top of hierarchy)

	private BigDecimal _salary;
	private Manager _manager;
	private DateTime _hireDate;
	
	protected Employee(String firstName, String lastName, DateTime dateOfBirth, DateTime hireDate) {
		super(firstName, lastName, dateOfBirth);
		this._hireDate = hireDate;
	}

	//getters and setters
	public void setSalary(BigDecimal salary) {
		this._salary = salary;
	}

	public DateTime get_hireDate() {
		return _hireDate;
	}

	public void setManager(Manager manager) {
		this._manager = manager;
		manager.addSubordinate(this);

		if (manager.getManager() != null) {
			manager.addSubordinate(this);
		}
	}

	public BigDecimal getSalary() {
		return _salary;
	}

	public Manager getManager() {
		return _manager;
	}

	public Interval getSeniority() {
		return new Interval(_hireDate, new Instant());
	}


}