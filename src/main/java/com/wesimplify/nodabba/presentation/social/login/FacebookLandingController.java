package com.wesimplify.nodabba.presentation.social.login;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.ImageType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wesimplify.nodabba.common.SocialConstants;
import com.wesimplify.nodabba.common.SocialUtil;
import com.wesimplify.nodabba.domain.social.SocialUser;
import com.wesimplify.nodabba.domain.social.SocialUser.Provider;

/**
 * @author sdoddi
 * Handles requests for the application Facebook's successful authentication. This class will populate the <code>SocialUser</code> object with Facebook logged in user.
 * The <code>SocialUser</code> is 
 */
@Controller
public class FacebookLandingController {
	
	/**
	 * This holds the logged in Facebook user connection details. This is created per request.
	 */
	@Inject
    @Qualifier("facebook")
    Connection<Facebook> facebook;
	
	private static final Logger logger = LoggerFactory.getLogger(FacebookLandingController.class);
	
	@RequestMapping(value = "/social/facebook/welcome", method = RequestMethod.GET)
	public String welcome(HttpServletRequest request, Model model) {
		Facebook fb = facebook.getApi();
		
		if (logger.isDebugEnabled()) {
			logger.debug("Facebook user details : {}", (fb != null ? (fb.isAuthorized()) : "null") );
		}
		
		// Uncomment the following line to see how easy posting to your timeline is.
		//fb.feedOperations().post(facebook.getKey().getProviderUserId(), "I just tried out Spring social API !");
		
		/*int numFriends = fb.friendOperations().getFriends().size();
		
		model.addAttribute("facebook", true);
		model.addAttribute("numFriends", numFriends);
		model.addAttribute("name", facebook.fetchUserProfile().getName());*/
		
		//UserProfile profile = facebook.fetchUserProfile();
		
		FacebookLink link = new FacebookLink("https://apps.facebook.com/testforpageapp/", 
		        "NODabba Home", 
		        "NODABBA : Book buffets at the best price", 
		        "FIND THE BEST RESTAURTANTS AT THE BEST PRICE more than 19,000 restaurants in India");
		//fb.feedOperations().postLink("FIND THE BEST RESTAURTANTS AT THE BEST PRICE more than 19,000 restaurants in India", link);
		
		//fb.feedOperations().updateStatus("FIND THE BEST RESTAURTANTS AT THE BEST PRICE more than 19,000 restaurants in India");
		
		FacebookProfile profile = fb.userOperations().getUserProfile();
		
		SocialUser socialUser = new SocialUser();
		socialUser.setCurrentProvider(Provider.FACEBOOK);
		socialUser.setDisplayName(facebook.getDisplayName());
		if (profile != null) {
			socialUser.setFirstName(profile.getFirstName());
			socialUser.setLastName(profile.getLastName());
			socialUser.setUserName(profile.getUsername());
			socialUser.setEmail(profile.getEmail());
			socialUser.setName(profile.getName());
			socialUser.getAdditionalProperties().put(SocialConstants.USER_IMAGE_URL, 
					SocialUtil.convertImageByteArrayToBase64(fb.userOperations().getUserProfileImage(ImageType.LARGE)));
		}
		
		
		request.getSession().setAttribute(SocialConstants.SOCIAL_USER_KEY_ATTRIBUTE, socialUser);
		
		return "forward:/start";
	}
	
}
