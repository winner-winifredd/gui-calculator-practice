package com.example.rewardyourteachersq011bjavapode.serviceImpl;

import com.example.rewardyourteachersq011bjavapode.enums.Role;
import com.example.rewardyourteachersq011bjavapode.models.Message;
import com.example.rewardyourteachersq011bjavapode.models.Teacher;
import com.example.rewardyourteachersq011bjavapode.models.User;
import com.example.rewardyourteachersq011bjavapode.repository.MessageRepository;
import com.example.rewardyourteachersq011bjavapode.repository.UserRepository;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import com.example.rewardyourteachersq011bjavapode.service.MessageService;
import com.example.rewardyourteachersq011bjavapode.utils.ResponseService;
import com.example.rewardyourteachersq011bjavapode.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.example.rewardyourteachersq011bjavapode.enums.NotificationType.APPRECIATION_NOTIFICATION;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final UserUtil userUtil;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    private final ResponseService<Message> responseService;

    private final NotificationServiceImpl notificationService;
    @Override
    public  ApiResponse<Message> sendAppreciationToStudent(Long sender_id, Long user_id, String message) {
        Teacher sender = userUtil.findTeacherById( sender_id );
        User reciever = userUtil.findUserById(user_id);
        Message messageToBeSent = new Message();
            messageToBeSent.setMessageBody(message);
            messageToBeSent.setUser(reciever);
            messageToBeSent.setSenderName(sender.getName());
            messageRepository.save(messageToBeSent);
            notificationService.saveNotification(user_id , "You Have A Message From " + sender.getName() ,APPRECIATION_NOTIFICATION);
        return new ApiResponse<>("success" , LocalDateTime.now() , messageToBeSent);
    }

}
