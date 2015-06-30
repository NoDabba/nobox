/**
 * 
 */
package com.wesimplify.nodabba.presentation.booking;

import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wesimplify.nodabba.common.BookingRequest;
import com.wesimplify.nodabba.common.EnhancedException;
import com.wesimplify.nodabba.domain.restaurant.Restaurant;
import com.wesimplify.nodabba.service.restaurant.RestaurantGateway;

/**
 * @author sdoddi
 *
 */
@Component
public class BookingViewBuilder {

	private static final Logger logger = LoggerFactory.getLogger(BookingViewBuilder.class);

	@Inject
    @Qualifier("restaurantGateway")
	private RestaurantGateway restaurantGateway;

	/**
	 * this 
	 * @param restaurantId
	 * @param offerId
	 * @param bookingRequest
	 * @return
	 */
	public Map<String, Object> getBookingDetails(String restaurantId, String offerId, BookingRequest bookingRequest) {
		
		Restaurant restaurant = null;
		try {
			restaurant = restaurantGateway.getRestaurantById(restaurantId, bookingRequest, true);
		} catch (EnhancedException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
