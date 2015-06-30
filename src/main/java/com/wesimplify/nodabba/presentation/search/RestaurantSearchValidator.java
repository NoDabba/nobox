/**
 * 
 */
package com.wesimplify.nodabba.presentation.search;

import com.wesimplify.nodabba.common.StringUtils;

/**
 * @author sdoddi
 * This class will validate the SearchRestaurantForm before processing by controller
 */
public final class RestaurantSearchValidator {

	/**
	 * this method will validate the search restaurant form for any missing or invalid data before processing for searching. if any errors found, this will 
	 * return true other wise false
	 * @param searchForm
	 * @return
	 */
	public static boolean validate(SearchRestaurantForm searchForm) 
	{
	
		if (StringUtils.isBlank(searchForm.getCityId())) {
			return true;
		}
		
		if (StringUtils.isBlank(searchForm.getSelectionKey())) {
			if (StringUtils.isBlank(searchForm.getSearchCriteria())) {
				return true;
			}
		}
		
		if (StringUtils.isBlank(searchForm.getSearchCriteria())) {
			if (StringUtils.isBlank(searchForm.getSelectionKey())) {
				return true;
			}
		}
		
		return false;
	}
}
