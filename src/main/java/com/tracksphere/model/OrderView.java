package com.tracksphere.model;

public class OrderView {
	
	private Order order;
	private String deliveryPersonName;
	public OrderView(Order order, String deliveryPersonName) {
		super();
		this.order = order;
		this.deliveryPersonName = deliveryPersonName;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public String getDeliveryPersonName() {
		return deliveryPersonName;
	}
	public void setDeliveryPersonName(String deliveryPersonName) {
		this.deliveryPersonName = deliveryPersonName;
	}
	
	

}
