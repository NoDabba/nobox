/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;


/**
 * @author sdoddi
 *
 */
public class MainPageOffer {

	private RestaurantProfile restaurantProfile;
	private float offerPercentage;
	/**
	 * @param restaurantProfile
	 * @param offerPercentage
	 */
	public MainPageOffer(RestaurantProfile restaurantProfile,
			float offerPercentage) {
		super();
		this.restaurantProfile = restaurantProfile;
		this.offerPercentage = offerPercentage;
	}
	/**
	 * @return the restaurantProfile
	 */
	public RestaurantProfile getRestaurantProfile() {
		return restaurantProfile;
	}
	/**
	 * @return the offerPercentage
	 */
	public float getOfferPercentage() {
		return offerPercentage;
	}
}
