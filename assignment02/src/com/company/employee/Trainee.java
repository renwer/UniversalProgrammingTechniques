package com.company.employee;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.Interval;

import java.util.Date;


public class Trainee extends Employee {

	// attributes:
	// * practice start date
	// * practice length (in days)

	private DateTime _practiceStartDate;
	private int _practiceLength;
	
	public Trainee(String firstName, String lastName, DateTime dateOfBirth, DateTime practiceStartDate, int practiceLength) {
		super(firstName, lastName, dateOfBirth, practiceStartDate);
		this._practiceLength = practiceLength;
	}

	public DateTime getPracticeStartDate() {
		return _practiceStartDate;
	}

	public int get_practiceLength() {
		return _practiceLength;
	}

	public void setPracticeStartDate(DateTime practiceStartDate) {
		this._practiceStartDate = practiceStartDate;
	}

	//trainee's seniority to be counted as 0
	@Override
	public Interval getSeniority() {
		return new Interval(null);
	}

	public void setPracticeLength(int practiceLength) {
		this._practiceLength = practiceLength;
	}
}