/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.UserDB;
import java.util.List;
import models.User;

/**
 *
 * @author johnn
 */
public class UserService {
    
    public User get(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        
        return user;
    }
    
    public List<User> getAll() throws Exception {
        UserDB userDB = new UserDB();
        List<User> users = userDB.getAll();
        
        return users;
    }
    
    public void insert(String email, int activeStatus, 
                       String fname, String lname, String password, int role) 
            throws Exception {
        UserDB userDB = new UserDB();
        userDB.insert(new User(email, activeStatus, fname, lname, password, role));
    }
    
    public void update(String email, int activeStatus, 
                       String fname, String lname, String password, int role) 
            throws Exception {
        UserDB userDB = new UserDB();
        userDB.update(new User(email, activeStatus, fname, lname, password, role));
    }
    
    public void delete(String email) throws Exception {
        User user = new User();
        user.setEmail(email);
        
        UserDB userDB = new UserDB();
        userDB.delete(user);
    }
}
