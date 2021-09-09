package com.blogpessoal.Turma29.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogpessoal.Turma29.model.Tema;
import com.blogpessoal.Turma29.repository.TemaRepository;
import com.blogpessoal.Turma29.services.exception.DataIntegratyViolationException;
import com.blogpessoal.Turma29.services.exception.ObjectNotFoundException;

@Service
public class TemaServices {

	@Autowired
	private TemaRepository repository;
	
	/*Busca todos as postagens*/
	public List<Tema> findAll(){
		List<Tema> list = repository.findAll();
		if(list.isEmpty()) {
			throw new DataIntegratyViolationException("Não existe nenhum tema");
		}
		return list;
		//return repository.findAll();
	}
	
	/*
	 * Busca pelo ID
	 */
	public ResponseEntity<Tema> findById(Long id) {
		return repository.findById((long) id).map(
				resp -> ResponseEntity.ok(resp)).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " não existe, Tipo: " + Tema.class.getName()));
	}
	
	/*
	 * Busca pelo tema
	 */
	public ResponseEntity<Optional<Tema>> findByTema(String temas) {
		Optional<Tema> tema = repository.findByTema(temas);
		if(tema.isEmpty()) {
			throw new DataIntegratyViolationException("Não existe nenhum tema com esse nome");
		}
		return ResponseEntity.ok(tema);
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
	
	public void delete(Long id) {
		findById(id);
		repository.deleteById(id);
	}
}
