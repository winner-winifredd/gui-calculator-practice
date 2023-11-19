package com.example.rewardyourteachersq011bjavapode.service;

import com.example.rewardyourteachersq011bjavapode.dto.TeacherRegistrationDto;
import com.example.rewardyourteachersq011bjavapode.enums.TransactionType;
import com.example.rewardyourteachersq011bjavapode.models.Transaction;
import com.example.rewardyourteachersq011bjavapode.models.User;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.response.UserRegistrationResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface TransactionService {
    ApiResponse<List<Transaction>> findAllTransactionForAUser(Long user_id);


    Transaction saveTransaction(BigDecimal amount, TransactionType transactionType, User sender, User receiver);

    ApiResponse<BigDecimal> totalMoneySent();
}
