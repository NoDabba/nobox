/**
 * 
 */
package com.wesimplify.nodabba.integration.primitives;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.wesimplify.nodabba.primitives.City;

/**
 * @author sdoddi
 *
 */
public class PrimitivesDAO {
	private final static Logger logger = LoggerFactory.getLogger(PrimitivesDAO.class);

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	/**
	 * This method will query database to return all unique cities with IDS
	 * @return
	 */
	public List<City> getUniqueCities() {
		City city = new City("TS001", "Hyderabad", "TS");
		List <City> citiesList = new ArrayList<City>();
		citiesList.add(city);
		return citiesList;
	}

}
