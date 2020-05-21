package com.marcos.prova.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcos.prova.entity.Transaction;

@Repository
public interface TransationRepository extends JpaRepository<Transaction, Long> {

}
