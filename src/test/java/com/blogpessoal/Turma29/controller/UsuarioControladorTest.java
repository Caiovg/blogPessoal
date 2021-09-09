package com.blogpessoal.Turma29.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.blogpessoal.Turma29.model.Usuario;
import com.blogpessoal.Turma29.repository.UsuarioRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioControladorTest {

	private @Autowired TestRestTemplate testRestTemplate;
	
	private Usuario usuarioParaCriar;
	private Usuario usuarioParaAlterar;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void test() {
		usuarioParaCriar = new Usuario("ba", "barbara@email.com", "babito", "1234567");
		usuarioParaAlterar = new Usuario("nicolas", "nicole@email.com", "nicole", "12345678");
	}
	
	@Test
	@Order(1)
	@DisplayName("✔ Cadastrar Usuário!")
	public void deveRealizarPostUsuario() {
		HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioParaCriar);
		ResponseEntity<Usuario> resposta = testRestTemplate
		.exchange("/usuarios", HttpMethod.POST, request, Usuario.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

	@Test
	@Order(2)
	@DisplayName("👍 Listar todos os Usuários!")
	public void deveMostrarTodosUsuarios() {
		ResponseEntity<String> resposta = testRestTemplate
				.withBasicAuth("barbara@email.com", "1234567")
				.exchange("/usuarios", HttpMethod.GET, null, String.class);
	
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	@Order(3)
	@DisplayName("😳 Alterar Usuário!")
	public void deveRealizarPutUsuario() {
	HttpEntity<Usuario> request = new HttpEntity<Usuario>(usuarioParaAlterar);
	ResponseEntity<Usuario> resposta = testRestTemplate
	.withBasicAuth("barbara@email.com", "1234567")
	.exchange("/usuarios", HttpMethod.PUT, request, Usuario.class);
	assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
}
