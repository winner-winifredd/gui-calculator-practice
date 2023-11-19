package com.example.rewardyourteachersq011bjavapode.service;

import com.example.rewardyourteachersq011bjavapode.dto.NotificationDto;
import com.example.rewardyourteachersq011bjavapode.enums.NotificationType;
import com.example.rewardyourteachersq011bjavapode.models.Notification;

import java.util.List;

public interface NotificationService {
    Notification saveNotification(Long userId, String message , NotificationType notificationType);

    Notification saveNotification(String email, String message, NotificationType notificationType);

    List<NotificationDto> retrieveUserNotifications();

    List<NotificationDto> retrieveUserMostRecentNotification();
}
