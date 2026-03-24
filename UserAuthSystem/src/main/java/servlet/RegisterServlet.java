package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.UserDAOImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import util.PasswordUtil;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 229106605262686383L;
	private static final Logger logger = Logger.getLogger(RegisterServlet.class.getName());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			String name = req.getParameter("name");
			String ageParam = req.getParameter("age");
			String mobileNumber = req.getParameter("mobile");
			String email = req.getParameter("email");
			String password = req.getParameter("password");

			if (name == null || name.isBlank() || ageParam == null || ageParam.isBlank() || mobileNumber == null
					|| mobileNumber.isBlank() || email == null || email.isBlank() || password == null
					|| password.isBlank()) {
				resp.sendRedirect("error.html");
			}
			// Validate age is a real number before parsing
			int age;
			try {
				age = Integer.parseInt(ageParam);
				if (age < 18 || age > 120) {
					resp.sendRedirect("error.html");
					return;
				}
			} catch (NumberFormatException e) {
				logger.log(Level.WARNING, "Invalid age input: " + ageParam);
				resp.sendRedirect("error.html");
				return;
			}
			// Validate mobile number format (10 digits)
			if (!mobileNumber.matches("\\d{10}")) {
				resp.sendRedirect("error.html");
				return;
			}

			// Validate email format
			if (!email.matches("^[\\w.+\\-]+@[\\w\\-]+\\.[a-zA-Z]{2,}$")) {
				resp.sendRedirect("error.html");
				return;
			}
			// Validate password Strength
			if (password.length() < 8) {
				resp.sendRedirect("error.html");
				return;
			}
			
			try {
			String hashPassword = PasswordUtil.hashPassword(password);
			User user = new User(name, age, mobileNumber, email, hashPassword);

			UserDAOImpl userDAOImpl = new UserDAOImpl();
			int result = userDAOImpl.registerUser(user);
			if (result > 0) {
				resp.sendRedirect("success.html");
			} else {
				resp.sendRedirect("error.html");
			}
		} catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
			System.out.println(e.getMessage());
			resp.sendRedirect("error.html");
		}
	}
}
