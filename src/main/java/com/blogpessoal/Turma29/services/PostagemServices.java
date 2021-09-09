package com.blogpessoal.Turma29.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogpessoal.Turma29.model.Postagem;
import com.blogpessoal.Turma29.repository.PostagemRepository;
import com.blogpessoal.Turma29.services.exception.DataIntegratyViolationException;
import com.blogpessoal.Turma29.services.exception.ObjectNotFoundException;

@Service
public class PostagemServices {

	@Autowired
	private PostagemRepository repository;

	/*Busca todos as postagens*/
	public List<Postagem> findAll(){
		List<Postagem> list = repository.findAll();
		if(list.isEmpty()) {
			throw new DataIntegratyViolationException("Não existe nenhuma postagem");
		}
		return list;
	}
	
	/*
	 * Busca pelo ID
	 */
	public ResponseEntity<Postagem> findById(Integer id) {
		return repository.findById((int) id).map(
				resp -> ResponseEntity.ok(resp)).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " não existe, Tipo: " + Postagem.class.getName()));
	}
	
	/*
	 * Busca pelo Titulo da postagem
	 */
	public ResponseEntity<List<Postagem>> findByTitulo(String titulo) {
		List<Postagem> postagem = repository.findAllByTituloContainingIgnoreCase(titulo);
		if(postagem.isEmpty()) {
			throw new DataIntegratyViolationException("Não existe nenhuma postagem com esse titulo");
		}
		return ResponseEntity.ok(postagem);
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
		findById(id);
		repository.deleteById(id);
	}
}
