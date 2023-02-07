package sites.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.PageInfo;
import common.PageType;
import dao.OrderDao;
import dao.OrderDetailDao;
import dao.ProductDao;
import model.Cart;
import model.Item;
import model.Order;
import model.Orders_detail;
import model.Orders_detailPK;
import model.Product;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/Checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_CHECKOUT_PAGE);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Order order = new Order();
		String orderDiscountCode = request.getParameter("orderDiscountCode");
		String userMail = request.getParameter("userMail");
		String userAddress = request.getParameter("userAddress");
		order.setOrderAddress(userAddress);
		order.setUserMail(userMail);
		order.setOrderDiscountCode(orderDiscountCode);
		HttpSession session = request.getSession();
		OrderDao dao = new OrderDao();
		Order createdOrder = dao.createOrder(order);

		Cart cart = (Cart) session.getAttribute("cart");
		ProductDao productDao = new ProductDao();
		OrderDetailDao orderDetailDao = new OrderDetailDao();
		List<Orders_detail> ordersDetailList = new ArrayList<>();

		for (Item item : cart.getItems()) {
			Product product = productDao.findById(item.getProductId());
			Orders_detailPK orders_detailPK = new Orders_detailPK();
			orders_detailPK.setOrderId(createdOrder.getOrderId());
			orders_detailPK.setProductId(product.getProductId());
			Orders_detail ordersDetail = new Orders_detail();
			ordersDetail.setId(orders_detailPK);
			ordersDetail.setAmountProduct(item.getAmountProduct());
			ordersDetail.setPriceProduct(item.getProductPrice());
			ordersDetailList.add(ordersDetail);
		}

		orderDetailDao.createOrderDetail(ordersDetailList);
		response.sendRedirect("/PRJ321x_Assignment_3_FX17393/Home");
	}

}
