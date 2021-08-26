package com.blogpessoal.Turma29.controller;

import java.util.List;
import java.util.Optional;

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

import com.blogpessoal.Turma29.model.Usuario;
import com.blogpessoal.Turma29.services.UsuarioServices;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UsuarioController {

	@Autowired
	private UsuarioServices service;
	
	/*
	 * Busca Todos
	 * implementar o DTO depois pois estou com preguiça
	 */
	@GetMapping
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> obj = service.findAll();
		return ResponseEntity.ok().body(obj);
	}
	
	/*
	 * Busca pelo ID
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Integer id){
		ResponseEntity<Usuario> obj = service.findById(id);
		return obj;
	}
	
	/*
	 * Busca pelo email
	 */
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<Optional<Usuario>> findByEmail(@PathVariable String email) {
		ResponseEntity<Optional<Usuario>> obj = service.findByEmail(email);
		return obj;
	}
	
	//Criação dos usuarios
	@PostMapping
	public Optional<Object> create(@RequestBody Usuario usuario){
		Optional<Object> obj = service.createUser(usuario);
		return obj;
	}
	
	/*
	 *Update passando todas as informações no body 
	 */
	@PutMapping
	public ResponseEntity<Usuario> Put(@RequestBody Usuario usuario){
		ResponseEntity<Usuario> obj = service.update(usuario);
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
