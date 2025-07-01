package com.tracksphere.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "delivery_persons")
public class DeliveryPerson {
	
	@Id
	private String id;
	private String name;
	private boolean available;
	
	// Constructors
	public DeliveryPerson() {
		super();
	}
	public DeliveryPerson(String name, boolean available) {
		super();
		this.name = name;
		this.available = available;
	}
	
	// Getters & Setters
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	
	
	

}
