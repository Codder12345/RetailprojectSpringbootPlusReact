package com.example.demo.Service;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
@Service
public class CloudinaryServiceImageImple implements CloudinaryImageService{

	 @Autowired
	 private Cloudinary cloudnary;
	@Override
	public Map upload(MultipartFile file) {
	    try {
	        Map<?, ?> uploadResult = cloudnary.uploader().upload(file.getBytes(), Map.of());
	        return Map.of("url", uploadResult.get("secure_url"));
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	

}
