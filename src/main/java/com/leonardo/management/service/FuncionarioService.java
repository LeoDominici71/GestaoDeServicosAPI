package com.leonardo.management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leonardo.management.entities.Funcionario;

@Service
public interface FuncionarioService {
	
	public List<Funcionario> GetFuncionario(); 

    public Funcionario GetFuncionarioById(Long id); 

    public void Post(Funcionario funcionario);
    
    public void Update(Funcionario funcionario); 

    public void Delete(Long id); 

}
