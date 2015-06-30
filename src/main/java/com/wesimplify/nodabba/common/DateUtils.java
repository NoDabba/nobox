/**
 * 
 */
package com.wesimplify.nodabba.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author sdoddi
 * This class provides the date utility functionalities like add, subtract etc..
 */
public class DateUtils {

	/**provides the default date format*/
	public final static String DEFAULT_DATE_FORMAT = "dd/MM/YYYY";

	public enum DateAddSubtractType {
		DAYS,
		WEEK,
		MONTH,
		YEAR
	}

	/**
	 * This method will format the date string to Date object for the given pattern. if the pattern passed as a null, this will
	 * apply the default pattern (dd/MM/YYYY)
	 * @param dateSrc
	 * @param pattern
	 * @return <code>Date</code>
	 * @throws ParseException
	 */
	public static Date formatStringToDate(String dateSrc, String pattern) throws ParseException {
		if (dateSrc == null) {
			throw new IllegalArgumentException("dateSrc cannot but null");
		}
		if (pattern == null) {
			pattern = DateUtils.DEFAULT_DATE_FORMAT;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(dateSrc);
	}
	
	/**
	 * formats Date to String for given pattern. If the pattern is null, this method will apply the default pattern available in this class.
	 * @param date
	 * @param pattern
	 * @return String
	 */
	public static String formatDateToString(Date date, String pattern) {
		if (date == null) {
			throw new IllegalArgumentException("Date cannot but null");
		}
		if (pattern == null) {
			pattern = DateUtils.DEFAULT_DATE_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * this method will add or subtract the date for the given noofdays with date type.
	 * @param date
	 * @param type
	 * @param noOfDays
	 * @return <code>Date</code>
	 */
	public static Date dateAddOrSubtract(Date date, DateAddSubtractType type, int noOfDays) {
		if (date == null) {
			throw new IllegalArgumentException("date cannot but null");
		}
		if (type == null) {
			throw new IllegalArgumentException("date type cannot but null");
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		switch (type) {
		case DAYS:
			cal.add(Calendar.DAY_OF_MONTH, noOfDays);
			break;
		case WEEK:
			cal.add(Calendar.DAY_OF_MONTH, noOfDays*7);
			break;
		case MONTH:
			cal.add(Calendar.MONTH, noOfDays);
			break;
		case YEAR:
			cal.add(Calendar.YEAR, noOfDays);
			break;
		}
		return cal.getTime();
	}
	
	/**
	 * helper for calculate day difference
	 * @param from
	 * @param to
	 * @return int (type casts from long)
	 */
	private static int daysDiff(long from, long to) {
	    return (int) Math.round( (to - from) / 86400000D ); // 1000 * 60 * 60 * 24
	}

	/**
	 * calculates the days difference between two days
	 * @param a
	 * @param b
	 * @return int
	 */
	public static int calculateDifference(Date a, Date b)
	{
		Calendar earlier = Calendar.getInstance();
		Calendar later = Calendar.getInstance();
		if (a.compareTo(b) < 0)
		{
			earlier.setTime(a);
			later.setTime(b);
		}
		else
		{
			earlier.setTime(b);
			later.setTime(a);
		}
		
		return daysDiff(earlier.getTimeInMillis(), later.getTimeInMillis());
	}

	/**
	 * calculates the dates pre and post 3 days for the given date
	 * @param fromDate
	 * @return Date[]
	 */
	public static Date[] arrayOfDateRangesBeforeAndAfter(Date fromDate) {
		Date currentDate = new Date();
		int difference = calculateDifference(currentDate, fromDate);
		Date start = null;
		if (difference < 3) {
			start = currentDate;
		}
		else {
			start = dateAddOrSubtract(fromDate, DateAddSubtractType.DAYS, -3);
		}

		Date[] listOfDates = new Date[7];
		for (int i=0;i<listOfDates.length;i++){
			listOfDates[i] = start;
			start = dateAddOrSubtract(start, DateAddSubtractType.DAYS, 1);
		}
		return listOfDates;
	}
	
	public static void main(String args[]) {
		
		System.out.println(DateUtils.formatDateToString(new Date(), "E"));
		
	}
}
