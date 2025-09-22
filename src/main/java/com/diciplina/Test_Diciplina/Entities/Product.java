package com.diciplina.Test_Diciplina.Entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_Product")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Double price;
	
	protected  Product() {
		
	}
	
	@OneToMany(mappedBy = "id.product")
	Set<ItemOrder> items = new HashSet<>();

	@ManyToMany(mappedBy = "products")
	Set<Store> stores = new HashSet<>();
	
	public Product(Long id,String name, Double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}
	
	@JsonIgnore
	public Set<Order> getOrders(){
		Set<Order> set = new HashSet<>();
		for (ItemOrder i: items) {
			set.add(i.getOrder());
		}
		return set;
	}
    @JsonIgnore
	public Set<Store> getStores(){
		return stores;
	}

	public String getName() {
		return name;
	}


	public Double getPrice() {
		return price;
	}

	public void setName(String name) {
		this.name = name;
	}


	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}
	

}
