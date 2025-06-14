package com.example.demo.Repository;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Category;
import com.example.demo.Model.Product;

@Repository("catRepo")
public class CategoryRepository {
	List<Category>list;
	@Autowired
	JdbcTemplate jdbctemplate;
	

	public boolean isAddNewCategory(Category category)
	{
		int value =jdbctemplate.update("insert into Categories values('0',?)",new PreparedStatementSetter()
				{

					public void setValues(PreparedStatement ps) throws SQLException {
						
						ps.setString(1, category.getCategoryName());
						
					}
				
				});
		return value>0?true:false;
				
	}
	public List<Category> getAllCategories(){ 
		list =jdbctemplate.query("select * from Categories" , new RowMapper<Category>()
	{
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			Category cat = new Category();
			cat.setCategoryID(rs.getInt("CategoryID"));
			cat.setCategoryName(rs.getString("CategoryName"));
	           
			
			return cat;
		}
	});
		return list;
	}
	
	public Category searchCategoryById(int id) {
		 list = jdbctemplate.query("select * from  Categories where CategoryID = ?",new PreparedStatementSetter() {
			            @Override
			            public void setValues(PreparedStatement ps) throws SQLException {
			                ps.setInt(1, id);
			            }
			        },
			        new RowMapper<Category>() {
			            @Override
			            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			            	Category cat = new Category();
			            	cat.setCategoryID(rs.getInt("CategoryID"));
			            	cat.setCategoryName(rs.getString("CategoryName"));
			            	
			                return cat;
			            }
			        }
			    );

			   
			    if (list.size() > 0) {
			        return list.get(0);
			    } else {
			        return null;
			    }
			}
			 public Category deletehCategoryById(int id) {
				 list = jdbctemplate.query( "select * from Categories where CategoryID = ?",  new PreparedStatementSetter() {
			                      @Override
			                      public void setValues(PreparedStatement ps) throws SQLException {
			                          ps.setInt(1, id);
			                      }
			                  },
			                  new RowMapper<Category>() {
			                      @Override
			                      public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			                    	  Category cat = new Category();
			                    	  cat.setCategoryID(rs.getInt("CategoryID"));
						              cat.setCategoryName(rs.getString("CategoryName"));
			      	                return cat;
			                      }
			                  }
			              );

			             
			              if (!list.isEmpty()) {
			                  jdbctemplate.update(  "delete from  Categories where  CategoryID = ?",new PreparedStatementSetter() {
			                          @Override
			                          public void setValues(PreparedStatement ps) throws SQLException {
			                              ps.setInt(1, id);
			                          }
			                      }
			                  );

			                  return list.get(0);
			              } else {
			                  return null; 
			          }


		}
			 public Category updateCategoryById(int id, Category updatedCategory) {
				 	int value = jdbctemplate.update("update Categories SET CategoryName = ? WHERE CategoryID = ?",new PreparedStatementSetter() {
				            @Override
				            public void setValues(PreparedStatement ps) throws SQLException {
				                ps.setString(1, updatedCategory.getCategoryName());
				             
				                
				                ps.setInt(2, id);
				            }
				        }
				    );

				    if (value > 0) {
				    	updatedCategory.setCategoryID(id);  
				        return updatedCategory;
				    } else {
				        return null;
				    }

				}

}
