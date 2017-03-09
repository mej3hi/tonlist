package com.tonlist.controller.Mobile;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tonlist.extraUtilities.ConvertTools;
import com.tonlist.persistence.entities.Event;
import com.tonlist.service.EventService;

@RestController
public class CalanderMobController {
	
    @Autowired
    private EventService eventService;
    
	/**	
	 * Get called when Get mapping url ("/calander") is accessed with GET.
	 * It finds all event to this day 
	 * @param day Is the day to look for.
	 * @param model Send over the events and day.
	 * @return day html page
	 */
	@GetMapping("/m/calander")
	public List<Event> calander(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date day){
		List<Event> events = eventService.findByDate(day);

		
		String dates = ConvertTools.arrayToString(eventService.findAllDates());

		
		return events;
	}

}
