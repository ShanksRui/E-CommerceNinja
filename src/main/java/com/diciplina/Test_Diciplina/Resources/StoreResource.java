package com.diciplina.Test_Diciplina.Resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.diciplina.Test_Diciplina.Entities.Store;
import com.diciplina.Test_Diciplina.Services.StoreService;
@RestController
@RequestMapping("/stores")
public class StoreResource {

	@Autowired
	private StoreService repository;
	
	@GetMapping
	public ResponseEntity<List<Store>> findAll(){
		List<Store> list = repository.findAll();
		return ResponseEntity.ok().body(list);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Store> findById(@PathVariable Long id ){
		Store obj = repository.findById(id);
		return ResponseEntity.ok().body(obj);
}	
	@PostMapping
	public ResponseEntity<Store> insert(@RequestBody Store store){
		store = repository.insert(store);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(store.getId()).toUri();
		return ResponseEntity.created(uri).body(store);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		repository.delete(id);
		return ResponseEntity.noContent().build();
	}
	@PutMapping("/{id}")
	public ResponseEntity<Store> update(@PathVariable Long id,@RequestBody Store store){
		Store obj = repository.update(id, store);
		return ResponseEntity.ok().body(obj);
	}
}
