package com.example.rewardyourteachersq011bjavapode.dto;

import com.example.rewardyourteachersq011bjavapode.enums.NotificationType;

public record NotificationDto(String notificationBody, NotificationType notificationType, String date) {

}
