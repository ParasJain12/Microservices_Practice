package com.lcwd.hotel.service;

import java.util.List;

import com.lcwd.hotel.model.Hotel;

public interface HotelService {
	Hotel create(Hotel hotel);
	
	List<Hotel> getAll();
	
	Hotel get(String id);
}
