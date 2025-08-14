package com.lcwd.user.service.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.lcwd.user.service.exceptions.ResourceNotFoundException;
import com.lcwd.user.service.external.services.HotelService;
import com.lcwd.user.service.model.Hotel;
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
	
	@Autowired
	private HotelService hotelService;
	
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
		Rating[] ratingsForUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),Rating[].class);
		logger.info("{} ",ratingsForUser);
		
		List<Rating> ratings = Arrays.stream(ratingsForUser).toList();
		List<Rating> ratingList = ratings.stream().map(rating ->{	
			//http://localhost:8082/hotels/b5961cf7-55ee-4070-817c-36649a1fe013
			//ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
			Hotel hotel = hotelService.getHotel(rating.getHotelId());
			//logger.info("Response status code: {}",forEntity.getStatusCode());
			rating.setHotel(hotel);
			return rating;
		}).collect(Collectors.toList());
		user.setRatings(ratingList);
		return user;
	}

}
