package servlets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        
        boolean messageBoolean = false;
        request.setAttribute("messageBoolean", messageBoolean);
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        //Handles if user wants to register
        if (action.equals("Register")) {
                response.sendRedirect("register");
        } else {
         
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String path = request.getContextPath();
            String message = "Invalid email or password";

            if (password == null || password.equals("")) {
                request.setAttribute("messageBoolean", true);
                request.setAttribute("message", message);
                if (email == null || email.equals("")) {
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                } else {
                    request.setAttribute("email", email);
                    getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                }
            }

            UserService us = new UserService();
            User user = us.login(email, password, path);

            if (user == null) {
                request.setAttribute("messageBoolean", true);
                request.setAttribute("message", "This account does not exist");
                request.setAttribute("email", email);
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            } else if (!user.getActive()) {
                request.setAttribute("messageBoolean", true);
                request.setAttribute("message", "The account " + email + " is disabled");
                Logger.getLogger(UserService.class.getName()).log(Level.FINE, "Disabled user tried to access their account {0}", email);
                
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                        .forward(request, response);
                return;
            }
            
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            
            if (user.getRole().getRoleId() == 1) {
                response.sendRedirect("admin");
            } else {
                response.sendRedirect("home");
            }
        }   
    }
}
