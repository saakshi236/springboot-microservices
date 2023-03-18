package com.mindtree.omf.oms.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Data
@Table(name = "order_details")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String username;
	
	private String restaurant;
	
	private double totalAmount;
	
	private String address;
	
	@OneToMany
	@Column(name = "orderitems")
	private List<Cuisine> orderItems;

	@Override
	public String toString() {
		return "Order [id=" + id + ", username=" + username + ", restaurant=" + restaurant + ", totalAmount="
				+ totalAmount + ", address=" + address + ", orderItems=" + orderItems + "]";
	}
}
