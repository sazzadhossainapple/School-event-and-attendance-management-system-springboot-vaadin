package com.example.springbootfrontend.service;

import java.util.List;

public interface GenericEventService<T,I>{
    List<T> findAll();
    T findById(I i);
    T saveEvent(T t);
    void delete(T t);

}
