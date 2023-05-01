package com.leonardo.management.repositories.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.leonardo.management.entities.Employee;
import com.leonardo.management.repositories.EmployeeDatabase;

@Repository
public class EmployeeDatabaseImpl implements EmployeeDatabase {

	private Map<Long, Employee> employees;

	public EmployeeDatabaseImpl() {
		this.employees = new HashMap<>();
	}

	@Override
	public Map<Long, Employee> getEmployee() {
		return employees;
	}

	@Override
	public Employee getEmployeeById(Long id) {
		return getEmployee().get(id);
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return employees.put(employee.getId(), employee);

	}

	@Override
	public void updateEmployee(Employee employee) {
		employees.put(employee.getId(), employee);

	}

	@Override
	public void deleteEmployee(Long id) {
		employees.remove(id);
	}

}
