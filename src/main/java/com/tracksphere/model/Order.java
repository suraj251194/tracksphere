package com.tracksphere.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
	
	@Id
	private String id;
	private String customerName;
	private String deliveryAddress;
	private String status; // e.g., Placed, Picked, On The Way, Delivered
	private String deliveryPersonId;
	
	// Constructors
	public Order() {
		super();
	}

	public Order(String id, String customerName, String deliveryAddress, String status, String deliveryPersonId) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.deliveryAddress = deliveryAddress;
		this.status = status;
		this.deliveryPersonId = deliveryPersonId;
	}

	// Getters & Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeliveryPersonId() {
		return deliveryPersonId;
	}

	public void setDeliveryPersonId(String deliveryPersonId) {
		this.deliveryPersonId = deliveryPersonId;
	}
	
	
	
	
	
	

}
