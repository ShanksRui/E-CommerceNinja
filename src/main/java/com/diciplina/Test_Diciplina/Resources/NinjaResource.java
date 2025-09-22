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

import com.diciplina.Test_Diciplina.Entities.Ninja;
import com.diciplina.Test_Diciplina.Services.NinjaService;

@RestController
@RequestMapping(value = "/ninjas")
public class NinjaResource {

	@Autowired
	private NinjaService repository;
	
	@GetMapping
	public ResponseEntity<List<Ninja>> findAll(){
		List<Ninja> list = repository.findAll();
		return ResponseEntity.ok().body(list);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Ninja> findById(@PathVariable Long id ){
		Ninja obj = repository.findById(id);
		return ResponseEntity.ok().body(obj);
}	
	@PostMapping
	public ResponseEntity<Ninja> insert(@RequestBody Ninja ninja){
		Ninja obj = repository.insert(ninja);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);	
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id ){
		repository.delete(id);
		return 	ResponseEntity.noContent().build();
	}
	@PutMapping("/{id}")
	public ResponseEntity<Ninja> update(@PathVariable Long id,@RequestBody Ninja ninja){
		Ninja obj = repository.update(id, ninja);
		return ResponseEntity.ok().body(obj);
	}
}
