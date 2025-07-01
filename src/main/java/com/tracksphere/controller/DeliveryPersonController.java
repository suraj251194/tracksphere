package com.tracksphere.controller;

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
import com.tracksphere.service.DeliveryPersonService;

@Controller
@RequestMapping("/delivery-persons")
public class DeliveryPersonController {
	
	@Autowired
	private DeliveryPersonService deliveryPersonService;
	
	@GetMapping
	public String listDeliveryPersons(Model model) {
		List<DeliveryPerson> list = deliveryPersonService.getAll();
		model.addAttribute("deliveryPersons" ,list);
		return "delivery-list";		
	}
	
	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("deliveryPerson", new DeliveryPerson());
		return "delivery-form";	
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute DeliveryPerson deliveryPerson) {
		deliveryPerson.setAvailable(true); // by default new person is available
		deliveryPersonService.save(deliveryPerson);
		return "redirect:/delivery-persons";		
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable String id) {
		deliveryPersonService.delete(id);
		return "redirect:/delivery-persons";	
	}
}
