/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.UserService;

/**
 *
 * @author johnn
 */
public class RegisterSevlet extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action.equals("Return to Login")) {
            response.sendRedirect("login");
        } else if (action.equals("Register")) {
            
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String path = request.getContextPath();

            if (firstName == null || firstName.equals("") 
                    || lastName == null || lastName.equals("") 
                    || email == null || email.equals("") 
                    || password == null || password.equals("")) {
                request.setAttribute("email", email);
                request.setAttribute("firstName", firstName);
                request.setAttribute("lastName", lastName);
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp")
                        .forward(request, response);
            }

            UserService us = new UserService();
            
            try {
                if (us.get(email) != null) {
                    String message = "";
                    request.setAttribute("message", message);
                    request.setAttribute("email", email);
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                            .forward(request, response);
                } else {
                    User user = us.register(firstName, lastName, email, password, path);

                    HttpSession session = request.getSession();
                    session.setAttribute("email", email);

                    if (user.getRole().getRoleId() == 1) {
                        response.sendRedirect("admin");
                    } else {
                        response.sendRedirect("home");
                    }
                }
            } catch (Exception ex) {
            }
        }
    }
}
