package br.com.msansone.api.clientservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.msansone.api.clientservice.model.Client;
import br.com.msansone.api.clientservice.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired 
	ClientRepository clientRepository;
	
	@Override
	public List<Client> getAll() {
		return clientRepository.findAll();
	}

	@Override
	public Client getById(Long id) {
		Optional<Client> ocli = clientRepository.findById(id);
		return ocli.isPresent()?ocli.get():null;
	}

	@Override
	public Client add(Client client) {
		
		Client nclient= new Client();
		nclient.setEmail(client.getEmail());
		nclient.setLogin(client.getLogin());
		nclient.setName(client.getName());
		nclient.setTelemovel(client.getTelemovel());
		return clientRepository.save(nclient);
	}

}
