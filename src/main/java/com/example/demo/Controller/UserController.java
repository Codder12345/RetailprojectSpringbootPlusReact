package com.example.demo.Controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.Model.User;
import com.example.demo.Service.UserService;
import com.example.demo.exception.UserNotFoundException;
@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/api/User")
public class UserController {

	
	@Autowired
	UserService useservice ;
	List<User> list = new ArrayList<>();

	
	@PostMapping("/adduser")
	public String addUser (@RequestBody User user)
	{
		 int rolId=4;
		boolean b =useservice.isAddNewUser(user);
		if(b)
		{
			return "user Added";
		}
		
		return "User Not added";
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody User user) {
//	    String username = user.getUserName();
//	    String password = user.getPassword();
//
//	    System.err.println("Attempting login for: " + username);
//
//	    User loggedInUser = useservice.login(username, password);
//	    System.out.println(loggedInUser);
//
//	    if (loggedInUser != null) {
//	        Map<String, Object> response = new HashMap<>();
//	        response.put("username", loggedInUser.getUserName());
//	        response.put("role", loggedInUser.getRoleId());
//	        System.out.println("Login successful");
//	        return ResponseEntity.ok(response); // HTTP 200
//	    } else {
//	        System.out.println("Login failed");
//	        return ResponseEntity.status(HttpStatus.FORBIDDEN)
//	                             .body("Invalid credentials");
//	    }
//	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
	    String username = user.getUserName();
	    String password = user.getPassword();

	    System.err.println("Attempting login for: " + username);

	    User loggedInUser = useservice.login(username, password);
	    System.out.println(loggedInUser);

	    if (loggedInUser != null) {
	        Map<String, Object> response = new HashMap<>();
	        response.put("userId", loggedInUser.getUserId()); // ✅ Add this
	        response.put("username", loggedInUser.getUserName());
	        response.put("role", loggedInUser.getRoleId());
	        System.out.println("Login successful");
	        return ResponseEntity.ok(response); // HTTP 200
	    } else {
	        System.out.println("Login failed");
	        return ResponseEntity.status(HttpStatus.FORBIDDEN)
	                             .body("Invalid credentials");
	    }
	}

	
	
	
	
	
	 @GetMapping("/viewAllUsers")
	    public List<User> getAllUsers() {
	        List<User> list = useservice.getAllUser();
	        if (list.size() != 0) {
	            return list;
	        } else {
	            throw new UserNotFoundException("There is no user data in the database table.");
	        }
	 }
	

	

    @GetMapping("/searchuser/{id}")
    public User searchUserById(@PathVariable int id) {
        User user = useservice.searchUserById(id);

        if (user != null) {
            return user;
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
    }

    @DeleteMapping("/deleteuser/{id}")
    public User deleteUserById(@PathVariable int id) {
        User user = useservice.deletehUserById(id);

        if (user != null) {
            return user ;
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
    }

    @PutMapping("/updateuser/{id}")
    public User updateUserById(@PathVariable int id, @RequestBody User updatedUser) {
        User user = useservice.updateUserById(id, updatedUser);

        if (user != null) {
            return user; 
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found.");
        }
    }
}
