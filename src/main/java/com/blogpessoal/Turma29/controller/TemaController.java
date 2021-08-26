package com.blogpessoal.Turma29.controller;

import java.util.List;
import java.util.Optional;

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

import com.blogpessoal.Turma29.model.Tema;
import com.blogpessoal.Turma29.services.TemaServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/tema")
public class TemaController {

	@Autowired
	private TemaServices service;
	
	/*
	 * Busca Todos
	 * implementar o DTO depois pois estou com preguiça
	 */
	@GetMapping
	public ResponseEntity<List<Tema>> findAll(){
		List<Tema> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	/*
	 * Busca pelo ID
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Tema> findById(@PathVariable Integer id){
		ResponseEntity<Tema> obj = service.findById(id);
		return obj;
	}
	
	/*
	 * Busca pelo tema
	 */
	@GetMapping(value = "/tema/{tema}")
	public ResponseEntity<Optional<Tema>> findByTema(@PathVariable String tema) {
		ResponseEntity<Optional<Tema>> obj = service.findByTema(tema);
		return obj;
	}
	
	//Criação dos Temas
	@PostMapping
	public ResponseEntity<Tema> create(@RequestBody Tema temas){
		ResponseEntity<Tema> obj = service.createTema(temas);
		return obj;
	}
	
	/*
	 *Update passando todas as informações no body 
	 */
	@PutMapping
	public ResponseEntity<Tema> Put(@RequestBody Tema temas){
		ResponseEntity<Tema> obj = service.update(temas);
		return obj;
	}
	
	/*
	 * Deleta um Tema
	 */
	@DeleteMapping("/{id}")
	public void Delete(@PathVariable Integer id) {
		service.delete(id);
	}
}
