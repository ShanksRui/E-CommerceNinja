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

import com.diciplina.Test_Diciplina.Entities.Order;
import com.diciplina.Test_Diciplina.Services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {

	@Autowired
	private OrderService repository;
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll(){
		List<Order> orders = repository.findAll();
		return ResponseEntity.ok().body(orders);
	}	
	@GetMapping("/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id ){
		Order obj = repository.findById(id);
		return ResponseEntity.ok().body(obj);
	}	
	@PostMapping
	public ResponseEntity<Order> insert(@RequestBody Order order){
		Order obj = repository.insert(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id ){
		repository.delete(id);
		return 	ResponseEntity.noContent().build();
	}
	@PutMapping("/{id}")
	public ResponseEntity<Order> update(@PathVariable Long id,@RequestBody Order order){
		Order obj = repository.update(id, order);
		return ResponseEntity.ok().body(obj);
	}
}
