package com.josegal.producto1rp.repository;

import com.josegal.producto1rp.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
