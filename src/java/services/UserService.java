package services;

import dataaccess.RoleDB;
import dataaccess.UserDB;
import java.util.List;
import models.Role;
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
