/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoryDB;
import dataaccess.ItemDB;
import dataaccess.UserDB;
import java.util.List;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author johnn
 */
public class ItemService {
    
    public Item get(int itemID) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(itemID);
        
        return item;
    }
    
    public Item get(String itemName) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(itemName);
        
        return item;
    }
    
    public List<Item> getAll() throws Exception {
        ItemDB itemDB = new ItemDB();
        List<Item> items = itemDB.getAll();
        
        return items;
    }
    
    public void insert(int categoryID, String itemName,
            double price, String owner) throws Exception {
        ItemDB itemDB = new ItemDB();
        UserDB userDB = new UserDB();
        CategoryDB categoryDB = new CategoryDB();
        
        User user = userDB.get(owner);
        Category category = categoryDB.get(categoryID);
        
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setOwner(user);
        item.setCategory(category);
        
        itemDB.insert(item);
    }
    
    public void update(int itemID, int categoryID, String itemName,
            double price, String owner) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(itemID);
        
        item.setItemName(itemName);
        item.setPrice(price);
        
        UserDB userDB = new UserDB();
        User user = userDB.get(owner);
        
        item.setOwner(user);
        
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(categoryID);
        
        item.setCategory(category);
        
        itemDB.update(item);
    }
    
    public void delete(int itemID) throws Exception {
        ItemDB itemDB = new ItemDB();
        Item item = itemDB.get(itemID);
        
        itemDB.delete(item);
    }
}
