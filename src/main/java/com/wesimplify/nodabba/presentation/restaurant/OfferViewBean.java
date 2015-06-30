/**
 * 
 */
package com.wesimplify.nodabba.presentation.restaurant;

import java.util.List;

import com.wesimplify.nodabba.domain.restaurant.Deal;

/**
 * @author sdoddi
 *
 */
public class OfferViewBean {
	private Deal bestOffer;
	private List<Deal> otherApplicableOffers;
	/**
	 * @param currentOffer
	 * @param otherApplicableOffers
	 */
	public OfferViewBean(Deal bestOffer, List<Deal> otherApplicableOffers) {
		super();
		this.bestOffer = bestOffer;
		this.otherApplicableOffers = otherApplicableOffers;
	}
	/**
	 * @return the bestOffer
	 */
	public Deal getBestOffer() {
		return bestOffer;
	}
	/**
	 * @return the otherApplicableOffers
	 */
	public List<Deal> getOtherApplicableOffers() {
		return otherApplicableOffers;
	}
	

}
