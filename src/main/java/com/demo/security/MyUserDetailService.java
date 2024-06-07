package com.demo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.Exception.UserNotFoundException;
import com.demo.model.UserCustom;
import com.demo.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserCustom> user = repo.findByUserName(username);
		if (user.isEmpty()) {
			throw new UserNotFoundException("user not available in database!! ");
		}else {
			UserCustom userCustom = user.get();
			return User.withUsername(userCustom.getUserName()).password(userCustom.getPassword()).roles(userCustom.getRole()).build();
		}
		
	}

}
