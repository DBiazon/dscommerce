package com.biazon.dscommerce;

import java.io.Serializable;

import com.biazon.dscommerce.entiteis.Order;
import com.biazon.dscommerce.entiteis.OrderItemPk;
import com.biazon.dscommerce.entiteis.Product;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_ORDER_ITEM")
@NoArgsConstructor
public class OrderItem implements Serializable{


	private static final long serialVersionUID = 9078143574557353693L;

	@EmbeddedId
	private OrderItemPk id = new OrderItemPk();
	
	private Integer quantity;
	
	private Double price;

	/**
	 * @param Order And Product
	 * @param quantity
	 * @param price
	 */
	public OrderItem(Order order, Product product, Integer quantity, Double price) {
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}	
	
	
	public Order getOrder() {
		return id.getOrder();
	}

	public void setOrder(Order order) {
		id.setOrder(order);
	}	
	
	public Product getProduct() {
		return id.getProduct();
	}

	public void setProduct(Product product) {
		id.setProduct(product);
	}	
	
}
