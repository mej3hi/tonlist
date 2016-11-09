package com.tonlist.service;


import com.tonlist.persistence.entities.Event;
import com.tonlist.persistence.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    
    /**
     * Save event into database
     * @param event Is the event form.
     */
    public void save(Event event) {
        eventRepository.save(event);
    }
    
	/**
	 * Find all events that was create by user
	 * @param event Is the specific username.
	 * @return List of events.
	 */
    public List<Event> findByUsername(String username) {
        return eventRepository.findByUsername(username);
    }
    
    /**
     * Find all events with that date.
     * @param date Is the specific date. 
     * @return list of events.
     */
    public List<Event> findByDate(Date date) {
        return eventRepository.findByDate(date);
    }
    
    /**
     * Find one event that has same specific username and id.
     * @param username Is the specific username.
     * @param id Is the specific id.
     * @return event. 
     */
    public Event findByUsernameAndId(String username, Long id) {
        return eventRepository.findByUsernameAndId(username, id);
    }
    
    /**
     * Find first six event that is from this day and up.
     * @return List of events
     */    
    public List<Event> findFirst6ByOrderByDateAsc(){
    	return eventRepository.findFirst6ByOrderByDateAsc();
    }
    
    
    public void deletebyUsername(String username){
    	eventRepository.deleteByUsername(username);
    }
    
    /**
     * Delete event that has same specific username and id.
     * @param username Is the specific username.
     * @param id Is the specific event id.
     */    
    public void deletebyUsernameAndId(String username, Long id){
    	eventRepository.deleteByUsernameAndId(username, id);
    }

    /**
     * Find all date in the event database. 
     * @return Array of date.
     */
    public Date[] findAllDates(){
    	return eventRepository.findAllDates();
    }

}
