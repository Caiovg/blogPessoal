package com.blogpessoal.Turma29.model;

public class UserLogin {

	//DTO
	//Poderia se Usuario DTO dentro de um pacote DTO
	private Long id;
	
	private String nome;
	
	//inves de usuario pode ser email
	private String usuario;
	
	private String senha;
	
	private String token;
	

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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
