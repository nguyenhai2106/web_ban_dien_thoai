package common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	public static String get(String userMail, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equalsIgnoreCase(userMail)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}

	public static Cookie add(String userMail, String value, int hour, HttpServletResponse response) {
		Cookie cookie = new Cookie(userMail, value);
		cookie.setMaxAge(3600 * hour);
		cookie.setPath("/");
		response.addCookie(cookie);
		return cookie;
	}
}
