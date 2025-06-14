package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Product;
import com.example.demo.Model.User;
import com.example.demo.Service.CustomerService;
import com.example.demo.exception.CustomerNotFoundException;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.exception.UserNotFoundException;

@CrossOrigin(origins = "http://localhost:5173") // Assuming React runs on 5173
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public String createCustomer(@RequestBody Customer model) {
        boolean isSaved = customerService.createCustomer(model);
       // System.out.println(model.getCustomerID()+model.getCustomerName()+model.getContactInfo()+model.getUserID());
        return isSaved ? "Customer saved successfully" : "An error occurred while saving the customer";
    }
    
//    List list=new ArrayList<>();
    
   @GetMapping("/getallcustomer")
	public List<Customer> getAllProducts()
	{
		List <Customer> list= customerService.getAllCustomers();
		if(list.size()!=0)
		{
			return list;
		}
		else
		{
			throw new CustomerNotFoundException("there is No data in Database table");
		}
		
		
	}
   
   @GetMapping("/searchcustomer/{id}")
   public Customer searchUserById(@PathVariable int id) {
	   
      Customer cs = customerService.searchCustomerById(id);
      
      
       if (cs != null) {
           return cs;
       } else {
           throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
       }
       
   }
   
   @DeleteMapping("/deletecustomerbyid/{id}")
	  public String  isDeleteCustomerByID(@PathVariable("id") Integer id)
	  {
		 boolean b=customerService.isDeleteCustomerByID(id);
		   if(b)
		   {
			return "customer deleted";   
		   }
		   else {
			   throw  new CustomerNotFoundException("no customer found");
		   }
		
	  }
   
   
    
   
   @PutMapping("/updatecustomer/{id}")
   public Customer updateCustomerById(@PathVariable int id, @RequestBody Customer cust) {
       Customer customer = customerService.updateCustomerById(id, cust);

       if (customer != null) {
           return customer; 
       } else {
           throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
       }
   }
}
