package com.biazon.dscommerce.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.biazon.dscommerce.entiteis.dtos.ProductResponseDTO;
import com.biazon.dscommerce.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/product")
@Tag(name = "Produtos", description = "End-Point de Produtos")
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	@Operation(summary = "Lista geral de Produtos", description = "Função responsável por lista todos os produtos")
	public ResponseEntity<List<ProductResponseDTO>> findAllProduct() {
		List<ProductResponseDTO> listProducts = productService.findAllProducts();
		
		
		return ResponseEntity.status(HttpStatus.OK).body(listProducts);
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Retorna Produto", description = "Função responsável por retorna o nome de um produto")
	public ResponseEntity<ProductResponseDTO> getOneProduct(@PathVariable Long id) {
		ProductResponseDTO nomeProduct = this.productService.getOneProduct(id);
		return ResponseEntity.status(HttpStatus.OK).body(nomeProduct);
	}
}
