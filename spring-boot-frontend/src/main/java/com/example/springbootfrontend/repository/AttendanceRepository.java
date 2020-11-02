package com.example.springbootfrontend.repository;


import com.example.springbootfrontend.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
//    public List<Attendance>findAllByName(String name);
//    public List<Attendance>findAllByNameContaining(String partialName);
//    public List<Attendance> findAttendanceByLocalDate(LocalDate localDate);

}
