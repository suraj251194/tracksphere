package com.tracksphere.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tracksphere.model.DeliveryPerson;

@Repository
public interface DeliveryPersonRepository extends MongoRepository<DeliveryPerson, String>{

}
