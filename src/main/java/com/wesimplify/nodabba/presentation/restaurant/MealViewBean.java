/**
 * 
 */
package com.wesimplify.nodabba.presentation.restaurant;

/**
 * @author sdoddi
 *
 */
public class MealViewBean {

	private String mealName;
	private String timings;
	private String menuDescription;
	private String menuUrl;
	/**
	 * @param mealName
	 * @param timings
	 * @param menuDescription
	 * @param menuUrl
	 */
	public MealViewBean(String mealName, String timings,
			String menuDescription, String menuUrl) {
		super();
		this.mealName = mealName;
		this.timings = timings;
		this.menuDescription = menuDescription;
		this.menuUrl = menuUrl;
	}
	/**
	 * @return the mealName
	 */
	public String getMealName() {
		return mealName;
	}
	/**
	 * @return the timings
	 */
	public String getTimings() {
		return timings;
	}
	/**
	 * @return the menuDescription
	 */
	public String getMenuDescription() {
		return menuDescription;
	}
	/**
	 * @return the menuUrl
	 */
	public String getMenuUrl() {
		return menuUrl;
	}
	
	
}
