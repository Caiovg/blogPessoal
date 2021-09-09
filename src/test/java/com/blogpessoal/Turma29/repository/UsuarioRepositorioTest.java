package com.blogpessoal.Turma29.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.blogpessoal.Turma29.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UsuarioRepositorioTest {

	private @Autowired UsuarioRepository repository;
	
	@BeforeEach
	void start() {
		Usuario obj1 = new Usuario("Charles", "Charles@email.com", "charlito", "1234567");
		if(!repository.findByEmail(obj1.getEmail()).isPresent())
				repository.save(obj1);
		
		Usuario obj2 = new Usuario("Lucas", "lucas@email.com", "lucão", "12345678");
		if(!repository.findByEmail(obj2.getEmail()).isPresent())
				repository.save(obj2);
	}
	
	@Test
	void pesquisaPorEmailExistenteRetornaTrue() {
		Usuario obj1 = repository.findByEmail("Charles@email.com").get();
		
		assertTrue(obj1.getEmail().equals("Charles@email.com"));
	}
	
}
