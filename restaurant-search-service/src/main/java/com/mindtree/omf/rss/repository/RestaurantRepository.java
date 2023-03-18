package com.mindtree.omf.rss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.omf.rss.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {

	public List<Restaurant> findByLocation(String location);

	public List<Restaurant> findByName(String name);
}
