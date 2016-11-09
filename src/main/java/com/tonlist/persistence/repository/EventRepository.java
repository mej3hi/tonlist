package com.tonlist.persistence.repository;


import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tonlist.persistence.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByUsername(String event);
    
    List<Event> findByDate(Date date);
    
    Event findByUsernameAndId(String username, Long id);
    
    @Query(nativeQuery = true, value = "SELECT * FROM EVENTS WHERE DATE >= CURDATE() ORDER BY DATE ASC LIMIT 6")
    List<Event> findFirst6ByOrderByDateAsc();
    
    @Transactional
    void deleteByUsername(String username);
    
    @Transactional
    void deleteByUsernameAndId(String username, Long id);
    
    @Query(nativeQuery = true, value = "SELECT DATE FROM EVENTS WHERE DATE >= CURDATE() GROUP BY DATE")
    Date[] findAllDates();
    
}
