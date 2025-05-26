package com.biazon.dscommerce.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biazon.dscommerce.entiteis.Product;
import com.biazon.dscommerce.entiteis.dtos.ProductResponseDTO;
import com.biazon.dscommerce.repositories.ProductRepository;


@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional(readOnly = true)
	public List<ProductResponseDTO> findAllProducts(){
		List<Product> listProducts = this.productRepository.findAll();
		List<ProductResponseDTO> listProductDto = new ArrayList<>();
		
		listProducts.stream().forEach(e  -> listProductDto.add(new ProductResponseDTO(e)));
	
		return listProductDto;
	}
	
	@Transactional(readOnly = true)
	public ProductResponseDTO getOneProduct(Long id){		
		Product product = this.productRepository.findById(id).get();
		return new ProductResponseDTO(product);
	} 
}
