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

import com.diciplina.Test_Diciplina.Entities.Village;
import com.diciplina.Test_Diciplina.Services.VillageService;

@RestController
@RequestMapping("/villages")
public class VillageResource {
	
	@Autowired
	private VillageService repository;
	
	@GetMapping
	public ResponseEntity<List<Village>> findAll(){
		List<Village> list = repository.findAll();
		return ResponseEntity.ok().body(list);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Village> findById(@PathVariable Long id ){
		Village village = repository.findById(id);
		return ResponseEntity.ok().body(village);
	}
	@PostMapping
	public ResponseEntity<Village> insert(@RequestBody Village village){
		village = repository.insert(village);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(village.getId()).toUri();
		return ResponseEntity.created(uri).body(village);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id){
		repository.delete(id);
		return ResponseEntity.noContent().build();	
	}
	@PutMapping("/{id}")
	public ResponseEntity<Village> update(@PathVariable Long id,@RequestBody Village village){
		Village obj = repository.update(id, village);
		return ResponseEntity.ok().body(obj);
	}
}
