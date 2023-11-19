package com.example.rewardyourteachersq011bjavapode.repository;

import com.example.rewardyourteachersq011bjavapode.enums.TransactionType;
import com.example.rewardyourteachersq011bjavapode.models.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findAllByUser_Id(Long user_id);
    List<Transaction> findTransactionsByUserEmail(String user_email);
    List<Transaction>findAllByUserEmailAndTransactionType(String user_email, TransactionType transactionType);
}
