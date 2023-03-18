package com.mindtree.omf.uis.service;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.mindtree.omf.uis.model.Order;
import com.mindtree.omf.uis.model.User;
import com.mindtree.omf.uis.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserRepository repository;

	@Autowired
	RestTemplate restTemplate;

	@Override
	public User saveOrder(User user) {
		LOG.info("Inside method: saveOrder");
		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    LOG.info(new Gson().toJson(user));
	    HttpEntity<String> request = new HttpEntity<String>(new Gson().toJson(user), headers);
	    Order orderObj = restTemplate.postForObject("http://order-management-service/order/userOrder", request, Order.class);
	    user.setOrderList(Arrays.asList(orderObj));
		repository.save(user);
		LOG.info("The response received by method-> saveOrder is " + user.toString());
		return user;
	}

	@Override
	public User findUser(int id) {
		LOG.info("Inside method: findUser");
		LOG.info("User->" + repository.findById(id).toString());
		LOG.info("The response received by method-> findUser");
		return repository.findById(id);
	}

}
