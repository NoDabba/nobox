/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author sdoddi
 * Primitives to hold the offers
 */
public class Offer {
	
	private int id;
	private String displayName;
	private Date validFromDate;
	private Date validToDate;
	private List<Date> blackListedDates = new ArrayList<Date>();
	private List<Meal> availableMeals = new ArrayList<Meal>();
	/**
	 * @param id
	 * @param displayName
	 * @param validFromDate
	 * @param validToDate
	 */
	public Offer(int id, String displayName, Date validFromDate,
			Date validToDate) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.validFromDate = validFromDate;
		this.validToDate = validToDate;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @return the validFromDate
	 */
	public Date getValidFromDate() {
		return validFromDate;
	}
	/**
	 * @return the validToDate
	 */
	public Date getValidToDate() {
		return validToDate;
	}
	/**
	 * @return the blackListedDates
	 */
	public List<Date> getBlackListedDates() {
		return blackListedDates;
	}
	
	/**
	 * adds the black listed date to the list
	 * @param date
	 */
	public void addBlackListedDates(Date date) {
		blackListedDates.add(date);
	}
	/**
	 * @return the availableMeals
	 */
	public List<Meal> getAvailableMeals() {
		return availableMeals;
	}
	
	/**
	 * adds the meal to the list
	 * @param meal
	 */
	public void addAvailableMeals(Meal meal) {
		availableMeals.add(meal);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Offer other = (Offer) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
