package com.blogpessoal.Turma29.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogpessoal.Turma29.model.Tema;
import com.blogpessoal.Turma29.repository.TemaRepository;

@Service
public class TemaServices {

	@Autowired
	private TemaRepository repository;
	
	/*Busca todos as postagens*/
	public List<Tema> findAll(){
		return repository.findAll();
	}
	
	/*
	 * Busca pelo ID
	 */
	public ResponseEntity<Tema> findById(Integer id) {
		return repository.findById((int) id).map(
				resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	/*
	 * Busca pelo tema
	 */
	public ResponseEntity<Optional<Tema>> findByTema(String email) {
		return ResponseEntity.ok(repository.findByTema(email));
	}
	
	/*
	 * Cria um novo tema
	 */
	public ResponseEntity<Tema> createTema(Tema temas) {
		return ResponseEntity.ok(repository.save(temas));
	}
	
	/*
	 * Atualizando um tema
	 */
	public ResponseEntity<Tema> update(Tema temas) {
		return ResponseEntity.ok(repository.save(temas));
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
