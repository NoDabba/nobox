/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;

import java.util.Date;
import java.util.List;


/**
 * @author sdoddi
 * Place holder to store the restaurant details
 */
public class Restaurant {

	private RestaurantProfile restaurantProfile;
	private Reviews reviews;
	private List<Meal> availableMeals;
	private OfferManager offerManager;
	private List<Date> holidayDates;
	
	/**
	 * @param restaurantProfile
	 */
	public Restaurant(RestaurantProfile restaurantProfile) {
		super();
		this.restaurantProfile = restaurantProfile;
	}
	
	/**
	 * @return the offerManager
	 */
	public OfferManager getOfferManager() {
		return offerManager;
	}

	/**
	 * @param offerManager the offerManager to set
	 */
	public void setOfferManager(OfferManager offerManager) {
		this.offerManager = offerManager;
	}

	/**
	 * @return the reviews
	 */
	public Reviews getReviews() {
		return reviews;
	}
	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(Reviews reviews) {
		this.reviews = reviews;
	}
	/**
	 * @return the restaurantProfile
	 */
	public RestaurantProfile getRestaurantProfile() {
		return restaurantProfile;
	}
	/**
	 * @return the availableMeals
	 */
	public List<Meal> getAvailableMeals() {
		return availableMeals;
	}
	/**
	 * @param availableMeals the availableMeals to set
	 */
	public void setAvailableMeals(List<Meal> availableMeals) {
		this.availableMeals = availableMeals;
	}

	/**
	 * @return the holidayDates
	 */
	public List<Date> getHolidayDates() {
		return holidayDates;
	}

	/**
	 * @param holidayDates the holidayDates to set
	 */
	public void setHolidayDates(List<Date> holidayDates) {
		this.holidayDates = holidayDates;
	}
}
