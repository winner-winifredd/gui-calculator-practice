package com.example.rewardyourteachersq011bjavapode.service;

import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;

public interface EmailService {
     boolean sendSimpleEmail(String body, String subject, String recieverEmail);

     ApiResponse<String> sendSimpleEmailWithAttachment(String body, String subject, String attachment);

}
