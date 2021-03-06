package com.tonlist.controller.Mobile;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tonlist.extraUtilities.ConvertTools;
import com.tonlist.persistence.entities.Event;
import com.tonlist.service.EventService;


@RestController
public class IndexMobController {
	
	
    @Autowired
    private EventService eventService;

    /**
     * Get called when Get mapping url ("/") is accessed with GET.
     * It returns first 6 events
     * @return list of events in json format
     */
	@GetMapping("/m/")
	public List<Event> event(HttpServletRequest h, HttpServletResponse r){
			
		List<Event> events = eventService.findFirst6ByOrderByDateAsc();
		
		return events;
		
	}
	
	

}



