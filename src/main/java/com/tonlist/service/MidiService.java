package com.tonlist.service;

import java.net.URL;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonlist.persistence.entities.Event;

@Service
public class MidiService {
	
    @Autowired
    private EventService eventService;
	
	/**
	 * Updates midi events to database from apis.is (gagnaveitu)
	 */
	public void updateEvents(){
		eventService.deletebyUsername("midi");

		try{
			String requestURL = "http://apis.is/concerts";
			URL midiRequest =  new URL(requestURL);
			 
			JSONTokener tokener = new JSONTokener(midiRequest.openStream());
	
			JSONObject obj = new JSONObject(tokener);
			 
			JSONArray array = obj.getJSONArray("results");
			for(int i = 0 ; i < array.length() ; i++){
				 Event tmp = new Event();
				 tmp.setUsername("midi");
				 tmp.setName(array.getJSONObject(i).getString("eventDateName"));
				 tmp.setLocation(array.getJSONObject(i).getString("eventHallName"));
	
				 String s = array.getJSONObject(i).getString("dateOfShow");
				 Date d = java.sql.Date.valueOf(s.replaceAll("T.*", ""));
	
				 tmp.setSqlDate(d);
				 tmp.setTime(s.replaceAll(".*?T(\\d{2}:\\d{2}):\\d{2}","$1"));
				 tmp.setImageurl(array.getJSONObject(i).getString("imageSource"));
				 
				 eventService.save(tmp);
			}
		}catch(Exception e){}
	}
		
}