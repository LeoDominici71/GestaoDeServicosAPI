package com.leonardo.management.repositories;

import java.util.Map;

import com.leonardo.management.entities.Employee;

public interface EmployeeDatabase {

	Map<Long, Employee> getEmployee();
	
	public Employee getEmployeeById(Long id);

	Employee saveEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployee(Long id);

}
