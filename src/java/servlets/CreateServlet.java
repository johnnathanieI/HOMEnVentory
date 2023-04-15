/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Category;
import models.Role;
import models.User;
import services.CategoryService;
import services.RoleService;
import services.UserService;

/**
 *
 * @author johnn
 */
public class CreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String type = request.getParameter("type");
        String message;
        request.setAttribute("messageBoolean", false);
        
        if (type.equals("user")) {
            String email = request.getParameter("email");
            String active = request.getParameter("active");
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String password = request.getParameter("password");
            String roleId = request.getParameter("role");
            
            try {
                UserService us = new UserService();
                
                User user = us.get(email);
                
                if (email == null || email.equals("") 
                        || firstName == null || firstName.equals("") 
                        || lastName == null || lastName.equals("") 
                        || password == null || password.equals("") 
                        || roleId == null || roleId.equals("")) {
                    request.setAttribute("messageBoolean", true);
                    message = "Error: There is an empty string and the user could not be created";
                    request.setAttribute("message", message);
                    Logger.getLogger(UserService.class.getName()).log(Level.INFO, "Admin tried to create a user with an empty field at {0}", email);
                    
                    getServletContext().getRequestDispatcher("/WEB-INF/category_create.jsp")
                            .forward(request, response);
                } else if (user != null) {
                    request.setAttribute("messageBoolean", true);
                    message = "Error: User already exists";
                    request.setAttribute("message", message);
                    Logger.getLogger(UserService.class.getName()).log(Level.INFO, "Admin tried to create an existing user at {0}", email);
                    
                    getServletContext().getRequestDispatcher("/WEB-INF/user_create.jsp")
                            .forward(request, response);
                } else {
                    boolean status = "checked".equals(active);
                    int rolenum = Integer.parseInt(roleId);
                    
                    us.insert(email, status, firstName, lastName, password, rolenum);
                    }
                
            } catch (Exception ex) {
            }
        } else if (type.equals("category")) {
            String id = request.getParameter("id");
            String cName = request.getParameter("categoryName");
            
            try {
                CategoryService cs = new CategoryService();
                int categorynum = Integer.parseInt(id);
                
                Category category = cs.get(categorynum);
                
                if (cName == null || cName.equals("") || id == null || id.equals("")) {
                    request.setAttribute("messageBoolean", true);
                    message = "Error: Empty string at ID or CategoryName";
                    request.setAttribute("message", message);
                    Logger.getLogger(UserService.class.getName()).log(Level.INFO, "Admin tried to create a category with an empty field at {0}", id);
                    
                    getServletContext().getRequestDispatcher("/WEB-INF/category_create.jsp")
                            .forward(request, response);
                 } else if (category != null) {
                    request.setAttribute("messageBoolean", true);
                    message = "Error: Category already exists";
                    request.setAttribute("message", message);
                    Logger.getLogger(UserService.class.getName()).log(Level.INFO, "Admin tried to create an existing category at {0}", id);
                    
                    getServletContext().getRequestDispatcher("/WEB-INF/category_create.jsp")
                            .forward(request, response);
                    return;
                } else {
                    cs.insert(categorynum, cName);    
                }
            } catch (Exception ex) {
            }
        }
        
        response.sendRedirect("admin");
    }
}
