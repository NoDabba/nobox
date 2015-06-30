/**
 * 
 */
package com.wesimplify.nodabba.presentation.restaurant;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wesimplify.nodabba.common.BookingRequest;
import com.wesimplify.nodabba.common.EnhancedException;
import com.wesimplify.nodabba.common.WebUtils;

/**
 * @author sdoddi
 * Main controller for all Restaurant inquiry. 
 */
@Controller
public class RestaurantInquiryController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestaurantInquiryController.class);

	@Autowired
	private RestaurantInquiryViewBuilder restaurantInquiryViewBuilder;
	

	/**
	 * This method will delegate the request to display the Restaurant details page with the provided id. If the
	 * details not valid or not available, this page will forward the request to landing page (/start)
	 * @param restaurantId
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/restaurant/inquiry/{restaurantId}", method = RequestMethod.GET)
	public String searchgetRestaurantbyID(@PathVariable String restaurantId, HttpServletRequest request, Model model) {
		
		// 1. check if the provided restaurantId is not null
		if (StringUtils.isEmpty(restaurantId))
		{
			logger.error("Restaurant Inquiry unable to get data as id is null or empty");
			return "formard:/start";
		}
		
		// 2. get the BookingRequest from session if available otherwise get the default BookingRequest 
		BookingRequest bookingRequest = WebUtils.getBookingRequestFromSession(request);
		RestaurantInquiryViewBean viewBean = null;
		try {
			// 3. get the view bean from the view builder
			viewBean = restaurantInquiryViewBuilder.getRestaurantDetailsById(restaurantId, bookingRequest, true);
		} catch (EnhancedException e) {
			logger.error("error getting Restaurant details for {} ", restaurantId, e);
		}
		
		//4. get the view to model if it is not null. other wise show the landing screen
		if (viewBean != null) {
			model.addAttribute("RestaurantInquiryViewBean", viewBean);
		}
		else
		{
			logger.error("Restaurant Inquiry view bean is null for given id {}", restaurantId);
			return "formard:/start";
		}
		return "restaurantdetails";
	}
	
}
