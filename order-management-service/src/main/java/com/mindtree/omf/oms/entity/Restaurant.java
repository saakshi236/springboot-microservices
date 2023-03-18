package com.mindtree.omf.oms.entity;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Restaurant {
	
	@Id
	private int id;
	
	private String name;
	private String location;
	private int budget;
	
	@OneToMany(mappedBy = "rid")
	private List<Cuisine> cuisine;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getBudget() {
		return budget;
	}

	public void setBudget(int budget) {
		this.budget = budget;
	}

	public List<Cuisine> getCuisine() {
		return cuisine;
	}

	public void setCuisine(List<Cuisine> cuisine) {
		this.cuisine = cuisine;
	}

	public Restaurant(int id, String name, String location, int budget, List<Cuisine> cuisine) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.budget = budget;
		this.cuisine = cuisine;
	}
	
	public Restaurant(int id, String name, String location, int budget) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.budget = budget;
	}

	public Restaurant() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + budget;
		result = prime * result + id;
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((cuisine == null) ? 0 : cuisine.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurant other = (Restaurant) obj;
		if (budget != other.budget)
			return false;
		if (id != other.id)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (cuisine == null) {
			if (other.cuisine != null)
				return false;
		} else if (!cuisine.equals(other.cuisine))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}	

}
