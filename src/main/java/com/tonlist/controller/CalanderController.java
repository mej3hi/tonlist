package com.tonlist.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tonlist.model.Event;
import com.tonlist.service.EventService;



@Controller
public class CalanderController {
    @Autowired
    private EventService eventService;
	
	@RequestMapping("/calander")
	public String calander(@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date day, Model model){
		List<Event> events = eventService.findByDate(day);
		model.addAttribute("events", events);
		model.addAttribute("day", day);
		
		return "day";
	}
}