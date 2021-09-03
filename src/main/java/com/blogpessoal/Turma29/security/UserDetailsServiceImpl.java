package com.blogpessoal.Turma29.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogpessoal.Turma29.model.Usuario;
import com.blogpessoal.Turma29.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		// ou findByUsuario
		Optional<Usuario> user = userRepository.findByEmail(userName);
		user.orElseThrow(() -> new UsernameNotFoundException(userName + "email não encontrado!"));
		return user.map(UserDetailsImpl :: new).get();
	}
}
