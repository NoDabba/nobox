/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;

import java.util.List;

/**
 * @author sdoddi
 * this object will have the restaurant reviews 
 */
public class Reviews {
	
	private float overallAverageRatings;
	private long numberOfReviews;
	private List<Review> reviews;
	/**
	 * @param overallAverageRatings
	 * @param numberOfReviews
	 */
	public Reviews(float overallAverageRatings, long numberOfReviews) {
		super();
		this.overallAverageRatings = overallAverageRatings;
		this.numberOfReviews = numberOfReviews;
	}
	/**
	 * @return the overallAverageRatings
	 */
	public float getOverallAverageRatings() {
		return overallAverageRatings;
	}
	/**
	 * @return the numberOfReviews
	 */
	public long getNumberOfReviews() {
		return numberOfReviews;
	}
	/**
	 * @return the reviews
	 */
	public List<Review> getReviews() {
		return reviews;
	}
	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}
