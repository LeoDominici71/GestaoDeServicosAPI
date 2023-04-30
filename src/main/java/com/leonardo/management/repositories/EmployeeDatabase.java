package com.leonardo.management.repositories;

import java.util.Map;

import com.leonardo.management.entities.Employee;

public interface EmployeeDatabase {
	
	Map<Integer, Employee> getEmployee();
	
	Employee saveEmployee(Employee employee);
	
	void updateEmployee(Employee employee);

}
