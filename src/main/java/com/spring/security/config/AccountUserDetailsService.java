package com.spring.security.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.entity.AccountUser;
import com.spring.security.model.AccountUserDetails;
import com.spring.security.repository.UserRepository;

@Service
public class AccountUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<AccountUser> users = userRepository.findByEmail(username);
		if (users.size() == 0) {
			throw new UsernameNotFoundException("User details not found for the user : " + username);
		}

		return new AccountUserDetails(users.get(0));
	}

}
