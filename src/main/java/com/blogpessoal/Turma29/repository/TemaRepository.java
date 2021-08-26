package com.blogpessoal.Turma29.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogpessoal.Turma29.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Integer>{

	Optional<Tema> findByTema(String email);
}
