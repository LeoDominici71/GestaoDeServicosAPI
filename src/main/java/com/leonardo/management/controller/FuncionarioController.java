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
import com.leonardo.management.service.FuncionarioServicos;
@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {
	
	 private final FuncionarioServicos funcionarioService;

	    public FuncionarioController(FuncionarioServicos funcionarioService) {
	        this.funcionarioService = funcionarioService;
	    }

	    @GetMapping
	    public ResponseEntity<List<Funcionario>> obterTodosFuncionarios() {
	        List<Funcionario> funcionarios = funcionarioService.obterTodosFuncionarios();
	        return ResponseEntity.ok(funcionarios);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Funcionario> obterFuncionarioPorId(@PathVariable Long id) {
	        Funcionario funcionario = funcionarioService.obterFuncionarioPorId(id);
	        if (funcionario == null) {
	            return ResponseEntity.notFound().build();
	        }
	        return ResponseEntity.ok(funcionario);
	    }

	    @PostMapping
	    public ResponseEntity<Void> adicionarFuncionario(@RequestBody Funcionario funcionario) {
	        try {
	            funcionarioService.adicionarFuncionario(funcionario);
	            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	                    .buildAndExpand(funcionario.getId()).toUri();
	            return ResponseEntity.created(location).build();
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().build();
	        }
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Void> atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
	        try {
	            funcionario.setId(id);
	            funcionarioService.atualizarFuncionario(funcionario);
	            return ResponseEntity.noContent().build();
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.badRequest().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> removerFuncionario(@PathVariable Long id) {
	        try {
	            funcionarioService.removerFuncionario(id);
	            return ResponseEntity.noContent().build();
	        } catch (IllegalArgumentException e) {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
