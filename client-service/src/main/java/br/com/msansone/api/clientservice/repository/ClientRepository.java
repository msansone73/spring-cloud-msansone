package br.com.msansone.api.clientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.msansone.api.clientservice.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
