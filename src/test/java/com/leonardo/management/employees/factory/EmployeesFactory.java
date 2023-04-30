package com.leonardo.management.employees.factory;

import com.leonardo.management.entities.dto.EmployeeDTO;

public class EmployeesFactory {
	
	public EmployeeDTO createEmployess() {
        EmployeeDTO employee1 = new EmployeeDTO(1, "Igor", "Developer", 1200.00,
    			"123456789", "Atkinson road");
		return employee1;
     
    }
	
	
	public EmployeeDTO createEmployess2() {
	EmployeeDTO employee2 = new EmployeeDTO(2, "Laura", "Developer", 1200.00,
			"123456789", "Atkinson av");
	return employee2;
   
}

}
