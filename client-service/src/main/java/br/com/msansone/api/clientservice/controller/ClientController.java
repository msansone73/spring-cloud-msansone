package br.com.msansone.api.clientservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.msansone.api.clientservice.model.Client;
import br.com.msansone.api.clientservice.repository.ClientRepository;
import br.com.msansone.api.clientservice.service.ClientService;



@RestController
@RequestMapping("/api/client")
public class ClientController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient eureka;
	
	@Autowired
	private ClientService clientService;
	
	@GetMapping("/ola")
	public ResponseEntity<String> getAllUser() {
		String body = "Ola";
		
		ResponseEntity<String> ret = restTemplate.getForEntity("http://security-service/api/security/user/pass/login/msansone", String.class);
		System.out.println("");
		System.out.println("lista de secuirty-service:");
		eureka.getInstances("security-service").stream()
		.forEach( cli -> System.out.println(cli.getHost()+cli.getPort()));
		System.out.println("fim:");
		
		return new ResponseEntity<String>(ret.getBody(),HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Client>> getAllClients(){
		
		List<Client> clients = clientService.getAll();
		
		return new ResponseEntity<>(clients, HttpStatus.OK );
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable Long id){
		
		Client client = clientService.getById(id);
		
		return new ResponseEntity<>(client, HttpStatus.OK );
	}
	
	@PostMapping("")
	public ResponseEntity<Client> addClient(@RequestBody Client client){

		Client newClient = clientService.add(client);
		return new ResponseEntity<>(newClient, HttpStatus.OK );
	}

	
}
