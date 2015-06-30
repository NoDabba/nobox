/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.wesimplify.nodabba.common.DateUtils;
import com.wesimplify.nodabba.common.DateUtils.DateAddSubtractType;
import com.wesimplify.nodabba.common.StringUtils;

/**
 * @author sdoddi
 *
 */
public class TestMe {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OfferWeekDayType type = getDayOfTheWeekTypeFromDate(new Date());
		System.out.println(type.name());
		
		Date bookingDate = DateUtils.dateAddOrSubtract(new Date(), DateAddSubtractType.DAYS, 8);
		System.out.println("Booking Date = > "+ DateUtils.formatDateToString(bookingDate, null));
		Date[] bookingDates = DateUtils.arrayOfDateRangesBeforeAndAfter(bookingDate);
		for (Date date : bookingDates) {
			System.out.println("Formatted Date = > "+ DateUtils.formatDateToString(date, null));
		}
		
		
		String searchKey = "Gachibowli";
		String patterntxt = "(Gachi)";
		String anotherPattern = ".*gachi.*";
		
		System.out.println(StringUtils.patternSearch(searchKey, anotherPattern));
		
		Pattern pattern = Pattern.compile(anotherPattern, Pattern.CASE_INSENSITIVE);
		
		Matcher matcher = pattern.matcher(searchKey);

        System.out.println(matcher.matches()+","+pattern.pattern());
		
		

	}

	/**
	 * gets the OfferWeekDayTye from the given Date
	 * @param date
	 * @return OfferWeekDayType
	 */
	private static OfferWeekDayType getDayOfTheWeekTypeFromDate(Date date) {
		String day = new SimpleDateFormat("EEE").format(new Date());
		return OfferWeekDayType.lookup(day);
	}
}
