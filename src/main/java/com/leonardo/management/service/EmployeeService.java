package com.leonardo.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leonardo.management.entities.Employee;

@Service
public interface EmployeeService{
	
	public List<Employee> getEmployee(); 

    public Employee getEmployeeById(Integer id); 

    public void postEmployee(Employee employee);
    
    public void updateEmployee(Employee employee); 

    public void deleteEmployee(Integer id); 

}
