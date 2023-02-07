package sites.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.PageInfo;
import common.PageType;
import dao.AccountDao;
import dao.OrderDao;
import model.Account;
import model.Order;

/**
 * Servlet implementation class Account
 */
@WebServlet("/Account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userLog = (String) session.getAttribute("userLog");
		String display = request.getParameter("display");
		Account account = null;
		AccountDao dao = new AccountDao();
		OrderDao orderDao = new OrderDao();
		if (userLog != null) {
			account = dao.findById(userLog);
			request.setAttribute("account", account);
		} else {
			PageInfo.prepareAndForwardSite(request, response, PageType.SITE_LOGIN_PAGE);
		}
		if (display != null && display.equals("hisPurchase")) {
			request.setAttribute("display", "hisPurchase");
			List<Order> orders = orderDao.findByUserMail("Order.findByUserMail", userLog);
			request.setAttribute("orders", orders);
		} else {
			request.setAttribute("display", "account");
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_ACCOUNT_PAGE);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
