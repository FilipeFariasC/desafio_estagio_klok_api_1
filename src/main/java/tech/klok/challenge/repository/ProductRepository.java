package tech.klok.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.klok.challenge.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
