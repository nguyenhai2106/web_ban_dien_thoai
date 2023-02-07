package admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import common.PageInfo;
import common.PageType;
import dao.AccountDao;
import model.Account;

/**
 * Servlet implementation class UserManagementServlet
 */
@WebServlet({ "/UserManagement", "/UserManagement/edit", "/UserManagement/update", "/UserManagement/delete",
		"/UserManagement/reset" })
public class UserManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserManagementServlet() {
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
		String admin = (String) session.getAttribute("admin");
		String url = request.getRequestURL().toString();
		if (admin != null) {
			Account account = null;
			if (url.contains("delete")) {
				delete(request, response);
			} else if (url.contains("edit")) {
				edit(request, response);
			} else if (url.contains("reset")) {
				account = new Account();
				request.setAttribute("user", account);
			}
			findAll(request, response);
			PageInfo.prepareAndForwardAdmin(request, response, PageType.USERS_MANAGEMENT_PAGE);
		} else {
			response.sendRedirect("/PRJ321x_Assignment_3_FX17393/Home");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = request.getRequestURL().toString();
		if (url.contains("delete")) {
			delete(request, response);
		} else if (url.contains("update")) {
			update(request, response);
		} else if (url.contains("reset")) {
			request.setAttribute("account", new Account());
		}
		findAll(request, response);
		PageInfo.prepareAndForwardAdmin(request, response, PageType.USERS_MANAGEMENT_PAGE);
	}

	protected void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccountDao accountDao = new AccountDao();
		try {
			List<Account> users = accountDao.findAll();
			request.setAttribute("users", users);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String userMail = request.getParameter("userMail");
			AccountDao dao = new AccountDao();
			Account account = dao.findById(userMail);
			request.setAttribute("account", account);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Account account = new Account();
			BeanUtils.populate(account, request.getParameterMap());
			AccountDao dao = new AccountDao();
			String userMail = request.getParameter("userMail");
			account.setPassword(dao.findById(userMail).getPassword());
			dao.update(account);
			request.setAttribute("message", "User Updated!!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String userMail = request.getParameter("userMail");
			AccountDao dao = new AccountDao();
			dao.delete(userMail);
			request.setAttribute("message", "User deleted!!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

}
