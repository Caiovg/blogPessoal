package com.blogpessoal.Turma29.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogpessoal.Turma29.model.Postagem;
import com.blogpessoal.Turma29.model.Tema;
import com.blogpessoal.Turma29.model.Usuario;
import com.blogpessoal.Turma29.repository.PostagemRepository;
import com.blogpessoal.Turma29.repository.TemaRepository;
import com.blogpessoal.Turma29.repository.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private TemaRepository tRepository;
	
	@Autowired
	private PostagemRepository pRepository;
	
	@Autowired
	private UsuarioServices service;
	
	public void instanceDB() {
		
		//Criação de Usuarios
		Usuario obj = new Usuario("Caio", "caio@gmail.com", "caiovg", "123456789");
		Usuario obj1 = new Usuario("Felipe", "felipe@gmail.com", "felipão", "123456789");
		Usuario obj2 = new Usuario("Sophia", "sophia@gmail.com", "SophiaDaTurma", "123456789");
		Usuario obj3 = new Usuario("Peter", "pete@gmail.com", "Homem Aranha", "123456789");
		Usuario obj4 = new Usuario("Lucas", "lucas@gmail.com", "Lucão", "123456789");
		
		//Criação de Temas
		Tema tema = new Tema("Java");
		Tema tema1 = new Tema("Python");
		Tema tema2 = new Tema("C#");
		Tema tema3 = new Tema("JavaScript");
		Tema tema4 = new Tema("PHP");
		
		//Criação das postagens
		Postagem post = new Postagem("As dificuldades do Java", "codigos em java sempre são chatos", tema, obj);
		Postagem post1 = new Postagem("Python e uma beleza", "codigos em python são para iniciantes em programação", tema1, obj1);
		Postagem post2 = new Postagem("C# e suas maravilhas", "codigos em c# são maravilhosos", tema2, obj2);
		Postagem post3 = new Postagem("A linguagem dos Deus e o JavaScript", "Quem programa em JavaScript e incrivel e espetacular", tema3, obj3);
		Postagem post4 = new Postagem("Codigos em PHP", "Os codigos em PHP são incrivel", tema4, obj4);
		
		service.createUser(obj);
		service.createUser(obj1);
		service.createUser(obj2);
		service.createUser(obj3);
		service.createUser(obj4);
		
		tRepository.saveAll(Arrays.asList(tema, tema1, tema2, tema3, tema4));
		
		pRepository.saveAll(Arrays.asList(post, post1, post2, post3, post4));
	}
}
