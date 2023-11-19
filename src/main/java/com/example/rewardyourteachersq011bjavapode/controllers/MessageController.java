package com.example.rewardyourteachersq011bjavapode.controllers;

import com.example.rewardyourteachersq011bjavapode.models.Message;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.serviceImpl.MessageServiceImpl;
import com.example.rewardyourteachersq011bjavapode.utils.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/api")
public class MessageController {

    private final ResponseService<ApiResponse<Message>> responseService;

    private final MessageServiceImpl messageServiceImplementation;

    @PostMapping(value = "/appreciate-student/{sender_id}/{receiver_id}")
    public ResponseEntity<ApiResponse<Message>> sendAppreciation(@PathVariable(value = "sender_id") Long sender_id , @PathVariable(value = "receiver_id") Long receiver_id , @RequestParam String message){
        return responseService.response(messageServiceImplementation.sendAppreciationToStudent(sender_id , receiver_id, message) , OK);
    }

}
