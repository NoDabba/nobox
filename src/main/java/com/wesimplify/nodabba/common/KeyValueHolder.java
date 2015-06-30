/**
 * 
 */
package com.wesimplify.nodabba.common;

/**
 * @author sdoddi
 * Place holder to store Key and Value for any type of data
 */
public final class KeyValueHolder {

	/**
	 * assumption here is all key types are in String datatype format
	 */
	private String key;
	/*
	 * assumption here is all value types are in String datatype format 
	 */
	private String value;
	
	/**
	 * @param key
	 * @param value
	 */
	public KeyValueHolder(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
