package com.example.rewardyourteachersq011bjavapode.service;

import com.example.rewardyourteachersq011bjavapode.config.Security.CustomUserDetails;
import com.example.rewardyourteachersq011bjavapode.dto.TeacherDetails;
import com.example.rewardyourteachersq011bjavapode.dto.TeacherEditProfileDto;
import com.example.rewardyourteachersq011bjavapode.dto.TeacherRegistrationDto;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.response.UserRegistrationResponse;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface ITeacherService {
    Page<TeacherDetails> getAllTeacherBySchoolWithPagination(int pageNo, int pageSize, String schoolName);

    Page<TeacherDetails> getAllTeachersWithPagination(int pageNo, int pageSize);
    ApiResponse<String> editTeacherProfile(CustomUserDetails currentUser, TeacherEditProfileDto teacherEditProfileDto);

    UserRegistrationResponse registerTeacher(TeacherRegistrationDto teacherDtod) throws IOException;

}
