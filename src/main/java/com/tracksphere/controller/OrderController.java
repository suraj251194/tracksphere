package com.tracksphere.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tracksphere.model.DeliveryPerson;
import com.tracksphere.model.Order;
import com.tracksphere.model.OrderView;
import com.tracksphere.service.DeliveryPersonService;
import com.tracksphere.service.OrderService;


@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private DeliveryPersonService deliveryPersonService;
	
	@Autowired
	private OrderMessageController orderMessageController;
	
	@GetMapping("/dashboard")
	public String showDashboard(Model model) {
		model.addAttribute("orders", orderService.getAllOrders());
		model.addAttribute("deliveryPersons", deliveryPersonService.getAll());
		return "dashboard";
	}
	
	@GetMapping
	public String listOrders(Model model) {
		List<Order> orders = orderService.getAllOrders();
		List<OrderView> views = new ArrayList<>();
		for(Order order : orders) {
			String name = "Not Assigned";
			if (order.getDeliveryPersonId() != null ) { //&& !order.getDeliveryPersonId().isEmpty()
				DeliveryPerson dp = deliveryPersonService.getById(order.getDeliveryPersonId());
				if (dp != null) name = dp.getName();
//				{
//					order.setDeliveryPersonId(dp.getName());
//				}
			}
			views.add(new OrderView(order, name));
		}
//		model.addAttribute("orders", orders);
		model.addAttribute("orderViews", views);
		return "order-list"; // JSP/Thymeleaf view
	}
	
	@GetMapping("/add")
	public String showOrderForm(Model model) {
		model.addAttribute("order", new Order());
		model.addAttribute("deliveryPersons", deliveryPersonService.getAll());
		return "order-form";
	}
	@PostMapping("/save")
	public String saveOrder(@ModelAttribute("order") Order order) {
		if (order.getDeliveryPersonId() == null || order.getDeliveryPersonId().isEmpty()) {
			DeliveryPerson person = deliveryPersonService.getAnyAvailablePerson();
			if (person != null) {
				order.setDeliveryPersonId(person.getId());
				person.setAvailable(false); // Mark as unavailable
				deliveryPersonService.save(person); // update in DB
			}
		}
		order.setStatus("Placed");
		orderService.saveOrder(order);
		return "redirect:/orders";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteOrder(@PathVariable String id) {
		
		Order order = orderService.getOrderById(id);
		
		if (order != null && order.getDeliveryPersonId() != null) {
			DeliveryPerson dp = deliveryPersonService.getById(order.getDeliveryPersonId());
			if (dp != null) {
				dp.setAvailable(true); // mark delivery person available again
				deliveryPersonService.save(dp);
			}
		}
		orderService.deleteOrder(id);
		return "redirect:/orders";	
	}
	
	// For Statuc check
	@GetMapping("/status/{id}")
	public String showStatusForm(@PathVariable String id, Model model) {
		Order order = orderService.getOrderById(id);
		model.addAttribute("order", order);
		return "order-status";		
	}
	
	@PostMapping("/updateStatus")
	public String updateOrderStatus(@ModelAttribute("order") Order order) {
		Order existing = orderService.getOrderById(order.getId());
		if (existing != null) {
			existing.setStatus(order.getStatus());
			orderService.saveOrder(existing);
			
			// Broadcast real-time update
			orderMessageController.broadcastOrderUpdate(existing);
		}
		return "redirect:/orders";
		
	}
	@GetMapping("/track")
	public String liveTracking(Model model) {
		List<Order> orders = orderService.getAllOrders();
		List<OrderView> views = new ArrayList<>();
		
		for(Order order : orders) {
			String name = "Not Assigned";
			if (order.getDeliveryPersonId() != null) {
				DeliveryPerson dp = deliveryPersonService.getById(order.getDeliveryPersonId());
				if(dp != null) name = dp.getName();
			}
			views.add(new OrderView(order, name));
		}
		model.addAttribute("orderViews", views);
//		model.addAttribute("orders", orderService.getAllOrders());
		return "order-track";
		
	}
}


