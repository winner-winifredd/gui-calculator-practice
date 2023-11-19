package com.example.rewardyourteachersq011bjavapode.repository;

import com.example.rewardyourteachersq011bjavapode.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUserEmail(String user_email);
    List<Notification> findAllByUserEmailOrderByUpdateDateDesc(String user_email);
    List<Notification> findFirst5ByUser_EmailOrderByUpdateDateDesc(String email);

}
