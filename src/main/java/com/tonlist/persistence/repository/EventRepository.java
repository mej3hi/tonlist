package com.tonlist.persistence.repository;


import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tonlist.persistence.entities.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
	/**
	 * Find all events that was create by user
	 * @param event Is the specific username.
	 * @return List of events.
	 */
    List<Event> findByUsername(String event);
    
    /**
     * Find all events with that date.
     * @param date Is the specific date. 
     * @return list of events.
     */
    List<Event> findByDate(Date date);
    
    /**
     * Find one event that has same specific username and id.
     * @param username Is the specific username.
     * @param id Is the specific id.
     * @return event. 
     */
    Event findByUsernameAndId(String username, Long id);
    
    /**
     * Find first six event that is from this day and up.
     * @return List of events
     */
    @Query(nativeQuery = true, value = "select * from events where date >= curdate() order by date asc limit 6")
    List<Event> findFirst6ByOrderByDateAsc();
    
  
    @Transactional
    void deleteByUsername(String username);
    
    /**
     * Delete event that has same specific username and id.
     * @param username Is the specific username.
     * @param id Is the specific event id.
     */
    @Transactional
    void deleteByUsernameAndId(String username, Long id);
    
    /**
     * Find all date in the event database. 
     * @return Array of date.
     */

    @Query(nativeQuery = true, value = "select date from events where date >= curdate() group by date")
    Date[] findAllDates();
    
}
