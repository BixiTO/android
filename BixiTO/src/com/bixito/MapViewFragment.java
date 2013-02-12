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
	
	@Override
	public View onCreateView(LayoutInflater arg0, ViewGroup arg1, Bundle arg2) {
	    View view = super.onCreateView(arg0, arg1, arg2);
	    initMap();
	    return view;
	}
	
	private void initMap(){
		UiSettings mapsSettings = getMap().getUiSettings();

		MarkerOptions m = new MarkerOptions();
		m.position(new LatLng(40, 40));
		m.title("Hello!");
		getMap().addMarker(m);
		
		GoogleMap map = getMap();
		map.setMyLocationEnabled(true);
		//map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		
		
	}
	
	public void updateStationList(ArrayList<BikeStation> stationList){
		this.stationList = stationList;
		
		Log.d("DEBUG", "Got: " + stationList.size() + " stations in the map.");
	}
}
