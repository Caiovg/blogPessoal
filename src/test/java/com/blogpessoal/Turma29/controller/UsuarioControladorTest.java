package com.blogpessoal.Turma29.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import com.blogpessoal.Turma29.model.Usuario;

class UsuarioControladorTest {

	private @Autowired TestRestTemplate testRestTemplate;
	
	private Usuario usuarioParaCriar;
	private Usuario usuarioParaAlterar;
	
	
	
	@BeforeAll
	void test() {
		usuarioParaCriar = new Usuario("Charles", "Charles@email.com", "charlito", "1234567");
		usuarioParaAlterar = new Usuario("Lucas", "lucas@email.com", "lucão", "12345678");
	}

}
