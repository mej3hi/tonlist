package com.tonlist.persistence.repository;


import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tonlist.persistence.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUsername(String event);
    
    List<Event> findByDate(Date date);
    
    @Transactional
    void deleteByUsername(String username);
    
}
