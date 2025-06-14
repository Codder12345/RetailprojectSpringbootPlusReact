package com.example.demo.Model;

import lombok.Data;

@Data
public class CartItem {
    private int id;
    private int productId;
    private int quantity;
    private int userId;
}