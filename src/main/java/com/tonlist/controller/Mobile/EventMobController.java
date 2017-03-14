package com.tonlist.controller.Mobile;

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
     * It store event form that user has create to database.
     * @param event Get the value from the form.
     * @param file Is the image from the form.
     * @param bindingResult It validate the form.
     * @param model Send over erro msg for the image. 
     * @return  Empty json String.
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
	     * @param model Send events over. 
	     * @return List of events.
	     */
	    @GetMapping("/m/myevents")
	    public List<Event> myEvent(){
	    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    	List<Event> events = eventService.findByUsername(username);
	    	//model.addAttribute("events", events);
	    	
	    	return events;
	    }
	    
	    /**
	     * Get called when Get mapping url ("/m/removeEvent") is accessed with GET.
	     * It allow user to remove his own events. 
	     * @param id Is the ID of event.
	     * @return Empty json String.
	     */
	    @GetMapping("/m/removeEvent")
	    public String removeEvent(@RequestParam Long id){
	    	System.out.println(id);
	    	String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    	eventService.deletebyUsernameAndId(username, id);
	    	
	    	return "\"Event successfully removed\"";
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
	        System.out.println(username);
	        System.out.println(event.getId());
	        
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
