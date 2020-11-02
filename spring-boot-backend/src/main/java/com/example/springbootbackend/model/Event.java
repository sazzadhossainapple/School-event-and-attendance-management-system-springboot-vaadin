package com.example.springbootbackend.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {
@Id
private long id;
private LocalDateTime localDateTime;
private String place;
private EventType eventType;
}
