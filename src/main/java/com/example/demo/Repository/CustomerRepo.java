package com.example.demo.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Customer;
import com.example.demo.Model.Product;
import com.example.demo.Model.User;

@Repository("customerRepo")
public class CustomerRepo {
	 
	
	List<Customer>list;
	@Autowired 
	JdbcTemplate jdbcTemplate;
	public boolean createCustomer(Customer model)
	{
		 int value=jdbcTemplate.update("INSERT INTO customers (customerID,customerName, contactInfo, userID) VALUES ('0',?, ?, ?)",new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
			
				    ps.setString(1, model.getCustomerName());
			        ps.setString(2, model.getContactInfo());
			        ps.setInt(3, model.getUserID());

			}
			 
		 });
	       
		return value>0?true:false;
	}
	
	
	public List<Customer> getAllCustomers(){ 
		list =jdbcTemplate.query("select *from customers ", new RowMapper<Customer>()
	{
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			
		   Customer c = new Customer();
                c.setCustomerID(rs.getInt("customerID"));
	            c.setCustomerName(rs.getString("customerName"));
	            c.setContactInfo(rs.getString("contactInfo"));
	            c.setUserID(rs.getInt("userID"));
	      
			
			return c;
		 }
    	});
		return list;
	}
	
	
	
	public Customer searchCustomerById(int id) {
	      
        list = jdbcTemplate.query("select * from Customers where customerID  = ?",new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, id); 
                }
            },
            new RowMapper<Customer>() {
                @Override
                public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Customer cs = new Customer();
                    cs.setCustomerID(rs.getInt("customerID"));
                    cs.setCustomerName(rs.getString("customerName"));
                    cs.setContactInfo(rs.getString("contactInfo"));
                    cs.setUserID(rs.getInt("userID"));
                    System.out.print(""+cs.getContactInfo()+""+cs.getCustomerID());
                    
                    return cs;
                }
            }
        );
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }
	
	  

    public boolean isDeleteCustomerByID(int id)
    {
    	
    int value=jdbcTemplate.update("delete  from customers where customerID="+id);
    	
    	return value>0?true:false;
    }
	   
    
    public Customer updateCustomerById(int id, Customer cust) {
        int value =jdbcTemplate.update("update Customers set customerName = ?, contactInfo = ?, userID= ? WHERE customerID= ?",
            new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                  
                    ps.setInt(1,cust.getCustomerID());
                    ps.setString(2, cust.getCustomerName());
                    ps.setInt(3, cust.getUserID());
                }
            }
        );
		
     
        if (value > 0) {
            cust.setCustomerID(id);  
            return cust;
        } else {
            return null;
        }


    } 
};



