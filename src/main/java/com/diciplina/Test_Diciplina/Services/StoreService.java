package com.diciplina.Test_Diciplina.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.diciplina.Test_Diciplina.Entities.Store;
import com.diciplina.Test_Diciplina.Repository.StoreRepository;
import com.diciplina.Test_Diciplina.Services.Exception.DataBaseException;
import com.diciplina.Test_Diciplina.Services.Exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StoreService {

	@Autowired
	private StoreRepository repository;
	
	
	public List<Store> findAll(){
		return repository.findAll();
	}
	
	public Store findById(Long id) {
		Optional<Store> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));		
	}
	public Store insert(Store store) {
		 Store obj =  repository.save(store);
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
	public Store update(Long id,Store store) {
   	 try {
   	Store entity = repository.getReferenceById(id);
   	updateData(entity, store);
   	return repository.save(entity);
   	 }catch(EntityNotFoundException e) {
   		 throw new ResourceNotFoundException(e.getMessage());
   	 }
	}
	public void updateData(Store entity,Store store) {
   	 entity.setName(store.getName());
     entity.setType(store.getType());
    }
}