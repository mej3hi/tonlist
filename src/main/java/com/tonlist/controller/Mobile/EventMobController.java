package com.tonlist.controller.Mobile;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tonlist.extraUtilities.FileManager;
import com.tonlist.persistence.entities.Event;
import com.tonlist.service.EventService;
import com.tonlist.validator.EventValidator;

@RestController
public class EventMobController {
	

    @Autowired
    private EventService eventService;
    
    @Autowired
    private EventValidator eventValidator;
    
    @Autowired
    private FileManager fileManager;
	

    
    /**
     * Get called when Post mapping url ("/m/createEvent") is accessed with POST.
     * It stores event form that user has created to database.
     * @param event contains all the values from the form.
     * @param file Is the image from the form.
     * @param bindingResult It validates the form. 
     * @return message string
     */    
	 @PostMapping("/m/createEvent")
	    public String postEvent(@RequestParam("file") MultipartFile file, @ModelAttribute Event event, BindingResult bindingResult) { 	
		 
		 event.setImageurl("");
	    		
		    	if(file.isEmpty()){
		    		event.setImageurl("NoImage");
		    	}
		    	
		    	if(file.getSize() > 2097152){
					event.setImageurl("SizeErr");
		    	}
	     	
	    	eventValidator.validate(event, bindingResult);
	               
	        if (bindingResult.hasErrors()){ 
	            return "\"hasError\"";
	        }
	        String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    	String s = fileManager.storeFile(file, username, event.getName());
	    	
	    	if(s == "erro"){
	    		return "\"imageS3Error\"";
	    	}
	    	
	    	
	    	event.setUsername(username);
	        event.setImageurl(s);
	        eventService.save(event);
	        
	        return "\"Event successfully created\"";
	    }
	 
	   /**
	     * Get called when Get mapping url ("/m/myevents") is accessed with GET.
	     * It finds all events that user has made.
	     * @return List of events in json format
	     */
	    @GetMapping("/m/myevents")
	    public List<Event> myEvent(){
	    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    	List<Event> events = eventService.findByUsername(username);
	    	Collections.reverse(events);
	    	
	    	
	    	return events;
	    }
	    
	    /**
	     * Get called when Get mapping url ("/m/removeEvent") is accessed with GET.
	     * It allows user to remove his own events. 
	     * @param id Is the ID of event.
	     * @return message string
	     */
	    @GetMapping("/m/removeEvent")
	    public String removeEvent(@RequestParam Long id){
	    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    	eventService.deletebyUsernameAndId(username, id);
	    	
	    	return "\"Event successfully removed\"";
	    }
	    
	    
	    /**
	     * Get called when Post mapping url ("/editEvent") is accessed with POST.
	     * It stores event form that user has edit to database.
	     * @param event contains all the values from the form.
	     * @param file Is the image from the form.
	     * @param bindingResult It validates the form. 
	     * @return message string
	     */
	    @PostMapping("/m/editEvent")
	    public String postEditEvent(@RequestParam("file") MultipartFile file, @ModelAttribute Event event, BindingResult bindingResult) {
	    
	    	if(file.isEmpty() && event.getImageurl()==null){
	    		event.setImageurl("NoImage");
	    	}
	    	
	    	if(!file.isEmpty() && file.getSize() > 2097152){
	    		event.setImageurl("SizeErr");
	    	}

	    	eventValidator.validate(event, bindingResult);
	               
	        if (bindingResult.hasErrors()){
	            return "\"error\"";
	        }
	        
	        String username = SecurityContextHolder.getContext().getAuthentication().getName();
	
	       
	        if(!file.isEmpty()){
		    	String s = fileManager.storeFile(file, username, event.getName());
		    	
		    	if(s == "erro"){
		    		String imageS3Erro = "\"error\"";
					
		    		return imageS3Erro;
		    	}
		    	
		        event.setImageurl(s);
	        }
	        
	        event.setUsername(username);
	        eventService.save(event);
	        
	        return "\"Event successfully saved\"";
	    }
	    
}
