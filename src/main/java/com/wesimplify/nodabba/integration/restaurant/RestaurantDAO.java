/**
 * 
 */
package com.wesimplify.nodabba.integration.restaurant;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wesimplify.nodabba.common.BookingRequest;
import com.wesimplify.nodabba.common.DateUtils;
import com.wesimplify.nodabba.common.DateUtils.DateAddSubtractType;
import com.wesimplify.nodabba.common.FilterCriteria;
import com.wesimplify.nodabba.domain.restaurant.GroupOfferType;
import com.wesimplify.nodabba.domain.restaurant.MainPageOffer;
import com.wesimplify.nodabba.domain.restaurant.Meal;
import com.wesimplify.nodabba.domain.restaurant.MealType;
import com.wesimplify.nodabba.domain.restaurant.OfferManager;
import com.wesimplify.nodabba.domain.restaurant.OfferWeekDayType;
import com.wesimplify.nodabba.domain.restaurant.Restaurant;
import com.wesimplify.nodabba.domain.restaurant.RestaurantProfile;
import com.wesimplify.nodabba.domain.restaurant.Reviews;
import com.wesimplify.nodabba.domain.restaurant.search.RestaurantSearchDetail;
import com.wesimplify.nodabba.primitives.Address;

/**
 * @author sdoddi
 * This class to query database to return restaurant details for given request type
 */
public class RestaurantDAO {

	private final static Logger logger = LoggerFactory.getLogger(RestaurantDAO.class);

	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * This will get the preferred restaurant offers on the home page
	 * @param city
	 * @return List<MainPageOffer>
	 */
	public List<MainPageOffer> getMainPageOffers(String city) {
		
		List<MainPageOffer> mainPageOffers = new ArrayList<MainPageOffer>();
		//will pass this value to sql query
		if (logger.isDebugEnabled()) {
			logger.debug("Getting the Main Page offers for city {} ", city);
		}

		Address address = new Address();
		address.setAddress1("First Floor, Ektha Pearl");
		address.setAddress2("Above Ratnadeep Supermarket");
		address.setArea("Kothaguda");
		address.setCity("Hyderabad");
		address.setState("Telangana");
		address.setPincode("500081");
		address.setMobileNumber("9032814444");

		List<String> restaurantUrls = new ArrayList<String>();
		restaurantUrls.add("/RES_04/courosel1.png");

		RestaurantProfile restaurantProfile  = new RestaurantProfile("RES_04", "Anantara Fine Dining Bar & Banquets", 
				address, null, null, restaurantUrls, null);
		
		MainPageOffer offer = new MainPageOffer(restaurantProfile, 20.00f);

		mainPageOffers.add(offer);
		mainPageOffers.add(offer);
		mainPageOffers.add(offer);
		
		return mainPageOffers; 
	}

	/**
	 * This will get all Restaurant by City
	 * @param city
	 * @return List<RestaurantProfile>
	 */
	public List<RestaurantProfile> getRestaurantsByCity(String city) {
		List<RestaurantProfile> restaurantProfileList = new ArrayList<RestaurantProfile>();
		if ("hyderabad".equals(city)) {
			Address address1 = new Address();
			address1.setArea("Kondapur");
			RestaurantProfile profile1 = new RestaurantProfile("RES_01", "Anantara", address1, null, null, null, null);
			restaurantProfileList.add(profile1);

			Address address2 = new Address();
			address2.setArea("Hitech City");
			RestaurantProfile profile2 = new RestaurantProfile("RES_02", "Paraside", address2, null, null, null, null);
			restaurantProfileList.add(profile2);
		}
		else if ("bangalore".equals(city)) {
			Address address1 = new Address();
			address1.setArea("Mahadapura");
			RestaurantProfile profile1 = new RestaurantProfile("RES_03", "Jalsa", address1, null, null, null, null);
			restaurantProfileList.add(profile1);

			Address address2 = new Address();
			address2.setArea("Marathally Ring Road");
			RestaurantProfile profile2 = new RestaurantProfile("RES_04", "Pink", address2, null, null, null, null);
			restaurantProfileList.add(profile2);
		}
		return restaurantProfileList;
	}
	
	/**
	 * This method will return List of RestaurantSearchDetail for given filterCriteria. 
	 * @param filterCriteria
	 * @return List<RestaurantSearchDetail>
	 */
	public List<RestaurantSearchDetail> findRestaurantByFilterCriteria(FilterCriteria filterCriteria) {
		List<RestaurantSearchDetail> restaurantSearchDetailList = new ArrayList<RestaurantSearchDetail> ();
		
		Address address = new Address();
		address.setAddress1("First Floor, Ektha Pearl");
		address.setAddress2("Above Ratnadeep Supermarket");
		address.setArea("Kothaguda");
		address.setCity("Hyderabad");
		address.setState("Telangana");
		address.setPincode("500081");
		address.setMobileNumber("9032814444");

		List<String> restaurantUrls = new ArrayList<String>();
		restaurantUrls.add("/RES_04/courosel1.png");
		restaurantUrls.add("/RES_04/courosel2.png");
		restaurantUrls.add("/RES_04/courosel3.png");

		StringBuffer addInformation = new StringBuffer();
		addInformation.append("1) Valet Parking").append("\n").append("Alchol available");
		//available meal types will have meal type, menu description or menu url and available days 

		RestaurantProfile restaurantProfile  = new RestaurantProfile("RES_04", "Anantara Fine Dining Bar & Banquets", 
				address, "http://www.facebook.com/Anantara", "/RES_04/logo.png", restaurantUrls, addInformation.toString());
		
		RestaurantSearchDetail restaurantSearchDetail = new RestaurantSearchDetail(restaurantProfile, 
				new BigDecimal(900), 10, new BigDecimal(810), new BigDecimal(90), 4.8f);
		restaurantSearchDetailList.add(restaurantSearchDetail);
		
		return restaurantSearchDetailList;
	}

	/**
	 * this method finds the Restaurant for given id from database. 
	 * @param id
	 * @return Restaurant
	 */
	public Restaurant findRestaurantById(String id) {
		Address address = new Address();
		address.setAddress1("First Floor, Ektha Pearl");
		address.setAddress2("Above Ratnadeep Supermarket");
		address.setArea("Kothaguda");
		address.setCity("Hyderabad");
		address.setState("Telangana");
		address.setPincode("500081");
		address.setMobileNumber("9032814444");

		List<String> restaurantUrls = new ArrayList<String>();
		restaurantUrls.add("/RES_04/courosel1.png");
		restaurantUrls.add("/RES_04/courosel2.png");
		restaurantUrls.add("/RES_04/courosel3.png");

		StringBuffer addInformation = new StringBuffer();
		addInformation.append("1) Valet Parking").append("\n").append("Alchol available");
		//available meal types will have meal type, menu description or menu url and available days 

		RestaurantProfile restaurantProfile  = new RestaurantProfile("RES_04", "Anantara Fine Dining Bar & Banquets", 
				address, "http://www.facebook.com/Anantara", "/RES_04/logo.png", restaurantUrls, addInformation.toString());

		List<Meal> availableMeals = new ArrayList<Meal>();
		Meal brunchMealType = new Meal(MealType.BRUNCH, new BigDecimal(800));
		brunchMealType.setTimings("08:00 AM to 10:00 AM");
		availableMeals.add(brunchMealType);

		Meal lunchMealType = new Meal(MealType.LUNCH, new BigDecimal(1000));
		lunchMealType.setTimings("12:00 PM to 03:00 PM");
		availableMeals.add(lunchMealType);

		Meal dinnerMealType = new Meal(MealType.DINNER, new BigDecimal(900));
		dinnerMealType.setTimings("07:00 PM to 10:00 PM");
		availableMeals.add(dinnerMealType);

		List<Date> holidayDates = new ArrayList<Date>();
		holidayDates.add(new Date());

		Restaurant restaurant = new Restaurant(restaurantProfile);
		restaurant.setAvailableMeals(availableMeals);
		restaurant.setReviews(new Reviews(3.5f, 256));
		restaurant.setHolidayDates(holidayDates);
		return restaurant;
	}

	/**
	 * This will create the OfferManager for given restaurant Id and selected Date
	 * @param id
	 * @param selectedDate
	 * @return OfferManager
	 */
	public OfferManager findOffersByRestaurantId(String id, BookingRequest bookingRequest) {
		OfferManager manager = new OfferManager(bookingRequest);
		manager.addOffer(1, "Offer1", new Date(), new Date(), MealType.BRUNCH, new BigDecimal(800), OfferWeekDayType.WED, 
				100, 50, 30, GroupOfferType.SMALL, 1, 2, 10, new BigDecimal(720), new BigDecimal(80));
		manager.addOffer(1, "Offer1", new Date(), new Date(), MealType.LUNCH, new BigDecimal(1000), OfferWeekDayType.WED, 
				100, 50, 30, GroupOfferType.SMALL, 1, 2, 10, new BigDecimal(900), new BigDecimal(100));
		manager.addOffer(1, "Offer1", new Date(), new Date(), MealType.DINNER, new BigDecimal(900), OfferWeekDayType.WED, 
				100, 50, 30, GroupOfferType.SMALL, 1, 2, 10, new BigDecimal(810), new BigDecimal(90));
		manager.addBlackListedDateToOffer(1, DateUtils.dateAddOrSubtract(new Date(), DateAddSubtractType.DAYS, 10));
		manager.addBlackListedDateToOffer(1, DateUtils.dateAddOrSubtract(new Date(), DateAddSubtractType.DAYS, 40));
		return manager;
	}
}
