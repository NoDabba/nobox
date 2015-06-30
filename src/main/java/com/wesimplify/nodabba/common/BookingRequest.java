/**
 * 
 */
package com.wesimplify.nodabba.common;

import java.util.Date;

import com.wesimplify.nodabba.domain.restaurant.MealType;

/**
 * @author sdoddi
 * Pojo to store the booking request. all fields are mandatory
 */
public class BookingRequest {

	private String city;
	private Date bookingDate;
	private int pax;
	private MealType mealType;
	/**
	 * @param bookingDate
	 * @param pax
	 * @param mealType
	 */
	public BookingRequest(String city, Date bookingDate, int pax, MealType mealType) {
		super();
		this.city = city;
		this.bookingDate = bookingDate;
		this.pax = pax;
		this.mealType = mealType;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @return the bookingDate
	 */
	public Date getBookingDate() {
		return bookingDate;
	}
	/**
	 * @return the pax
	 */
	public int getPax() {
		return pax;
	}
	/**
	 * @return the mealType
	 */
	public MealType getMealType() {
		return mealType;
	}
}
