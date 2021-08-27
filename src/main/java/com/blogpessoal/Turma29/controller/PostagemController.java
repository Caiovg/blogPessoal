package com.blogpessoal.Turma29.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogpessoal.Turma29.model.Postagem;
import com.blogpessoal.Turma29.services.PostagemServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/postagens")
public class PostagemController {

	/*test*/
	@Autowired
	private PostagemServices service;
	
	/*
	 * Busca Todos
	 * implementar o DTO depois pois estou com preguiça
	 */
	@GetMapping
	public ResponseEntity<List<Postagem>> findAll(){
		List<Postagem> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	/*
	 * Busca pelo ID
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Postagem> findById(@PathVariable Integer id){
		ResponseEntity<Postagem> obj = service.findById(id);
		return obj;
	}
	
	/*
	 * Busca pelo Titulo
	 */
	@GetMapping(value = "/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> findByTitulo(@PathVariable String titulo) {
		ResponseEntity<List<Postagem>> obj = service.findByTitulo(titulo);
		return obj;
	}
	
	//Criação das postagens
	@PostMapping
	public ResponseEntity<Postagem> create(@RequestBody Postagem postagem){
		ResponseEntity<Postagem> obj = service.create(postagem);
		return obj;
	}
	
	/*
	 *Update passando todas as informações no body 
	 */
	@PutMapping
	public ResponseEntity<Postagem> Put(@RequestBody Postagem postagem){
		ResponseEntity<Postagem> obj = service.update(postagem);
		return obj;
	}
	
	/*
	 * Deleta uma postagem
	 */
	@DeleteMapping("/{id}")
	public void Delete(@PathVariable Integer id) {
		service.delete(id);
	}

}

