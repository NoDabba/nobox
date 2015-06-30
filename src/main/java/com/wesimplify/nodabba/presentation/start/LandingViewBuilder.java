/**
 * 
 */
package com.wesimplify.nodabba.presentation.start;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.wesimplify.nodabba.common.EnhancedException;
import com.wesimplify.nodabba.common.SocialConstants;
import com.wesimplify.nodabba.common.SocialUtil;
import com.wesimplify.nodabba.common.WebUtils;
import com.wesimplify.nodabba.domain.restaurant.MainPageOffer;
import com.wesimplify.nodabba.domain.social.SocialUser;
import com.wesimplify.nodabba.primitives.City;
import com.wesimplify.nodabba.service.primitives.PrimitivesGateway;
import com.wesimplify.nodabba.service.restaurant.RestaurantGateway;

/**
 * @author sdoddi
 * Helper class for LandingController to build the view
 */
@Component
public class LandingViewBuilder {
	
	private static final Logger logger = LoggerFactory.getLogger(LandingViewBuilder.class);
	
	@Inject
    @Qualifier("primitivesGateway")
	private PrimitivesGateway primitivesGateway;
	
	@Inject
    @Qualifier("restaurantGateway")
	private RestaurantGateway restaurantGateway;
	
	
	private final String COOKIE_NAME = "nodabbacity";
	
	/**
	 * This method will return all configured cities
	 * 
	 */
	public Map<String, Object> buildLandingPage(HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> model = new HashMap<String, Object>();
		//get restaurant unique cities
		List<City> uniqueCities = null;
		List<MainPageOffer> mainPageOffers = null;
		try {
			uniqueCities = primitivesGateway.getUniqueCities();
			model.put("cities", uniqueCities);
		}
		catch (EnhancedException ex) {
			logger.error("error getting restaurant unique cities from business service", ex);
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("List of Cities {} ", (uniqueCities != null ? uniqueCities.size() : "null"));
		}
		
		//set default city
		String defaultCity = getCookieValue(uniqueCities, request, response);
		if (defaultCity != null) {
			model.put("defaultSelectedCity", defaultCity);
		}
		
		//get the main page offers
		try {
			if (defaultCity == null) {
				defaultCity = uniqueCities != null && uniqueCities.size() > 0 ? uniqueCities.get(0).getId() : "HYD";
			}
			mainPageOffers = restaurantGateway.getBestOffersforHomePageByCityId(defaultCity);
			model.put("offers", mainPageOffers);
		}
		catch (EnhancedException ex) {
			logger.error("error getting restaurant unique cities from business service", ex);
		}
		
		//set the social user details if available
		SocialUser user = SocialUtil.getSocialUser(request);
		if (user != null) {
			model.put("socialUserName", SocialUtil.getSocialUserName(user));
			model.put("socialUserProfileImage", user.getAdditionalProperties().get(SocialConstants.USER_IMAGE_URL));
		}
		
		return model;
	}
	
	/**
	 * This method will create default city to cookie if not available, otherwise it will read city from cookie and use that as a default city
	 * @param uniqueCities
	 * @param request
	 * @param response
	 */
	public String getCookieValue(List<City> uniqueCities, HttpServletRequest request, HttpServletResponse response) {
		String defaultCityID = WebUtils.getCookieValue(COOKIE_NAME, request);
		if (logger.isDebugEnabled()) {
			logger.debug("Default city found from cookie {} ", defaultCityID);
		}
		
		if (StringUtils.isEmpty(defaultCityID)) {
			if (uniqueCities != null && uniqueCities.size() > 0) {
				City city = uniqueCities.get(0);
				defaultCityID = city.getId();
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Getting and setting default city to cookie {} ", defaultCityID);
			}
			WebUtils.setCookie(COOKIE_NAME, defaultCityID, response);
		}
		return defaultCityID;
	}
}

