/**
 * 
 */
package com.wesimplify.nodabba.presentation.partners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author sdoddi
 * This Controller is created to service different kids of tasks for Partners. Other than sign-in url mapping all other urls mentioned in this controller
 * will be prefixed with <b>/secure</b>. Spring Security filter will not allow unauthorized access to <b>/secure</b> url's.
 */
@Controller
@RequestMapping("/partners")
public class PartnersController {
	
	private static final Logger logger = LoggerFactory.getLogger(PartnersController.class);

	
	/**
	 * This will show the Restauranter sign-in page if restauranter person is not logged in
	 * @return String
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String showLoginPage() {
		if (logger.isDebugEnabled())
		{
			logger.debug("<Partners> Showing the Restauranter Login page");
		}
		return "/partners/login";
	}
	
	/**
	 * This will show the Restauranter landing page (restauranter/index.jsp) after successful authentication and authorization.
	 * @return String
	 */
	@RequestMapping(value = "/secure/index", method = RequestMethod.GET)
	public String showIndexPage() {
		if (logger.isDebugEnabled())
		{
			logger.debug("<partners> Showing the Restauranter Index page");
		}
		return "/partners/index";
	}
}
