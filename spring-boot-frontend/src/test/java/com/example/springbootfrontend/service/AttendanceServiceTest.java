package com.example.springbootfrontend.service;

import com.example.springbootfrontend.model.Attendance;
import com.example.springbootfrontend.model.Event;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttendanceServiceTest {
    @Autowired
    private AttendanceService attendanceService;

    public void testFindAll(){
        List<Attendance> attendanceList= attendanceService.findAll();
        assertNotNull(attendanceList);
       attendanceList.forEach(System.out::println);

    }

    @Test
    public void testFindById(){
     Attendance expectedAttendance = new Attendance(2017000000199l, "Sazzad Hossain", LocalDate.of(2020,Month.JUNE,5));
      Attendance actualById= attendanceService.findById(2017000000199l);
        assertEquals(expectedAttendance,actualById);

    }

}
