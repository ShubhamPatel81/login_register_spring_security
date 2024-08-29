package com.security.Register_login_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.security.Register_login_security.entity.User;
import com.security.Register_login_security.repositry.UserRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo repo;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	public User saveUser(User user) {

		
	String password= 	passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		
		
		user.setRole("ROLE_USER");
		User newuser = repo.save(user);

		return newuser;
	}

	@Override
	public void removeSessionMessage() {
		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();
		session.removeAttribute("msg");
	}

}
