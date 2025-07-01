package com.tracksphere.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tracksphere.model.DeliveryPerson;
import com.tracksphere.repository.DeliveryPersonRepository;

@Service
public class DeliveryPersonService {
	
	@Autowired
	private DeliveryPersonRepository deliveryPersonRepository;
	
	public List<DeliveryPerson> getAll(){
		return deliveryPersonRepository.findAll();
	}
	
	public DeliveryPerson getById(String id) {
		return deliveryPersonRepository.findById(id).orElse(null);
	}
	
	public DeliveryPerson save(DeliveryPerson dp) {
		return deliveryPersonRepository.save(dp);
	}
	
	public void delete(String id) {
		deliveryPersonRepository.deleteById(id);
	}
	
	public DeliveryPerson getAnyAvailablePerson() {
		return deliveryPersonRepository.findAll()
				.stream()
				.filter(DeliveryPerson::isAvailable)
				.findFirst()
                .orElse(null);
		
	}

}
