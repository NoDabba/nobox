/**
 * 
 */
package com.wesimplify.nodabba.service.primitives;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

import com.wesimplify.nodabba.common.EnhancedException;
import com.wesimplify.nodabba.integration.primitives.PrimitivesDAO;
import com.wesimplify.nodabba.primitives.City;
import com.wesimplify.nodabba.services.BusinessAwareService;

/**
 * @author sdoddi
 * This service will returns all static and maintenance related information back to callers
 */
public class PrimitivesGateway {

	private final static Logger logger = LoggerFactory.getLogger(PrimitivesGateway.class);

	/**
	 * this will help services to return the required declared bean from Spring Framework
	 */
	private BusinessAwareService businessAwareService;
	
	/**
	 * @param businessAwareService the businessAwareService to set
	 */
	public void setBusinessAwareService(BusinessAwareService businessAwareService) {
		this.businessAwareService = businessAwareService;
	}
	
	
	/**
	 * This will return all unique restaurant cities available with the system
	 * @return List<String>
	 */
	@Cacheable("uniqueCities")
	public List<City> getUniqueCities() throws EnhancedException{
		PrimitivesDAO primitivesDAO = businessAwareService.getBean("primitivesDAO", PrimitivesDAO.class);
		try
		{
			List<City> citiesList = primitivesDAO.getUniqueCities();
			return citiesList;
		}
		catch (Exception ex) {
			logger.error("Error when accessing Cities from DAO", ex);
			throw new EnhancedException("ERR_PRI_CITIES_001", "error getting unique cities from database", ex);
		}
	}
}
