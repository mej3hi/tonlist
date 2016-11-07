package com.tonlist.support;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;


import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;


@Configuration
public class MvcConfig {
	
	
    @Bean  
    public ResourceBundleMessageSource messageSource() {  
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();  
        source.setBasename("validation");  
        source.setUseCodeAsDefaultMessage(true);  
        return source;  
    }
    
    
    @Value("${cloud.aws.credentials.accessKey}")
	private String accessKey;
    
    @Value("${cloud.aws.credentials.secretKey}")
	private String secretKey;
        
    @Bean
	public BasicAWSCredentials awsCreds() {
		return new BasicAWSCredentials(accessKey, secretKey);
	}
    
    @Bean
    public AmazonS3Client s3client() {
  	return new AmazonS3Client(awsCreds()); 
    }


}
