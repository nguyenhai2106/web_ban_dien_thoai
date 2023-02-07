package sites.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;

@WebServlet("/UpdateCart")
public class UpdateCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] arrayProductIds = request.getParameterValues("productId");
		String[] arrayQuantities = new String[arrayProductIds.length];

		for (int i = 1; i <= arrayQuantities.length; i++) {
			String productQuantity = request.getParameter("quantity" + i);
			arrayQuantities[i - 1] = productQuantity;
		}
		int[] productIds = Arrays.stream(arrayProductIds).mapToInt(Integer::parseInt).toArray();
		int[] quantities = Arrays.stream(arrayQuantities).mapToInt(Integer::parseInt).toArray();
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		cart.updateItem(productIds, quantities);
		String url = request.getContextPath().concat("/Cart");
		response.sendRedirect(url);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
