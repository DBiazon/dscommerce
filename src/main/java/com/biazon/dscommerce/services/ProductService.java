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
		
		for (Product product : listProducts) {
			ProductResponseDTO productResponseDTO = new ProductResponseDTO(product);
			
			listProductDto.add(productResponseDTO);
		}
		
		return listProductDto;
	}
	
	@Transactional(readOnly = true)
	public ProductResponseDTO getOneProduct(Long id){
//		Optional<Product> resultProduct = this.productRepository.findById(id);
//		Product product = resultProduct.get();
//		ProductResponseDTO productResponseDTO = new ProductResponseDTO(product);
//		return productResponseDTO;
		
		Product product = this.productRepository.findById(id).get();
		return new ProductResponseDTO(product);
	} 
}
