package com.diciplina.Test_Diciplina.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.diciplina.Test_Diciplina.Entities.Order;
import com.diciplina.Test_Diciplina.Repository.OrderRepository;
import com.diciplina.Test_Diciplina.Services.Exception.DataBaseException;
import com.diciplina.Test_Diciplina.Services.Exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repository;
	
	public List<Order> findAll(){
		return repository.findAll();
	}
	
	public Order findById(Long id ) {
		Optional<Order> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));				 
	}
	public Order insert(Order order) {
		 Order obj =  repository.save(order);
		 return obj;
	}	
	public void delete(Long id) {
		if(!repository.existsById(id)) 
		throw new ResourceNotFoundException(id);
		
		try {
			repository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataBaseException(e.getMessage());
		}
	}
	public Order update(Long id,Order order) {
	  try {
		Order entity = repository.getReferenceById(id);
		updateData(entity, order);
		return repository.save(entity);
	  }catch(EntityNotFoundException e) {
		throw new ResourceNotFoundException(id);
	}		
}
	public void updateData(Order entity, Order order) {
		entity.setMoment(order.getMoment());
	    entity.setStatus(order.getStatus());
	}
	
}
