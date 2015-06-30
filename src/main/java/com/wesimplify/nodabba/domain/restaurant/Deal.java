/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author sdoddi
 *
 */
public class Deal {

	private Date bookingDate;
	private OfferWeekDayType offerWeekDayType;
	private int minPax;	
	private int maxPax;
	private float offerPercentage;
	private BigDecimal basePrice;
	private BigDecimal offerPrice;
	private BigDecimal savePrice;
	private boolean isHoliday;
	
	/**
	 * @return the offerWeekDayType
	 */
	public OfferWeekDayType getOfferWeekDayType() {
		return offerWeekDayType;
	}
	/**
	 * @param offerWeekDayType the offerWeekDayType to set
	 */
	public void setOfferWeekDayType(OfferWeekDayType offerWeekDayType) {
		this.offerWeekDayType = offerWeekDayType;
	}
	/**
	 * @return the minPax
	 */
	public int getMinPax() {
		return minPax;
	}
	/**
	 * @param minPax the minPax to set
	 */
	public void setMinPax(int minPax) {
		this.minPax = minPax;
	}
	/**
	 * @return the maxPax
	 */
	public int getMaxPax() {
		return maxPax;
	}
	/**
	 * @param maxPax the maxPax to set
	 */
	public void setMaxPax(int maxPax) {
		this.maxPax = maxPax;
	}
	/**
	 * @return the offerPercentage
	 */
	public float getOfferPercentage() {
		return offerPercentage;
	}
	/**
	 * @param offerPercentage the offerPercentage to set
	 */
	public void setOfferPercentage(float offerPercentage) {
		this.offerPercentage = offerPercentage;
	}
	/**
	 * @return the basePrice
	 */
	public BigDecimal getBasePrice() {
		return basePrice;
	}
	/**
	 * @param basePrice the basePrice to set
	 */
	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}
	/**
	 * @return the offerPrice
	 */
	public BigDecimal getOfferPrice() {
		return offerPrice;
	}
	/**
	 * @param offerPrice the offerPrice to set
	 */
	public void setOfferPrice(BigDecimal offerPrice) {
		this.offerPrice = offerPrice;
	}
	/**
	 * @return the savePrice
	 */
	public BigDecimal getSavePrice() {
		return savePrice;
	}
	/**
	 * @param savePrice the savePrice to set
	 */
	public void setSavePrice(BigDecimal savePrice) {
		this.savePrice = savePrice;
	}
	/**
	 * @return the bookingDate
	 */
	public Date getBookingDate() {
		return bookingDate;
	}
	/**
	 * @param bookingDate the bookingDate to set
	 */
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	/**
	 * @return the isHoliday
	 */
	public boolean isHoliday() {
		return isHoliday;
	}
	/**
	 * @param isHoliday the isHoliday to set
	 */
	public void setHoliday(boolean isHoliday) {
		this.isHoliday = isHoliday;
	}
	
	
}
