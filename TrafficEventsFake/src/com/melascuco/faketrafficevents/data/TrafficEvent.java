package com.melascuco.faketrafficevents.data;

import java.util.Date;

public class TrafficEvent {
	
	int severity = 0;
	int durationMins = 0;
	Date time;
	String title;
	String description;
	Coordinate coordinate;
	
	
	
	public int getSeverity() {
		return severity;
	}



	public void setSeverity(int severity) {
		this.severity = severity;
	}



	public int getDurationMins() {
		return durationMins;
	}



	public void setDurationMins(int durationMins) {
		this.durationMins = durationMins;
	}



	public Date getTime() {
		return time;
	}



	public void setTime(Date time) {
		this.time = time;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Coordinate getCoordinate() {
		return coordinate;
	}



	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}



	public String toString() {
		return "Event in " + coordinate.toString() + ". " + title + ". Description: " + description;
	}

}
