package com.blogpessoal.Turma29.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogpessoal.Turma29.model.Usuario;
import com.blogpessoal.Turma29.repository.UsuarioRepository;

@Service
public class UsuarioServices {

	@Autowired
	private UsuarioRepository repository;
	
	/*Busca todos aos usuarios*/
	public List<Usuario> findAll(){
		return repository.findAll();
	}
	
	/*
	 * Busca pelo ID
	 */
	public ResponseEntity<Usuario> findById(Integer id) {
		return repository.findById((int) id).map(
				resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	/*
	 * Busca pelo email do usuario
	 */
	public ResponseEntity<Optional<Usuario>> findByEmail(String email) {
		return ResponseEntity.ok(repository.findByEmail(email));
	}
	
	/*
	 * Cria um novo usuario
	 */
	public Optional<Object> createUser(Usuario usuario) {
		return repository.findByEmail(usuario.getEmail()).map(usuarioExistente -> {
			return Optional.empty();
		}).orElseGet(() -> {
			return Optional.ofNullable(repository.save(usuario));
		});
	}
	
	/*
	 * Atualizando um usuario
	 */
	public ResponseEntity<Usuario> update(Usuario usuario) {
		return ResponseEntity.ok(repository.save(usuario));
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}

}
