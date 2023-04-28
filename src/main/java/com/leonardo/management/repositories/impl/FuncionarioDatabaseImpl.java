package com.leonardo.management.repositories.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;

import com.leonardo.management.entities.Funcionario;
import com.leonardo.management.repositories.FuncionariosDatabase;

public class FuncionarioDatabaseImpl implements FuncionariosDatabase{
	
	@Bean
    public Map<Long, Funcionario> getFuncionarios() {
        return new HashMap<>();
    }
	
	

}
