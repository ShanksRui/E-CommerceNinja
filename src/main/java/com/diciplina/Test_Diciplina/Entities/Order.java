package com.diciplina.Test_Diciplina.Entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.diciplina.Test_Diciplina.Entities.Enum.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "tb_Order")
public class Order implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Instant moment;
	private Integer status;
	
	@ManyToOne()
	@JoinColumn(name = "ninja_id")
	private Ninja ninja;
	
	
	@OneToMany(mappedBy = "id.order",cascade = CascadeType.ALL)
	private Set<ItemOrder> items = new HashSet<>();
			
	protected Order () {
		
	}
	
	public Order(Long id, Instant moment, OrderStatus status,Ninja ninja) {
		this.id = id;
		this.moment = moment;
		setStatus(status);
		this.ninja = ninja;
	}
	public Set<ItemOrder> getItems(){
		return items;
	}
	public Double getTotal() {
		double sum = 0.0;
		for (ItemOrder i : items) {
			sum += i.getSubTotal();
		}
		return sum;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Instant getMoment() {
		return moment;
	}
	public void setMoment(Instant moment) {
		this.moment = moment;
	}
	public OrderStatus getStatus() {
		return OrderStatus.ValueOfStatusCode(status);
	}
	public void setStatus(OrderStatus status) {
		this.status = status.getCode();
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	
}
