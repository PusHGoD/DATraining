package com.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(encoder());
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// BCrypt of password 'password'
		auth.inMemoryAuthentication().withUser("user")
				.password("$2a$04$CoIeSOJCXHTDb41RPA7apufycZqy0Pm.ip4k/RNKarOxYEv.qteKW").roles("USER").and()
				.withUser("admin").password("$2a$04$uoUMVoQ2c809m422247dGOtqh8F04a9Z920Qv8zaaJ1KbvD1691Ye")
				.roles("ADMIN").and().passwordEncoder(encoder());

		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/", "/product/**").hasRole("USER")
				.antMatchers("/location/**", "/time/**", "/sales/**").hasRole("ADMIN").and().httpBasic().and()
				.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler());
	}
}
