package com.training.security;

import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig
// extends WebSecurityConfigurerAdapter
{

	// @Override
	// protected void configure(AuthenticationManagerBuilder auth) throws
	// Exception {
	//
	// auth.jdbcAuthentication().dataSource(dataSource)
	// .usersByUsernameQuery("select username, password, enabled"
	// + " from users where username=?")
	// .authoritiesByUsernameQuery("select username, authority "
	// + "from authorities where username=?")
	// .passwordEncoder(new BCryptPasswordEncoder());
	// }

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	//
	// http.authorizeRequests().anyRequest().hasAnyRole("ADMIN", "USER")
	// .and()
	// .httpBasic(); // Authenticate users with HTTP basic authentication
	// }
}
