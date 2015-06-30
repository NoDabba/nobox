/**
 * 
 */
package com.wesimplify.nodabba.common;

import java.util.Calendar;
import java.util.Date;

import com.wesimplify.nodabba.common.DateUtils.DateAddSubtractType;
import com.wesimplify.nodabba.domain.restaurant.MealType;

/**
 * @author sdoddi
 *
 */
public class BookingRequestUtil {
	
	public static final String DEFAULT_CITY="HYD";
	public static final MealType DEFAULT_MEALTYPE= MealType.LUNCH;
	public static final int DEFAULT_PAX= 2;

	/**
	 * helper method to create default booking request. 
	 * @return BookingRequest
	 */
	public static BookingRequest defaultBookingRequest() {
		int pax = BookingRequestUtil.DEFAULT_PAX;
		MealType mealType = BookingRequestUtil.DEFAULT_MEALTYPE;
		Calendar currentDate = Calendar.getInstance();
		int hourOfTheDay = currentDate.get(Calendar.HOUR_OF_DAY);
		if (hourOfTheDay > 14 && hourOfTheDay < 21) {
			mealType =  MealType.DINNER;
		}
		
		if (hourOfTheDay > 21) {
			Date nextDate = DateUtils.dateAddOrSubtract(currentDate.getTime(), DateAddSubtractType.DAYS, 1);
			currentDate.setTime(nextDate);
		}
		
		BookingRequest bookingRequest = new BookingRequest(DEFAULT_CITY,currentDate.getTime(),pax, mealType);
		return bookingRequest;
	}
	
}
