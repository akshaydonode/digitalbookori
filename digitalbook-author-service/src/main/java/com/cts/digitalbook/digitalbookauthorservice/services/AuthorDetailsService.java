package com.cts.digitalbook.digitalbookauthorservice.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.digitalbook.digitalbookauthorservice.entities.AuthorEntity;
import com.cts.digitalbook.digitalbookauthorservice.repositories.AuthorRepository;

@Service
public class AuthorDetailsService implements UserDetailsService {

	@Autowired
	private AuthorRepository dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<SimpleGrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority("AUTHORENTITY"));
		Optional<AuthorEntity> author = dao.findByEmailId(username);
		return new User(author.get().getAuthorEmail(), author.get().getPassword(), authorities);
	}

}