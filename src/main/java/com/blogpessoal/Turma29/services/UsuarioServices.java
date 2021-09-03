package com.blogpessoal.Turma29.services;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogpessoal.Turma29.model.UserLogin;
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
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			return Optional.ofNullable(repository.save(usuario));
		});
	}
	
	/*
	 * Metodo de login 
	 */
	public Optional<?> logar(Optional<UserLogin> user){
		//Verifica o email ou no meu caso o user
		return repository.findByEmail(user.get().getUsuario()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
			//verifica as senhas 
			if(encoder.matches(user.get().getSenha(), usuarioExistente.getSenha())) {
					
					String auth = user.get().getUsuario() + ":" + user.get().getSenha();
					byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
					String authHeader = "Basic " + new String(encodedAuth);
					
					user.get().setToken(authHeader);
					user.get().setId(usuarioExistente.getIdUsuario());
					user.get().setNome(usuarioExistente.getNome());
					user.get().setSenha(usuarioExistente.getEmail());
					
					return Optional.ofNullable(user);
			}else {
				return Optional.empty(); //Senha esteja incorreta
			}
			
		}).orElseGet(() -> {
			return Optional.empty(); //Email não existente
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
