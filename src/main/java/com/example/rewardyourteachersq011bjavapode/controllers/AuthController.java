package com.example.rewardyourteachersq011bjavapode.controllers;

import com.example.rewardyourteachersq011bjavapode.dto.LoginDTO;
import com.example.rewardyourteachersq011bjavapode.dto.PrincipalDto;
import com.example.rewardyourteachersq011bjavapode.dto.TeacherRegistrationDto;
import com.example.rewardyourteachersq011bjavapode.dto.UserDto;
import com.example.rewardyourteachersq011bjavapode.exceptions.ResourceNotFoundException;
import com.example.rewardyourteachersq011bjavapode.response.*;
import com.example.rewardyourteachersq011bjavapode.service.AuthService;
import com.example.rewardyourteachersq011bjavapode.service.UserService;
import com.example.rewardyourteachersq011bjavapode.utils.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    private final UserService userService;
    private final ResponseService<ApiResponse<PrincipalDto>> responseService;


    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody LoginDTO authRequest) throws ResourceNotFoundException {
        return responseService.response(authService.loginUser(authRequest), HttpStatus.OK);
    }

    @PostMapping("/social-login")
    public ResponseEntity<?> generateSocialToken(@RequestBody SocialLoginRequest authRequest){
        return responseService.response(authService.socialLogin(authRequest), HttpStatus.OK);
    }


}
