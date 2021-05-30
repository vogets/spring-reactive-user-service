package com.example.userservice.repository;

import com.example.userservice.entities.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends ReactiveCrudRepository<UserTransaction,Integer> {
}
