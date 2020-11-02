package com.example.springbootfrontend.service;

import com.example.springbootfrontend.model.Attendance;
import com.example.springbootfrontend.repository.AttendanceRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class AttendanceService implements GenericAttendanceService<Attendance,Long> {

    private AttendanceRepository attendanceRepository;
    public AttendanceService(AttendanceRepository attendanceRepository){
        this.attendanceRepository = attendanceRepository;

    }

    public Attendance findById(Long id)
    {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Attendance> response = restTemplate
                .exchange(
                        "https://schooleventmanagementsystem.herokuapp.com/api/v3/attendance" + "/" + id,
                        HttpMethod.GET,
                        null,
                        Attendance.class);
       Attendance attendance=response.getBody();
        return attendance;

    }

    public List<Attendance> findAll(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Attendance>> response = restTemplate
                .exchange(
                        "https://schooleventmanagementsystem.herokuapp.com/api/v3/attendance",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<Attendance>>() {
                        }
                );
        List<Attendance>attendanceList=response.getBody();
        return attendanceList;

    }


    public Attendance saveAttendance(Attendance attendance){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Attendance> attendanceHttpEntity =new HttpEntity<>(attendance);
        ResponseEntity<Attendance> eventResponseEntity = restTemplate
                .exchange(
                        "https://schooleventmanagementsystem.herokuapp.com/api/v3/attendance",
                        HttpMethod.POST,
                      attendanceHttpEntity,
                        Attendance.class);

        return eventResponseEntity.getBody();
    }


    public void delete(Attendance attendance){

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Attendance> response =
                restTemplate.exchange("https://schooleventmanagementsystem.herokuapp.com/api/v3/attendance/{id}",
                        HttpMethod.DELETE,
                        new HttpEntity<>(attendance),
                        Attendance.class,
                        attendance.getId());
        response.getBody();

    }

}
