package com.mindtree.omf.rss.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.omf.rss.entity.Cuisine;
import com.mindtree.omf.rss.entity.Restaurant;
import com.mindtree.omf.rss.repository.CuisineRepository;
import com.mindtree.omf.rss.repository.RestaurantRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import brave.sampler.Sampler;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	Sampler sampler;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	CuisineRepository cuisineRepository;
	
//	@HystrixCommand(fallbackMethod = "fallbackMethod")
	@Override
	public List<Restaurant> getRestaurants() {
		List<Restaurant> list = restaurantRepository.findAll();
		for(Restaurant r: list) {
			r.setCuisine(cuisineRepository.findByRid(r.getId()));
		}
		return list;
	}

//	@HystrixCommand(fallbackMethod = "fallbackMethod")
	@Override
	public List<Cuisine> getMenu(int id) {
		return cuisineRepository.findByRid(id);
	}

//	@HystrixCommand(fallbackMethod = "fallbackMethod")
	@Override
	public List<Restaurant> filterByLocation(String location) {
		return restaurantRepository.findByLocation(location);
	}
	
//	@HystrixCommand(fallbackMethod = "fallbackMethod")
	@Override
	public List<Restaurant> filterByName(String name) {
		return restaurantRepository.findByName(name);
	}

//	@HystrixCommand(fallbackMethod = "fallbackMethod")
	@Override
	public List<Cuisine> filterByCuisine(String name) {
		return cuisineRepository.findByName(name);
	}

	@Override
	public Cuisine getCuisine(int id) {
		return cuisineRepository.findById(id);
	}
}
