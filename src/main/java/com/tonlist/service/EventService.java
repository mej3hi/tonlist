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

    public void save(Event event) {
        eventRepository.save(event);
    }

    public List<Event> findByUsername(String username) {
        return eventRepository.findByUsername(username);
    }
    
    public List<Event> findByDate(Date date) {
        return eventRepository.findByDate(date);
    }
    
    public Event findByUsernameAndId(String username, Long id) {
        return eventRepository.findByUsernameAndId(username, id);
    }
    
    public List<Event> findFirst6ByOrderByDateAsc(){
    	return eventRepository.findFirst6ByOrderByDateAsc();
    }
    
    public void deletebyUsername(String username){
    	eventRepository.deleteByUsername(username);
    }
    
    public void deletebyUsernameAndId(String username, Long id){
    	eventRepository.deleteByUsernameAndId(username, id);
    }
    
    public Date[] findAllDates(){
    	return eventRepository.findAllDates();
    }

}
