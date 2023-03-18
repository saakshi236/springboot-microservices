package com.mindtree.omf.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.omf.oms.entity.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Integer>  {

}
