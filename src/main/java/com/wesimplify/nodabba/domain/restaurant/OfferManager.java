/**
 * 
 */
package com.wesimplify.nodabba.domain.restaurant;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.wesimplify.nodabba.common.BookingRequest;
import com.wesimplify.nodabba.common.DateUtils;

/**
 * @author sdoddi
 * Manages all offers for individual restaurant
 *
 */
public class OfferManager {

	/**stores the available offers to list*/
	private List<Offer> availableOffers = new ArrayList<Offer>();
	
	/**stores the booking date*/
	private BookingRequest bookingRequest;
	
	/**
	 * stores the booking date to local variable
	 * @param bookingDate
	 */
	public OfferManager(BookingRequest bookingRequest) {
		this.bookingRequest = bookingRequest;
	}

	/**
	 * stores the data to Offers list. 
	 * @param offerid
	 * @param validFromDate
	 * @param validToDate
	 * @param mealType
	 * @param basePrice
	 * @param offerWeekDayType
	 * @param totalCapacity
	 * @param offerCapacity
	 * @param bookedCapacity
	 * @param groupOfferType
	 * @param pax
	 * @param offerPercentage
	 * @param offerPrice
	 * @param savePrice
	 */
	public void addOffer(int offerid, String displayName, Date validFromDate, Date validToDate, MealType mealType, BigDecimal basePrice, 
			OfferWeekDayType offerWeekDayType, int totalCapacity, int offerCapacity, 
			int bookedCapacity, GroupOfferType groupOfferType, int minpax, int maxpax, float offerPercentage, BigDecimal offerPrice, BigDecimal savePrice) {
		Offer offer = findOfferbyId(offerid);
		if (offer == null) {
			offer = new Offer(offerid, displayName, validFromDate, validToDate);
			availableOffers.add(offer);
		}

		Meal meal = findMealbyMealTypeFromOffer(offer, mealType);
		if (meal == null) {
			meal = new Meal(mealType, basePrice);
			offer.addAvailableMeals(meal);
		}

		OfferWeekDay offerWeekDay = findOfferWeekDayByTypeFromMeal(meal, offerWeekDayType);
		if (offerWeekDay == null) {
			offerWeekDay = new OfferWeekDay(offerWeekDayType, totalCapacity, offerCapacity, bookedCapacity);
			meal.addOfferWeekDays(offerWeekDay);
		}

		offerWeekDay.addGroupOffers(new GroupOffer(groupOfferType, minpax, maxpax, offerPercentage, offerPrice, savePrice));
	}

	/**
	 * Returns the available offers
	 * @return List<Offer>
	 */
	public List<Offer> getOffers() {
		return availableOffers;
	}

	/**
	 * adds the black listed date to the offer
	 * @param offerId
	 * @param blackListedDate
	 */
	public void addBlackListedDateToOffer(int offerId, Date blackListedDate) {
		Offer offer = findOfferbyId(offerId);
		if (offer != null) {
			offer.addBlackListedDates(blackListedDate);
		}

	}
	/**
	 * gets the offer By Id. If not found, this will return null
	 * @param offerId
	 * @return Offer
	 */
	public Offer findOfferbyId(int offerId) {
		for (Offer offer : availableOffers) {
			if (offer.getId() == offerId) {
				return offer;
			}
		}
		return null;
	}

	/**
	 * finds the Meal by meal type from given Offer
	 * @param offer
	 * @param mealType
	 * @return Meal
	 */
	private Meal findMealbyMealTypeFromOffer(Offer offer, MealType mealType) {
		for (Meal meal : offer.getAvailableMeals()) {
			if (meal.getMealType().equals(mealType))
			{
				return meal;
			}
		}
		return null;
	}

	/**
	 * finds the OfferWeekDay by OfferWeekDayType from given Meal
	 * @param meal
	 * @param offerWeekDayType
	 * @return
	 */
	private OfferWeekDay findOfferWeekDayByTypeFromMeal(Meal meal, OfferWeekDayType offerWeekDayType) {
		for (OfferWeekDay offerWeekDay : meal.getOfferWeekDays()) {
			if (offerWeekDay.getOfferWeekDayType().equals(offerWeekDayType)) {
				return offerWeekDay;
			}
		}
		return null;
	}

	/**
	 * gets the best deal for given bookingdate, mealType and Pax
	 * @param bookingDate
	 * @param mealType
	 * @param pax
	 * @return deal
	 */
	private Deal findBestOfferByDateAndPaxAndMealtype(Date bookingDate, MealType mealType, int pax) {
		Deal deal = null;
		OfferWeekDayType offerWeekDayType = getDayOfTheWeekTypeFromDate(bookingDate);
		offerLoop: for (Offer offer : availableOffers) {
			Date fromDate = offer.getValidFromDate();
			Date toDate = offer.getValidToDate();
			
			// 1. check if the request booking date is between offers from and to date validity
			if (! (fromDate.getTime() <= bookingDate.getTime() && bookingDate.getTime() <= toDate.getTime())) {
				continue offerLoop;
			}
			
			// 2. check if the request booking date is not a black listed date
			List<Date> blackListedDates = offer.getBlackListedDates();
			if (blackListedDates != null) {
				for(Date blackListedDate : blackListedDates) {
					int difference = DateUtils.calculateDifference(bookingDate, blackListedDate);
					if (difference > 0) {
						continue offerLoop;
					}
				}
			}
			
			List<Meal> meals = offer.getAvailableMeals();
			for (Meal meal : meals) {
				if (meal.getMealType().equals(mealType)) {
					List<OfferWeekDay> offerWeekDays = meal.getOfferWeekDays();
					for (OfferWeekDay offerWeekDay : offerWeekDays) {
						if (offerWeekDay.getOfferWeekDayType().equals(offerWeekDayType)) {
							List<GroupOffer> groupOffers = offerWeekDay.getGroupOffers();
							for (GroupOffer groupOffer : groupOffers) {
								if (pax >= groupOffer.getMinPax() && pax <= groupOffer.getMaxPax()) {
									if (deal == null) {
										deal = new Deal();
										deal.setOfferWeekDayType(offerWeekDay.getOfferWeekDayType());
										deal.setMinPax(groupOffer.getMinPax());
										deal.setMaxPax(groupOffer.getMaxPax());
										deal.setOfferPercentage(groupOffer.getOfferPercentage());
										deal.setOfferPrice(groupOffer.getOfferPrice());
										deal.setBasePrice(meal.getBasePrice());
										deal.setSavePrice(groupOffer.getSavePrice());
										deal.setBookingDate(bookingDate);
									}
									else
									{
										if (groupOffer.getSavePrice().doubleValue() > deal.getSavePrice().doubleValue()) {
											deal = new Deal();
											deal.setOfferWeekDayType(offerWeekDay.getOfferWeekDayType());
											deal.setMinPax(groupOffer.getMinPax());
											deal.setMaxPax(groupOffer.getMaxPax());
											deal.setOfferPercentage(groupOffer.getOfferPercentage());
											deal.setOfferPrice(groupOffer.getOfferPrice());
											deal.setBasePrice(meal.getBasePrice());
											deal.setSavePrice(groupOffer.getSavePrice());
											deal.setBookingDate(bookingDate);
										}
									}
								}
							}// group offers loop
						}
					}//offerWeekDays loop
				}
			}//meal loop
		}
		return deal;
	}
	
	/**
	 * this method gets the best offer for given booking request from offers (overlapping offers based on the booking dates)
	 * @return Deal
	 */
	public Deal getBestOffer() {
		Deal deal = findBestOfferByDateAndPaxAndMealtype(bookingRequest.getBookingDate(), bookingRequest.getMealType(), bookingRequest.getPax());
		if (deal == null) {
			OfferWeekDayType offerWeekDayType = getDayOfTheWeekTypeFromDate(bookingRequest.getBookingDate());
			deal = new Deal();
			deal.setOfferWeekDayType(offerWeekDayType);
			deal.setBookingDate(bookingRequest.getBookingDate());
		}
		return deal;
	}

	/**
	 * This method will get the best offers for seven days pre and post booking date. if the deal is not available for a given/formatted booking date,
	 * this will get generate default Deal without any base price. the calling method has to fill the base price from the Restaurant' meal Type default base price
	 * @return List<Deal>
	 */
	public List<Deal> getBestDealsForSevenDays() {
		List<Deal> bestSevenDayDealsList = new ArrayList<Deal>();
		Date[] sevenDateList = DateUtils.arrayOfDateRangesBeforeAndAfter(bookingRequest.getBookingDate());
		for (Date formattedBookingDate : sevenDateList) {
			Deal deal = findBestOfferByDateAndPaxAndMealtype(formattedBookingDate, bookingRequest.getMealType(), bookingRequest.getPax());
			if(deal == null){
				OfferWeekDayType offerWeekDayType = getDayOfTheWeekTypeFromDate(formattedBookingDate);
				deal = new Deal();
				deal.setOfferWeekDayType(offerWeekDayType);
				deal.setBookingDate(formattedBookingDate);
			}
			bestSevenDayDealsList.add(deal);
		}
		return bestSevenDayDealsList;
	}
	
	/**
	 * gets the OfferWeekDayTye from the given Date
	 * @param date
	 * @return OfferWeekDayType
	 */
	private OfferWeekDayType getDayOfTheWeekTypeFromDate(Date date) {
		String day = new SimpleDateFormat("EEE").format(new Date());
		return OfferWeekDayType.lookup(day);
	}
}
