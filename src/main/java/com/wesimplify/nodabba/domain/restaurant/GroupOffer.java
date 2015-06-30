/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;

import java.math.BigDecimal;

/**
 * @author sdoddi
 *
 */
public class GroupOffer {
	
	private GroupOfferType groupOfferType;
	private int minPax;
	private int maxPax;
	private float offerPercentage;
	private BigDecimal offerPrice;
	private BigDecimal savePrice;
	/**
	 * @param groupOfferType
	 * @param minPax
	 * @param maxPax
	 * @param offerPercentage
	 * @param offerPrice
	 * @param savePrice
	 */
	public GroupOffer(GroupOfferType groupOfferType, int minPax, int maxPax,
			float offerPercentage, BigDecimal offerPrice, BigDecimal savePrice) {
		super();
		this.groupOfferType = groupOfferType;
		this.minPax = minPax;
		this.maxPax = maxPax;
		this.offerPercentage = offerPercentage;
		this.offerPrice = offerPrice;
		this.savePrice = savePrice;
	}
	/**
	 * @return the groupOfferType
	 */
	public GroupOfferType getGroupOfferType() {
		return groupOfferType;
	}
	/**
	 * @return the minPax
	 */
	public int getMinPax() {
		return minPax;
	}
	/**
	 * @return the maxPax
	 */
	public int getMaxPax() {
		return maxPax;
	}
	/**
	 * @return the offerPercentage
	 */
	public float getOfferPercentage() {
		return offerPercentage;
	}
	/**
	 * @return the offerPrice
	 */
	public BigDecimal getOfferPrice() {
		return offerPrice;
	}
	/**
	 * @return the savePrice
	 */
	public BigDecimal getSavePrice() {
		return savePrice;
	}
}
