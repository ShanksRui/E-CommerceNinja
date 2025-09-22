package com.diciplina.Test_Diciplina.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.diciplina.Test_Diciplina.Entities.Ninja;
import com.diciplina.Test_Diciplina.Repository.NinjaRepository;
import com.diciplina.Test_Diciplina.Services.Exception.DataBaseException;
import com.diciplina.Test_Diciplina.Services.Exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class NinjaService {

	@Autowired
	private NinjaRepository repository;
	
	public List<Ninja> findAll(){
		return repository.findAll();
	}
	public Ninja findById(Long id ) {
		Optional<Ninja> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));		
	}
	public Ninja insert(Ninja ninja) {
		 Ninja obj =  repository.save(ninja);
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
     public Ninja update(Long id,Ninja ninja) {
    	 try {
    	Ninja entity = repository.getReferenceById(id);
    	updateData(entity, ninja);
    	return repository.save(entity);
    	 }catch(EntityNotFoundException e) {
    		 throw new ResourceNotFoundException(e.getMessage());
    	 }
	}
     public void updateData(Ninja entity,Ninja ninja) {
    	 entity.setName(ninja.getName());
    	 entity.setJutsu(ninja.getJutsu());
    	 entity.setSalary(ninja.getSalary());
    	 entity.setStamina(ninja.getStamina());
    	 entity.setVillage(ninja.getVillage());
     }
  }
