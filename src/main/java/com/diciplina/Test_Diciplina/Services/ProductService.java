package com.diciplina.Test_Diciplina.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.diciplina.Test_Diciplina.Entities.Product;
import com.diciplina.Test_Diciplina.Repository.ProductRepository;
import com.diciplina.Test_Diciplina.Services.Exception.DataBaseException;
import com.diciplina.Test_Diciplina.Services.Exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

	
	@Autowired
	private ProductRepository repository;
	
	public List<Product> findAll(){
		return repository.findAll();
	}
	
	public Product findById(Long id ) {
		Optional<Product> obj = repository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));		
	}
	public Product insert(Product product) {
		 Product obj =  repository.save(product);
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
	public Product update(Long id,Product product) {
   	 try {
   	Product entity = repository.getReferenceById(id);
   	updateData(entity, product);
   	return repository.save(entity);
   	 }catch(EntityNotFoundException e) {
   		 throw new ResourceNotFoundException(e.getMessage());
   	 }
}
	public void updateData(Product entity,Product product) {
   	 entity.setName(product.getName());
   	 entity.setPrice(product.getPrice());
    }
}
