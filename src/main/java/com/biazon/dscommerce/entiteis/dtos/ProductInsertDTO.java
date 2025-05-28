package com.biazon.dscommerce.entiteis.dtos;

import com.biazon.dscommerce.entiteis.Product;

public record ProductInsertDTO(String name, String description, Double price, String imgUrl) {

	public ProductInsertDTO(Product product) {
		this(product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl());
	}
}
