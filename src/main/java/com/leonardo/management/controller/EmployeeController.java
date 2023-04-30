package com.leonardo.management.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leonardo.management.entities.Employee;
import com.leonardo.management.entities.dto.EmployeeDTO;
import com.leonardo.management.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/employees")
@Api("Employees API")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	@ApiOperation("GET EMPLOYEES")
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {
		List<EmployeeDTO> employeeDTOs = employeeService.getEmployee();
		return ResponseEntity.ok(employeeDTOs);
	}

	@GetMapping("/{id}")
	@ApiOperation("GET EMPLOYEES BY ID")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id) {
		EmployeeDTO dto = employeeService.getEmployeeById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	@ApiOperation("CREATE NEW EMPLOYEE")
	public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO createdEmployeeDTO = employeeService.postEmployee(employeeDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdEmployeeDTO.getId()).toUri();
		return ResponseEntity.created(location).body(createdEmployeeDTO);
	}

	@PutMapping("/{id}")
	@ApiOperation("UPDATE EMPLOYEE")
	public ResponseEntity<EmployeeDTO> Update(@PathVariable Integer id, @Valid @RequestBody EmployeeDTO employee) {
		employee = employeeService.updateEmployee(id, employee);
		return ResponseEntity.ok().body(employee);

	}

	@DeleteMapping("/{id}")
	@ApiOperation("DELETE EMPLOYEE BY ID")
	public ResponseEntity<Void> Delete(@PathVariable Integer id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
}
