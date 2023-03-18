package com.mindtree.omf.uis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.omf.uis.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findById(int id);
}
