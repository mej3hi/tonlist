package com.tonlist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        	.authorizeRequests()
        		.antMatchers("/", "/signUp","/favicon.ico","/static/**","/webjars/**").permitAll()
        		.anyRequest().authenticated()
        		.and()
    		.formLogin()
    			.loginPage("/signIn")
    			.defaultSuccessUrl("/",true)
    			.permitAll()
    			.and()
			.logout()
				.permitAll();
        
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("mej3").password("1234").roles("mej3");
    }
    
      

}