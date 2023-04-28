package com.leonardo.management;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.leonardo.management.entities.Funcionario;
import com.leonardo.management.service.impl.FuncionarioServiceImpl;

@SpringBootApplication
public class ManagementApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ManagementApplication.class, args);
	}

	@Autowired
	FuncionarioServiceImpl service;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("========================== Salvando =============================");
		
		service.Post(new Funcionario(1L, "Isadora", "Porteira", 1200.00, "123456789", "Rua Itarare"));
		service.Post(new Funcionario(2L, "Isadora", "Porteira", 1200.00, "123456789", "Rua Itarare"));
		service.Post(new Funcionario(3L, "Isadora", "Porteira", 1200.00, "123456789", "Rua Itarare"));
		service.Post(new Funcionario(4L, "Isadora", "Porteira", 1200.00, "123456789", "Rua Itarare"));
		service.Post(new Funcionario(5L, "Isadora", "Porteira", 1200.00, "123456789", "Rua Itarare"));
		System.out.println();
		System.out.println("========================== Find All =============================");
		
		List<Funcionario> todosFuncionarios = service.GetFuncionario();
		
		todosFuncionarios.forEach(System.out::println);
		
		System.out.println();
		System.out.println("========================== Find By Id =============================");
		
		System.out.println(service.GetFuncionarioById(1L));
		System.out.println();
		System.out.println("========================== Update =============================");
		Funcionario funcionarioAtualizado = service.Update(new Funcionario(1L, "Isadora", "Porteira", 1200.00, "123456789", "Rua Itarare"));
		System.out.println();
		System.out.println();
		System.out.println("========================== Delete =============================");
		service.Delete(1L);
		
		
		
	}

}
