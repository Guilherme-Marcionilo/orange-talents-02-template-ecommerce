package br.com.zup.desafio.mercadolivre.seguranca;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Profile("dev")
public class DevSecurityConfiguration extends WebSecurityConfigurerAdapter  {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/**").permitAll()
			.and()
				.cors()
			.and()
				.csrf().disable();
				
	}
	
	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
	  return authenticationManager();
	}
	
}