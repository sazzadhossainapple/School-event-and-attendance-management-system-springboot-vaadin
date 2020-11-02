package com.example.springbootbackend.service;

import com.example.springbootbackend.exception.ResourceAlreadyExistException;
import com.example.springbootbackend.exception.ResourceDoseNotExistException;
import com.example.springbootbackend.model.Attendance;
import com.example.springbootbackend.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;

    }

    // findById method
    public Attendance findById(long id) throws ResourceDoseNotExistException {

        Optional<Attendance> optionalAttendance = attendanceRepository.findById(id);
        if (optionalAttendance.isPresent()) {
            return optionalAttendance.get();
        } else {
            throw new ResourceDoseNotExistException(id + "");
        }
    }

    //findAll method
    public List<Attendance> findAll() {
        List<Attendance> attendanceList = new ArrayList<>();
        attendanceRepository.findAll().forEach(attendanceList::add);
        return attendanceList;
    }


    //deleteById method
    public boolean deleteById(long id) throws ResourceDoseNotExistException {

        Optional<Attendance> attendanceOptional = attendanceRepository.findById(id);
        attendanceOptional.ifPresent(event -> attendanceRepository.deleteById(id));
        attendanceOptional.orElseThrow(() -> new ResourceDoseNotExistException(id + ""));
        return true;

    }

    //insert method
    public Attendance insertAttendance(Attendance attendance) throws ResourceAlreadyExistException {
        Optional<Attendance> optionalAttendance = attendanceRepository.findById(attendance.getId());
        if (optionalAttendance.isPresent()) {
            throw new ResourceAlreadyExistException(attendance.getId() + "");
        } else {
            return attendanceRepository.save(attendance);
        }

    }


    //update method

    public Attendance updateAttendance(long id, Attendance attendance) throws ResourceDoseNotExistException {
        Optional<Attendance> optionalAttendance = attendanceRepository.findById(id);
        if (optionalAttendance.isPresent()) {

            attendance.setId(id);
            return attendanceRepository.save(attendance);

        } else {
            throw new ResourceDoseNotExistException(id + "");
        }

    }
}
