package com.blogpessoal.Turma29.services;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.blogpessoal.Turma29.model.Postagem;
import com.blogpessoal.Turma29.repository.PostagemRepository;

@Service
public class PostagemServices {

	@Autowired
	private PostagemRepository repository;

	/*Busca todos as postagens*/
	public List<Postagem> findAll(){
		return repository.findAll();
	}
	
	/*
	 * Busca pelo ID
	 */
	public ResponseEntity<Postagem> findById(Integer id) {
		return repository.findById((int) id).map(
				resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	/*
	 * Busca pelo Titulo da postagem
	 */
	public ResponseEntity<List<Postagem>> findByTitulo(String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	/*
	 * Cria um nova postagem
	 */
	public ResponseEntity<Postagem> create(Postagem postagem) {
		return ResponseEntity.ok(repository.save(postagem));
	}
	
	/*
	 * Atualizando uma postagem
	 */
	public ResponseEntity<Postagem> update(Postagem postagem) {
		return ResponseEntity.ok(repository.save(postagem));
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
