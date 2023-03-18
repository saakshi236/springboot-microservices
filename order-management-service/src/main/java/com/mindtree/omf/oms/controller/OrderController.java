package com.mindtree.omf.oms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.omf.oms.entity.Delivery;
import com.mindtree.omf.oms.entity.Order;
import com.mindtree.omf.oms.entity.User;
import com.mindtree.omf.oms.service.OrderServiceImpl;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderServiceImpl service;
	
	@PostMapping(value="/totalAmount", consumes = "application/json")
	public String getTotalAmount(@RequestBody List<Integer> list) {
		return service.getTotalAmount(list);
	}
	
	@PostMapping(value="/userOrder", consumes = "application/json")
	public Order saveOrderDetails(@RequestBody User user) {
		return service.saveOrderDetails(user);
	}
	
	@PutMapping(value="/{id}")
	public void saveChangedOrderDetails(@PathVariable int id, @RequestBody List<Integer> list) {
		 service.saveChangedOrderDetails(id, list);
	}
	
	@GetMapping("/{id}")
	public Order findOrderDetails(@PathVariable int id) {
		return service.findOrderDetails(id);
	}
	
	@GetMapping("/all")
	public List<Order> getAllOrders() {
		return service.getAllOrders();
	}
	
	@DeleteMapping("/{id}")
	public boolean deleteOrder(@PathVariable int id) {
		service.deleteOrder(id);
		return true;
	}
	
	@GetMapping("/deliveryDetail/{id}")
	public Delivery getdeliveryDetail(@PathVariable int id) {
		return service.getdeliveryDetail(id);
	}
}