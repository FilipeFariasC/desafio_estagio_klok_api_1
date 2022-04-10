package tech.klok.challenge.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tech.klok.challenge.model.Field;

public interface FieldRepository extends JpaRepository<Field, Long> {
	
	@Query("SELECT p.fields FROM Product p WHERE p.id = :productId")
	Set<Field> findFieldsByProductId(@Param("productId") Long productId);
}
