package com.example.springbootfrontend.service;

import java.util.List;

public interface GenericAttendanceService<T,I> {
    List<T> findAll();
    T findById(I i);
    T saveAttendance(T t);
    void delete(T t);
}
