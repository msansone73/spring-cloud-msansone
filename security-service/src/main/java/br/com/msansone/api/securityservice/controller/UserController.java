package br.com.msansone.api.securityservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.msansone.api.securityservice.model.User;
import br.com.msansone.api.securityservice.service.UserService;

@RestController
@RequestMapping("/api/security")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/user")
	public ResponseEntity<List<User>> getAllUser() {
		List<User> users =  userService.getAllUser();
		return new ResponseEntity<List<User>>(users,HttpStatus.OK);
	}
	
}
;