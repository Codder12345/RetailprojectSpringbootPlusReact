package com.example.demo.Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Model.Product;
import com.example.demo.Service.CloudinaryImageService;
import com.example.demo.Service.ProductService;
import com.example.demo.exception.ProductNotFoundException;

@RestController
@CrossOrigin(origins="/**")
@RequestMapping("/api/Product") // <-- fixed
public class ProductController {

    @Autowired
    private ProductService productService;
    
    
    @PostMapping("/saveproduct")
    public ResponseEntity<String> saveProduct(@RequestBody Product product) {
    	boolean isAdded = productService.isAddNewProduct(product);
        return ResponseEntity.ok(isAdded ? "Product Added" : "Product Not Added");
    }


    
//    
//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) {
//        try {
//            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
//            Path filePath = Paths.get(fileName, fileName);
//            Files.createDirectories(filePath.getParent());
//            Files.write(filePath, file.getBytes());
//
//            // Save file path to the database with the product entity here if needed
//
//            return ResponseEntity.ok("Image uploaded successfully: " + fileName);
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body("Upload failed");
//        }
//    }
//    
//    
//    
//    
//    
//    
    private CloudinaryImageService imageService;

    public ProductController(CloudinaryImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("cloudinaryupload")
    public Map uploadImage(@RequestParam("image") MultipartFile image) {
        return imageService.upload(image);
    }

    
    @GetMapping("/viewAllProduct")
    public List<Product> getAllProducts() {
        List<Product> list = productService.getAllProduct();
        if (!list.isEmpty()) {
            return list;
        } else {
            throw new ProductNotFoundException("No product data in the database.");
        }
    }

    @GetMapping("/searchProduct/{id}")
    public Product searchProductById(@PathVariable int id) {
        Product product = productService.searchProductById(id);
        if (product != null) {
            return product;
        } else {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
    }
    
    
    @GetMapping("/searchProductcategory/{categoryId}")
    public List<Product> searchProductByCategory(@PathVariable int categoryId) {
        List<Product> products = productService.searchProductCategory(categoryId);
        if (!products.isEmpty()) {
            return products;
        } else {
            throw new ProductNotFoundException("No products found in category ID: " + categoryId);
        }
    }


    
    @GetMapping("/total-stock")
    public ResponseEntity<Integer> getTotalStock() {
        int totalStock = productService.fetchTotalStockQuantity();
        return ResponseEntity.ok(totalStock);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable int id) {
        boolean isDeleted = productService.deletehProductById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Product with ID " + id + " was deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.SC_NOT_FOUND)
                                 .body("Product with ID " + id + " not found.");
        }
    }
}
