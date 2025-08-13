package com.lcwd.user.service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.model.Rating;
import com.lcwd.user.service.model.User;
import com.lcwd.user.service.repository.UserRepository;
import com.lcwd.user.service.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public User saveUser(User user) {
		String randomUserId = UUID.randomUUID().toString();
		user.setUserId(randomUserId);
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User getUser(String userId) {
		User user =  userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id not found"));
		//ArrayList<Rating> ratingsForUser = restTemplate.getForObject("http://localhost:8083/ratings/users/4e030b72-6324-4aa4-9488-d87f53b4791f",ArrayList.class);
		ArrayList<Rating> ratingsForUser = restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(),ArrayList.class);
		logger.info("{} ",ratingsForUser);
		user.setRatings(ratingsForUser);
		return user;
	}

}
