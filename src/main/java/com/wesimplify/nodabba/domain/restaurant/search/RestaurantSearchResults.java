/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant.search;

import java.util.List;



/**
 * @author sdoddi
 * Main object to return all search results for the given search query. You can add the categories and corresponding search results associated to it.
 * That means, this can hold multiple search results for given category.
 */
public final class RestaurantSearchResults {

	
	private List<LocationSearchDetail> locationSearchDetailList;
	private List<RestaurantSearchDetail> restaurantSearchDetailsList;
	
	/**
	 * @return the locationSearchDetailList
	 */
	public List<LocationSearchDetail> getLocationSearchDetailList() {
		return locationSearchDetailList;
	}
	/**
	 * @param locationSearchDetailList the locationSearchDetailList to set
	 */
	public void setLocationSearchDetailList(
			List<LocationSearchDetail> locationSearchDetailList) {
		this.locationSearchDetailList = locationSearchDetailList;
	}
	/**
	 * @return the restaurantSearchDetailsList
	 */
	public List<RestaurantSearchDetail> getRestaurantSearchDetailsList() {
		return restaurantSearchDetailsList;
	}
	/**
	 * @param restaurantSearchDetailsList the restaurantSearchDetailsList to set
	 */
	public void setRestaurantSearchDetailsList(
			List<RestaurantSearchDetail> restaurantSearchDetailsList) {
		this.restaurantSearchDetailsList = restaurantSearchDetailsList;
	}
}
