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

import com.example.demo.Model.InventoryModel;
import com.example.demo.Model.User;

@Repository("Inventoryrepo")
public class InventoryRepo {
	@Autowired
	JdbcTemplate jdbc;
	List<InventoryModel> list;
	public boolean createInventory(InventoryModel model) {
		int value = jdbc.update(
		        "INSERT INTO inventory ( ProductID, Stocklevel, lowStockAlert) VALUES ( ?, ?, ?)",
		        ps -> {
		            ps.setInt(1, model.getProductID());
		            ps.setInt(2, model.getStocklevel());
		            ps.setInt(3, model.getLowStockAlert());
		        }
		    );
	        
	    
	    return value > 0?true:false;
	}
	
	public List<InventoryModel> getAllInventoryInfo() {
	    return jdbc.query("select * from inventory", new RowMapper<InventoryModel>() {
	        public InventoryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
	            InventoryModel u = new InventoryModel();
	            u.setInvenID(rs.getInt("InvenID")); // fixed here
	            u.setProductID(rs.getInt("ProductID"));
	            u.setStocklevel(rs.getInt("Stocklevel"));
	            u.setLowStockAlert(rs.getInt("lowStockAlert"));
	            return u;
	        }
	     });
	}
	
	public InventoryModel searchInventoryById(int id) {
	    String sql = "SELECT * FROM inventory WHERE InvenID = ?";
	    
	    List<InventoryModel> list = jdbc.query(sql, ps -> ps.setInt(1, id),
	        (rs, rowNum) -> {
	            InventoryModel model = new InventoryModel();
	            model.setInvenID(rs.getInt("InvenID")); // Adjust if column name is InventoryID
	            model.setProductID(rs.getInt("ProductID"));
	            model.setStocklevel(rs.getInt("Stocklevel"));
	            model.setLowStockAlert(rs.getInt("LowStockAlert")); // Case-sensitive depending on DB
	            return model;
	        });

	    return list.isEmpty() ? null : list.get(0);
	}
	
	public InventoryModel deleteInventoryByID(int id)
	{
		List<InventoryModel> value=jdbc.query("delete from inventory where InvenID=?",new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				// TODO Auto-generated method stub
				ps.setInt(1, id);
				
			}
		}, new RowMapper<InventoryModel>() {

			@Override
			public InventoryModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				InventoryModel model=new InventoryModel();
				model.setInvenID(rs.getInt("InventoryID"));
				model.setProductID(rs.getInt("ProductID"));
				model.setLowStockAlert(rs.getInt("stocklevel"));
				return model;
			}
		});
		if (list.size() > 0) {
            return list.get(0); 
        } else {
            return null;
        }
	}
}
