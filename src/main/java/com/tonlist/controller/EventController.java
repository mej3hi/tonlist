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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.tonlist.model.FileManager;
import com.tonlist.persistence.entities.Event;
import com.tonlist.service.EventService;
import com.tonlist.validator.EventValidator;


@Controller
@SessionAttributes("event")
public class EventController {
	
    @Autowired
    private EventService eventService;
    
    @Autowired
    private EventValidator eventValidator;
    
    @Autowired
    private FileManager fileManager;
    

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
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
    	String s = fileManager.storeFile(file, username, event.getName());
    	
    	if(s == "erro"){
    		String imageS3Erro = "Faild to store image in our database, Please try again";
			model.addAttribute("erroImage", imageS3Erro);
    		return "createEvent";
    	}
    	
    	event.setUsername(username);
        event.setImageurl(s);
        eventService.save(event);
        
        return "redirect:/myevents";
    }
    
    @GetMapping("/myevents")
    String myEvent(Model model){
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	List<Event> events = eventService.findByUsername(username);
    	model.addAttribute("events", events);
    	
    	return "myevents";
    }
    
    @GetMapping("/removeEvent")
    String removeEvent(@RequestParam Long id){
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	eventService.deletebyUsernameAndId(username, id);
    	
    	return "redirect:/myevents";
    }
    
    @GetMapping("/editEvent")
    String editEvent(@RequestParam Long id, Model model){
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	Event event = eventService.findByUsernameAndId(username, id);
    	model.addAttribute("event", event);
    	
    	return "editEvent";
    }
    
    @PostMapping("/editEvent")
    public String postEditEvent(@ModelAttribute Event event, @RequestParam("file") MultipartFile file, BindingResult bindingResult, Model model) {
    	System.out.println("lala: "+event.getImageurl());
    	if( !file.isEmpty() || event.getImageurl() == null){
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
    	}

    	eventValidator.validate(event, bindingResult);
               
        if (bindingResult.hasErrors()) 
            return "editEvent";
        
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        
        if(!file.isEmpty()){
	    	String s = fileManager.storeFile(file, username, event.getName());
	    	
	    	if(s == "erro"){
	    		String imageS3Erro = "Faild to store image in our database, Please try again";
				model.addAttribute("erroImage", imageS3Erro);
	    		return "createEvent";
	    	}
	    	
	        event.setImageurl(s);
        }
               
        eventService.save(event);
        
        return "redirect:/myevents";
    }

}
