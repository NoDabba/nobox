/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;

import java.util.Date;

/**
 * @author sdoddi
 *
 */
public class Review {
	private String user;
	private String comments;
	private float individualRating;
	private Date date;
	/**
	 * @param user
	 * @param comments
	 * @param individualRating
	 * @param date
	 */
	public Review(String user, String comments, float individualRating,
			Date date) {
		super();
		this.user = user;
		this.comments = comments;
		this.individualRating = individualRating;
		this.date = date;
	}
	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @return the individualRating
	 */
	public float getIndividualRating() {
		return individualRating;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
}
