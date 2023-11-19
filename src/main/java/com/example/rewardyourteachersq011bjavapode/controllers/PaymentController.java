package com.example.rewardyourteachersq011bjavapode.controllers;


import com.example.rewardyourteachersq011bjavapode.dto.InitializeTransactionRequest;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.response.InitializeTransactionResponse;
import com.example.rewardyourteachersq011bjavapode.response.VerifyTransactionResponse;
import com.example.rewardyourteachersq011bjavapode.service.PaymentService;
import com.example.rewardyourteachersq011bjavapode.service.RewardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PaymentController {
    private PaymentService paymentService;

    private RewardService rewardService;

    @PostMapping("/make-payment")
    public ResponseEntity<?> pay(@RequestBody InitializeTransactionRequest request) throws Exception {
        return ResponseEntity.ok(paymentService.initTransaction(request));
    }
// todo : fix
    @GetMapping("/verify-transaction")
    public void verifyTransaction(String reference, HttpServletResponse response) throws IOException {
        paymentService.verifyTransaction(reference);
        response.sendRedirect("http://localhost:3000/student-dashboard");
    }

    @PostMapping("/rewardTeacher/{teacherId}")
    public ResponseEntity<ApiResponse<String>> rewardTeacher(@PathVariable("teacherId") Long teacherId,  @RequestBody InitializeTransactionRequest request) {
        return ResponseEntity.ok(rewardService.rewardTeacherByTeacherId(teacherId, request));
    }
}
