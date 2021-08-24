package com.blogpessoal.Turma29.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.Turma29.model.Postagem;
import com.blogpessoal.Turma29.repository.PostagemRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/postagens")
public class PostagemController {

	
	@Autowired
	private PostagemRepository repository;
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable long id){
	return repository.findById((int) id).map(
			resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());

	}
	
	@GetMapping(value = "/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> findByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@GetMapping
	public ResponseEntity<List<Postagem>> findAll(){
		return ResponseEntity.ok().body(repository.findAll());
	}
	
}

