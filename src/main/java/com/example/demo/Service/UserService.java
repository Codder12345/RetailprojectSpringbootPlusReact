package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;

@Service("useservice")
public class UserService {

	
	@Autowired
	private UserRepository useRepo;
	
	public boolean isAddNewUser(User user)
	{
		return useRepo.isAddNewUser(user);
	}
	public User login(String username, String password) {
	    return useRepo.loginUser(username, password);
	}

	public List<User> getAllUser()
	{
		return useRepo.getAllUsers();
		
	}
	public User searchUserById(int id)
	{
		return useRepo.searchUserById(id);
	}
	
	public User deletehUserById(int id)
	{
		return useRepo.deletehUserById(id);
	}
	public User updateUserById(int id, User updateUser)
	{
		return useRepo.updateUserById(id, updateUser);
	}

}

