package com.example.demo.Model;

import lombok.Data;

@Data

public class Product {
	
	private int productID;
    private String  productName;
    private double productPrice ;
    private int    categoryID ;
    private String productBrand;
    private int    stockQuantity;
    private String imageUrl;  


	
	
  
}