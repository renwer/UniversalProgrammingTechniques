package com.company.payroll;

import java.math.BigDecimal;

import com.company.employee.Employee;

public final class PayrollEntry {

	private final Employee _employee;
	private final BigDecimal _salaryPlusBonus;
	private final BigDecimal _salary;
	private final BigDecimal _bonus;
	
	public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
		this._employee = employee;
		this._salary = salary;

		if (salary == null) {
		    throw new RuntimeException();
        }

		this._bonus = bonus;
		this._salaryPlusBonus = bonus == null ? salary : salary.add(bonus); // validate whether salary and bonus are not null
	}

	public BigDecimal getSum() {
		return _salaryPlusBonus;
	}

	@Override
	public String toString() {
		return
				_employee + " has earned " + _salaryPlusBonus + "\n";
	}

    public Employee get_employee() {
        return _employee;
    }

    public BigDecimal get_salary() {
        return _salary;
    }

    public BigDecimal get_bonus() {
        return _bonus;
    }
}