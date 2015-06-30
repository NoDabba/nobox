/**
 * 
 */
package com.wesimplify.nodabba.presentation.restaurant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wesimplify.nodabba.common.BookingRequest;
import com.wesimplify.nodabba.common.DateUtils;
import com.wesimplify.nodabba.common.EnhancedException;
import com.wesimplify.nodabba.domain.restaurant.Deal;
import com.wesimplify.nodabba.domain.restaurant.Meal;
import com.wesimplify.nodabba.domain.restaurant.OfferManager;
import com.wesimplify.nodabba.domain.restaurant.Restaurant;
import com.wesimplify.nodabba.domain.restaurant.RestaurantProfile;
import com.wesimplify.nodabba.service.restaurant.RestaurantGateway;

/**
 * @author sdoddi
 * This class will build the Restaurant Inquiry view bean for given Restaurant ID. 
 */
@Component
public class RestaurantInquiryViewBuilder {

	@Inject
	@Qualifier("restaurantGateway")
	private RestaurantGateway restaurantGateway;

	/**
	 * this method will call the restaurant service for given id. if the data available, this method will populate the view bean. If the
	 * isOffersRequired flag is true, this will make a call to offers service to get the offers data and pass that in the offers views bean.
	 * @param restaurantId
	 * @param isOffersRequired
	 * @return <code>RestaurantInquiryViewBean</code>
	 * @throws EnhancedException 
	 */
	public RestaurantInquiryViewBean getRestaurantDetailsById(String restaurantId, BookingRequest bookingRequest, boolean isOffersRequired) throws EnhancedException {

		// 1. get Restaurant details
		Restaurant restaurant = restaurantGateway.getRestaurantById(restaurantId, bookingRequest, isOffersRequired);
		RestaurantProfile profile = restaurant.getRestaurantProfile();

		// 2. populate Restaurant view bean
		RestaurantInquiryViewBean viewBean = new RestaurantInquiryViewBean(profile.getKey(), profile.getDisplayName(), profile.getAddress(),
				profile.getAdditionalInformation(), restaurant.getReviews().getOverallAverageRatings(), restaurant.getReviews().getNumberOfReviews(), 
				profile.getFaceBookLink(), profile.getLogoUrl(), profile.getRestaurantUrls());

		/// 3. populate meals view bean
		List<Meal> availableMeals = restaurant.getAvailableMeals();
		BigDecimal basePriceForSelectedMealType = null;
		List<MealViewBean> mealViewBeans = new ArrayList<MealViewBean>();
		for (Meal meal : availableMeals) {
			if (meal.getMealType().equals(bookingRequest.getMealType())) {
				basePriceForSelectedMealType = meal.getBasePrice();
			}
			MealViewBean mealViewBean = new MealViewBean(meal.getMealType().name(), meal.getTimings(), meal.getMenuDescription(), meal.getMenuUrl());
			mealViewBeans.add(mealViewBean);
		}
		viewBean.setMealViewBeans(mealViewBeans);

		// 4. populate offers view bean
		if (isOffersRequired) {

			// if the best offer base price is not available set the base price of the default meal type set at the restaurant
			OfferManager offerManager = restaurant.getOfferManager();
			Deal bestDeal = offerManager.getBestOffer();
			List<Date> holidayDates = restaurant.getHolidayDates();
			if (holidayDates != null) {
				for (Date holidayDate : holidayDates) {
					int difference = DateUtils.calculateDifference(bestDeal.getBookingDate(), holidayDate);
					if (difference > 0) {
						bestDeal.setHoliday(true);
						break;
					}
				}
			}

			if (!bestDeal.isHoliday()) {
				if (bestDeal.getBasePrice() == null) {
					bestDeal.setBasePrice(basePriceForSelectedMealType);
				}
			}

			// if the best offer base price is not available set the base price of the default meal type set at the restaurant
			List<Deal> sevenDayDeals = offerManager.getBestDealsForSevenDays();
			for (Deal sevenDayDeal : sevenDayDeals) {
				if (holidayDates != null) {
					holidayLoop: for (Date holidayDate : holidayDates) {
						int difference = DateUtils.calculateDifference(sevenDayDeal.getBookingDate(), holidayDate);
						if (difference > 0) {
							sevenDayDeal.setHoliday(true);
							break holidayLoop;
						}
					}
				}
				if (!sevenDayDeal.isHoliday()) {
					if (sevenDayDeal.getBasePrice() == null) {
						sevenDayDeal.setBasePrice(basePriceForSelectedMealType);
					}
				}
			}

			OfferViewBean offerViewBean = new OfferViewBean(bestDeal, sevenDayDeals);
			viewBean.setOfferViewBean(offerViewBean);
		}

		return null;
	}

}
