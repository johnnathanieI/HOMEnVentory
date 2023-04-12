/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dataaccess.CategoryDB;
import java.util.List;
import models.Category;

/**
 *
 * @author johnn
 */
public class CategoryService {
    public Category get(int categoryID) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(categoryID);
        
        return category;
    }
    
    public List<Category> getAll() throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        List<Category> categories = categoryDB.getAll();
        
        return categories;
    }
    
    public void insert(int categoryID, String categoryName) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        Category category = new Category(categoryID, categoryName);
        
        categoryDB.insert(category);
    }
    
    public void update(int categoryID, String categoryName) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(categoryID);
        
        category.setCategoryName(categoryName);
        
        categoryDB.update(category);
    }
    
    public void delete(int categoryID) throws Exception {
        CategoryDB categoryDB = new CategoryDB();
        Category category = categoryDB.get(categoryID);
        
        categoryDB.delete(category);
    }
}
