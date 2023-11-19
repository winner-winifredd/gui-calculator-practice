package com.example.rewardyourteachersq011bjavapode.serviceImpl;

import com.example.rewardyourteachersq011bjavapode.dto.TeacherDetails;
import com.example.rewardyourteachersq011bjavapode.dto.TeacherRegistrationDto;
import com.example.rewardyourteachersq011bjavapode.enums.NotificationType;
import com.example.rewardyourteachersq011bjavapode.enums.Role;
import com.example.rewardyourteachersq011bjavapode.enums.SchoolType;
import com.example.rewardyourteachersq011bjavapode.enums.Status;
import com.example.rewardyourteachersq011bjavapode.models.*;
import com.example.rewardyourteachersq011bjavapode.repository.SubjectRepository;
import com.example.rewardyourteachersq011bjavapode.repository.TeacherRepository;
import com.example.rewardyourteachersq011bjavapode.repository.UserRepository;
import com.example.rewardyourteachersq011bjavapode.repository.WalletRepository;
import com.example.rewardyourteachersq011bjavapode.service.ITeacherService;
import com.example.rewardyourteachersq011bjavapode.utils.UserUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TeacherServiceImplTest {
    @InjectMocks
    private TeacherServiceImpl teacherServiceImpl;

    @Mock
    WalletRepository walletRepository;

    @Mock
    TeacherRepository teacherRepository;

    @Mock
    SubjectRepository subjectRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    UserRepository userRepository;

    @Mock
    UserUtil userUtil;

    private MultipartFile multipartFile;
    private List<String> listSubject;
    private Teacher teacher;

    List<Subject> subjectList = new ArrayList<>();
    List<Transaction> transactionList = new ArrayList<>();
    List<Message> messageList = new ArrayList<>();
    List<Notification> notificationList;




    @BeforeEach
    void setUp() {
        teacher = new Teacher( "chioma",Role.TEACHER,"chioma@gmail.com","1234", "","",transactionList, messageList, notificationList, "school","20", Status.INSERVICE,"", SchoolType.SECONDARY,"oxy.png",subjectList);
        listSubject = new ArrayList<>();
        listSubject.add("Math");
    }

    @Test
    @DisplayName("Should return the list of teachers with pagination")
    void getAllTeachersWithPaginationShouldReturnListOfTeachersWithPagination() {
        int pageNo = 1;
        int pageSize = 2;
        Page<TeacherDetails> teacherDetailsPage =
                teacherServiceImpl.getAllTeachersWithPagination(pageNo, pageSize);
        assertEquals(2, teacherDetailsPage.getSize());
    }

    @Test
    void testGetAllTeachersWithPagination() {

        assertTrue(teacherServiceImpl.getAllTeachersWithPagination(1, 3).toList().isEmpty());
        assertTrue(teacherServiceImpl.getAllTeachersWithPagination(Integer.MIN_VALUE, 3).toList().isEmpty());
    }


    @Test
    void registerTeacher() throws IOException {
        multipartFile = mock(MultipartFile.class);
        TeacherRegistrationDto teacherDto = new TeacherRegistrationDto("2012-2016", listSubject, SchoolType.SECONDARY);
        when(userRepository.findUserByEmail(teacherDto.getEmail())).thenReturn(Optional.empty());
        when(userUtil.uploadImage(multipartFile)).thenReturn("uploaded");

        var actual = teacherServiceImpl.registerTeacher(teacherDto);
        assertEquals("success", actual.getMessage());
    }
}

