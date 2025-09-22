package com.diciplina.Test_Diciplina.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.diciplina.Test_Diciplina.Entities.Village;
import com.diciplina.Test_Diciplina.Repository.VillageRepository;
import com.diciplina.Test_Diciplina.Services.Exception.DataBaseException;
import com.diciplina.Test_Diciplina.Services.Exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VillageService {

	@Autowired
	private VillageRepository repository;
	
	public List<Village> findAll(){
		return repository.findAll();
	}
	
	public Village findById(Long id) {
		Optional<Village> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));		
	}
	public Village insert(Village village) {
		 Village obj =  repository.save(village);
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
	public Village update(Long id,Village village) {
   	 try {
   	Village entity = repository.getReferenceById(id);
   	updateData(entity, village);
   	return repository.save(entity);
   	 }catch(EntityNotFoundException e) {
   		 throw new ResourceNotFoundException(e.getMessage());
   	 }
	}
	public void updateData(Village entity,Village village) {
   	 entity.setName(village.getName());
   	 entity.setRegion(village.getRegion());
   	 
    }
}
