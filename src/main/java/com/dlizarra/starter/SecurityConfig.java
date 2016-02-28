package com.dlizarra.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Import({ SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class })
@Profile("!test")
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/resources/**", "/signup")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
			.loginPage("/login")
			.permitAll()
			.and()
			.logout()
			.permitAll();
		http
			.authorizeRequests()
			.anyRequest()
			.permitAll();
		http.authorizeRequests().antMatchers("/").permitAll().and()
				.authorizeRequests().antMatchers("/console/**").permitAll();

		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(final UserDetailsService userDetailsService) {
		final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth, final UserDetailsService userDetailsService)
			throws Exception {
		auth
			.authenticationProvider(daoAuthenticationProvider(userDetailsService))
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}

}
