/**
 * 
 */
package com.wesimplify.nodabba.common;

/**
 * @author sdoddi
 * Common Exception for the application
 */
@SuppressWarnings("serial")
public class EnhancedException extends Exception {

	private String errorCode;
	public EnhancedException(String errorCode, String errorDescription)
	{
		super(errorCode+":"+errorDescription);
		this.errorCode = errorCode;
	}
	public EnhancedException(String errorCode, String errorDescription, Exception ex)
	{
		super(errorCode+":"+errorDescription, ex);
		this.errorCode = errorCode;
	}
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
}
