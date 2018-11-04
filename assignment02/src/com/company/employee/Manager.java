package com.company.employee;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public final class Manager extends Worker {

	// attributes
	// * subordinates (a list of immediate subordinates)
	// * all subordinates (a list of subordinates in all hierarchy)

	private List<Employee> immediateSubordinates;
	private List<Employee> allSubordinates;

	public Manager(String firstName, String lastName, DateTime dateOfBirth, DateTime hireDate) {
		super(firstName, lastName, dateOfBirth, hireDate);
		immediateSubordinates = new ArrayList<>();
		allSubordinates = new ArrayList<>();
	}

	//getters
	public List<Employee> getImmediateSubordinates() {
		return immediateSubordinates;
	}

	public List<Employee> getAllSubordinates() {
		return allSubordinates;
	}

	public void addSubordinate(Employee e) {
		this.immediateSubordinates.add(e);
		this.allSubordinates.add(e);
	}
}