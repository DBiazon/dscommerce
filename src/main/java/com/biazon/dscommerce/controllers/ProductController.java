package com.biazon.dscommerce.controllers;

import java.net.URI;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.biazon.dscommerce.entiteis.dtos.ProductInsertDTO;
import com.biazon.dscommerce.entiteis.dtos.ProductResponseDTO;
import com.biazon.dscommerce.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
@Tag(name = "Produtos", description = "End-Point de Produtos")
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	@Operation(summary = "Listar Produtos", description = "Função responsável por lista todos os produtos")
	public ResponseEntity<Page<ProductResponseDTO>> findAllProduct(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.findAllProducts(pageable));
	}
	
	@GetMapping("/{id}")
	@Operation(summary = "Retornar Produto", description = "Função responsável por retorna o nome de um produto")
	public ResponseEntity<ProductResponseDTO> getOneProduct(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.getOneProduct(id));
	}
	
	@PostMapping
	@Operation(summary = "Criar Produto", description = "Função responsável por criar um produto")
	public ResponseEntity<ProductResponseDTO> creatProducts(@Valid @RequestBody ProductInsertDTO insertDTO) {
		ProductResponseDTO dto  = productService.creatProducts(new ProductResponseDTO(insertDTO));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.id()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar Produto", description = "Função responsável por Atualiza um produto")
	public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductInsertDTO insertDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.updateProducts(id, insertDTO));
	}
	

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar Produto", description = "Função responsável por deletar um produto")
	public ResponseEntity<Void> deletProduct(@PathVariable Long id) {
		productService.deletProduct(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 
		
	}
}
