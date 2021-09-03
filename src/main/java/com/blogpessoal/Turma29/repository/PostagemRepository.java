package com.blogpessoal.Turma29.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogpessoal.Turma29.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Integer>{
	
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
	
}
