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
import com.blogpessoal.Turma29.services.exception.DataIntegratyViolationException;
import com.blogpessoal.Turma29.services.exception.ObjectNotFoundException;

@Service
public class UsuarioServices {

	@Autowired
	private UsuarioRepository repository;
	
	/*Busca todos aos usuarios*/
	public List<Usuario> findAll(){
		List<Usuario> list = repository.findAll();
		if(list.isEmpty()) {
			throw new DataIntegratyViolationException("Não existe nenhum usuario");
		}
		return list;
	}
	
	/*
	 * Busca pelo ID
	 */
	public ResponseEntity<Usuario> findById(Integer id) {
		return repository.findById((int) id).map(
				resp -> ResponseEntity.ok(resp)).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + " não existe, Tipo: " + Usuario.class.getName()));
	}
	
	/*
	 * Busca pelo email do usuario
	 */
	public ResponseEntity<Optional<Usuario>> findByEmail(String email) {
		Optional<Usuario> user = repository.findByEmail(email);
		if(user.isEmpty()) {
			throw new DataIntegratyViolationException("Não existe nenhuma usuario com esse email");
		}
		return ResponseEntity.ok(user);
	}
	
	/*
	 * Cria um novo usuario
	 */
	public Optional<Object> createUser(Usuario usuario) {
		return Optional.ofNullable(repository.findByEmail(usuario.getEmail()).map(usuarioExistente -> {
			return Optional.empty().orElseThrow(() -> new ObjectNotFoundException("Email ja cadastrado"));
		}).orElseGet(() -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaEncoder = encoder.encode(usuario.getSenha());
			usuario.setSenha(senhaEncoder);
			return Optional.ofNullable(repository.save(usuario));
		}));
	}
	
	/*
	 * Metodo de login 
	 */
	public Optional<?> logar(Optional<UserLogin> user){
		//Verifica o email ou no meu caso o user
		return Optional.ofNullable(repository.findByEmail(user.get().getUsuario()).map(usuarioExistente -> {
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
				return Optional.empty().orElseThrow(() -> new ObjectNotFoundException("Senha Incorreta")); //Senha esteja incorreta
			}
			
		}).orElseGet(() -> {
			return Optional.empty().orElseThrow(() -> new ObjectNotFoundException("Usuario não registrado na base de dados.")); //Email não existente
		}));
	}
	
	
	/*
	 * Atualizando um usuario
	 */
	public Optional<Object> update(Optional<Usuario> usuario) {
		return Optional.ofNullable(repository.findById(usuario.get().getIdUsuario()).map(usuarioExistente -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String senhaCriptografada = encoder.encode(usuario.get().getSenha());
			
			usuarioExistente.setNome(usuario.get().getNome());
			usuarioExistente.setEmail(usuario.get().getEmail());
			usuarioExistente.setUsuario(usuario.get().getUsuario());
			usuarioExistente.setSenha(senhaCriptografada);
			return Optional.ofNullable(repository.save(usuarioExistente));
			
		}).orElseThrow(() ->
				new ObjectNotFoundException("Erro ao atualizar usuario")
		));
	}
	
	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}

}
