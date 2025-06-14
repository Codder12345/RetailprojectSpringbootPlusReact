package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.mapper.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class ProjectConfig {
	@Bean
	public Mapper mapper()
	{
		return new Mapper();
	}

	@Bean 
	
	public Cloudinary cloudinary() {
	    Map<String, String> config = new HashMap<>();
	    config.put("cloud_name", "dbxrly4ci");
	    config.put("api_key", "791896861245918");
	    config.put("api_secret", "_8rODTdgUhEnQhgkyZuox6u0fUE");
	    config.put("secure", "true");
	    return new Cloudinary(config);
	}
}
