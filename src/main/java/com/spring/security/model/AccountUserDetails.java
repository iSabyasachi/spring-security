package com.spring.security.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.security.entity.AccountUser;

public class AccountUserDetails implements UserDetails{
	private static final long serialVersionUID = -6790946490872875352L;
	
	private final AccountUser accountUser;
	
	public AccountUserDetails(AccountUser accountUser) {
		this.accountUser = accountUser;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(accountUser.getRole()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return accountUser.getPwd();
	}

	@Override
	public String getUsername() {
		return accountUser.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}

	@Override
	public boolean isEnabled() {		
		return true;
	}

}
