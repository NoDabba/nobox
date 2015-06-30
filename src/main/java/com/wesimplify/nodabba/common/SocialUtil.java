/**
 * 
 */
package com.wesimplify.nodabba.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import com.wesimplify.nodabba.domain.social.SocialUser;

/**
 * @author sdoddi
 * Helper class to provide required functionality for Social Controllers and its helpers
 */
public final class SocialUtil {
	
	
	/**
	 * helper method to return the <code>SocialUser</code> from HttpSession if available.
	 * @param request
	 * @return SocialUser
	 */
	public static SocialUser getSocialUser(HttpServletRequest request)
	{
		HttpSession session = request.getSession(false); 
		if (session == null)
		{
			return null;
		}
		
		SocialUser user = (SocialUser) session.getAttribute(SocialConstants.SOCIAL_USER_KEY_ATTRIBUTE);
		return user;
	}
	
	/**
	 * This method will check if first and last name available if not available will return display name. Otherwise, empty
	 * string is returned.
	 * @param user
	 * @return String
	 */
	public static String getSocialUserName(SocialUser user) {
		String name = user.getDisplayName();
		if (StringUtils.isEmpty(name)) {
			name = user.getFirstName();
			if (!StringUtils.isEmpty(user.getLastName())) {
				name = name + " " + user.getLastName();
			}
		}
		return name;
	}
	
	/**
	 * helper method to convert image to base 64 string. If the passed image array is null, this method will return null
	 * @param image
	 * @return String
	 */
	public static String convertImageByteArrayToBase64(byte image[]) {
		if (image != null) {
			byte[] encImage = Base64.encodeBase64(image);
			return new String(encImage);
		}
		return null;
	}
}
