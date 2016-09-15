package com.tonlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    String index(ModelMap model){
		model.put("serverTime", "Er farinn í rasgat 2");
		
        return "index";
    }
 
	@RequestMapping(value = "/index2", method = RequestMethod.GET)
    String index2(ModelMap model){
		//model.put("serverTime", "Er farinn í rasgat 2");
		
        return "index2";
    }
 
}
