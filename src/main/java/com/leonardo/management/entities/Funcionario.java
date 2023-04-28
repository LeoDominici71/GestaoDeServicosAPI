package com.leonardo.management.entities;

public class Funcionario {

	private Long id;
	private String nome;
	private String designacao;
	private Double salario;
	private String telefone;
	private String endereco;

	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	public Funcionario(Long id, String nome, String designacao, Double salario, String telefone, String endereco) {

		this.id = id;
		this.nome = nome;
		this.designacao = designacao;
		this.salario = salario;
		this.telefone = telefone;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public String toString() {
		return "Funcionario [id = " + id + ", nome = " + nome + ", designacao = " + designacao + ", salario = " + salario
				+ ", telefone = " + telefone + ", endereco = " + endereco + "]";
	}

}
