package com.biazon.dscommerce.services;

import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.biazon.dscommerce.entiteis.Product;
import com.biazon.dscommerce.entiteis.dtos.ProductInsertDTO;
import com.biazon.dscommerce.entiteis.dtos.ProductResponseDTO;
import com.biazon.dscommerce.exceptions.DatabaseException;
import com.biazon.dscommerce.exceptions.ResourceNotFoundException;
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
		return new ProductResponseDTO(productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado")));

	}

	@Transactional
	public ProductResponseDTO creatProducts(ProductResponseDTO dto) {
		Product product = new Product();
		BeanUtils.copyProperties(dto, product);

		return new ProductResponseDTO(productRepository.save(product));
	}

	@Transactional
	public ProductResponseDTO updateProducts(Long id, ProductInsertDTO dto) {
		try {
			Product product = productRepository.getReferenceById(id);
			BeanUtils.copyProperties(dto, product);
			return new ProductResponseDTO(productRepository.save(product));
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException("Produto não encontrado");
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	public void deletProduct(Long id) {
		if(!productRepository.existsById(id)) {
			throw new ResourceNotFoundException("Produto não encontrado");
		}
		try {
			productRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Falha de integridade refencial");
		}
		
	}

}
