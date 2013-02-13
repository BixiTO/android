package com.bixito;

import java.util.ArrayList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bixito.station.BikeStation;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapViewFragment extends MapFragment {
	ArrayList<BikeStation> stationList = null;
	ArrayList<MarkerOptions> markerList = null;
	
	@Override
	public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle savedInstanceState) {
	    View view = super.onCreateView(arg0, arg1, savedInstanceState);
	    
	    if(getArguments() != null){
	    	stationList = getArguments().getParcelableArrayList("stationList");
	    	Log.d("DEBUG", "Mapview got: " + stationList.size() + " stations.");
	    	initMap();
	    }
	    else
	    	Log.d("DEBUG", "MapView has a null station list.");
	    
	    
	    return view;
	}
	
	private void initMap(){
		UiSettings mapsSettings = getMap().getUiSettings();

		//MarkerOptions m = new MarkerOptions();
		//m.position(new LatLng(40, 40));
		//m.title("Hello!");
		//getMap().addMarker(m);
		placeMarkers();
		GoogleMap map = getMap();
		map.setMyLocationEnabled(true);
		//map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		
		
	}
	
	private void placeMarkers(){
		markerList = new ArrayList<MarkerOptions>();
		MarkerOptions currentMarker;
		BikeStation currentStation = null;
		for(int i = 0; i < stationList.size(); i++){
			currentMarker = new MarkerOptions();
			currentStation = stationList.get(i);
			currentMarker.position(new LatLng(currentStation.getLatitude(), currentStation.getLongitude()));
			currentMarker.title(currentStation.getStationName());
			getMap().addMarker(currentMarker);
		}
	}
	
	public void updateStationList(ArrayList<BikeStation> stationList){
		this.stationList = stationList;
		
		Log.d("DEBUG", "Got: " + stationList.size() + " stations in the map.");
	}
}
