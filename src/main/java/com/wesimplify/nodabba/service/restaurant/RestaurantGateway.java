/**
 * 
 */
package com.wesimplify.nodabba.service.restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.wesimplify.nodabba.common.BookingRequest;
import com.wesimplify.nodabba.common.EnhancedException;
import com.wesimplify.nodabba.common.FilterCriteria;
import com.wesimplify.nodabba.common.FilterResults;
import com.wesimplify.nodabba.common.KeyValueHolder;
import com.wesimplify.nodabba.common.Message;
import com.wesimplify.nodabba.common.MessageType;
import com.wesimplify.nodabba.common.StringUtils;
import com.wesimplify.nodabba.domain.restaurant.MainPageOffer;
import com.wesimplify.nodabba.domain.restaurant.OfferManager;
import com.wesimplify.nodabba.domain.restaurant.Restaurant;
import com.wesimplify.nodabba.domain.restaurant.RestaurantProfile;
import com.wesimplify.nodabba.domain.restaurant.search.LocationSearchDetail;
import com.wesimplify.nodabba.domain.restaurant.search.RestaurantSearchDetail;
import com.wesimplify.nodabba.integration.restaurant.RestaurantDAO;
import com.wesimplify.nodabba.primitives.Address;
import com.wesimplify.nodabba.services.BusinessAwareService;

/**
 * @author sdoddi
 * This is Restaurant Orchestrator service. this will in turn delegate the request to individual responsibility objects.
 */
public class RestaurantGateway {

	private static final Logger logger = LoggerFactory.getLogger(RestaurantGateway.class);

	/**
	 * this will help services to return the required declared bean from Spring Framework
	 */
	private BusinessAwareService businessAwareService;
	
	/**
	 * @param businessAwareService the businessAwareService to set
	 */
	public void setBusinessAwareService(BusinessAwareService businessAwareService) {
		this.businessAwareService = businessAwareService;
	}
	
	
	/**
	 * This method will build the list of best offers that needs to be displayed on the home page.
	 * @param cityId
	 * @return List<Restaurant>
	 */
	@Cacheable(value = "bestOffersforHomePageByCityId", key = "#cityId")
	public List<MainPageOffer> getBestOffersforHomePageByCityId(String cityId) throws EnhancedException {
		
		List<MainPageOffer> offers = new ArrayList<MainPageOffer>();
		try {
			offers = getRestaurantDAO().getMainPageOffers(cityId);
		}
		catch (Exception ex) {
			throw new EnhancedException("ERR_RES_DETAILS_401", "Error getting Main Page offers", ex);
		}
		if (offers == null) {
			throw new EnhancedException("ERR_RES_DETAILS_402", "Main Page offers not available");
		}
		return offers;
	}
	
	
	/**
	 * get Restaurant details for given Restaurant id. Booking date is passed to get the offers
	 * @param id
	 * @return Restaurant
	 */
	public Restaurant getRestaurantById(String id, BookingRequest bookingRequest, boolean getOffers) throws EnhancedException {
		Restaurant restaurant = null;
		
		try {
			restaurant = getRestaurantDAO().findRestaurantById(id);
		}
		catch (Exception ex) {
			throw new EnhancedException("ERR_RES_DETAILS_001", "Error getting Restaurant details", ex);
		}
		if (restaurant == null) {
			throw new EnhancedException("ERR_RES_DETAILS_101", "Restaurant not found");
		}
		if (getOffers) {
			OfferManager offerManager = getOffersByRestaurantId(id, bookingRequest);
			restaurant.setOfferManager(offerManager);
		}
		return restaurant;
	}
	
	/**
	 * gets the offers for given Restaurant
	 * @param id
	 * @param bookingRequest
	 * @return OfferManager
	 * @throws EnhancedException
	 */
	public OfferManager getOffersByRestaurantId(String id, BookingRequest bookingRequest) throws EnhancedException {
		if (id == null) {
			throw new EnhancedException("ERR_RES_DETAILS_301", "Restaurant Id is required to get the offers");
		}
		if (bookingRequest == null) {
			throw new EnhancedException("ERR_RES_DETAILS_302", "Booking Request is required to get the offers");
		}
		OfferManager offerManager = getRestaurantDAO().findOffersByRestaurantId(id, bookingRequest);
		return offerManager;
	}

	/**
	 * This method will return the auto complete search results for given filter criteria and city
	 * @param cityId
	 * @param filterCriteria
	 * @throws EnhancedException
	 */
	public FilterResults<Map<String, List<KeyValueHolder>>> searchRestaurantsMetaData(String cityId, FilterCriteria filterCriteria) throws EnhancedException {
		String searchKeyword = (String) filterCriteria.getCriteria("keyword");
		try {
			List<RestaurantProfile> listOfAllRestaurants = getRestaurantsByCity(cityId);
			int results = 0;
			Map<String, List<KeyValueHolder>> data = null;
			if (listOfAllRestaurants != null) {
				List<KeyValueHolder> areaCategoryList= new ArrayList<KeyValueHolder>();
				List<KeyValueHolder> restaurantCategoryList= new ArrayList<KeyValueHolder>();
				for (RestaurantProfile restaurantProfile : listOfAllRestaurants) {
					
					String restaurantDisplayName = restaurantProfile.getDisplayName();
					if (restaurantDisplayName != null) {
						if (searchKeyword.startsWith(restaurantDisplayName)) {
							KeyValueHolder valueHodler = new KeyValueHolder(restaurantProfile.getKey(), restaurantProfile.getDisplayName());
							restaurantCategoryList.add(valueHodler);
						}
					}
					
					Address address = restaurantProfile.getAddress();
					if (address != null && address.getArea() != null) {
						if (searchKeyword.startsWith(address.getArea())) {
							KeyValueHolder valueHodler = new KeyValueHolder(restaurantProfile.getKey(), restaurantProfile.getDisplayName());
							areaCategoryList.add(valueHodler);
						}
					}
				}
				results = areaCategoryList.size() > 0 ? 1 : (restaurantCategoryList.size() > 1 ? 1 : 0);
				data = new HashMap<String, List<KeyValueHolder>>();
				data.put("areas", areaCategoryList);
				data.put("restaurants", restaurantCategoryList);
			}
			FilterResults<Map<String, List<KeyValueHolder>>> filterResults = new FilterResults<Map<String, List<KeyValueHolder>>>(results, data); 
			return filterResults;
		}
		catch (Exception ex) {
			logger.error("Error when doing the searching for locations and resturants", ex);
			throw new EnhancedException("ERR_RES_SEARCH_001", "error getting unique cities from database", ex);
		}
	}
	
	/**
	 * This will build the main search results for given search criteria. This will create two main models <br>
	 * 1. Location Search results with matching Restaurant count<br>
	 * 2. Restaurant search results for given criteria
	 * @param cityId
	 * @param filterCriteria
	 * @return
	 * @throws EnhancedException
	 */
	public FilterResults<Map<String, Object>> searchRestaurants(String cityId, FilterCriteria filterCriteria)throws EnhancedException {
		Map<String, Object> data = new HashMap<String, Object>();
		// pass the filter criteria to DAO
		// build the area details
		// build the Restaurants list with pagination
		String searchKeyword = (String) filterCriteria.getCriteria("keyword");
		if (!StringUtils.isBlank(searchKeyword)) {
			String requiredPattern = ".*"+searchKeyword+".*";
			Pattern pattern = Pattern.compile(requiredPattern, Pattern.CASE_INSENSITIVE);
			
			List<RestaurantProfile> listOfAllRestaurants = getRestaurantsByCity(cityId);
			if (listOfAllRestaurants != null) {
				
				Map<String, LocationSearchDetail> tmpLocationDetailsMap = new HashMap<String, LocationSearchDetail>();
				for (RestaurantProfile restaurantProfile : listOfAllRestaurants) {
					Address address = restaurantProfile.getAddress();
					if (address != null && address.getArea() != null) {
						if (pattern.matcher(address.getArea()).matches()) {
							LocationSearchDetail locationSearchDetail = null;
							String key = restaurantProfile.getDisplayName().toLowerCase();
							int count = 1;
							if (tmpLocationDetailsMap.containsKey(key)) {
								locationSearchDetail = tmpLocationDetailsMap.get(key);
								count = locationSearchDetail.getNoOfRestaurants() + 1;
							}
							else
							{
								locationSearchDetail = new LocationSearchDetail(key, restaurantProfile.getDisplayName());
								tmpLocationDetailsMap.put(key, locationSearchDetail);
							}
							locationSearchDetail.setNoOfRestaurants(count);
						}
					}
				}
				data.put("LocationSearchDetail", tmpLocationDetailsMap.values());
			}
		}
		
		//RestaurantSearchResults
		List<RestaurantSearchDetail> restaurantSearchDetailList = null;
		try {
			restaurantSearchDetailList = getRestaurantDAO().findRestaurantByFilterCriteria(filterCriteria);
			data.put("searchResults", restaurantSearchDetailList);
		}
		catch (Exception ex) {
			logger.error("Error getting Search results for given filter criteria", ex);
			data.put("failureMessage", new Message(MessageType.ERROR, "Error getting Restaurant search results for given filter criteria"));
		}
		
		FilterResults<Map<String, Object>> results  = new FilterResults<Map<String, Object>>(data.size(), data);
		return results;
	}
	
	/**
	 * This methods returns the List of minimal restaurant data for given city id. This will also do caching. 
	 * @param cityId
	 * @return List<RestaurantProfile>
	 * @throws EnhancedException
	 */
	@Cacheable(value = "restaurantsByCityCache", key = "#cityId")
	public List<RestaurantProfile> getRestaurantsByCity(String cityId) throws EnhancedException {
		try {
			return getRestaurantDAO().getRestaurantsByCity(cityId);
		}
		catch (Exception ex) {
			logger.error("Error when getting the restaurants by city", ex);
			throw new EnhancedException("ERR_RES_SEARCH_001", "error getting unique cities from database", ex);
		}
	}
	
	/**
	 * Helper method to return <code>RestaurantDAO</code> from BusinessServiceAware
	 * @return RestaurantDAO
	 */
	private RestaurantDAO getRestaurantDAO() {
		return businessAwareService.getBean("restaurantDAO", RestaurantDAO.class);
	}
}
