package com.tonlist.repository;


import com.tonlist.model.Event;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUsername(String event);
    
    List<Event> findByDate(Date date);
    
    @Transactional
    void deleteByUsername(String username);
    
}
