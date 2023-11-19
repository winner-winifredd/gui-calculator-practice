package com.example.rewardyourteachersq011bjavapode.service;

import com.example.rewardyourteachersq011bjavapode.models.Message;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;

public interface MessageService {

    ApiResponse<Message> sendAppreciationToStudent(Long sender_Id, Long user_id , String message);
}
