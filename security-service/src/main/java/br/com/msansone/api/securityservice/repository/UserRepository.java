package br.com.msansone.api.securityservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.msansone.api.securityservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
