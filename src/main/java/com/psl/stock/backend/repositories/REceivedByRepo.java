package com.psl.stock.backend.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.stock.backend.entities.Logistic_ReceivedBy;

@Repository
public interface REceivedByRepo extends JpaRepository<Logistic_ReceivedBy, Integer> {
    // @Query(value = "select ")
    Optional<Logistic_ReceivedBy> findByReceiver(String receivedBy);
}
