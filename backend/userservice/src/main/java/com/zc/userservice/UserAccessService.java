package com.zc.userservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@CrossOrigin
@RestController
public class UserAccessService {
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@GetMapping(path="/")
	public @ResponseBody String reSt() {
		return "You received a message from User-Service Microservice.";
	}

	@GetMapping("/all")
	@HystrixCommand(fallbackMethod = "defaultGetAllUsers")
	public ResponseEntity<List<User>> getAllUsersFromDB() {
		
		List<ServiceInstance> instances = discoveryClient.getInstances("user-database");
		ServiceInstance s = instances.get(0);
		String serviceAddress = "http://" + s.getHost() + ":" + s.getPort() + "/all";
		
		@SuppressWarnings("unchecked")
		Class<List<User>> cls = (Class<List<User>>)(Object)List.class;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<User>> result = restTemplate.getForEntity(serviceAddress, cls);
		
		return result;
	}
	
	@GetMapping(path="/login")
	public @ResponseBody String Login (@RequestParam String name, @RequestParam String passwd) {
		
		ResponseEntity<List<User>> result = getAllUsersFromDB();
		
		List<User> userList = result.getBody();
		//return userList.get(0).getName();
		// Validate name and passwd with data from database
		for (User u: userList) {
			if (u.getName() == name && u.getPasswd() == passwd) {
				return "correct";
			}
		}
		
		return "wrong";
	}
	
	public ResponseEntity<List<User>> defaultGetAllUsers() {
		List<User> ul = new ArrayList<User>();
		User n = new User();
		n.setName("test");
		n.setPasswd("test");
		n.setEmail("test@test.com");
		ul.add(n);
		return ResponseEntity.accepted().body(ul);
	}
	
}
