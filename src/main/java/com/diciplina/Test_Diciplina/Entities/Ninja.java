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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_Ninja")
public class Ninja implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Integer stamina;
	private Integer jutsu;
	private Double salary;
		
	@JsonIgnore
	@OneToMany(mappedBy = "ninja")
	private Set<Order> orders = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "vila")	
	private Village village;
	
	
	public Set<Order> getOrders(){
		return orders;
	}
	
	protected Ninja() {
	  
  }
	
	public Ninja(Long id, String name, Integer stamina, Integer jutsu, Double salary,Village village) {
		this.id = id;
		this.name = name;
		this.stamina = stamina;
		this.jutsu = jutsu;
		this.salary = salary;
		this.village = village;
	}
	public Ninja(Long id, String name, Integer stamina, Integer jutsu, Double salary) {
		this.id = id;
		this.name = name;
		this.stamina = stamina;
		this.jutsu = jutsu;
		this.salary = salary;
	}
	

	public Integer getGastoStamina() {
		return stamina - jutsu;
	}

	public Double getSalary() {
		return salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getStamina() {
		return stamina;
	}

	public Integer getJutsu() {
		return jutsu;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStamina(Integer stamina) {
		this.stamina = stamina;
	}

	public void setJutsu(Integer jutsu) {	
		this.jutsu = jutsu;
	}

	public Village getVillage() {
		return village;
	}
	
	public void setVillage(Village village) {
		this.village = village;
		
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
		Ninja other = (Ninja) obj;
		return Objects.equals(id, other.id);
	}

}
