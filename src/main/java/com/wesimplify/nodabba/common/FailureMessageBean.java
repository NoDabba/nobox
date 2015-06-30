/**
 * 
 */
package com.wesimplify.nodabba.common;

/**
 * @author sdoddi
 * Abstracts all repository, service and Controller exception with proper error code and Error description. 
 */
public class FailureMessageBean {

	public String errorCode;
	public String errorDescription;
	public String action;
	public String actionUrl;
	
	/**
	 * Stores all error details for given use case.
	 * @param errorCode
	 * @param errorDescription
	 * @param action
	 * @param actionUrl
	 */
	public FailureMessageBean(String errorCode, String errorDescription,
			String action, String actionUrl) {
		super();
		this.errorCode = errorCode;
		this.errorDescription = errorDescription;
		this.action = action;
		this.actionUrl = actionUrl;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorDescription
	 */
	public String getErrorDescription() {
		return errorDescription;
	}

	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @return the actionUrl
	 */
	public String getActionUrl() {
		return actionUrl;
	}

}
