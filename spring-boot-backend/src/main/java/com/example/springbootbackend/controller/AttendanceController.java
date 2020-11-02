package com.example.springbootbackend.controller;

import com.example.springbootbackend.exception.ResourceAlreadyExistException;
import com.example.springbootbackend.exception.ResourceDoseNotExistException;
import com.example.springbootbackend.model.Attendance;
import com.example.springbootbackend.service.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v3/attendance")
public class AttendanceController {

    private AttendanceService attendanceService;
    public AttendanceController(AttendanceService attendanceService){
        this.attendanceService = attendanceService;

    }



    @GetMapping("/{id}")
    public ResponseEntity<Attendance> getAttendance(@PathVariable long id) {

        try {
            Attendance attendance = attendanceService.findById(id);
            return ResponseEntity.ok(attendance);
        } catch (ResourceDoseNotExistException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Attendance>> getAttendances() {
        List<Attendance> attendanceList = attendanceService.findAll();
        return ResponseEntity.ok(attendanceList);
    }



    @PostMapping("")
    public ResponseEntity<Attendance> insertAttendance(@RequestBody Attendance attendance) {
        try {
           Attendance insertAttendance = attendanceService.insertAttendance(attendance);
            return ResponseEntity.status(HttpStatus.CREATED).body(insertAttendance);
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().body(null);
        }

    }


    @PutMapping(value = "{id}")
    public ResponseEntity<?> saveAttendance(@RequestBody Attendance attendance,
                                       @PathVariable("id") long id) throws ResourceDoseNotExistException {

        Attendance currentUser = attendanceService.findById(id);
        if (currentUser == null) {
            return new ResponseEntity(new ResourceDoseNotExistException("Unable to update. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        currentUser.setName(attendance.getName());
        currentUser.setLocalDate(attendance.getLocalDate());
        attendanceService.updateAttendance(id,currentUser);
        return new ResponseEntity<Attendance>(currentUser, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteAttendance(@PathVariable long id) {
        try {
            boolean deleted = attendanceService.deleteById(id);
            return ResponseEntity.ok(id);
        } catch (ResourceDoseNotExistException e) {
            return ResponseEntity.notFound().build();

        }

    }
}
