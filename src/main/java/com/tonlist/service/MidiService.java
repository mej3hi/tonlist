package com.tonlist.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonlist.model.Event;

@Service
public class MidiService {
	
    @Autowired
    private EventService eventService;
	
	public void updateEvents(){
		eventService.deletebyUsername("midi");

		
		String requestURL = "http://apis.is/concerts";
		URL midiRequest = null;
		try {
			midiRequest = new URL(requestURL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 JSONTokener tokener = null;
		try {
			tokener = new JSONTokener(midiRequest.openStream());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		 
	}
		
}