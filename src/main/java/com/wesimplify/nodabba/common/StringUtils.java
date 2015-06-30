/**
 * 
 */
package com.wesimplify.nodabba.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * @author sdoddi
 *
 */
public final class StringUtils {
	
	/*
	 * <p>Checks if a CharSequence is whitespace, empty ("") or null.</p>
	 *
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param cs  the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is null, empty or whitespace
	 * @since 2.0
	 * @since 3.0 Changed signature from isBlank(String) to isBlank(CharSequence)
	 */
	public static boolean isBlank(final CharSequence cs) {
	    int strLen;
	    if (cs == null || (strLen = cs.length()) == 0) {
	        return true;
	    }
	    for (int i = 0; i < strLen; i++) {
	        if (Character.isWhitespace(cs.charAt(i)) == false) {
	            return false;
	        }
	    }
	    return true;
	}

	/**
	 * converts the string to integer. otherwise 0
	 * @param number
	 * @return int
	 */
	public static int convertStringToInteger(String number) {
		int i = 0;
		try {
			i = Integer.valueOf(number);
		}
		catch (Exception ex) {}
		return i;
	}
	
	/**
	 * converts the String to Date with dd/MM/yyyy format. Otherwise current date is returned.
	 * @param dateStr
	 * @return Date
	 */
	public static Date convertStringToDate(String dateStr) {
		try {
			if (dateStr != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				return sdf.parse(dateStr);
			}
		}
		catch (Exception ex) {}
		return new Date();
	}
	
	/**
	 * This method will do pattern (regex) searching for given search text and pattern
	 * @param searchText
	 * @param pattern
	 * @return boolean
	 */
	public static boolean patternSearch(String searchText, String pattern) {
		String patterncompile = "."+pattern+".*";
        return Pattern.matches(pattern, searchText);
	}
}
