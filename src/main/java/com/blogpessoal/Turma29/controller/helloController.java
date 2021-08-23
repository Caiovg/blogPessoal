package com.blogpessoal.Turma29.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/")
public class helloController {

	@GetMapping
	public String helloWord() {
		return "Hello World I";
	}
	
	@GetMapping(value = "/hello")
	public String helloWord2() {
		return "Hello World II";
	}
	
	@GetMapping(value = "/habilidades-mentalidades")
	public String habilidades_mentalidades() {
		return "1 - Atenção aos detalhes <br/>\n"
				+ "2 - Persistência <br/>\n"
				+ "3 - Comunicação  <br/>\n"
				+ "4 - Proatividade <br/>\n"
				+ "\r\n";
	}
	
	@GetMapping(value = "/objetivos")
	public String objetivos() {
		return "Spring";
	}
	
}
