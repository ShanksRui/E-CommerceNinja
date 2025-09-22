package com.diciplina.Test_Diciplina.Entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_Store")
public class Store implements Serializable{
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String type;

	
	@JsonManagedReference
	@OneToOne
	@JoinColumn(name = "village_id")
	private Village village;
	
	@ManyToMany
	@JoinTable(name = "storeId_productId",joinColumns = @JoinColumn(name="store_id"),
	inverseJoinColumns = @JoinColumn(name = "product_id"))
	Set<Product> products = new HashSet<>();
	
	public Village  getVillage() {
		return village;
	}
	
	protected  Store() {
		
	}
	
	public Store(Long id,String name,String type ,Village village) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.village = village;		
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Product> getProducts(){
		return products;
	}

	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		return Objects.equals(id, other.id);
	}

	
	
	
	
}
