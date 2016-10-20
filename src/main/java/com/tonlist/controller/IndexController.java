package com.tonlist.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tonlist.persistence.entities.Event;
import com.tonlist.service.EventService;

@Controller
public class IndexController {
	
    @Autowired
    private EventService eventService;
	
	@RequestMapping("/")
    String index(Model model){
		Date day = today();
		List<Event> events = eventService.findByDate(day);
		model.addAttribute("events", events);
		model.addAttribute("day", day);
		
		return "index";
    }
	
	private Date today(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");      
		Date day=new Date();
		try {
			day = sdf.parse(sdf.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return day;
	}
	
 
}
