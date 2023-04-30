package com.leonardo.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leonardo.management.entities.Employee;
import com.leonardo.management.entities.dto.EmployeeDTO;

@Service
public interface EmployeeService{
	
	public List<EmployeeDTO> getEmployee(); 

    public EmployeeDTO getEmployeeById(Integer id); 

    public EmployeeDTO postEmployee(EmployeeDTO employee);
    
    public EmployeeDTO updateEmployee(Integer id, EmployeeDTO employee); 

    public void deleteEmployee(Integer id); 

}
