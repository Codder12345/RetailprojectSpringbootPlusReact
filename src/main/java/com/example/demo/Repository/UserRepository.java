package com.example.demo.Repository;

import com.example.demo.Model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("userRepo")
public class UserRepository {
		List<User>list;
    @Autowired
    JdbcTemplate jdbcTemplate;

    
    public boolean isAddNewUser(User user) {
      int value = jdbcTemplate.update("insert into User(UserName, Password, RoleID) values(?, ?, ?)"
, new PreparedStatementSetter() {

                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, user.getUserName());
                    ps.setString(2, user.getPassword());
                    ps.setInt(3, user.getRoleId());
                }
      
    });
      return value>0?true:false;
  	
    }

	
    public User loginUser(String username, String password) {
        String sql = "SELECT * FROM User WHERE UserName = ? AND Password = ?";

        List<User> users = jdbcTemplate.query(sql,
            new PreparedStatementSetter() {
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, username);
                    ps.setString(2, password);
                }
            },
            new RowMapper<User>() {
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User u = new User();
                    u.setUserId(rs.getInt("UserID"));
                    u.setUserName(rs.getString("UserName"));
                    u.setPassword(rs.getString("Password"));
                    u.setRoleId(rs.getInt("RoleID"));
                    return u;
                }
            }
        );

        return users.isEmpty() ? null : users.get(0);
    }



    
    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from User", new RowMapper<User>() {
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User u = new User();
                    u.setUserId(rs.getInt("UserID"));
                    u.setUserName(rs.getString("UserName"));
                    u.setPassword(rs.getString("Password"));
                    u.setRoleId(rs.getInt("RoleID"));
                    return u;
                }
            }
        );
    }
    

    public User searchUserById(int id) {
      
        list = jdbcTemplate.query("select * from User where UserID = ?",new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setInt(1, id); 
                }
            },
            new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = new User();
                    user.setUserId(rs.getInt("UserID"));
                    user.setUserName(rs.getString("UserName"));
                    user.setPassword(rs.getString("Password"));
                    user.setRoleId(rs.getInt("RoleID"));
                    return user;
                }
            }
        );

       
        if (list.size() > 0) {
            return list.get(0); 
        } else {
            return null;
        }
    }

    public User deletehUserById(int id) {

        list = jdbcTemplate.query( "select * from User where UserID = ?",
                  new PreparedStatementSetter() {
                      @Override
                      public void setValues(PreparedStatement ps) throws SQLException {
                          ps.setInt(1, id);
                      }
                  },
                  new RowMapper<User>() {
                      @Override
                      public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                          User user = new User();
                          user.setUserId(rs.getInt("UserID"));
                          user.setUserName(rs.getString("UserName"));
                          user.setPassword(rs.getString("Password"));
                          user.setRoleId(rs.getInt("RoleID"));
                          return user;
                      }
                  }
              );

        if (list.size() > 0) {
            return list.get(0); 
        } else {
            return null;
        }
             
          }
    
    public User updateUserById(int id, User user) {
        int value =jdbcTemplate.update("update User set UserName = ?, Password = ?, RoleID = ? WHERE UserID = ?",
            new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps) throws SQLException {
                    ps.setString(1, user.getUserName());
                    ps.setString(2, user.getPassword());
                    ps.setInt(3, user.getRoleId());
                    ps.setInt(4, user.getUserId());
                }
            }
        );
		
     
        if (value > 0) {
            user.setUserId(id);  
            return user;
        } else {
            return null;
        }


    } 

}
