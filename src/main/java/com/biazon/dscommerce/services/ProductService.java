package com.biazon.dscommerce.services;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biazon.dscommerce.entiteis.Product;
import com.biazon.dscommerce.entiteis.dtos.ProductInsertDTO;
import com.biazon.dscommerce.entiteis.dtos.ProductResponseDTO;
import com.biazon.dscommerce.repositories.ProductRepository;

@Service
public class ProductService {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional(readOnly = true)
	public Page<ProductResponseDTO> findAllProducts(Pageable pageable) {
		Page<Product> listProducts = productRepository.findAll(pageable);

		return listProducts.map(ProductResponseDTO::new);
	}

	@Transactional(readOnly = true)
	public ProductResponseDTO getOneProduct(Long id) {
		return new ProductResponseDTO(productRepository.findById(id).get());
	}

	@Transactional
	public ProductResponseDTO creatProducts(ProductResponseDTO dto) {
		Product product = new Product();
		BeanUtils.copyProperties(dto, product);

		return new ProductResponseDTO(productRepository.save(product));
	}

	@Transactional
	public ProductResponseDTO updateProducts(Long id, ProductInsertDTO dto) {
		Product product = productRepository.getReferenceById(id);
		BeanUtils.copyProperties(dto, product);

		return new ProductResponseDTO(productRepository.save(product));
	}
	
	@Transactional
	public void deletProduct(Long id) {
		productRepository.deleteById(id);
	}

}
