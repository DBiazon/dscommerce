package com.biazon.dscommerce.entiteis.dtos;

import com.biazon.dscommerce.entiteis.Product;

public record ProductResponseDTO(Long id,String name, String description, Double price, String imgUrl) {

	public ProductResponseDTO(Long id,String name, String description, Double price, String imgUrl) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	public ProductResponseDTO(Product product) {
		this(
				product.getId(),
				product.getName(),
				product.getDescription(),
				product.getPrice(),
				product.getImgUrl()
				);
	}
}
