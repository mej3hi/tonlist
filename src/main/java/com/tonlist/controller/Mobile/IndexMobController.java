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
	

    
	
	@GetMapping("/m/")
	public List<Event> event(HttpServletRequest h, HttpServletResponse r){
		
		Enumeration headerName = h.getHeaderNames();
		System.out.println("Header :  ");
		while (headerName.hasMoreElements()){
			String k = (String)headerName.nextElement();		
			System.out.println(h.getHeader(k));
		}
		
		
		Enumeration b = h.getParameterNames();
		System.out.println("Attribute :  ");
		while (b.hasMoreElements()){
			String k = (String)b.nextElement();		
			System.out.println(h.getParameter(k));
		}
		
    	Enumeration c = h.getParameterNames();
		System.out.println("Parameter :  ");
		while (c.hasMoreElements()){
			String k = (String)c.nextElement();		
			System.out.println(h.getParameter(k));
		}
		
		
		List<Event> events = eventService.findFirst6ByOrderByDateAsc();
		
		return events;
		
	}
	
	

}



