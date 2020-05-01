package com.zc.userdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/")
public class UserMainController {

	@Autowired
	private UserRepository userRepository;
	
	@CrossOrigin
	@PostMapping(path="/add") 
	public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String passwd, @RequestParam String email) {
		
		User n = new User();
	    n.setName(name);
	    n.setPasswd(passwd);
	    n.setEmail(email);
	    userRepository.save(n);
	    return "User: [" + n.getName() + "] saved.";
	}
	
	@CrossOrigin
	@GetMapping(path="/")
	public @ResponseBody String reSt() {
		return "You received a message from User-Database Microservice.";
	}
	
	@CrossOrigin
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}
  
}

