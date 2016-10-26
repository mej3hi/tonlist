package com.tonlist.persistence.entities;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "events")
public class Event {
    private Long id;
	private String username;
	private String name;
	private String location;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date;
	private String time;
	private String imageurl;
	private String description;
	private String musicgenres;
	
	public Event(){
		this.musicgenres = "Other";
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date){
    	this.date = date;
    }
    
    public void setSqlDate(Date date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusicgenres() {
		return musicgenres;
	}

	public void setMusicgenres(String musicgenres) {
		this.musicgenres = musicgenres;
	}



}