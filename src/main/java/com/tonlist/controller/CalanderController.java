package com.tonlist.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tonlist.extraUtilities.ConvertTools;
import com.tonlist.persistence.entities.Event;
import com.tonlist.service.EventService;



@Controller
public class CalanderController {
	
    @Autowired
    private EventService eventService;
    
	/**	
	 * Get called when Get mapping url ("/calander") is accessed with GET.
	 * It finds all event to this day 
	 * @param day Is the day to look for.
	 * @param model Send over the events and day.
	 * @return day html page
	 */
	@GetMapping("/calander")
	public String calander(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date day, Model model){
		List<Event> events = eventService.findByDate(day);
		model.addAttribute("events", events);
		model.addAttribute("day", day);
		
		String dates = ConvertTools.arrayToString(eventService.findAllDates());
		model.addAttribute("dates", dates);
		
		return "index";
	}
	

}