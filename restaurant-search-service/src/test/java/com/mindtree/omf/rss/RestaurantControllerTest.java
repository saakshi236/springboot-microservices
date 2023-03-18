package com.mindtree.omf.rss;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.mindtree.omf.rss.controller.RestaurantController;
import com.mindtree.omf.rss.entity.Cuisine;
import com.mindtree.omf.rss.entity.Restaurant;
import com.mindtree.omf.rss.service.RestaurantServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@AutoConfigureMockMvc
class RestaurantControllerTest {

	@InjectMocks
	private RestaurantController controller;

	@Mock
	RestaurantServiceImpl service;

	@Autowired
	private MockMvc mvc;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

	private final String URI = "http://localhost:9001/restaurant";

	@Test
	void testGetRestaurants() throws Exception {
		String uri = URI + "/list";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		List<Restaurant> restaurantList = new Gson().fromJson(content, List.class);
		assertTrue(!restaurantList.isEmpty());
	}

	@Test
	void testGetMenu() throws Exception {
		String uri = URI + "/menu/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		List<Cuisine> cuisineList = new Gson().fromJson(content, List.class);
		assertTrue(!cuisineList.isEmpty());
	}

	@Test
	void testGetCuisine() throws Exception {
		String uri = URI + "/cuisine/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		Cuisine cuisine = new Gson().fromJson(content, Cuisine.class);
		assertTrue(cuisine != null);
	}

	@Test
	void testFilterByLocation() throws Exception {
		String uri = URI + "/location=Nadiad";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		List<Restaurant> restaurantList = new Gson().fromJson(content, List.class);
		assertTrue(!restaurantList.isEmpty());
	}

	@Test
	void testFilterByName() throws Exception {
		String uri = URI + "/name=Sunflower";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		List<Restaurant> restaurantList = new Gson().fromJson(content, List.class);
		assertTrue(!restaurantList.isEmpty());
	}

	@Test
	void testFilterByCuisine() throws Exception {
		String uri = URI + "/cuisine=Thepla";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		
		String content = mvcResult.getResponse().getContentAsString();
		List<Cuisine> cuisineList = new Gson().fromJson(content, List.class);
		assertTrue(!cuisineList.isEmpty());
	}

}
