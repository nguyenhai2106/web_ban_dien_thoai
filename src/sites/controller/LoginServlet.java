package sites.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.CookieUtils;
import common.PageInfo;
import common.PageType;
import dao.AccountDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userLog = (String) session.getAttribute("userLog");
		if (userLog != null && !userLog.equals("")) {
			request.setAttribute("message", "Login Successfully!");
			response.sendRedirect("/PRJ321x_Assignment_3_FX17393/Home");
			return;
		}

		String userMail = CookieUtils.get("userMail", request);
		request.setAttribute("userMail", userMail);

		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			request.getSession(true).invalidate();
			String regexMail = "^[A-Z0-9_a-z]+@[A-Z0-9\\.a-z]+\\.[A-Za-z]{2,6}$";
			String regex = "[a-zA-Z0-9_!@#$%^&*]+";
			String userMail = request.getParameter("userMail");
			String password = request.getParameter("password");
			String remember = request.getParameter("remember");

			if (remember != null) {
				System.out.println(remember);
				CookieUtils.add("userMail", userMail, 2, response);
			} else {
				CookieUtils.add("userMail", userMail, 0, response);
			}

			HttpSession session = request.getSession(true);
			if (!password.matches(regex) || !userMail.matches(regexMail)) {
				session.setAttribute("error", "Invalid Syntax!");
				PageInfo.prepareAndForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
			}

			AccountDao dao = new AccountDao();
			boolean adminLoginResult = dao.checkAdminLogin(userMail, password);
			boolean loginResult = dao.checkLogin(userMail, password);

			if (adminLoginResult) {
				request.getSession().setAttribute("userLog", userMail);
				request.getSession().setAttribute("admin", userMail);
				request.getRequestDispatcher("/UserManagement").forward(request, response);
			} else if (loginResult) {
				request.getSession().setAttribute("userLog", userMail);
				request.getRequestDispatcher("/Account").forward(request, response);
			} else {
				session.setAttribute("error", "Wrong email or password!");
				PageInfo.prepareAndForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
			}
		} catch (NullPointerException e) {
			PageInfo.prepareAndForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
