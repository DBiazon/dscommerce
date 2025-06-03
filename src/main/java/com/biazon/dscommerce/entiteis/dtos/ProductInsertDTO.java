package com.biazon.dscommerce.entiteis.dtos;

import com.biazon.dscommerce.entiteis.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductInsertDTO(
		@NotBlank(message = "Campo requerido") @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres") 
		String name,

		@Size(min = 10, message = "Descrição precisa ter no mínimo 10 caracteres") 
		String description,

		@Positive(message = "O preço deve ser positivo") 
		Double price, 
		String imgUrl) {

	public ProductInsertDTO(Product product) {
		this(product.getName(), product.getDescription(), product.getPrice(), product.getImgUrl());
	}
}
