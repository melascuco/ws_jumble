package com.melascuco.faketrafficevents.data;

public class Coordinate {
	Double lat;
	Double log;
	
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLog() {
		return log;
	}

	public void setLog(Double log) {
		this.log = log;
	}

	public String toString() {
		return "Lat: " + lat + ", Log: " + log;
	}

}
