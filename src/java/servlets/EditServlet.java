package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import services.CategoryService;
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
        
        response.sendRedirect("admin");
    }
}
