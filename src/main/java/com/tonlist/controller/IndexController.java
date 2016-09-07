package com.tonlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    String index(ModelMap model){
		model.put("serverTime", "Þetta tokst");
		
        return "index";
    }
 
}
