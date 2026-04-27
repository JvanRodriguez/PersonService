package co.edu.uptc.repository;

import co.edu.uptc.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
    Page<PersonEntity> findAll(Pageable pageable);
    
    Page<PersonEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}