package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.CartItem;
import com.example.demo.Service.CartService;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins="/**")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
        @RequestParam("productId") int productId,
        @RequestParam int quantity,
        @RequestParam int userId
    ) {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);
        cartItem.setUserId(userId);
        boolean success = cartService.addToCart(cartItem);  

        if (success) {
            return ResponseEntity.ok("Product added to cart.");
        } else {
            return ResponseEntity.badRequest().body("Failed to add product to cart.");
        }
    }




    @GetMapping("/view/{userId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable int userId) {
        List<CartItem> cartItems = cartService.getCartItems(userId);
        System.out.println(cartItems);
        return ResponseEntity.ok(cartItems);
    }


    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<String> removeCartItem(@PathVariable int cartItemId) {
        boolean success = cartService.removeCartItem(cartItemId);
        if (success) {
            return ResponseEntity.ok("Item removed from cart.");
        } else {
            return ResponseEntity.badRequest().body("Failed to remove item from cart.");
        }
    }
}