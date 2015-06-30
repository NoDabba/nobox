/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;


/**
 * @author sdoddi
 *
 */
public enum OfferWeekDayType {
	
	MON("Mon"),
	TUE("Tue"),
	WED("Wed"),
	THU("Thu"),
	FRI("Fri"),
	SAT("Sat"),
	SUN("Sun");
	
	private String day;
	
	private OfferWeekDayType(String day) {
		this.day = day;
	}
	
	public String getCode() {
		return day;
	}
	
	public static OfferWeekDayType lookup(String day) {
		OfferWeekDayType offerWeekDayTypes[] = OfferWeekDayType.values();
		for (OfferWeekDayType offerWeekDayType : offerWeekDayTypes) {
			if (offerWeekDayType.getCode().equalsIgnoreCase(day)) {
				return offerWeekDayType;
			}
		}
		return null;
	}
}
