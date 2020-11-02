package com.example.springbootbackend.service;

import com.example.springbootbackend.exception.ResourceAlreadyExistException;
import com.example.springbootbackend.model.Attendance;
import com.example.springbootbackend.model.Event;
import com.example.springbootbackend.repository.AttendanceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import static com.example.springbootbackend.model.EventType.Chess;
import static com.example.springbootbackend.model.EventType.DrawingCompetitions;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttendanceServiceTest {

    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Test
    public void deleteAll(){
       attendanceRepository.deleteAll();
    }
    @Test
    public void testInsertEvent() {
        Attendance attendance = new Attendance(1234,"Sazzad Hossain", LocalDate.of(2020,Month.FEBRUARY,23));

        try {
          Attendance insertAttendance = attendanceService.insertAttendance(attendance);
            assertEquals(attendance, insertAttendance);
        } catch (ResourceAlreadyExistException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = ResourceAlreadyExistException.class)
    public void  testInsertExitingEvent() throws ResourceAlreadyExistException{
        Attendance attendance1 = new Attendance(1235,"Sazzad Hossain",LocalDate.of(2020,Month.JANUARY,23));
        Attendance attendance2 = new Attendance(1235,"Khalid Hossain",LocalDate.of(2020,Month.JANUARY,23));

        try {
            Attendance insertAttendance = attendanceService.insertAttendance(attendance1);
            assertEquals(attendance1,insertAttendance);
        } catch (ResourceAlreadyExistException e) {
            e.printStackTrace();
        }
        try {
            Attendance insertAttendance = attendanceService.insertAttendance(attendance2);
            assertEquals(attendance2,insertAttendance);
        } catch (ResourceAlreadyExistException e) {
            throw e;
        }
    }
}
