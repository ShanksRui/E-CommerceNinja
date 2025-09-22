package com.diciplina.Test_Diciplina.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_Village")
public class Village implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String region;
	
	
	@OneToMany(mappedBy = "village")
	private List<Ninja> ninjas = new ArrayList<>();
	
    @JsonBackReference
	@OneToOne(mappedBy = "village",cascade = CascadeType.ALL)
	private Store store;
	
    @JsonIgnore
	public List<Ninja> getNinjas() {
		return ninjas;
	}
      protected Village() {
		
	}
	public Village(Long id, String name, String region) {
		this.id = id;
		this.name = name;
		this.region = region;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getRegion() {
		return region;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRegion(String region) {
		this.region = region;
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
		Village other = (Village) obj;
		return Objects.equals(id, other.id);
	}


	
}
