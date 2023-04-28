package com.leonardo.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leonardo.management.entities.Funcionario;

@Service
public interface FuncionarioService {
	
	public List<Funcionario> obterTodosFuncionarios(); 

    public Funcionario obterFuncionarioPorId(Long id); 

    public void adicionarFuncionario(Funcionario funcionario);
    
    public void atualizarFuncionario(Funcionario funcionario); 

    public void removerFuncionario(Long id); 

}
