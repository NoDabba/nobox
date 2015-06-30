/**
 * 
 */
package com.wesimplify.nodabba.presentation.restaurant;

import java.util.List;

import com.wesimplify.nodabba.primitives.Address;

/**
 * @author sdoddi
 * 
 */
public class RestaurantInquiryViewBean {
	
	private String restaurantId;
	private String restaurantName;
	private Address postalAddress;
	private String additionalInformation;
	private float ratings;
	private long numberOfReviews;
	private String faceBookLink;
	private String logoUrl;
	private List<String> restaurantUrls;
	private OfferViewBean offerViewBean;
	private List<MealViewBean> mealViewBeans;
	/**
	 * @param restaurantId
	 * @param restaurantName
	 * @param postalAddress
	 * @param additionalInformation
	 * @param ratings
	 * @param numberOfReviews
	 * @param faceBookLink
	 * @param logoUrl
	 * @param restaurantUrls
	 */
	public RestaurantInquiryViewBean(String restaurantId,
			String restaurantName, Address postalAddress,
			String additionalInformation, float ratings, long numberOfReviews,
			String faceBookLink, String logoUrl, List<String> restaurantUrls) {
		super();
		this.restaurantId = restaurantId;
		this.restaurantName = restaurantName;
		this.postalAddress = postalAddress;
		this.additionalInformation = additionalInformation;
		this.ratings = ratings;
		this.numberOfReviews = numberOfReviews;
		this.faceBookLink = faceBookLink;
		this.logoUrl = logoUrl;
		this.restaurantUrls = restaurantUrls;
	}
	/**
	 * @return the restaurantId
	 */
	public String getRestaurantId() {
		return restaurantId;
	}
	/**
	 * @return the restaurantName
	 */
	public String getRestaurantName() {
		return restaurantName;
	}
	/**
	 * @return the postalAddress
	 */
	public Address getPostalAddress() {
		return postalAddress;
	}
	/**
	 * @return the additionalInformation
	 */
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	/**
	 * @return the ratings
	 */
	public float getRatings() {
		return ratings;
	}
	/**
	 * @return the numberOfReviews
	 */
	public long getNumberOfReviews() {
		return numberOfReviews;
	}
	/**
	 * @return the faceBookLink
	 */
	public String getFaceBookLink() {
		return faceBookLink;
	}
	/**
	 * @return the logoUrl
	 */
	public String getLogoUrl() {
		return logoUrl;
	}
	/**
	 * @return the restaurantUrls
	 */
	public List<String> getRestaurantUrls() {
		return restaurantUrls;
	}
	/**
	 * @return the offerViewBean
	 */
	public OfferViewBean getOfferViewBean() {
		return offerViewBean;
	}
	/**
	 * @param offerViewBean the offerViewBean to set
	 */
	public void setOfferViewBean(OfferViewBean offerViewBean) {
		this.offerViewBean = offerViewBean;
	}
	/**
	 * @return the mealViewBeans
	 */
	public List<MealViewBean> getMealViewBeans() {
		return mealViewBeans;
	}
	/**
	 * @param mealViewBeans the mealViewBeans to set
	 */
	public void setMealViewBeans(List<MealViewBean> mealViewBeans) {
		this.mealViewBeans = mealViewBeans;
	}
	
}
