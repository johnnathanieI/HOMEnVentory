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
import models.User;
import services.CategoryService;
import services.ItemService;
import services.UserService;

/**
 *
 * @author johnn
 */
public class CreateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        String email = (String) session.getAttribute("email");
        
        if (email == null) {
            response.sendRedirect("login");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String type = request.getParameter("type");
        String message;
        request.setAttribute("messageBoolean", false);
        String action = request.getParameter("action");
        
        if (action.equals("Finish")) {
            if (type.equals("user")) {
                String email = request.getParameter("email");
                String active = request.getParameter("active");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String password = request.getParameter("password");
                String roleId = request.getParameter("roleId");

                try {
                    UserService us = new UserService();

                    User user = us.get(email);

                    if (email == null && email.equals("") 
                            && firstName == null && firstName.equals("") 
                            && lastName == null && lastName.equals("") 
                            && password == null && password.equals("") 
                            && roleId == null && roleId.equals("")) {
                        response.sendRedirect("admin");
                        return;
                    } else if (email == null || email.equals("") 
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
                        return;
                    } else if (user != null) {
                        request.setAttribute("messageBoolean", true);
                        message = "Error: User already exists";
                        request.setAttribute("message", message);
                        Logger.getLogger(UserService.class.getName()).log(Level.INFO, "Admin tried to create an existing user at {0}", email);

                        getServletContext().getRequestDispatcher("/WEB-INF/user_create.jsp")
                                .forward(request, response);
                        return;
                    } else {
                        boolean status = "checked".equals(active);
                        int rolenum = Integer.parseInt(roleId);

                        us.insert(email, status, firstName, lastName, password, rolenum);
                        response.sendRedirect("admin");
                        return;
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
                        Logger.getLogger(CategoryService.class.getName()).log(Level.INFO, "Admin tried to create a category with an empty field at {0}", id);

                        getServletContext().getRequestDispatcher("/WEB-INF/category_create.jsp")
                                .forward(request, response);
                        return;
                    } else if (cName == null && cName.equals("") && id == null && id.equals("")) {
                        response.sendRedirect("admin");
                        return;
                    } else if (category != null) {
                        request.setAttribute("messageBoolean", true);
                        message = "Error: Category already exists";
                        request.setAttribute("message", message);
                        Logger.getLogger(CategoryService.class.getName()).log(Level.INFO, "Admin tried to create an existing category at {0}", id);

                        getServletContext().getRequestDispatcher("/WEB-INF/category_create.jsp")
                                .forward(request, response);
                        return;
                    } else {
                        cs.insert(categorynum, cName);   
                        response.sendRedirect("admin");
                        return;
                    }
                } catch (Exception ex) {
                }
            } else if (type.equals("item")) {
                HttpSession session = request.getSession();

                int categoryId = Integer.parseInt(request.getParameter("category"));
                String itemName = request.getParameter("itemName");
                String temp = request.getParameter("price");
                String owner = (String) session.getAttribute("email");
                
                List<Category> categories = null;

                try {
                    double price = Double.parseDouble(temp);
                    
                    CategoryService cs = new CategoryService();
                    ItemService is = new ItemService();
                    
                    categories = cs.getAll();
                    
                    request.setAttribute("categories", categories);

                        if (itemName == null || itemName.equals("")
                                || temp == null || temp.equals("")) {
                            request.setAttribute("messageBoolean", true);
                            message = "Error: Invalid name, or price";
                            request.setAttribute("message", message);
                            Logger.getLogger(UserService.class.getName()).log(Level.INFO, "User tried to add a new item with invalid variables {0}", owner);
                            
                            getServletContext().getRequestDispatcher("/WEB-INF/item_create.jsp")
                                    .forward(request, response);
                        } else {
                                if (price < 0) {
                                request.setAttribute("messageBoolean", true);
                                message = "Error: Price cannot be a negative";
                                request.setAttribute("message", message);
                                Logger.getLogger(UserService.class.getName()).log(Level.INFO, "User tried to add an item with a negative number {0}", owner);

                                getServletContext().getRequestDispatcher("/WEB-INF/item_create.jsp")
                                        .forward(request, response);
                            } else {
                                is.insert(categoryId, itemName, price, owner);
                                response.sendRedirect("home");
                            }
                        }

                } catch (Exception ex) {
                    request.setAttribute("messageBoolean", true);
                    message = "Error: Invalid name, or price";
                    request.setAttribute("message", message);
                    Logger.getLogger(UserService.class.getName()).log(Level.INFO, "User tried to add a new item with invalid variables {0}", owner);
                            
                    getServletContext().getRequestDispatcher("/WEB-INF/item_create.jsp")
                            .forward(request, response);
                }
            }
        } else if (action.equals("Return")) {
            String role = request.getParameter("role");

            if (role.equals("admin")) {
                response.sendRedirect("admin");
            } else if (role.equals("user")) {
                response.sendRedirect("home");
            }
        }    
    }
}
