package com.example.bixito.station;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.xml.sax.SAXException;

import android.sax.Element;
import android.sax.EndElementListener;
import android.sax.EndTextElementListener;
import android.sax.RootElement;
import android.util.Log;
import android.util.Xml;

public class StationParser {
	URL sourceDocumentURL;
	//Tags from the XML file
	static final String ROOT_TAG = "stations";
	//Currently unused, should give the last date/time of when the source
	//XML file was updated
	//static final String LIST_UPDATE_TIME = "lastUpdate";
	static final String STATION_TAG = "station";
	static final String ID = "id";
	static final String NAME = "name";
	static final String TERMINAL_NAME = "terminalName";
	static final String LAST_COMM_WITH_SERVER = "lastCommWithServer";
	static final String LATITUDE = "lat";
	static final String LONGITUDE = "long";
	static final String INSTALLED = "installed";
	static final String LOCKED = "locked";
//	static final String INSTALL_DATE = "installDate";
//	static final String REMOVAL_DATE = "removalDate";
	static final String TEMPORARY = "temporary";
	static final String PUBLIC = "public";
	static final String NUMBER_OF_BIKES = "nbBikes";
	static final String NUMBER_OF_EMPTY_DOCKS = "nbEmptyDocks";
	static final String STATION_LAST_UPDATE_TIME = "latestUpdateTime";
	
	public StationParser(String sourceDocument) throws MalformedURLException{
		this.sourceDocumentURL = new URL(sourceDocument);
	}
	
	/**
	 * Returns the fully parsed bike station list
	 * @return a BikeStation ArrayList
	 * @throws SAXException 
	 * @throws IOException 
	 */
	public ArrayList<BikeStation> getStationList() throws IOException, SAXException{
		//Create arraylist to store bike station objects
		final ArrayList<BikeStation> bikeStations = new ArrayList<BikeStation>();
		Log.d("DEBUG", "Starting to parse XML file: " + sourceDocumentURL.toString());
		
		final BikeStation currentStation = new BikeStation();
		
		//Find root tag
		RootElement root = new RootElement(ROOT_TAG);
		
		//Set the tag used to identify a station
		Element station = root.getChild(STATION_TAG);
		
		//Add listeners for each xml tag to populate the station object's data
		
		//At the end of each station, add it to the array
		station.setEndElementListener(new EndElementListener(){
			public void end(){
				bikeStations.add(currentStation.copy());
			}
		});
		
		station.getChild(ID).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setStationId(Integer.parseInt(contents));
			}
		});
		station.getChild(NAME).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setStationName(contents);
			}
		});
		station.getChild(TERMINAL_NAME).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setTerminalName(contents);
			}
		});
		station.getChild(LAST_COMM_WITH_SERVER).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setLastCommWithServer(Long.parseLong(contents));
			}
		});
		station.getChild(LATITUDE).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setLatitude(Double.parseDouble(contents));
			}
		});
		station.getChild(LONGITUDE).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setLongitude(Double.parseDouble(contents));
			}
		});
		station.getChild(INSTALLED).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setInstalled(contents.equalsIgnoreCase("true"));
			}
		});
		station.getChild(LOCKED).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setLocked(contents.equalsIgnoreCase("true"));
			}
		});
		/* Currently unused
		station.getChild(INSTALL_DATE).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setInstallDate();
			}
		});
		station.getChild(REMOVAL_DATE).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setRemovalDate;
			}
		});
		*/
		station.getChild(TEMPORARY).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setTemporary(contents.equalsIgnoreCase("true"));
			}
		});
		station.getChild(PUBLIC).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setPublicStation(contents.equalsIgnoreCase("true"));
			}
		});
		station.getChild(NUMBER_OF_BIKES).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setNbBikes(Integer.parseInt(contents));
			}
		});
		station.getChild(NUMBER_OF_EMPTY_DOCKS).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setNbEmptyDocks(Integer.parseInt(contents));
			}
		});
		station.getChild(STATION_LAST_UPDATE_TIME).setEndTextElementListener(new EndTextElementListener(){
			public void end(String contents){
				currentStation.setLatestUpdateTime(Long.parseLong(contents));
			}
		});
		
		//Attempt to download the xml file
		Xml.parse(sourceDocumentURL.openConnection().getInputStream(), 
				Xml.Encoding.UTF_8,
				root.getContentHandler());
		
		Log.d("DEBUG", "Successfully parsed XML File.");
		
		return bikeStations;
	}
	
	

}

