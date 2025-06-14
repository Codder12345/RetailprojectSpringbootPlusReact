package com.example.demo.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.CartItem;

@Repository("cartRepo")
public class CartRepository {
	@Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean addToCart(CartItem cartItem) {
        String sql = "INSERT INTO cart_item (productId, quantity, userId) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, cartItem.getProductId(), cartItem.getQuantity(), cartItem.getUserId());
        return rowsAffected > 0;
    }

    public List<CartItem> getCartItems(int userId) {
        String sql = "SELECT * FROM cart_item WHERE userId = ?";
        
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            CartItem cartItem = new CartItem();
            cartItem.setId(rs.getInt("id"));
            cartItem.setProductId(rs.getInt("productId"));
            cartItem.setQuantity(rs.getInt("quantity"));
            cartItem.setUserId(rs.getInt("userId"));
            return cartItem;
        }, userId);
    }

    public boolean removeCartItem(int cartItemId) {
        String sql = "DELETE FROM cart_item WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, cartItemId);
        return rowsAffected > 0;
    }

}
