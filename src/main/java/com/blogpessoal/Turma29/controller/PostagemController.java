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
import com.blogpessoal.Turma29.repository.PostagemRepository;

@CrossOrigin("*")
@RestController
@RequestMapping("/postagens")
public class PostagemController {

	
	@Autowired
	private PostagemRepository repository;
	
	/*
	 * Busca pelo ID
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable Integer id){
	return repository.findById((int) id).map(
			resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());

	}
	
	/*
	 * Busca pelo Titulo
	 */
	@GetMapping(value = "/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> findByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	/*
	 * Busca Todos
	 */
	@GetMapping
	public ResponseEntity<List<Postagem>> findAll(){
		return ResponseEntity.ok().body(repository.findAll());
	}
	
	//Update passsando o id pela URI
	/*@PutMapping(value = "/{id}")
	public ResponseEntity<Postagem> update(@PathVariable Integer id, @Valid @RequestBody Postagem objDTO){
		Postagem oldObj = GetById(id);
		oldObj.setTitulo(objDTO.getTitulo());
		oldObj.setTxt(objDTO.getTxt());
		oldObj.setData(objDTO.getData());
		return repository.save(oldObj);
	}*/
		
	//Update passando todas as informações no body
	@PutMapping
	public ResponseEntity<Postagem> Put(@RequestBody Postagem produto){
		return ResponseEntity.ok(repository.save(produto));
	}

	//Criação das postagens
	@PostMapping
	public ResponseEntity<Postagem> Post(@RequestBody Postagem produto){
		return ResponseEntity.ok(repository.save(produto));
	}
	
	/*Deleta uma postagem*/
	@DeleteMapping("/{id}")
		public void Delete(@PathVariable Integer id) {
		repository.deleteById(id);
	}
}

