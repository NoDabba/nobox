package com.wesimplify.nodabba.presentation.social.login;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

import com.wesimplify.nodabba.domain.social.User;
import com.wesimplify.nodabba.integration.social.UserDao;

public class SocialSignInAdapter implements SignInAdapter {
	
	UserDao userDao;
	
	public SocialSignInAdapter(UserDao userDao) {
		this.userDao = userDao;
	}

	public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
    	
		User user = userDao.findUserById(localUserId);
  	
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getGrantedAuthorities()));
       
	
		return null;
    }
}