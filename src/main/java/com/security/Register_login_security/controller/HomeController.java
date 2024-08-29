package com.security.Register_login_security.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.security.Register_login_security.entity.User;
import com.security.Register_login_security.repositry.UserRepo;
import com.security.Register_login_security.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private UserService service;
	
    @GetMapping("/")
    public String index() {
        return "index"; 
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

 

//    @GetMapping("/home")
//    public String homePage() {
//        return "home"; 
//    }

    @GetMapping("/login")
    public String login() {
        return "login"; 
    }

    @GetMapping("/user/profile")
    public String profile(Principal p, Model m) {
    String email= p.getName();
    User  user= repo.findByEmail(email);
    m.addAttribute("user", user);
        return "profile";
    }
    
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute User user, HttpSession session) {
//    	System.out.println(user);
    User u =service.saveUser(user);
    	
    	if(u != null) {
//    		System.out.println("Save SuccessFully !!!");
     		session.setAttribute("msg", "Register Successfully ");
    	} 
    	else  {
//    		System.out.println("Error in userSave Method on HomeController!!");
    		session.setAttribute("msg", "Error in userSave Method on HomeController!!");
    	}
    	return "redirect:/register";
    }
    
    @ModelAttribute
    public void commonUser(Principal p, Model m) {
    	if(p!= null) {
    		String email= p.getName();
    		    User  user= repo.findByEmail(email);
    		    m.addAttribute("user", user);
    	}
    		    
    }
}
