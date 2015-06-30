/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sdoddi
 *
 */
public class Meal {
	
	private MealType mealType;
	private BigDecimal basePrice;
	private String timings;
	private String menuDescription;
	private String menuUrl;
	private List<OfferWeekDay> offerWeekDays = new ArrayList<OfferWeekDay>();
		
	/**
	 * @param mealType
	 */
	public Meal(MealType mealType) {
		super();
		this.mealType = mealType;
	}
	/**
	 * @param mealType
	 * @param basePrice
	 * @param offerWeekDays
	 */
	public Meal(MealType mealType, BigDecimal basePrice) {
		super();
		this.mealType = mealType;
		this.basePrice = basePrice;
	}
	/**
	 * @return the mealType
	 */
	public MealType getMealType() {
		return mealType;
	}
	/**
	 * @return the basePrice
	 */
	public BigDecimal getBasePrice() {
		return basePrice;
	}
	/**
	 * @return the offerWeekDays
	 */
	public List<OfferWeekDay> getOfferWeekDays() {
		return offerWeekDays;
	}
	
	/**
	 * adds the offerWeekDays to list
	 * @param offerWeekDay
	 */
	public void addOfferWeekDays(OfferWeekDay offerWeekDay) {
		offerWeekDays.add(offerWeekDay);
	}
	/**
	 * @return the timings
	 */
	public String getTimings() {
		return timings;
	}
	/**
	 * @param timings the timings to set
	 */
	public void setTimings(String timings) {
		this.timings = timings;
	}
	/**
	 * @return the menuDescription
	 */
	public String getMenuDescription() {
		return menuDescription;
	}
	/**
	 * @param menuDescription the menuDescription to set
	 */
	public void setMenuDescription(String menuDescription) {
		this.menuDescription = menuDescription;
	}
	/**
	 * @return the menuUrl
	 */
	public String getMenuUrl() {
		return menuUrl;
	}
	/**
	 * @param menuUrl the menuUrl to set
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((mealType == null) ? 0 : mealType.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meal other = (Meal) obj;
		if (mealType != other.mealType)
			return false;
		return true;
	}
}
