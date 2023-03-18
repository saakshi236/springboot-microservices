package com.mindtree.omf.rss.service;

import java.util.List;

import com.mindtree.omf.rss.entity.Cuisine;
import com.mindtree.omf.rss.entity.Restaurant;

public interface RestaurantService {

	public List<Restaurant> getRestaurants();
	public List<Cuisine> getMenu(int id);
	public List<Restaurant> filterByLocation(String location);
	List<Restaurant> filterByName(String name);
	List<Cuisine> filterByCuisine(String name);
	Cuisine getCuisine(int id);
}
