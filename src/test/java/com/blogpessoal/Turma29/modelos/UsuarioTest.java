package com.blogpessoal.Turma29.modelos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.blogpessoal.Turma29.model.Usuario;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UsuarioTest {

	public Usuario usuario;
	
	@Autowired
	private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	Validator validator = factory.getValidator();
	
	
	@BeforeEach
	public void start() {
		usuario = new Usuario("Caio", "Caio@email.com","Caiovg", "1234567");
	}

	@Test
	void validaUsuarioCorretoRetornaTrue() {
		Set<ConstraintViolation<Usuario>> objViolacao = validator.validate(usuario);
		assertTrue(objViolacao.isEmpty());
	}
	
	@Test
	void validaUsuarioIncorretoRetornaFalse() {
		Usuario usuarioComFalha = new Usuario("","bia@gmail.com","Biarr","1234569");
		Set<ConstraintViolation<Usuario>> objViolacao = validator.validate(usuarioComFalha);
		assertFalse(objViolacao.isEmpty());
	}
}