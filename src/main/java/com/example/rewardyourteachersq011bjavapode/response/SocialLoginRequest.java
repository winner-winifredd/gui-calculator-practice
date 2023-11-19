package com.example.rewardyourteachersq011bjavapode.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@Builder
public class SocialLoginRequest {
    @Pattern(regexp = "^[A-Za-z]*$", message = "Invalid FirstName")
    private String name;
    private String email;
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
//            message = "Minimum eight characters, at least one letter and one number")
//    private String password;
    private String password;

}
