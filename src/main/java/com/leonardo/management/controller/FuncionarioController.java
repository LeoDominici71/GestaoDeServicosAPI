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
@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
	
	 private final FuncionarioService funcionarioService;

	    public FuncionarioController(FuncionarioService funcionarioService) {
	        this.funcionarioService = funcionarioService;
	    }

	    @GetMapping
	    public ResponseEntity<List<Funcionario>> GetFuncionario() {
	        List<Funcionario> funcionarios = funcionarioService.GetFuncionario();
	        return ResponseEntity.ok(funcionarios);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Funcionario> GetFuncionarioById(@PathVariable Long id) {
	        Funcionario funcionario = funcionarioService.GetFuncionarioById(id);
	        if (funcionario == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(funcionario);
	    }

	    @PostMapping
	    public ResponseEntity<Void> Post(@RequestBody Funcionario funcionario) {
	        try {
	            funcionarioService.Post(funcionario);
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
	            funcionarioService.Update(funcionario);
	            return ResponseEntity.noContent().build();
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> Delete(@PathVariable Long id) {
	        try {
	            funcionarioService.Delete(id);
	            return ResponseEntity.noContent().build();
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
