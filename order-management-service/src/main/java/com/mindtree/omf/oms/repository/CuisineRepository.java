package com.mindtree.omf.oms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.omf.oms.entity.Cuisine;


@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Integer> {
	
	public Cuisine findById(int id);
	public List<Cuisine> findByRid(int id);
	public List<Cuisine> findByName(String name);

}
