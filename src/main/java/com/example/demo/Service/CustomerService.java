package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Product;
import com.example.demo.Model.User;
import com.example.demo.Repository.CustomerRepo;

@Service("customerservice")
public class CustomerService {
	
	@Autowired
	CustomerRepo customerRepo;
	
	public boolean createCustomer(Customer model)
	{
		return customerRepo.createCustomer(model);
	}
	
	public List<Customer>  getAllCustomers()
	{
		return customerRepo.getAllCustomers();
	}
	public Customer searchCustomerById(int id)
	{
		return customerRepo.searchCustomerById(id);
	}
	
	public boolean isDeleteCustomerByID(int id)
	{
		return customerRepo.isDeleteCustomerByID(id);
	}
	public Customer updateCustomerById(int id, Customer cust)
	{
		return customerRepo.updateCustomerById(id, cust);
	}

}
