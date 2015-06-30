/**
 * 
 */
package com.wesimplify.nodabba.presentation.booking;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wesimplify.nodabba.common.StringUtils;

/**
 * @author sdoddi
 *
 */
@Controller
public class BookingController {

	/**
	 * this is a state less helper to support all restaurant tasks
	 */
	@Autowired
	private BookingViewBuilder bookingViewBuilder;
	
	private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
	

	/**
	 * This method is first entry point to get the restaurants for given search criteria.
	 * @param name
	 * @return Map
	 */
	@RequestMapping(value = "/restaurant/{restaurantId}/offer/{offerId}/booking", method = RequestMethod.GET) 
	 public String showBookingConfirmationScreen(@PathVariable String restaurantId, @PathVariable String offerId, HttpServletRequest request, ModelMap model) {
		if (StringUtils.isBlank(restaurantId) || StringUtils.isBlank(offerId)) {
			model.put("error", "Invalid information. Please select offer.");
			return "forward:/start";
		}
		
		
		
		return null;
	}
}
