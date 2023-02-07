package common;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;

public class PageInfo {
	public static Map<PageType, PageInfo> pageRoute = new HashedMap();

	static {
		pageRoute.put(PageType.SITE_LOGIN_PAGE, new PageInfo("Login Page", "/sites/users/login.jsp", null));
		pageRoute.put(PageType.SITE_REGISTRATION_PAGE,
				new PageInfo("Registration Page", "/sites/users/registration.jsp", null));
		pageRoute.put(PageType.SITE_HOME_PAGE, new PageInfo("Home Page", "/home/home.jsp", null));
		pageRoute.put(PageType.SITE_PRODUCT_DETAIL_PAGE,
				new PageInfo("Product Detail Page", "/sites/products/productDetail.jsp", null));
		pageRoute.put(PageType.SITE_CART_PAGE, new PageInfo("Shopping Cart Page", "/sites/users/cart.jsp", null));
		pageRoute.put(PageType.SITE_CHECKOUT_PAGE, new PageInfo("Checkout Page", "/sites/users/checkout.jsp", null));

		pageRoute.put(PageType.SITE_ACCOUNT_PAGE, new PageInfo("Account Page", "/sites/users/account.jsp", null));

		pageRoute.put(PageType.USERS_MANAGEMENT_PAGE, new PageInfo("Dashboard Page", "/admin/users/users.jsp", null));
		pageRoute.put(PageType.PRODUCTS_MANAGEMENT_PAGE,
				new PageInfo("Dashboard Page", "/admin/products/products.jsp", null));
	}

	private String title;
	private String contentUrl;
	private String scriptUrl;

	public PageInfo(String title, String contentUrl, String scriptUrl) {
		super();
		this.title = title;
		this.contentUrl = contentUrl;
		this.scriptUrl = scriptUrl;
	}

	public static void prepareAndForwardSite(HttpServletRequest request, HttpServletResponse response,
			PageType pageType) throws ServletException, IOException {
		PageInfo page = pageRoute.get(pageType);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/sites/layout.jsp").forward(request, response);
	}

	public static void prepareAndForwardAdmin(HttpServletRequest request, HttpServletResponse response,
			PageType pageType) throws ServletException, IOException {
		PageInfo page = pageRoute.get(pageType);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/layout.jsp").forward(request, response);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public String getScriptUrl() {
		return scriptUrl;
	}

	public void setScriptUrl(String scriptUrl) {
		this.scriptUrl = scriptUrl;
	}

}
