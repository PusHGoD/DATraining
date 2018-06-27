package com.training.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.model.jpa.Role;
import com.training.model.jpa.User;
import com.training.repository.UserRepository;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findOneByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("No user with username '" + username + "' found!");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role r : user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
		}
		UserDetails securedUser = (UserDetails) new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), authorities);
		return securedUser;
	}

}
