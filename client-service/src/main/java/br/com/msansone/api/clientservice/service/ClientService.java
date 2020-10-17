package br.com.msansone.api.clientservice.service;

import java.util.List;

import br.com.msansone.api.clientservice.model.Client;

public interface ClientService {

	List<Client> getAll();

	Client getById(Long id);

}
