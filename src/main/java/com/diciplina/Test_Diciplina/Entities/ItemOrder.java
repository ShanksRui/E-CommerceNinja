package com.diciplina.Test_Diciplina.Entities;

import com.diciplina.Test_Diciplina.Entities.PK.ItemOrderPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_Order_Item")
public class ItemOrder {

	@EmbeddedId
	private ItemOrderPK id = new ItemOrderPK();
	
	
	private Integer quantity;
	private Double price;
		
    protected ItemOrder () {
		
	}
	public ItemOrder(Order order,Product product,Integer quantity) {
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = product.getPrice();
	}
	public Double getSubTotal() {
		return quantity * price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

    @JsonIgnore
	public Order getOrder() {
		return id.getOrder();
	}

	public Product getProduct() {
		return id.getProduct();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}

	public void setProduct(Product product) {
		id.setProduct(product);
	}
	
	
}
