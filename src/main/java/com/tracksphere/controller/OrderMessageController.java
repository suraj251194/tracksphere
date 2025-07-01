package com.tracksphere.controller;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.tracksphere.model.Order;

@Controller
public class OrderMessageController {
	
	private final SimpMessagingTemplate messagingTemplate;

	public OrderMessageController(SimpMessagingTemplate messagingTemplate) {
		super();
		this.messagingTemplate = messagingTemplate;
	}
	
	// Send order update to all clients
	public void broadcastOrderUpdate(Order order) {
		messagingTemplate.convertAndSend("/topic/orderUpdates", order);
	}

}
