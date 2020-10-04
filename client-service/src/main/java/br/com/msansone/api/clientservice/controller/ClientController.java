package br.com.msansone.api.clientservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/ola")
	public ResponseEntity<String> getAllUser() {
		String body = "Ola";
		
		ResponseEntity<String> ret = restTemplate.getForEntity("http://security-service/api/security/user/pass/login/msansone", String.class);
		
		return new ResponseEntity<String>(ret.getBody(),HttpStatus.OK);
	}

	
}
