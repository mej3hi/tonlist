package com.tonlist.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, proxyTargetClass = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }    
	
  
   /* @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable() 
        	.authorizeRequests()
        		.antMatchers("/","/calander", "/signUp","/forgetPassword","/resetPassword","/favicon.ico","/static/**","/webjars/**").permitAll()
        		.anyRequest().authenticated()
        		.and()
    		.formLogin()
    			.loginPage("/m/signIn")
    			.defaultSuccessUrl("/m/",true)
    			.permitAll()
    			.and()
			.logout()
				.permitAll();
		
  
        
    }*/
    
	    @Configuration
	    @Order(1)
	    public static class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter{
	    	@Override
	    	protected void configure(HttpSecurity http) throws Exception {
	            http.csrf().disable()
                .antMatcher("/m/**")
                .authorizeRequests()
                	.antMatchers("/m/").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .httpBasic();
                	
	    	}
	    	
	    }
	    
	    @Configuration
	    @Order(2)
	    public static class FormWebSecurityConfig extends WebSecurityConfigurerAdapter{
	    	@Override
	    	protected void configure(HttpSecurity http) throws Exception {
	            http
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
	    }
    
    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }    
    

}