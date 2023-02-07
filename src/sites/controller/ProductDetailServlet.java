package sites.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageInfo;
import common.PageType;
import dao.ProductDao;
import model.Product;

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/ProductDetail")
public class ProductDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductDetailServlet() {
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
		findById(request, response);
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_PRODUCT_DETAIL_PAGE);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void findById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int productId = Integer.parseInt(request.getParameter("productId"));
			ProductDao dao = new ProductDao();
			Product product = dao.findById(productId);
			request.setAttribute("product", product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
