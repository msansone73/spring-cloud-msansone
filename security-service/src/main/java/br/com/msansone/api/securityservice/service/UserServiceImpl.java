package br.com.msansone.api.securityservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.msansone.api.securityservice.model.User;
import br.com.msansone.api.securityservice.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getAllUser() {
		return userRepository.findAll(); 
	}

	@Override
	public User getByLogin(String login) {
		return userRepository.findByLogin(login);
	}

	@Override
	public User getById(Long userId) {
		Optional<User> ouser = userRepository.findById(userId);
		return ouser.isPresent()?ouser.get():null;
	}

}
