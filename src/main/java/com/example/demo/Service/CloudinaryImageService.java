package com.example.demo.Service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryImageService {
	
	 Map<String, Object> upload(MultipartFile file);
}
