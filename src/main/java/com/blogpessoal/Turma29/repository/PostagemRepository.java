package com.blogpessoal.Turma29.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blogpessoal.Turma29.model.Postagem;
import com.blogpessoal.Turma29.model.Usuario;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Integer>{
	
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
	
}
