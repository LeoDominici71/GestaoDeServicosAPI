package com.leonardo.management.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leonardo.management.dto.EmployeeDTO;

@Service
public interface EmployeeService{
	
	public List<EmployeeDTO> getEmployee(); 

    public EmployeeDTO getEmployeeById(Long id); 

    public EmployeeDTO postEmployee(EmployeeDTO employee);
    
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employee); 

    public void deleteEmployee(Long id); 

}
