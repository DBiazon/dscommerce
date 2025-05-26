package com.biazon.dscommerce.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<ProductResponseDTO> findAllProducts(Pageable pageable){
		Page<Product> listProducts = this.productRepository.findAll(pageable);
	
		return listProducts.map(ProductResponseDTO::new);
	}
	
	@Transactional(readOnly = true)
	public ProductResponseDTO getOneProduct(Long id){		
		Product product = this.productRepository.findById(id).get();
		return new ProductResponseDTO(product);
	} 
}
