package com.mindtree.omf.oms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mindtree.omf.oms.entity.Cuisine;
import com.mindtree.omf.oms.entity.Delivery;
import com.mindtree.omf.oms.entity.Order;
import com.mindtree.omf.oms.entity.User;
import com.mindtree.omf.oms.repository.CuisineRepository;
import com.mindtree.omf.oms.repository.DeliveryRepository;
import com.mindtree.omf.oms.repository.OrderRepository;
import com.mindtree.omf.oms.repository.RestaurantRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CuisineRepository cuisineRepository;
	
	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	DeliveryRepository deliveryRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	String restaurantUrl = "http://restaurant-search-service/restaurant";

	@HystrixCommand(fallbackMethod = "defaultGetTotalAmount")
	@Override
	public String getTotalAmount(List<Integer> list) {
		LOG.info("Inside method: getTotalAmount");
		double totalAmount = 0;
		for(int i: list) {
			totalAmount += this.restTemplate.getForObject(restaurantUrl+"/cuisine/"+i, Cuisine.class).getPrice();
		}
		LOG.info("The response received by method-> getTotalAmount is " + totalAmount);
		return "Total amount to be paid: "+totalAmount;
	}

	@HystrixCommand(fallbackMethod = "defaultSave")
	@Override
	public Order saveOrderDetails(User user) {
		LOG.info("Inside method: saveOrderDetails");
		Order order = new Order();
		LOG.info(user.getUsername());
		LOG.info(user.getAddress());
		order.setUsername(user.getUsername());
		order.setAddress(user.getAddress());
		this.fetchDataAndSave(user.getCuisineList(), order);
		LOG.info("The response received by method-> saveOrderDetails");
		LOG.info(order.toString());
		return order;
	}

	@Override
	public Order findOrderDetails(int id) {
		return orderRepository.findById(id);
	}

	@Override
	public List<Order> getAllOrders() {
		LOG.info("Inside method: getAllOrders");
		LOG.info("The response received by method-> getAllOrders");
		return orderRepository.findAll();
	}

	@Override
	public boolean deleteOrder(int id) {
		orderRepository.deleteById(id);
		return true;
	}

	@HystrixCommand(fallbackMethod = "defaultFetchAndSave")
	@Override
	public void saveChangedOrderDetails(int id, List<Integer> list) {
		Order updateOrder = orderRepository.findById(id);
		this.fetchDataAndSave(list,updateOrder);
	}
	
	@Override
	public void fetchDataAndSave(List<Integer> list, Order order) {
		LOG.info("Inside method: fetchDataAndSave");
		List<Cuisine> cuisineList = new ArrayList<>();
		double totalAmount = 0;
		for(int i: list) {
			totalAmount += this.restTemplate.getForObject(restaurantUrl+"/cuisine/"+i, Cuisine.class).getPrice();
			cuisineList.add(this.restTemplate.getForObject(restaurantUrl+"/cuisine/"+i, Cuisine.class));
			LOG.info("The response received by method-> fetchDataAndSave is " + this.restTemplate.getForObject(restaurantUrl+"/cuisine/"+i, Cuisine.class));
		}
		order.setTotalAmount(totalAmount);
		order.setOrderItems(cuisineList);
		order.setRestaurant(restaurantRepository.findById(cuisineList.get(0).getRid()).getName());
		LOG.info(order.toString());
		orderRepository.save(order);
	}
	
	@Override
	public Delivery getdeliveryDetail(int id) {
		LOG.info("Inside method: getdeliveryDetail");
		Order deliveryOrder = orderRepository.findById(id);
		Delivery delivery = new Delivery();
		delivery.setAddress(deliveryOrder.getAddress());
		delivery.setDeliveryDate(new Date());
		delivery.setOrderId(deliveryOrder.getId());
		delivery.setStatus("Delivery Pending");
		deliveryRepository.save(delivery);
		LOG.info("The response received by method->: getdeliveryDetail");
		return delivery;
	}
	
	// ---fallback methods---
	
	public Order defaultSave(User user) {
		LOG.info("Inside method: defaultSave");
		Order order = new Order();
		List<Cuisine> defaultList = new ArrayList<Cuisine>();
		defaultList.add(cuisineRepository.findById(1));
		order.setOrderItems(defaultList);
		order.setRestaurant("Default Restaurant");
		LOG.info("The response received by method-> defaultSave is " + order.toString());
		return orderRepository.save(order);
	}
	
	public void defaultFetchAndSave(int id, List<Integer> list) {
		LOG.info("Inside method: defaultFetchAndSave");
		Order updateOrder = orderRepository.findById(1);
		List<Cuisine> defaultList = new ArrayList<Cuisine>();
		defaultList.add(cuisineRepository.findById(1));
		updateOrder.setOrderItems(defaultList);
		updateOrder.setRestaurant("Default Update Restaurant");
		LOG.info("The response received by method-> defaultFetchAndSave is " + updateOrder.toString());
		orderRepository.save(updateOrder);
	}
	
	private String defaultGetTotalAmount(List<Integer> list) {
		LOG.info("Inside method: defaultGetTotalAmount");
		LOG.info("The response received by method-> defaultGetTotalAmount");
        return "Could not compute total, try later!";
    }
}
