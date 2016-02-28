package com.dlizarra.starter.support.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dlizarra.starter.user.User;

@SuppressWarnings("serial")
public class CustomUserDetails extends User implements UserDetails {

	public CustomUserDetails(final User user) {
		super(user);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles().stream()
			.map(role -> new SimpleGrantedAuthority(role.getRolename().name()))
			.collect(Collectors.toList());

	}

	@Override
	public String getPassword() {
		return super.getPassword();
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
		return super.isEnabled();
	}

}
