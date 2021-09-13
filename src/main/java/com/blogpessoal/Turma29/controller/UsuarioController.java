package com.blogpessoal.Turma29.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.blogpessoal.Turma29.model.UserLogin;
import com.blogpessoal.Turma29.model.Usuario;
import com.blogpessoal.Turma29.services.UsuarioServices;

import io.swagger.annotations.Api;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuarios")
@Api(tags = "Controlador de Usuario", description = "Utilitario de Usuarios")
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
	public ResponseEntity<Usuario> findById(@PathVariable Long id){
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
	public Optional<Object> create(@Valid @RequestBody Usuario usuario){
		Optional<Object> obj = service.createUser(usuario);
		return obj;
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> Autentication(@Valid @RequestBody Optional<UserLogin> user){
		return service.logar(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	/*
	 *Update passando todas as informações no body 
	 */
	@PutMapping
	public ResponseEntity<Object> Put(@Valid @RequestBody Optional<Usuario> usuario){
		Optional<?> obj = service.update(usuario);
		return ResponseEntity.ok(obj);
	}
	
	/*
	 * Deleta uma postagem
	 */
	@DeleteMapping("/{id}")
	public void Delete(@PathVariable Long id) {
		service.delete(id);
	}
}
