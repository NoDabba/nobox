/**
 * 
 */
package com.wesimplify.nodabba.primitives;

/**
 * @author sdoddi
 *
 */
public class City {
	
	public String id;
	private String name;
	private String state;
	/**
	 * @param id
	 * @param name
	 */
	public City(String id, String name, String state) {
		super();
		this.id = id;
		this.name = name;
		this.state = state;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
}
