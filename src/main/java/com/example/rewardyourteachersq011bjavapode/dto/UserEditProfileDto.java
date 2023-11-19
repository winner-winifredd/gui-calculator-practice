package com.example.rewardyourteachersq011bjavapode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEditProfileDto {
    private String name;
    private String school;
    private String telephone;
    private String email;

}
