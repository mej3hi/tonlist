package com.tonlist.service;


import com.tonlist.model.Event;
import com.tonlist.repository.EventRepository;
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
    
    public void deletebyUsername(String username){
    	eventRepository.deleteByUsername(username);
    }

}