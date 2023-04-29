package com.leonardo.management.repositories.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.leonardo.management.entities.Employee;
import com.leonardo.management.repositories.EmployeeDatabase;

@Repository
public class EmployeeDatabaseImpl implements EmployeeDatabase {

	private Map<Integer, Employee> employees;

	public EmployeeDatabaseImpl() {
		this.employees = new HashMap<>();
	}

	@Override
	public Map<Integer, Employee>  getEmployee() {
		return employees;
	}


}
