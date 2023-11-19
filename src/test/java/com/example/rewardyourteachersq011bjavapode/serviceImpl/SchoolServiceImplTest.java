package com.example.rewardyourteachersq011bjavapode.serviceImpl;

import com.example.rewardyourteachersq011bjavapode.dto.SchoolDTO;
import com.example.rewardyourteachersq011bjavapode.models.School;
import com.example.rewardyourteachersq011bjavapode.repository.SchoolRepository;
import com.example.rewardyourteachersq011bjavapode.response.ApiResponse;
import org.aspectj.weaver.ast.Var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class SchoolServiceImplTest {

    @Mock
    private SchoolRepository schoolRepository;

    @InjectMocks
    private SchoolServiceImpl schoolService;

    private School school;
    private School school2;
    private List<School> schoolList;
    private SchoolDTO schoolDTO;

    @BeforeEach
    void setUp() {
        school = new School("fgc", "nise", "anambra", "primary");
        school2 = new School("fggc", "nise", "anambra", "primary");
        schoolList = new ArrayList<>(Arrays.asList(school, school2));

        schoolDTO = new SchoolDTO("St Charles","Lagos","Lagos","SECONDARY");

        schoolRepository.saveAllAndFlush(schoolList);
        when(schoolRepository.findAll()).thenReturn(schoolList);
    }

    @Test
    void addSchool() {
        Reader reader = new StringReader("name,address,state,type\nfgc,nise, Anambra,Secondary\nfgc,nise, Anambra,Secondary\nfgc,nise, Anambra,Secondary");
        BufferedReader bufferedReader = new BufferedReader(reader);
        var actual = schoolService.addSchool(bufferedReader);
        assertEquals("Schools  Added", actual);

    }

    @Test
    void addNewSchool() {
        ApiResponse<School>response =schoolService.addNewSchools(schoolDTO);
        String expected  = "St Charles";
        assertEquals(expected, response.getData().getName());



    }
}