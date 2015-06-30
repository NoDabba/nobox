package com.wesimplify.nodabba.presentation.social.login;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

import com.wesimplify.nodabba.integration.social.UserDao;

public class AccountConnectionSignUp implements ConnectionSignUp {

	private UserDao userDao;
	
	public AccountConnectionSignUp(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public String execute(Connection<?> connection) {
		
		/*UserProfile profile = connection.fetchUserProfile();
		String userName = profile.getEmail();
		/if (StringUtils.isEmpty(userName)) {
			userName = profile.getUsername();
		}
		
		if (StringUtils.isEmpty(userName)) {
			userName = connection.getKey().getProviderId() + ":"+connection.getKey().getProviderUserId();
		}*/
		
		
		String userName = connection.getKey().getProviderUserId();
		userDao.createUser(userName);
		
		return userName;
	}
}
