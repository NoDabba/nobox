/**
 * 
 */
package com.wesimplify.nodabba.presentation.start;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sdoddi
 * This is a landing controller that will display the index page. User logging in from any social media website will be landed here after
 * successful OAUTH 2 authentication. This will load any restaurant related data to model and forward the request to index page. 
 */
@Controller
public class LandingController {

	private static final Logger logger = LoggerFactory.getLogger(LandingController.class);

	@Autowired
	private LandingViewBuilder landingViewBuilder;
	/**
	 * This will delegate the request to helper to load the restaurant data and set the data back to Model
	 * @param model
	 * @return String
	 */
	@RequestMapping("/start")
	public String build(Model model, HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled())
		{
			logger.debug("index controller is delegating the request to Helper..");
		}
		Map<String,Object> data = landingViewBuilder.buildLandingPage(request, response);
		model.addAllAttributes(data);
		return "index";
	}


	/**
	 * This will delegate the request to helper to load the restaurant data and set the data back to Model
	 * @param model
	 * @return String
	 */
	@RequestMapping("/")
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
		return build(model, request, response);
	}
}
