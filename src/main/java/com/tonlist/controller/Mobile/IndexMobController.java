package com.tonlist.controller.Mobile;

import java.util.Date;
import java.util.List;

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
	

    
	
	@GetMapping("/m/")
	public List<Event> event(){
		List<Event> events = eventService.findFirst6ByOrderByDateAsc();
		
		return events;
		
	}
	
	

}



