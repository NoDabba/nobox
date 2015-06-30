/**
 * 
 */
package com.wesimplify.nodabba.domain.social;

import java.util.HashMap;
import java.util.Map;

import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;

/**
 * @author sdoddi
 * Social Domain object to store all logged in social user details.
 */
public class SocialUser {
	
	/**
	 * 
	 * @author sdoddi
	 * list of all supported social providers
	 */
	public enum Provider {
		FACEBOOK,
		TWITTER,
		LINKEDIN
	}
	/**
	 * logged in social display name
	 */
	private String displayName;
	/**
	 * logged in social user name
	 */
	private String name;
	/**
	 * logged in social user name
	 */
	private String userName;
	/**
	 * logged in social first name
	 */
	private String firstName;
	/**
	 * logged in social last name
	 */
	private String lastName;
	/**
	 * logged in social email address. this can be null as twitter doesn't return email address
	 */
	private String email;
	/**
	 * user's logged in social service provider type
	 */
	private Provider currentProvider;
	/**
	 * any additional details like user profile, cover pictures, friends list etc.. This will vary from provider from provider.
	 */
	private Map<String, String> additionalProperties = new HashMap<String, String>();
	
	private PagedList<Reference> friendsList;
	
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the currentProvider
	 */
	public Provider getCurrentProvider() {
		return currentProvider;
	}
	/**
	 * @param currentProvider the currentProvider to set
	 */
	public void setCurrentProvider(Provider currentProvider) {
		this.currentProvider = currentProvider;
	}
	/**
	 * @return the additionalProperties
	 */
	public Map<String, String> getAdditionalProperties() {
		return additionalProperties;
	}
	/**
	 * @param additionalProperties the additionalProperties to set
	 */
	public void setAdditionalProperties(Map<String, String> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}
	/**
	 * @return the friendsList
	 */
	public PagedList<Reference> getFriendsList() {
		return friendsList;
	}
	/**
	 * @param friendsList the friendsList to set
	 */
	public void setFriendsList(PagedList<Reference> friendsList) {
		this.friendsList = friendsList;
	}
	
}
