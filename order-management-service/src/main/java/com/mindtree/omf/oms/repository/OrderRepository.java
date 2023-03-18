package com.mindtree.omf.oms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.omf.oms.entity.Order;


@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	public Order findById(int id);
}
