package com.leonardo.management.controller;

import java.net.URI;
import java.util.List;

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
	@ApiOperation("PEGAR FUNCIONARIOS")
	public ResponseEntity<List<Employee>> getEmployee() {
		List<Employee> funcionarios = employeeService.getEmployee();
		return ResponseEntity.ok(funcionarios);
	}

	@GetMapping("/{id}")
	@ApiOperation("PEGAR OS DETALHES DOS FUNCIONARIOS POR ID")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
		Employee funcionario = employeeService.getEmployeeById(id);
		if (funcionario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(funcionario);
	}

	@PostMapping
	@ApiOperation("ADICIONAR NOVO FUNCIONARIO")
	public ResponseEntity<Void> post(@Valid @RequestBody Employee funcionario) {
		employeeService.postEmployee(funcionario);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(funcionario.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping("/{id}")
	@ApiOperation("ATUALIZAR FUNCIONARIO")
	public ResponseEntity<Void> Update(@PathVariable Integer id, @Valid @RequestBody Employee funcionario) {

		funcionario.setId(id);
		employeeService.updateEmployee(funcionario);
		return ResponseEntity.noContent().build();

	}

	@DeleteMapping("/{id}")
	@ApiOperation("DELETAR FUNCIONARIO POR ID")
	public ResponseEntity<Void> Delete(@PathVariable Integer id) {
		try {
			employeeService.deleteEmployee(id);
			return ResponseEntity.noContent().build();
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
