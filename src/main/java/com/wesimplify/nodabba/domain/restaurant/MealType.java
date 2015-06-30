/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;

/**
 * @author sdoddi
 * Meal types enum
 */
public enum MealType {
	
	BRUNCH("brunch"),
	LUNCH("lunch"),
	DINNER("dinner");

	private String code;
	
	/**
	 * Constructor to store the code
	 * @param code
	 */
	private MealType(String code) {
		this.code = code;
	}
	
	/**
	 * returns the code.
	 * @return String
	 */
	public String getCode() {
		return this.code;
	}
	
	/**
	 * does the reverse look from string to enum. if not found, it will by default return the LUNCH
	 * @param code
	 * @return
	 */
	public static MealType lookup(String code) {
		MealType values[] = MealType.values();
		for (MealType value : values) {
			if (value.code.equals(code)) {
				return value;
			}
		}
		return MealType.LUNCH;
	}
	
}
