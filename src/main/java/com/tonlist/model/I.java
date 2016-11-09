package com.tonlist.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class I {
	
	public static String arrayToString(Date[] array){
		String s = "";
		for(int i=0; i < array.length; i++){
			s+=","+dateToString(array[i]);
		}
		return s.replaceFirst(",", "");
	}
	
	public static String dateToString(Date date){
		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		return df.format(date);
	}
	
}