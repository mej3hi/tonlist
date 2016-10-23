package com.tonlist.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.tonlist.persistence.entities.Event;

@Component
public class EventValidator implements Validator {
	@Override
	public boolean supports(Class<?> aClass) {
		return Event.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Event event = (Event) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");
		if (event.getName().length() < 3 || event.getName().length() > 32) {
			errors.rejectValue("name", "Size.eventForm.name");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location", "NotEmpty");
		if (event.getLocation().length() < 3 || event.getLocation().length() > 32) {
			errors.rejectValue("location", "Size.eventForm.location");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty");
		if (!validDate(event.getDate())){
			errors.rejectValue("date", "Size.eventForm.date");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "NotEmpty");
		if (!validTime(event.getTime())){
			errors.rejectValue("time", "Size.eventForm.time");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
		if (event.getDescription().length() < 3 || event.getDescription().length() > 500) {
			errors.rejectValue("description", "Size.eventForm.description");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "musicgenres", "NotEmpty");
		
	}

	private static Pattern pattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
	private static Matcher matcher;

	public static boolean validTime(String time) {
		matcher = pattern.matcher(time);
		return matcher.matches();
	}

	public static boolean validDate(Date date) {

		if (date == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);

		try {
			sdf.parse(sdf.format(date));

		} catch (ParseException e) {

			return false;
		}

		return true;
	}

}




