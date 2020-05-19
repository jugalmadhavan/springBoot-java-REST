package com.example.arithmetic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface CalculationRepository extends JpaRepository<com.example.arithmetic.data.Book, Long> {

}
