package com.example.bixito.station;

import java.util.ArrayList;

public class StationParser {
	String sourceDocument;
	
	public StationParser(String sourceDocument){
		this.sourceDocument = sourceDocument;
	}
	/**
	 * Returns a dummy station list with fake data
	 * @return a Dummy BikeStation Array List
	 */
	public ArrayList<BikeStation> getDummyStationList(){
		ArrayList<BikeStation> bikeStations = new ArrayList<BikeStation>();
		bikeStations.add(new BikeStation(0, "Station1", "Terminal1", 0, 0, 0, true, false, false, true, 0, 0, 0));
		bikeStations.add(new BikeStation(0, "Station2", "Terminal2", 1, 1, 1, true, false, false, true, 1, 1, 1));
		return bikeStations;
	}
}
