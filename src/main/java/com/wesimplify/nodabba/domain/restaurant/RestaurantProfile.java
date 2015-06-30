/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;

import java.util.List;

import com.wesimplify.nodabba.primitives.Address;

/**
 * @author sdoddi
 * Restaurant common data will come here. not making this tightly coupled with <code>Restaurant</code>
 */
public class RestaurantProfile {

	private String key;
	private String displayName;
	private Address address;
	private String faceBookLink;
	private String logoUrl;
	private List<String> restaurantUrls;
	private String additionalInformation;
	
	
	/**
	 * @param key
	 * @param displayName
	 * @param address
	 * @param faceBookLink
	 * @param logoUrl
	 * @param restaurantUrls
	 * @param additionalInformation
	 */
	public RestaurantProfile(String key, String displayName, Address address,
			String faceBookLink, String logoUrl, List<String> restaurantUrls,
			String additionalInformation) {
		super();
		this.key = key;
		this.displayName = displayName;
		this.address = address;
		this.faceBookLink = faceBookLink;
		this.logoUrl = logoUrl;
		this.restaurantUrls = restaurantUrls;
		this.additionalInformation = additionalInformation;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
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
	 * @return the additionalInformation
	 */
	public String getAdditionalInformation() {
		return additionalInformation;
	}
}
