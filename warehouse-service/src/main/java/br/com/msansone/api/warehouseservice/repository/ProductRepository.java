package br.com.msansone.api.warehouseservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.msansone.api.warehouseservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
