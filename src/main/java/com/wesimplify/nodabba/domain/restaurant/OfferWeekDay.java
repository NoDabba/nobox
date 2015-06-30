/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sdoddi
 *
 */
public class OfferWeekDay {
	
	private OfferWeekDayType offerWeekDayType;
	private int totalCapacity;
	private int offerCapacity;
	private int bookedCapacity; //TODO we need to decide if this fields is requied or not
	private List<GroupOffer> groupOffers = new ArrayList<GroupOffer>();
	/**
	 * @param offerWeekDayType
	 * @param totalCapacity
	 * @param offerCapacity
	 * @param bookedCapacity
	 * @param groupOffers
	 */
	public OfferWeekDay(OfferWeekDayType offerWeekDayType, int totalCapacity,
			int offerCapacity, int bookedCapacity) {
		super();
		this.offerWeekDayType = offerWeekDayType;
		this.totalCapacity = totalCapacity;
		this.offerCapacity = offerCapacity;
		this.bookedCapacity = bookedCapacity;
	}
	/**
	 * @return the offerWeekDayType
	 */
	public OfferWeekDayType getOfferWeekDayType() {
		return offerWeekDayType;
	}
	/**
	 * @return the totalCapacity
	 */
	public int getTotalCapacity() {
		return totalCapacity;
	}
	/**
	 * @return the offerCapacity
	 */
	public int getOfferCapacity() {
		return offerCapacity;
	}
	/**
	 * @return the bookedCapacity
	 */
	public int getBookedCapacity() {
		return bookedCapacity;
	}
	/**
	 * @return the groupOffers
	 */
	public List<GroupOffer> getGroupOffers() {
		return groupOffers;
	}
	
	/**
	 * adds the groupOffers to the list
	 * @param groupOffer
	 */
	public void addGroupOffers(GroupOffer groupOffer) {
		groupOffers.add(groupOffer);
	}
}
