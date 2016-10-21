package com.tonlist.validator;


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

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "NotEmpty");
        /*if (event.getTime().length() < 3 || event.getTime().length() > 32) {
            errors.rejectValue("time", "Size.eventForm.time");
        }*/
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "NotEmpty");
       /* if (event.getTime().length() < 3 || event.getTime().length() > 32) {
            errors.rejectValue("date", "Size.eventForm.date");
        }*/
        
        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors, "imageurl", "NotEmpty");
        if (event.getTime().length() < 3 || event.getTime().length() > 32) {
            errors.rejectValue("imageurl", "Size.eventForm.imageurl");
        }*/
        
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");
        if (event.getDescription().length() < 3 || event.getDescription().length() > 500) {
            errors.rejectValue("description", "Size.eventForm.description");
        }

    }
    
  
}
