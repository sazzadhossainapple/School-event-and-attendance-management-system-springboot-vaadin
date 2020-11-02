package com.example.springbootbackend.repository;

import com.example.springbootbackend.model.Attendance;;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AttendanceRepository extends CrudRepository<Attendance,Long> {
    public List<Attendance>findAllByName(String name);
    public List<Attendance>findAllByNameContaining(String partialName);
    public List<Attendance> findAttendanceByLocalDate(LocalDate localDate);

}
