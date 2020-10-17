package br.com.msansone.api.securityservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.msansone.api.securityservice.model.User;
import br.com.msansone.api.securityservice.model.rest.ReturnError;
import br.com.msansone.api.securityservice.model.rest.UserResponse;
import br.com.msansone.api.securityservice.service.UserService;

@RestController
@RequestMapping("/api/security")
public class UserController {

	@Autowired
	UserService userService;
	
	private static Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> users =  userService.getAllUser();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
	@GetMapping("/user/id/{userId}")
	public ResponseEntity<UserResponse> getById(@PathVariable Long userId){
		User user = userService.getById(userId);
		UserResponse userResponse = new UserResponse();
		
		
		if (user!=null) {
			userResponse.setUser(user);
			userResponse.setReturnError(null);
			
		} else {
			userResponse.setUser(null);
			userResponse.setReturnError(new ReturnError("User not found", "getByLogin(login)"));
		}
		
		ResponseEntity<UserResponse> response = new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/user/login/{login}")
	public ResponseEntity<UserResponse> getByLogin(@PathVariable String login){
		User user = userService.getByLogin(login);
		UserResponse userResponse = new UserResponse();
		
		
		if (user!=null) {
			userResponse.setUser(user);
			userResponse.setReturnError(null);
			
		} else {
			userResponse.setUser(null);
			userResponse.setReturnError(new ReturnError("User not found", "getByLogin(login)"));
		}
		
		ResponseEntity<UserResponse> response = new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
		
		return response;
	}
	
	@GetMapping("/user/pass/login/{login}")
	public String getPasswordByLogin(@PathVariable String login){
		User user = userService.getByLogin(login);
		UserResponse userResponse = new UserResponse();
		String pass="";
		
		LOG.info("getPasswordByLogin({})", login);
		
		if (user!=null) {
			userResponse.setUser(user);
			pass = user.getPassword();
			userResponse.setReturnError(null);
			
		} else {
			userResponse.setUser(null);
			userResponse.setReturnError(new ReturnError("User not found", "getByLogin(login)"));
		}
		
		ResponseEntity<String> response = new ResponseEntity<String>(pass, HttpStatus.OK);
		
		return pass;
	}
	
}
;