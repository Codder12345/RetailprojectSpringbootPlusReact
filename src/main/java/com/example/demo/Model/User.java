package com.example.demo.Model;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String userName;
    private String password;
    private int roleId;
}
