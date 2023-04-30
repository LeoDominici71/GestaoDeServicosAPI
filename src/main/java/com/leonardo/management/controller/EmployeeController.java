package com.leonardo.management.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leonardo.management.entities.dto.EmployeeDTO;
import com.leonardo.management.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {
		List<EmployeeDTO> employeeDTOs = employeeService.getEmployee();
		return ResponseEntity.ok(employeeDTOs);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
		EmployeeDTO dto = employeeService.getEmployeeById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
		EmployeeDTO createdEmployeeDTO = employeeService.postEmployee(employeeDTO);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdEmployeeDTO.getId()).toUri();
		return ResponseEntity.created(location).body(createdEmployeeDTO);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeDTO> Update(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employee) {
		employee = employeeService.updateEmployee(id, employee);
		return ResponseEntity.ok().body(employee);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> Delete(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
}
