package com.bixito;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
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
	    	if(googleMapsIsInstalled()){
	    		initMap();
	    	}
	    	else{
	    		//Tell user to install google maps
	    		installGoogleMaps();
	    	}
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
	
	private void installGoogleMaps(){
		Builder b = new AlertDialog.Builder(getActivity());
		b.setMessage(getString(R.string.google_maps_missing));
		b.setPositiveButton(getString(R.string.google_maps_install_button), installGoogleMapsListener());
		AlertDialog dialog = b.create();
		dialog.show();
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
			currentMarker.snippet("Bikes: " + currentStation.getNbBikes() + " Empty: " + currentStation.getNbEmptyDocks());
			getMap().addMarker(currentMarker);
		}
	}
	
	public void updateStationList(ArrayList<BikeStation> stationList){
		this.stationList = stationList;
		
		Log.d("DEBUG", "Got: " + stationList.size() + " stations in the map.");
	}
	
	public boolean googleMapsIsInstalled(){
		try{
			ApplicationInfo info = this.getActivity().getPackageManager().getApplicationInfo("com.google.android.apps.maps", 0);
			//If no exception is returned, then Google Maps is installed
			return true;
		}
		catch(PackageManager.NameNotFoundException e){
			//Google Maps is not installed
			return false;
		}
	}
	
	public OnClickListener installGoogleMapsListener(){
		return new OnClickListener(){

			@Override
			public void onClick(DialogInterface dialog, int arg1) {
				
				//Launch the market to install google maps
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.apps.maps"));
				startActivity(intent);
				
				getActivity().finish();
			}
			
		};
	}
}
