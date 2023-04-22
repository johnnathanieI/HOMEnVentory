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
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import models.User;
import services.CategoryService;
import services.ItemService;
import services.UserService;

/**
 *
 * @author johnn
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String itemId = request.getParameter("itemId");
        
        displayAll(request, response);   
        
    }
    
    private void displayAll(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        List<Item> items = null;
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        
        UserService us = new UserService();
        
        try {
            User user = us.get(email);
            items = user.getItemList();
        } catch (Exception ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, "Could not retrieve user after login", email);
        }
        
        request.setAttribute("items", items);
        getServletContext().getRequestDispatcher("/WEB-INF/home.jsp")
                .forward(request, response);
    }
    
    private void displayItem(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        Item item = null;
        List<Category> categories = null;
        
        ItemService is = new ItemService();
        CategoryService cs = new CategoryService();
        
        String temp = request.getParameter("itemId");
        int itemId = Integer.parseInt(temp);
        
        try {
            item = is.get(itemId);
            categories = cs.getAll();
        } catch (Exception ex) {
            Logger.getLogger(ItemService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        request.setAttribute("item", item);
        request.setAttribute("categories", categories);
        
        getServletContext().getRequestDispatcher("/WEB-INF/item_edit.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        String itemId = request.getParameter("itemId");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        
        ItemService is = new ItemService();
        
        HttpSession session = request.getSession();
        
        switch (action) {
            case "Logout":
                response.sendRedirect("login");
            break;
            case "Delete":
                int item = Integer.parseInt(itemId);
                
                try {
                    is.delete(item);
                    displayAll(request, response);
                } catch (Exception ex) {
                }
            break;
            case "Add Item":
                List<Category> categories = null;
                
                CategoryService cs = new CategoryService();
                
                try {
                    categories = cs.getAll();
                } catch (Exception ex) {
                }
                
                request.setAttribute("categories", categories);
                
                getServletContext().getRequestDispatcher("/WEB-INF/item_create.jsp")
                        .forward(request, response);
            break;
            case "Edit":
                request.setAttribute("itemId", itemId);
                
                displayItem(request, response);
            break;
            case "Settings":
                session.getAttribute("firstName");
                session.getAttribute("lastName");
                
                getServletContext().getRequestDispatcher("/WEB-INF/user_settings.jsp")
                        .forward(request, response);
            break;
        }
    }
}
