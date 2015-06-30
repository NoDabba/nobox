/**
 * 
 */
package com.wesimplify.nodabba.common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author sdoddi
 * Helper class to support all utilities for presentation tier
 */
public final class WebUtils {

	/**
	 * Gets the cookie value from given cookie name from servlet request. If not found, this will return null
	 * @param searchCookieName
	 * @param request
	 * @return String
	 */
	public static String getCookieValue(String searchCookieName, HttpServletRequest request) {
		String value = null;
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (searchCookieName.equals(cookie.getName())) {
					value = cookie.getValue();
				}
			}
		}
		return value;
	}
	
	/**
	 * This method will set the cooke to http servlet response
	 * @param cookieName
	 * @param cookieValue
	 * @param request
	 */
	public static void setCookie(String cookieName, String cookieValue, HttpServletResponse response)  {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(Integer.MAX_VALUE);
		response.addCookie(cookie);
	}
	
	/**
	 *  Booking request will be stored in the session. if not available or session expired, this method will
	 *  create default BookingRequest 
	 * @param request
	 * @return BookingRequest
	 */
	public static BookingRequest getBookingRequestFromSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		BookingRequest bookingRequest = (BookingRequest) session.getAttribute(BookingRequest.class.getName());
		if (bookingRequest == null) {
			bookingRequest = BookingRequestUtil.defaultBookingRequest();
			session.setAttribute(BookingRequest.class.getName(), bookingRequest);
		}
		return bookingRequest;
	}
	
	/**
	 * helper method to create set the BookingRequest to session. if session is not available, this will create new session and set Booking Request to it.
	 * @param bookingRequest
	 * @param request
	 */
	public static void setBookingRequestFromSession(BookingRequest bookingRequest, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (bookingRequest == null) {
			bookingRequest = BookingRequestUtil.defaultBookingRequest();
		}
		session.setAttribute(BookingRequest.class.getName(), bookingRequest);

	}
	
}
