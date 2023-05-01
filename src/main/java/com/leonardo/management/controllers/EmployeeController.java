package com.leonardo.management.controllers;

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

import com.leonardo.management.dto.EmployeeDTO;
import com.leonardo.management.services.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/employees")
@Api(value = "API REST Employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@GetMapping
	@ApiOperation("GET EMPLOYEES")
	public ResponseEntity<List<EmployeeDTO>> getEmployees() {

		List<EmployeeDTO> employeeDTOs = employeeService.getEmployee();

		return ResponseEntity.ok().body(employeeDTOs);
	}

	@GetMapping("/{id}")
	@ApiOperation("GET EMPLOYEE DETAILS BY ID")
	@ApiResponses({ @ApiResponse(code = 200, message = "EMPLOYEE FOUND"),
			@ApiResponse(code = 404, message = "EMPLOYEE NOT FOUND") })
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable @ApiParam("Employee ID") Long id) {

		EmployeeDTO dto = employeeService.getEmployeeById(id);

		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation("CREATE EMPLOYEE")
	@ApiResponses({ @ApiResponse(code = 201, message = "EMPLOYEE CREATED"),
			@ApiResponse(code = 422, message = "VALIDATION ERROR") })
	public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {

		EmployeeDTO createdEmployeeDTO = employeeService.postEmployee(employeeDTO);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdEmployeeDTO.getId()).toUri();

		return ResponseEntity.created(location).body(createdEmployeeDTO);
	}

	@PutMapping("/{id}")
	@ApiOperation("UPDATE EMPLOYEE BY ID")
	@ApiResponses({ @ApiResponse(code = 201, message = "EMPLOYEE UPDATED"),
			@ApiResponse(code = 422, message = "VALIDATION ERROR") })
	public ResponseEntity<EmployeeDTO> Update(@PathVariable @ApiParam("Employee ID") Long id,
			@Valid @RequestBody EmployeeDTO employee) {

		employee = employeeService.updateEmployee(id, employee);

		return ResponseEntity.ok().body(employee);

	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation("DELETE EMPLOYEE BY ID")
	@ApiResponses({ @ApiResponse(code = 204, message = "EMPLOYEE DELETED"),
			@ApiResponse(code = 404, message = "EMPLOYEE NOT FOUND") })
	public ResponseEntity<Void> Delete(@PathVariable @ApiParam("Employee ID") Long id) {

		employeeService.deleteEmployee(id);

		return ResponseEntity.noContent().build();
	}
}
