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
import models.Item;
import models.Role;
import models.User;
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
                    String temp = request.getParameter("roleId");
                    int role = Integer.parseInt(temp);
                    boolean status;

                    if (active.equals("checked")) {
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
                    HttpSession session = request.getSession();
                    
                    String email = (String) session.getAttribute("email");
                    
                    request.setAttribute("messageBoolean", true);
                    request.setAttribute("message", "Invalid field at category, name, or price");
                    Logger.getLogger(UserService.class.getName()).log(Level.INFO, "There was an invalid input in one of the fields {0}", email);

                    getServletContext().getRequestDispatcher("/WEB-INF/item_edit.jsp")
                            .forward(request, response);
                    return;
                }
                
                response.sendRedirect("home");
            } else if (action.equals("Apply")) {
                String email = request.getParameter("email");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String password = request.getParameter("password");
                int roleId = Integer.parseInt(request.getParameter("roleId"));
                
                UserService us = new UserService();
                
                HttpSession session = request.getSession();
                
                try { us.update(email, true, firstName, lastName, password, roleId);
                } catch (Exception ex) {
                }
                
                session.setAttribute("firstName", firstName);
                session.setAttribute("email", email);
                session.setAttribute("lastName", lastName);
                session.setAttribute("password", password);
                
                getServletContext().getRequestDispatcher("/WEB-INF/user_settings.jsp")
                        .forward(request, response);
            } else if (action.equals("Return")) {
                response.sendRedirect("home");
            } else if (action.equals("Deactivate")) {
                HttpSession session = request.getSession();
                
                String email = (String) session.getAttribute("email");
                
                UserService us = new UserService();
                
                try {
                    User user = us.get(email);
                    
                    us.update(email, false, user.getFirstName(), user.getLastName(), user.getPassword(), user.getRole().getRoleId());
                } catch (Exception ex) {
                }
                
                response.sendRedirect("login");
            }
        } 
    }
}
