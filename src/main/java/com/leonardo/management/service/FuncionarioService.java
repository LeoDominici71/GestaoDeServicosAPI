package com.leonardo.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leonardo.management.entities.Funcionario;

@Service
public interface FuncionarioService {
	
	public List<Funcionario> getFuncionario(); 

    public Funcionario getFuncionarioById(Long id); 

    public void postFuncionario(Funcionario funcionario);
    
    public void updateFuncionario(Funcionario funcionario); 

    public void deleteFuncionario(Long id); 

}
