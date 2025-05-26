package com.biazon.dscommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.biazon.dscommerce.entiteis.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
}
