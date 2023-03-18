package com.mindtree.omf.rss.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.omf.rss.entity.Cuisine;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Integer> {
	
	public List<Cuisine> findByRid(int id);
	public List<Cuisine> findByName(String name);
	public Cuisine findById(int id);
}
