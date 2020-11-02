package com.example.springbootbackend.repository;

import com.example.springbootbackend.model.Attendance;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttendanceRepositoryTest {

    @Autowired
    private AttendanceRepository attendanceRepository;
    @Test
    public void  testCreatedAttendance(){
        Attendance attendance = new Attendance(1234l,"Sazzad Hossain", LocalDate.now());
        Attendance savedAttendance = attendanceRepository.save(attendance);
        assertEquals(attendance,savedAttendance);
    }
}
