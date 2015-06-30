/**
 * 
 */
package com.wesimplify.nodabba.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author sdoddi
 * This class will hold the list of filter criteria in the same order.
 */
public final class FilterCriteria {

	/**
	 * stores all criteria in map in the same insertion order
	 */
	private Map<String, Object> criteriaMap = new LinkedHashMap<String, Object>();
	
	/**
	 * adds the elements to the criteriaMap
	 * @param key
	 * @param value
	 */
	public void addCriteria(String key, Object value)
	{
		this.criteriaMap.put(key, value);
	}
	
	/**
	 * this will return the stored value for gioven key otherwise null is returned. the calling class should handle the null checks
	 * @param key
	 * @return Object
	 */
	public Object getCriteria(String key) {
		return this.criteriaMap.get(key); 
	}
	
	/**
	 * returns all keys 
	 * @return String[]
	 */
	public String[] getAllCriteriaKeys() {
		return this.criteriaMap.keySet().toArray(new String[criteriaMap.size()]);
	}
}
