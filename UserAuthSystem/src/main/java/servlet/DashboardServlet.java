package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 906634068044750562L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession(false);

		if (session == null || session.getAttribute("userName") == null) {
			resp.sendRedirect("login.html");
			return;
		}
		// Prevent dashboard from being cached
		resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
		resp.setHeader("Pragma", "no-cache");
		resp.setHeader("Expires", "0");
		String userName = (String) session.getAttribute("userName");
		resp.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = resp.getWriter()) {
			out.println("<DOCTYPE html>");
			out.println("<html lang='en'>");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<title>Dashboard</title>");
			out.println("<style>");
			out.println("body{font-family: Segoe UI, sans-serif; margin: 40px;}");
			out.println("h2{color:rbb(27,38,141);}");
			out.println("a{color:rgb(27,38,141);}");
			out.println("</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h2>Welcome, " + escapeHTMl(userName) + "!</h2>");
			out.println("<a href='logout'>Logout</a>");
			out.println("</body>");
			out.println("</html>");
		}

	}

	private String escapeHTMl(String input) {
		if (input == null)
			return "";
		return input.replace("&", "&amp;").replace("<", "&lt").replace(">", "&gt;").replace("\"", "&quot;").replace("'",
				"&#x27");

	}
}
