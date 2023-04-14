/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.RoleDB;
import java.util.List;
import models.Role;

/**
 *
 * @author johnn
 */
public class RoleService {
    
    public Role get(int roleID) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleID);
        
        return role;
    }
    
    public Role get(String roleName) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleName);
        
        return role;
    }
    
    public List<Role> getAll() throws Exception {
        RoleDB roleDB = new RoleDB();
        List<Role> roles = roleDB.getAll();
        
        return roles;
    }
    
    public void insert(int roleID, String roleName) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = new Role(roleID, roleName);
        roleDB.insert(role);
    }
    
    public void update(int roleID, String roleName) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleID);
        
        role.setRoleName(roleName);
        
        roleDB.update(role);
    }
    
    public void delete(int roleID) throws Exception {
        RoleDB roleDB = new RoleDB();
        Role role = roleDB.get(roleID);
        
        roleDB.delete(role);
    }
}
