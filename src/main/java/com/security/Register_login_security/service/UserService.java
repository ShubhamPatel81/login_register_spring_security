package com.security.Register_login_security.service;

import com.security.Register_login_security.entity.User;

public interface UserService {
	public User saveUser(User  user);

	public void removeSessionMessage();  
}
