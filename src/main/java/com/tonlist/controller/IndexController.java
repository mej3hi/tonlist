package com.tonlist.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tonlist.persistence.entities.Event;
import com.tonlist.service.EventService;

@Controller
public class IndexController {
	
    @Autowired
    private EventService eventService;
	
	@GetMapping("/")
    String index(Model model){
		List<Event> events = eventService.findFirst6ByOrderByDateAsc();
		model.addAttribute("events", events);

		String dates = arrayToString(eventService.findAllDates());
		model.addAttribute("dates", dates);
		
		return "index";
    }
	
	private String arrayToString(Date[] array){
		String s = "";
		for(int i=0; i < array.length; i++){
			s+=","+dateToString(array[i]);
		}
		return s.replaceFirst(",", "");
	}
	
	private String dateToString(Date date){
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(date);
	}
	
	//hallo
 
}
