/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant.search;

/**
 * @author sdoddi
 *
 */
public class LocationSearchDetail {
	
	private String key;
	private String displayName;
	private int noOfRestaurants;
	/**
	 * @param key
	 * @param displayName
	 */
	public LocationSearchDetail(String key, String displayName) {
		super();
		this.key = key;
		this.displayName = displayName;
	}
	/**
	 * @return the noOfRestaurants
	 */
	public int getNoOfRestaurants() {
		return noOfRestaurants;
	}
	/**
	 * @param noOfRestaurants the noOfRestaurants to set
	 */
	public void setNoOfRestaurants(int noOfRestaurants) {
		this.noOfRestaurants = noOfRestaurants;
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
}
