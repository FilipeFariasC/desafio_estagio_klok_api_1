package tech.klok.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.klok.challenge.model.Adhesion;

@Repository
public interface AdhesionRepository extends JpaRepository<Adhesion, Long> {

}
