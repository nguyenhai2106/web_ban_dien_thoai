package sites.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.PageInfo;
import common.PageType;
import dao.ProductDao;
import model.Product;

@WebServlet("/Home")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String doSearch = request.getParameter("doSearch");
		if (doSearch != null) {
			findBySearch(request, response);
		} else {
			findAll(request, response);
		}
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_HOME_PAGE);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	protected void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDao dao = new ProductDao();
		long countAllProduct = dao.countAllProduct();
		int maxPages = (int) Math.ceilDiv(countAllProduct, 6);
		String currentPage = request.getParameter("currentPage");

		int currentPageData = 0;
		if (currentPage != null) {
			currentPageData = Integer.parseInt(currentPage) - 1;
		}
		if (currentPageData < 0) {
			currentPageData = 0;
		} else if (currentPageData >= maxPages) {
			currentPageData = maxPages - 1;
		}

		int pageSize = 6;

		try {
			List<Product> products = dao.findAllPagination(currentPageData, pageSize);
			request.setAttribute("currentPage", currentPageData + 1);
			request.setAttribute("products", products);
			request.setAttribute("maxPages", maxPages);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
	}

	protected void findBySearch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String productBrand = request.getParameter("productBrand");
		String productName = request.getParameter("productName");
		ProductDao dao = new ProductDao();
		long countAllProduct = dao.countAllSearch(productBrand, productName);
		int maxPages = (int) Math.ceilDiv(countAllProduct, 6);
		String currentPage = request.getParameter("currentPage");

		int currentPageData = 0;
		if (currentPage != null) {
			currentPageData = Integer.parseInt(currentPage) - 1;
		}
		if (currentPageData >= maxPages) {
			currentPageData = maxPages - 1;
		}

		if (currentPageData < 0) {
			currentPageData = 0;
		}

		int pageSize = 6;
		try {
			List<Product> products = dao.findProducts(productBrand, productName, currentPageData, pageSize);
			if (products.size() == 0) {
				request.setAttribute("errorSearch", "Sorry, no products found!");
			}
			request.setAttribute("currentPage", currentPageData + 1);
			request.setAttribute("products", products);
			request.setAttribute("maxPages", maxPages);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
	}

}
