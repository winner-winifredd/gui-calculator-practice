package com.example.rewardyourteachersq011bjavapode.serviceImpl;

import com.example.rewardyourteachersq011bjavapode.models.Notification;
import com.example.rewardyourteachersq011bjavapode.models.User;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender javaMailSender;


    @Value("decagonpode@gmail.com")
    private String sender;


    @Override
    public boolean sendSimpleEmail(String body, String subject, String receiverEmail) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(receiverEmail);
            message.setText(body);
            message.setSubject(subject);
            javaMailSender.send(message);
            log.info("Email sent to %s successfully".formatted(receiverEmail));
            return true;
        } catch (Exception e) {
            log.error(e.getLocalizedMessage());
            return false;
        }
    }

    @Override
    public ApiResponse<String> sendSimpleEmailWithAttachment(String body, String subject, String attachment) {
        User user = new User();
        Notification notification = new Notification();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;
        FileSystemResource fileSystemResource;
        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setText(notification.getNotificationBody());
            mimeMessageHelper.setSubject(subject);
            fileSystemResource = new FileSystemResource(new File(attachment));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        String response = " Email sent  successfully";
        return new ApiResponse<>("success", LocalDateTime.now(), response);

    }
}
