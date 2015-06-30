/**
 * 
 */
package com.wesimplify.nodabba.common;

/**
 * @author sdoddi
 *
 */
public final class FilterResults<T> {

	private int results;
	private T data;
	/**
	 * @param results
	 * @param data
	 */
	public FilterResults(int results, T data) {
		super();
		this.results = results;
		this.data = data;
	}
	/**
	 * @return the results
	 */
	public int getResults() {
		return results;
	}
	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}
}
