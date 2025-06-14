package com.example.demo.Repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import com.example.demo.Model.Product;

@Repository
public class ProductRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isAddNewProduct(Product product) {
        int value = jdbcTemplate.update(
            "INSERT INTO Products (ProductName, ProductPrice, CategoryID,productBrand, StockQuantity, ImageUrl) VALUES (?, ?, ?, ?, ?, ?)",
            ps -> {
            	ps.setString(1, product.getProductName());
            	ps.setDouble(2, product.getProductPrice());
            	ps.setInt(3, product.getCategoryID());
            	ps.setString(4, product.getProductBrand());
            	ps.setInt(5, product.getStockQuantity());
            	ps.setString(6, product.getImageUrl());


            }
        );
        return value > 0;
    }

  
    public List<Product> getAllProduct() {
        return jdbcTemplate.query(
            "SELECT * FROM Products",
            (rs, rowNum) -> {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductPrice(rs.getDouble("ProductPrice"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setProductBrand(rs.getString("productBrand"));
                product.setStockQuantity(rs.getInt("StockQuantity"));
                product.setImageUrl(rs.getString("imageurl"));
                return product;
            }
        );
    }

    public Product searchProductById(int id) {
        List<Product> list = jdbcTemplate.query(
            "SELECT * FROM Products WHERE ProductID = ?",
            ps -> ps.setInt(1, id),
            (rs, rowNum) -> {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductPrice(rs.getDouble("ProductPrice"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setProductBrand(rs.getString("productBrand"));
                product.setStockQuantity(rs.getInt("StockQuantity"));
               product.setImageUrl(rs.getString("imageurl"));
                return product;
            }
        );

        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            return null;
        }
    }
    
    
    public List<Product> searchProductCategory(int categoryId) {
        return jdbcTemplate.query(
            "SELECT * FROM Products WHERE CategoryID = ?",
            ps -> ps.setInt(1, categoryId),
            (rs, rowNum) -> {
                Product product = new Product();
                product.setProductID(rs.getInt("ProductID"));
                product.setProductName(rs.getString("ProductName"));
                product.setProductPrice(rs.getDouble("ProductPrice"));
                product.setCategoryID(rs.getInt("CategoryID"));
                product.setProductBrand(rs.getString("productBrand"));
                product.setStockQuantity(rs.getInt("StockQuantity"));
                product.setImageUrl(rs.getString("ImageUrl"));
                return product;
            }
        );
    }

    
    
    public int getTotalStockQuantity() {
        String sql = "SELECT SUM(StockQuantity) FROM Products";
        Integer total = jdbcTemplate.queryForObject(sql, Integer.class);
        return total != null ? total : 0;
    }

    public boolean deletehProductById(int id) {
        Product product = searchProductById(id);
        if (product != null) {
            jdbcTemplate.update("DELETE FROM Products WHERE ProductID = ?", ps -> ps.setInt(1, id));
        }
        return product != null;
    }


	public Object findById(Long productID) {
		// TODO Auto-generated method stub
		return null;
	}

}
