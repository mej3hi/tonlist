package com.tonlist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * It handle what page are allowed to see.
 * Handle sign in and sign out.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }    
	
  
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity 
        	.authorizeRequests()
        		.antMatchers("/","/calander", "/signUp","/forgetPassword","/resetPassword","/favicon.ico","/static/**","/webjars/**").permitAll()
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
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }    
    

}