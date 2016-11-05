package com.tonlist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tonlist.model.FileManager;
import com.tonlist.persistence.entities.Event;
import com.tonlist.service.EventService;
import com.tonlist.validator.EventValidator;


@Controller
public class EventController {
	
    @Autowired
    private EventService eventService;
    
    @Autowired
    private EventValidator eventValidator;

    @GetMapping("/createEvent")
    String createEvent(Model model){	
		model.addAttribute("event", new Event());
        
		return "createEvent";
    }
	
    @PostMapping("/createEvent")
    public String postEvent(@ModelAttribute Event event, @RequestParam("file") MultipartFile file, BindingResult bindingResult, Model model) {
    	
	    	if(file.isEmpty()){
	    		String noImage = "Please choose picture";
				model.addAttribute("erroImage", noImage);
	    		return "createEvent";
	    	}
	    	
	    	if(file.getSize() > 2097152){
	    		String imageToBig = "The picture is to big, Max size 2mb";
				model.addAttribute("erroImage", imageToBig);
	    		return "createEvent";
	    	}
     	

    	eventValidator.validate(event, bindingResult);
               
        if (bindingResult.hasErrors()) 
            return "createEvent";
        
    	String s = FileManager.storeFile(file);
        event.setImageurl(s);
        eventService.save(event);
        
        return "redirect:/myevents";
    }
    
    @RequestMapping("/myevents")
    String myEvent(Model model){
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	List<Event> events = eventService.findByUsername(username);
    	model.addAttribute("events", events);
    	
    	return "myevents";
    }
    
    @RequestMapping("/removeEvent")
    String removeEvent(@RequestParam Long id){
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	eventService.deletebyUsernameAndId(username, id);
    	
    	return "myevents";
    }
    
    @GetMapping("/editEvent")
    String editEvent(@RequestParam Long id, Model model){
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	Event event = eventService.findByUsernameAndId(username, id);
    	System.out.println("getMapping"+event.getImageurl());
    	model.addAttribute("event", event);
    	
    	return "editEvent";
    }
    
    @PostMapping("/editEvent")
    public String postEditEvent(@ModelAttribute Event event, @RequestParam("file") MultipartFile file, BindingResult bindingResult, Model model) {

	    	if(!file.isEmpty() && file.getSize() > 2097152){
	    		String imageToBig = "The picture is to big, Max size 2mb";
				model.addAttribute("erroImage", imageToBig);
	    		return "editEvent";
	    	}
     	

    	eventValidator.validate(event, bindingResult);
               
        if (bindingResult.hasErrors()) 
            return "editEvent";
        
    	String s = FileManager.storeFile(file);
        event.setImageurl(s);
        eventService.save(event);
        
        return "redirect:/myevents";
    }

}
