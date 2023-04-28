package com.leonardo.management.controller;

import java.net.URI;
import java.util.List;

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

import com.leonardo.management.entities.Funcionario;
import com.leonardo.management.service.FuncionarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping(value = "/funcionarios")
@Api("Funcionarios API")
public class FuncionarioController {
	
	 private final FuncionarioService funcionarioService;

	    public FuncionarioController(FuncionarioService funcionarioService) {
	        this.funcionarioService = funcionarioService;
	    }

	    @GetMapping
	    public ResponseEntity<List<Funcionario>> getFuncionario() {
	        List<Funcionario> funcionarios = funcionarioService.getFuncionario();
	        return ResponseEntity.ok(funcionarios);
	    }

	    @GetMapping("/{id}")
	    @ApiOperation("PEGAR OS DETALHES DOS FUNCIONARIOS POR ID")
	    public ResponseEntity<Funcionario> getFuncionarioById(@PathVariable Long id) {
	        Funcionario funcionario = funcionarioService.getFuncionarioById(id);
	        if (funcionario == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(funcionario);
	    }

	    @PostMapping
	    public ResponseEntity<Void> post(@RequestBody Funcionario funcionario) {
	        try {
	            funcionarioService.postFuncionario(funcionario);
	            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	                    .buildAndExpand(funcionario.getId()).toUri();
	            return ResponseEntity.created(location).build();
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().build();
	        }
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Void> Update(@PathVariable Long id, @RequestBody Funcionario funcionario) {
	        try {
	            funcionario.setId(id);
	            funcionarioService.updateFuncionario(funcionario);
	            return ResponseEntity.noContent().build();
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> Delete(@PathVariable Long id) {
	        try {
	            funcionarioService.deleteFuncionario(id);
	            return ResponseEntity.noContent().build();
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
