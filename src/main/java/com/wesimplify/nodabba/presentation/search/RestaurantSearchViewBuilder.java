/**
 * 
 */
package com.wesimplify.nodabba.presentation.search;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wesimplify.nodabba.common.BookingRequestUtil;
import com.wesimplify.nodabba.common.EnhancedException;
import com.wesimplify.nodabba.common.FilterCriteria;
import com.wesimplify.nodabba.common.FilterResults;
import com.wesimplify.nodabba.common.KeyValueHolder;
import com.wesimplify.nodabba.common.StringUtils;
import com.wesimplify.nodabba.domain.restaurant.MealType;
import com.wesimplify.nodabba.domain.restaurant.Restaurant;
import com.wesimplify.nodabba.service.restaurant.RestaurantGateway;

/**
 * @author sdoddi
 * Helper class for <code>RestaurantSearchController</code> to search Restaurant from <code>RestaurantBusinessService</code>
 */
@Component
public class RestaurantSearchViewBuilder {
	
	private static final Logger logger = LoggerFactory.getLogger(RestaurantSearchViewBuilder.class);

	@Inject
    @Qualifier("restaurantGateway")
	private RestaurantGateway restaurantGateway;
	
	/**
	 * This method will return the search results for given search criteria
	 * @param searchKey
	 * @param city
	 * @return RestaurantSearchResults
	 * @throws EnhancedException
	 */
	public FilterResults<Map<String, List<KeyValueHolder>>> autoComplete(String city, String searchKey) throws EnhancedException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("Searching for autoComplete for city {} and keyword is {}", city, searchKey);
		}
		
		FilterCriteria filterCriteria = new FilterCriteria();
		filterCriteria.addCriteria("keyword", searchKey);
		
		FilterResults<Map<String, List<KeyValueHolder>>> results = restaurantGateway.searchRestaurantsMetaData(city, filterCriteria);
		if (logger.isDebugEnabled()) {
			if (results != null) {
				logger.debug("Search results for autoComplete {} and data {}", results.getResults(), results.getData());
			}
			else
			{
				logger.debug("Search results for autoComplete is null for city {} and searchKey {}", city, searchKey);
			}
		}
		return results;
	}
	
	/**
	 * Will build the necessary model data for search results. This method will initiate the search query and store the Async future to user session. 
	 * @param searchForm
	 * @return FilterResults<Map<String, Object>>
	 */
	public FilterResults<Map<String, Object>> buildSearchResultsPage(SearchRestaurantForm searchForm) throws EnhancedException {
		
		String city = searchForm.getCityId();
		String selectionKey = searchForm.getSelectionKey();
		String mealTypeStr = searchForm.getMealType();
		String searchText = searchForm.getSearchCriteria();
		String aroundMe = searchForm.getAroundMe();
		Date date = StringUtils.convertStringToDate(searchForm.getDate());
		int people = StringUtils.convertStringToInteger(searchForm.getPeople());
		
		if (people <= 0) {
			people = BookingRequestUtil.DEFAULT_PAX;
		}
		
		MealType mealType = BookingRequestUtil.DEFAULT_MEALTYPE;
		if (StringUtils.isBlank(mealTypeStr)) {
			mealType = MealType.lookup(mealTypeStr);
		}
		
		FilterCriteria filterCriteria = new FilterCriteria();
		filterCriteria.addCriteria("keyword", searchText);
		filterCriteria.addCriteria("key", selectionKey);
		filterCriteria.addCriteria("type", mealType);
		filterCriteria.addCriteria("date", date);
		filterCriteria.addCriteria("people", people);
		filterCriteria.addCriteria("aroundme", aroundMe);
		
		FilterResults<Map<String, Object>> results = restaurantGateway.searchRestaurants(city, filterCriteria);;
		if (logger.isDebugEnabled()) {
			if (results != null) {
				logger.debug("Search results for Restaurant {} and data {}", results.getResults(), results.getData());
			}
			else
			{
				logger.debug("Search results for Restaurant is null for city {} and filterCriteria {}", city, filterCriteria);
			}
		}
		return results;
	}
	
	/**
	 * This method will return all Restaurant's for given area.
	 * @param city
	 * @param area
	 * @return List<Restaurant>
	 * @throws EnhancedException
	 */
	public List<Restaurant> findRestaurantsByArea(String city, String area) throws EnhancedException{
		FilterCriteria filterCriteria = new FilterCriteria();
		filterCriteria.addCriteria("city", city);
		filterCriteria.addCriteria("area", area);
		return null;
	}
	
	/**
	 * This method will return Restaurant for given Restaurant id.
	 * @param id
	 * @return Restaurant
	 * @throws EnhancedException
	 */
	public Restaurant findRestaurantById(String id) throws EnhancedException{
		FilterCriteria filterCriteria = new FilterCriteria();
		filterCriteria.addCriteria("restaurant", id);
		return null;
	}
	
}
