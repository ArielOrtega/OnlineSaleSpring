package com.yoyo.ventas.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yoyo.ventas.data.UserData;
import com.yoyo.ventas.domain.Client;

@Service
@Transactional
public class CustomUserDetailService {
	@Autowired
	private UserData userData;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Client client = userData.findByEmail(username);
		if(client.getClienteId() == 0)
			throw new UsernameNotFoundException("Email "+ username + "not found");
		else
			return new org.springframework.security.core.userdetails.User(client.getUsername(), client.getPassword(), getAuthorities(client));
	}
	
	private static Collection<? extends GrantedAuthority> getAuthorities(Client client){
		String[] userRoles = client.getRoles()
				.stream()
				.map((role)-> role.getName())
				.toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}
}
