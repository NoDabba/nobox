/**
 * 
 */
package com.wesimplify.nodabba.presentation.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author sdoddi
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminLandingController {

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String showLoginPage() {
		return "/admin/login";
	}
	
	@RequestMapping(value = "/secure/index", method = RequestMethod.GET)
	public String showIndexPage() {
		return "/admin/index";
	}
}
