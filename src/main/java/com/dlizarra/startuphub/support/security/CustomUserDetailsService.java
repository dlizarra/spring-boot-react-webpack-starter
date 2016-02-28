package com.dlizarra.startuphub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dlizarra.startuphub.user.User;
import com.dlizarra.startuphub.user.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username " + username + " not found."));

		return new CustomUserDetails(user);
	}

}
