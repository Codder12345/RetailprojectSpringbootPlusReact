package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.InventoryModel;
import com.example.demo.Model.User;
import com.example.demo.Repository.InventoryRepo;
import com.example.demo.exception.UserNotFoundException;
@RequestMapping("/api/Inventory")
@RestController
public class InventoryController {
	@Autowired 
	InventoryRepo invenservice;
	
	@PostMapping("/createinventory")
	public String createInventory(InventoryModel invent)
	{
	
		 boolean b=invenservice.createInventory(invent);
			System.out.println(invent);
		 if(b)
		 {
				return "Inventory Added"; 
		 }
		 return " Inventory not found";
		
	}
	
	 @GetMapping("/viewAllInventory")
	    public List<InventoryModel> getAllUsers() {
		 List<InventoryModel> list =invenservice.getAllInventoryInfo();
	        if (list.size() != 0) {
	            return list;
	        } else {
	            throw new UserNotFoundException("There is noInventory data in the database table.");
	        }
	 }
	 
	 @GetMapping("/searchinventory/{id}")
	 public InventoryModel searchInventoryById(@PathVariable int id)
	 {
	
		 InventoryModel m=invenservice.searchInventoryById(id);
		
		return m;
	 }

	 @DeleteMapping("/deleteInventory/{id}")
	 public InventoryModel deleteInventoryById(@PathVariable int id)
	 {
	
		 InventoryModel m=invenservice.deleteInventoryByID(id);
		
		return m;
	 }
}
