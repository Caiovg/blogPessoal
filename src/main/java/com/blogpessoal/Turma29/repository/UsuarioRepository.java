package com.blogpessoal.Turma29.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogpessoal.Turma29.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByEmail(String email);
}
