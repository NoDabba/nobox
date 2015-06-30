/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant.search;

import java.math.BigDecimal;

import com.wesimplify.nodabba.domain.restaurant.RestaurantProfile;

/**
 * @author sdoddi
 * This will hold the light weight of search results
 */
public class RestaurantSearchDetail {

	private RestaurantProfile profile;
	private BigDecimal basePrice;
	private float offerPercentage;
	private BigDecimal offerPrice;
	private BigDecimal savePrice;
	private float ratings;
	/**
	 * @param profile
	 * @param basePrice
	 * @param offerPercentage
	 * @param offerPrice
	 * @param savePrice
	 * @param ratings
	 */
	public RestaurantSearchDetail(RestaurantProfile profile,
			BigDecimal basePrice, float offerPercentage, BigDecimal offerPrice,
			BigDecimal savePrice, float ratings) {
		super();
		this.profile = profile;
		this.basePrice = basePrice;
		this.offerPercentage = offerPercentage;
		this.offerPrice = offerPrice;
		this.savePrice = savePrice;
		this.ratings = ratings;
	}
	/**
	 * @return the profile
	 */
	public RestaurantProfile getProfile() {
		return profile;
	}
	/**
	 * @return the basePrice
	 */
	public BigDecimal getBasePrice() {
		return basePrice;
	}
	/**
	 * @return the offerPercentage
	 */
	public float getOfferPercentage() {
		return offerPercentage;
	}
	/**
	 * @return the offerPrice
	 */
	public BigDecimal getOfferPrice() {
		return offerPrice;
	}
	/**
	 * @return the savePrice
	 */
	public BigDecimal getSavePrice() {
		return savePrice;
	}
	/**
	 * @return the ratings
	 */
	public float getRatings() {
		return ratings;
	}
}
