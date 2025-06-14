package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Product;
import com.example.demo.Repository.ProductRepo;

@Service("productservice")
public class ProductService {

	
	@Autowired
	private  ProductRepo  productRepo;
	
	public boolean isAddNewProduct(Product product)
	{
		return  productRepo.isAddNewProduct(product);
	}
	
	public List<Product> getAllProduct()
	{
		return productRepo.getAllProduct();
	}
	public Product searchProductById(int id)
	{
		return productRepo.searchProductById(id);
	}
	
	public List<Product> searchProductCategory(int categoryId) {
	    return productRepo.searchProductCategory(categoryId);
	}

	
	
	 public int fetchTotalStockQuantity() {
	        return productRepo.getTotalStockQuantity();
	    }
	public boolean deletehProductById(int id)
	{
		return productRepo.deletehProductById(id);
	}
}
