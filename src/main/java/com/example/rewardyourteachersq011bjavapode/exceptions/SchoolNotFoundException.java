package com.example.rewardyourteachersq011bjavapode.exceptions;

import lombok.Getter;

@Getter
public class SchoolNotFoundException extends RuntimeException{
    private final String message;

    public SchoolNotFoundException(String message) {
        this.message = message;
    }
}
