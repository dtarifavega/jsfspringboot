
package com.java.creacionusuarios;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
         .antMatchers("/h2-console/**").permitAll()
         .antMatchers("/index.xhtml").permitAll()
        .and()
        .logout()
        .permitAll();

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }
   
	 
	 
	   
   
}