package com.mindtree.omf.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.omf.oms.entity.Restaurant;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	public List<Restaurant> findByLocation(String location);
	public Restaurant findById(int id);
	public List<Restaurant> findByName(String name);
}
