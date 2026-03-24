package servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;
import util.PasswordUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 7571181459811346119L;
	private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());
	private UserDAO userDAO = new UserDAOImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("login.html");
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String email = req.getParameter("email");
	    String password = req.getParameter("password");

	    if (email == null || email.isBlank() || password == null || password.isBlank()) {
	        resp.sendRedirect(req.getContextPath() + "/error.html");
	        return;
	    }

	    try {
	        User u = userDAO.validateUser(email);
	        if (u != null && PasswordUtil.verifyPassword(password, u.getPassword())) {
	            HttpSession existing = req.getSession(false);
	            if (existing != null) {
	                existing.invalidate();
	            }
	            HttpSession session = req.getSession(true);
	            session.setAttribute("userId", u.getId());
	            session.setAttribute("userName", u.getName());
	            session.setMaxInactiveInterval(15 * 60);

	            resp.sendRedirect(req.getContextPath() + "/dashboard"); // ← fixed
	        } else {
	            resp.sendRedirect(req.getContextPath() + "/error.html");
	        }
	    } catch (Exception e) {
	        logger.log(Level.SEVERE, "Login error for email: " + email, e);
	        resp.sendRedirect(req.getContextPath() + "/error.html");
	    }
	}
}
