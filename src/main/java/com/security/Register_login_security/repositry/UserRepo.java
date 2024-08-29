package com.security.Register_login_security.repositry;
import org.springframework.data.jpa.repository.JpaRepository;
import com.security.Register_login_security.entity.User;
import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer> {
	
	public User findByEmail(String email); 
	
}
