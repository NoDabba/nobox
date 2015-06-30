/**
 * 
 */
package com.wesimplify.nodabba.presentation.search;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wesimplify.nodabba.common.BookingRequest;
import com.wesimplify.nodabba.common.BookingRequestUtil;
import com.wesimplify.nodabba.common.DateUtils;
import com.wesimplify.nodabba.common.EnhancedException;
import com.wesimplify.nodabba.common.FailureMessageBean;
import com.wesimplify.nodabba.common.FilterResults;
import com.wesimplify.nodabba.common.KeyValueHolder;
import com.wesimplify.nodabba.common.Message;
import com.wesimplify.nodabba.common.MessageType;
import com.wesimplify.nodabba.common.StringUtils;
import com.wesimplify.nodabba.common.WebUtils;
import com.wesimplify.nodabba.domain.restaurant.MealType;

/**
 * @author sdoddi
 * This is a restful service to return the restaurant data in JSON format.
 */

@Controller
public class HomePageRestaurantSearchController {

	/**
	 * this is a state less helper to support all restaurant tasks
	 */
	@Autowired
	private RestaurantSearchViewBuilder restaurantSearchViewBuilder;
	
	private static final Logger logger = LoggerFactory.getLogger(HomePageRestaurantSearchController.class);
	
	/**
	 * This method is first entry point to get the restaurants for given search criteria.
	 * @param name
	 * @return Map
	 */
	@RequestMapping(value = "/restaurant/{cityId}/autocomplete", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody Map<String, Object> searchByKeyword(@PathVariable String cityId, 
    		@RequestParam(value="searchText") String searchCriteria) {
		Map<String, Object> results = new HashMap<String, Object>();
		try {
			FilterResults<Map<String, List<KeyValueHolder>>> searchResults = restaurantSearchViewBuilder.autoComplete(cityId, searchCriteria);
			if (logger.isDebugEnabled()) {
				if (searchResults != null && searchResults.getResults() > 0) {
					logger.debug("<Restaurant> search results are {} for searchCriteria {} and city {}", new Object[]{searchResults.getResults(), searchCriteria, cityId});
				}
				else
				{
					logger.debug("<Restaurant> search results not found for searchCriteria {} and city {}", new Object[]{searchCriteria, cityId});
				}
			}
			results.put("results", searchResults); 
		}
		catch (EnhancedException ex) {
			logger.error("Error occured while searching autoComplete for Restaurants {}", searchCriteria, ex);
			results.put("error", new FailureMessageBean(ex.getErrorCode(), "Sorry please try again", null, null));
		}
		catch(Exception ex) {
			logger.error("Unknown error occured while searching autoComplete for Restaurants {}", searchCriteria, ex);
			results.put("error", new FailureMessageBean("ERR_RC_SEARCH_001", "Sorry please try again", null, null));
		}
		return results;
    }
	
	/**
	 * This method will get the results based on the search criteria.<br>
	 * This method will asynchronously initiate the search results and return the user to search results page with pre-population of user selected values. Front end 
	 * will make another request to new url mappings to get the search results in JSON response. The initiated search results will stored in user session and removed once 
	 * after returned.
	 * @param cityId
	 * @param selectionType
	 * @param selectionKey
	 * @param searchCriteria
	 * @param date
	 * @param people
	 * @return String
	 */
	@RequestMapping("/restaurant/search")
	public String searchResturants(@ModelAttribute("searchForm") SearchRestaurantForm searchForm, HttpServletRequest request, ModelMap model) {
		boolean isInvalidForm = RestaurantSearchValidator.validate(searchForm);
		if (isInvalidForm) {
			model.put("error", "Invalid information. please enter all required fields and try again.");
		}
		else
		{
			WebUtils.setBookingRequestFromSession(getBookingRequest(searchForm), request);
			
			try {
				FilterResults<Map<String, Object>> results = restaurantSearchViewBuilder.buildSearchResultsPage(searchForm);
				if (results != null) {
					if (logger.isDebugEnabled()) {
						logger.debug("searchRestaurants results {} ", results);
					}
					model.addAllAttributes(results.getData());
				}
			}
			catch (EnhancedException ex) {
				logger.error("EnhancedException error occured while searching resturants {}", ex);
				model.put("error", new Message(MessageType.ERROR, "Error occured while searching Restaurants. Please try again later"));
			}
			catch(Exception ex) {
				logger.error("Unknown error occured while searching restaurants {}", ex);
				model.put("error", new Message(MessageType.ERROR, "Error occured while searching Restaurants. Please try again later"));
			}
		}
		return "restaurant/searchresults";
	}
	
	/**
	 * creates a BookingRequest from SearchRestaurantForm
	 * @param searchForm
	 * @return BookingRequest
	 */
	private BookingRequest getBookingRequest(SearchRestaurantForm searchForm) {
		MealType mealType = BookingRequestUtil.DEFAULT_MEALTYPE;
		Date currentDate = new Date();
		int pax = BookingRequestUtil.DEFAULT_PAX;
		try {
			pax = Integer.valueOf(searchForm.getPeople());
		}
		catch (Exception ex){}
		
		try {
			mealType = MealType.valueOf(searchForm.getMealType());
		}
		catch (Exception ex){}
		
		try {
			currentDate = DateUtils.formatStringToDate(searchForm.getDate(), DateUtils.DEFAULT_DATE_FORMAT);
		}
		catch (Exception ex){}
		BookingRequest bookingRequest = new BookingRequest(StringUtils.isBlank(searchForm.getCityId()) ? BookingRequestUtil.DEFAULT_CITY : searchForm.getCityId(),
				currentDate, pax, mealType);
		return bookingRequest;
	}
}