package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import services.CategoryService;
import services.ItemService;
import services.RoleService;
import services.UserService;

/**
 *
 * @author johnn
 */
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");
        String action = request.getParameter("action");
        String userRole = request.getParameter("role");
        
        if (userRole.equals("admin")) {
            if (action.equals("Finish")) {
                if (type.equals("category")) {
                    CategoryService cs = new CategoryService();

                    String categoryName = request.getParameter("categoryName");
                    int id = Integer.parseInt(request.getParameter("id"));

                    try {
                        cs.update(id, categoryName);
                    } catch (Exception ex) {
                    }
                } else if (type.equals("user")) {
                    String email = request.getParameter("email");
                    String active = request.getParameter("active");
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String password = request.getParameter("password");
                    String temp = request.getParameter("role");
                    int role = Integer.parseInt(temp);
                    boolean status;

                    if (active != null && active.equals("checked")) {
                        status = true;
                    } else {
                        status = false;
                    }

                    UserService us = new UserService();

                    try {
                        us.update(email, status, firstName, lastName, password, role);
                    } catch (Exception ex) {
                    }
                }
            }
            
            response.sendRedirect("admin");
        } else if (userRole.equals("user")) {
            if (action.equals("Finish")) {
                String temp = request.getParameter("itemId");
                int itemId = Integer.parseInt(temp);
                
                temp = request.getParameter("category");
                int categoryId = Integer.parseInt(temp);
                
                String itemName = request.getParameter("itemName");
                
                temp = request.getParameter("price");
                double price = Double.parseDouble(temp);
                
                String owner = request.getParameter("owner");
                
                ItemService is = new ItemService();
                
                try {
                    is.update(itemId, categoryId, itemName, price, owner);
                } catch (Exception ex) {
                }
            } 
            
            response.sendRedirect("home");
        }
    }
}
