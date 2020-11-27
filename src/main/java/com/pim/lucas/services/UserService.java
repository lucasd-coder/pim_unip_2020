package com.pim.lucas.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.pim.lucas.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
	}
}
