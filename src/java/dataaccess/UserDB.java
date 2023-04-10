package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.User;

/**
 *
 * @author johnn
 */
public class UserDB {
    
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM user";
        
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                String email = rs.getString(1);
                int activeStatus = rs.getInt(2);
                String fname = rs.getString(3);
                String lname = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);
                
                User user = new User(email, activeStatus, fname, lname, password, role);
                users.add(user);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return users;
    }
    
    public User get(String email) throws Exception {
        User user = null;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM user WHERE email = ?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            
            rs = ps.executeQuery();
            if (rs.next()) {
                int activeStatus = rs.getInt(2);
                String fname = rs.getString(3);
                String lname = rs.getString(4);
                String password = rs.getString(5);
                int role = rs.getInt(6);
                
                user = new User(email, activeStatus, fname, lname, password, role);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        
        return user;
    }
    
    public void update(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
        String sql = "UPDATE user SET activeStatus = ?"
                + " fname = ?, lname = ?, "
                + "password = ?, role = ? WHERE email = ?";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setInt(1, user.getActiveStatus());
            ps.setString(2, user.getFname());
            ps.setString(3, user.getFname());
            ps.setString(4, user.getPassword());
            ps.setInt(5, user.getRole());
            ps.setString(6, user.getEmail());
            
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void insert(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
        String sql = "INSERT INTO user VALUES (?, ?, ?, ?, ?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setInt(2, user.getActiveStatus());
            ps.setString(3, user.getFname());
            ps.setString(4, user.getFname());
            ps.setString(5, user.getPassword());
            ps.setInt(6, user.getRole());
            
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
    
    public void delete(User user) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        
        String sql = "DELETE FROM user WHERE email = ?";
        
        try {
            ps = con.prepareStatement(sql);
            
            ps.setString(1, user.getEmail());
            
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }
}