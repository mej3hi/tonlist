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
    
    /**
     * Get called when Get mapping url ("/createEvent") is accessed with GET.
     * It open event form for the user to fill out.
     * @param model Create new Event for the form.
     * @return createEvent html page.
     */  
    @GetMapping("/createEvent")
    String createEvent(Model model){	
		model.addAttribute("event", new Event());
        
		return "createEvent";
    }
	
    /**
     * Get called when Post mapping url ("/createEvent") is accessed with POST.
     * It store event form that user has create to database.
     * @param event Get the value from the form.
     * @param file Is the image from the form.
     * @param bindingResult It validate the form.
     * @param model Send over erro msg for the image. 
     * @return createEvent html page.
     */
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
    
    /**
     * Get called when Get mapping url ("/myevents") is accessed with GET.
     * It finds all events that user has made.
     * @param model Send events over. 
     * @return myevents html page.
     */
    @GetMapping("/myevents")
    String myEvent(Model model){
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	List<Event> events = eventService.findByUsername(username);
    	model.addAttribute("events", events);
    	
    	return "myevents";
    }
    
    /**
     * Get called when Get mapping url ("/removeEvent") is accessed with GET.
     * It allow user to remove his own events. 
     * @param id Is the ID of event.
     * @return redirect:/myevents html page.
     */
    @GetMapping("/removeEvent")
    String removeEvent(@RequestParam Long id){
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	eventService.deletebyUsernameAndId(username, id);
    	
    	return "redirect:/myevents";
    }
    
    /**
     * Get called when Get mapping url ("/editEvent") is accessed with GET.
     * It allow user to edit his events.
     * @param id Is the ID of event.
     * @param model Send event over. 
     * @return editEvent html page.
     */
    @GetMapping("/editEvent")
    String editEvent(@RequestParam Long id, Model model){
    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
    	Event event = eventService.findByUsernameAndId(username, id);
    	model.addAttribute("event", event);
    	
    	return "editEvent";
    }
    
    /**
     * Get called when Post mapping url ("/editEvent") is accessed with POST.
     * It store event form that user has edit to database.
     * @param event Get the value from the form.
     * @param file Is the image from the form.
     * @param bindingResult It validate the form.
     * @param model Send over erro msg for the image. 
     * @return redirect:/myevents html page.
     */
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
