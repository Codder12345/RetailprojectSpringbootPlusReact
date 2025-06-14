package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.CartItem;
import com.example.demo.Repository.CartRepository;

@Service("cartService")
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public boolean addToCart(CartItem cartItem) {
        return cartRepository.addToCart(cartItem);
    }

    public List<CartItem> getCartItems(int userId) {
        return cartRepository.getCartItems(userId);
    }

    public boolean removeCartItem(int cartItemId) {
        return cartRepository.removeCartItem(cartItemId);
    }
}
