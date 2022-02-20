package com.ing.usermanagement.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * @author prabuddha
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserManagementAuthenticationEntryPoint authenticationEntryPoint;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		List<GrantedAuthority> ingUsergrantedAuthorities = new ArrayList<>();
		ingUsergrantedAuthorities.add(new SimpleGrantedAuthority("READ_DATA"));

		List<GrantedAuthority> ingAdminggrantedAuthorities = new ArrayList<>();
		ingAdminggrantedAuthorities.add(new SimpleGrantedAuthority("READ_DATA"));
		ingAdminggrantedAuthorities.add(new SimpleGrantedAuthority("WRITE_DATA"));

		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.inMemoryAuthentication().withUser("user123").password(encoder.encode("user123")).roles("ING_USER")
				.authorities(ingUsergrantedAuthorities).and().withUser("admin").password(encoder.encode("admin"))
				.roles("ING_ADMIN").authorities(ingAdminggrantedAuthorities);
	}

	@Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/h2-console/**");
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.httpBasic().authenticationEntryPoint(authenticationEntryPoint).and().authorizeRequests()
				.antMatchers(HttpMethod.PUT, "/api/userdetails/**")
				.access("hasAuthority('WRITE_DATA') and isAuthenticated()")
				.antMatchers(HttpMethod.GET, "/api/userdetails/**")
				.access("hasAuthority('READ_DATA') and isAuthenticated()").and().csrf().disable();
	}
}
