package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Role;
import models.User;

/**
 *
 * @author johnn
 */
public class UserService {
    
    public User login(String email, String password) {
        UserDB userDB = new UserDB();
        
        try {
            User user = userDB.get(email);
            if (password.equals(user.getPassword())) {
                Logger.getLogger(UserService.class.getName()).log(Level.INFO, "Successful login {0}", email);
                return user;
            }
        } catch (Exception ex) {
        }
        return null;
    }
    
    public User register(String firstName, String lastName, String email, String password, String path) {
        UserDB userDB = new UserDB();
        
        try {
            User user = new User(email, true, firstName, lastName, password);
            RoleDB roleDB = new RoleDB();
                
            Role role = roleDB.get(2);
            user.setRole(role);
                
            userDB.insert(user);
            Logger.getLogger(UserService.class.getName()).log(Level.INFO, "Successful registration {0}", email);
            
            return user;
        } catch (Exception ex) {
        }
        
        return null;
    }
    
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
    
    public void insert(String email, boolean activeStatus, 
                       String fname, String lname, String password, int roleID) 
            throws Exception {
        User user = new User(email, activeStatus, fname, lname, password);
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleID);
        user.setRole(role);
        
        UserDB userDB = new UserDB();
        userDB.insert(user);
    }
    
    public void update(String email, boolean activeStatus, 
                       String fname, String lname, String password, int roleID) 
            throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        user.setActive(activeStatus);
        user.setFirstName(fname);
        user.setLastName(lname);
        user.setPassword(password);
        
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleID);
        user.setRole(role);
        
        userDB.update(user);
    }
    
    public void delete(String email) throws Exception {
        UserDB userDB = new UserDB();
        User user = userDB.get(email);
        userDB.delete(user);
    }
}
