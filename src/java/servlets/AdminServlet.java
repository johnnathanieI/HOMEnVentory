/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Category;
import models.User;
import services.CategoryService;
import services.ItemService;
import services.UserService;

/**
 *
 * @author johnn
 */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        
        //Checks if edit was pressed which will retrieve the email for that user object and redirect to the edit page
        if (email != null && !email.isEmpty()) {
            displayUser(request, response);
            
        //Otherwise take admin to home view for admins which overlooks all user and category info
        } else {
            displayAll(request, response);
        }
    }
    
    private void displayAll(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<User> users = null;
        List<Category> categories = null;
        
        UserService us = new UserService();
        CategoryService cs = new CategoryService();
        
        try {
            users = us.getAll();
            categories = cs.getAll();
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("categories", categories);
        request.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp")
                .forward(request, response);
    }
    
    //Displays single user for editing purposes
    private void displayUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = null;
        
        UserService us = new UserService();
        
        String email = request.getParameter("email");
        
        try {
            user = us.get(email);
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/WEB-INF/user_edit.jsp")
                .forward(request, response);
    }
    
    private void displayCategory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Category category = null;
        
        CategoryService cs = new CategoryService();
        
        String temp = request.getParameter("id");
        int id = Integer.parseInt(temp);
        
        try {
            category = cs.get(id);
        } catch (Exception ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("category", category);
        getServletContext().getRequestDispatcher("/WEB-INF/category_edit.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        String id = request.getParameter("id");
        
        UserService us = new UserService();
        ItemService is = new ItemService();
        
        if (action.equals("Edit User")) {
            request.setAttribute("email", email);
            displayUser(request, response);
            
        } else if (action.equals("Create New User")) {
            getServletContext().getRequestDispatcher("/WEB-INF/user_create.jsp")
                    .forward(request, response);
            
        } else if (action.equals("Create New Category")) {
            getServletContext().getRequestDispatcher("/WEB-INF/category_create.jsp")
                    .forward(request, response);
            
        } else if (action.equals("Delete")) {
            try {
                //Create user object in order to retrieve associated items and clear the list
                User user = us.get(email);
                user.getItemList().clear();
                
                //Delete the user after clearing all items associated with the user
                us.delete(email);
                
                //Refresh page
                displayAll(request, response);
            } catch (Exception ex) {
            }
        } else if (action.equals("Edit Category")) {
            request.setAttribute("id", id);
            displayCategory(request, response);
        } else if (action.equals("Logout")) {
            response.sendRedirect("login");
        }
    }
}
