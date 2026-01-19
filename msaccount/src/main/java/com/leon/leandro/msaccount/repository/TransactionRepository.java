package com.leon.leandro.msaccount.repository;

import com.leon.leandro.msaccount.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountIdOrderByCreatedAtDesc(Long accountId);

    List<Transaction> findByAccountIdAndCreatedAtBetween(
            Long accountId,
            LocalDateTime from,
            LocalDateTime to
    );
}