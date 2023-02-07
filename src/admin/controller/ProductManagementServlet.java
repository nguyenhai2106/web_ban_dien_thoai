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
import dao.ProductDao;
import model.Account;
import model.Product;

/**
 * Servlet implementation class ProductManagementServlet
 */
@WebServlet({ "/ProductManagement", "/ProductManagement/reset", "/ProductManagement/delete",
		"/ProductManagement/update", "/ProductManagement/create", "/ProductManagement/edit" })
public class ProductManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductManagementServlet() {
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
			PageInfo.prepareAndForwardAdmin(request, response, PageType.PRODUCTS_MANAGEMENT_PAGE);
		} else {
			response.sendRedirect("/PRJ321x_Assignment_3_FX17393/Home");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String url = request.getRequestURL().toString();
		if (url.contains("create")) {
			insert(request, response);
		} else if (url.contains("delete")) {
			delete(request, response);
		} else if (url.contains("update")) {
			update(request, response);
		} else if (url.contains("reset")) {
			request.setAttribute("product", new Product());
		}
		findAll(request, response);
		PageInfo.prepareAndForwardAdmin(request, response, PageType.PRODUCTS_MANAGEMENT_PAGE);
	}

	protected void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDao productDao = new ProductDao();
		try {
			List<Product> products = productDao.findAll();
			request.setAttribute("products", products);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
	}

	protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String productId = request.getParameter("productId");
			ProductDao dao = new ProductDao();
			Product product = dao.findById(Integer.parseInt(productId));
			request.setAttribute("product", product);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Product product = new Product();
			BeanUtils.populate(product, request.getParameterMap());
			ProductDao dao = new ProductDao();
			dao.update(product);
			request.setAttribute("message", "Product Updated!!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int productId = Integer.parseInt(request.getParameter("productId"));
			ProductDao dao = new ProductDao();
			dao.delete(productId);
			request.setAttribute("message", "Product deleted!!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

	protected void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Product product = new Product();

			product.setProductImgSource(request.getParameter("productImgSource"));

			product.setProductBrand(request.getParameter("productBrand"));

			product.setProductDes(request.getParameter("productDes"));

			product.setProductName(request.getParameter("productName"));

			product.setProductPrice(Float.parseFloat(request.getParameter("productPrice")));

			product.setProductType(request.getParameter("productType"));

			ProductDao dao = new ProductDao();
			dao.insert(product);
			request.setAttribute("message", "User inserted!!!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}
	}

}
