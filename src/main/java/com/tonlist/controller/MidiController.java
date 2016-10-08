package com.tonlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tonlist.service.MidiService;

@Controller
public class MidiController {

	@Autowired
	private MidiService midi;
	
    @RequestMapping("/midi")
    String midi(){
    	midi.updateEvents();
    	
    	return "redirect:/";
    }
	
}