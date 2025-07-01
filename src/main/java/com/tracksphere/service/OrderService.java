package com.tracksphere.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracksphere.model.Order;
import com.tracksphere.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getAllOrders(){
		return orderRepository.findAll();
	}
	
	public Order getOrderById(String id) {
		return orderRepository.findById(id).orElse(null);
		
	}
	
	public Order saveOrder(Order order) {
		return orderRepository.save(order);
	}
	
	public void deleteOrder(String id) {
		orderRepository.deleteById(id);
	}

}
