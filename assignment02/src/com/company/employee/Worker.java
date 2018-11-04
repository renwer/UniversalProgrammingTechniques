package com.company.employee;

import org.joda.time.DateTime;

import java.math.BigDecimal;

public class Worker extends Employee {

	private DateTime _employmentDate;
	private BigDecimal _bonus;

	// attributes
	// * employment date
	// * bonus
	
	public Worker(String firstName, String lastName, DateTime dateOfBirth, DateTime hireDate) {
		super(firstName, lastName, dateOfBirth, hireDate);
	}

	public DateTime getEmploymentDate() {
		return _employmentDate;
	}

	public BigDecimal getBonus() {
		return _bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this._bonus = bonus;
	}
}