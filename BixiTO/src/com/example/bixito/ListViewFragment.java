package com.example.bixito;

import java.util.ArrayList;

import android.app.ListFragment;
import android.os.Bundle;

import com.example.bixito.station.BikeStation;
import com.example.bixito.station.StationParser;

public class ListViewFragment extends ListFragment {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StationParser stationParser = new StationParser("dummy");
		ArrayList<BikeStation> stationList = stationParser.getDummyStationList();
		
		ListViewAdapter adapter = new ListViewAdapter(getActivity(), stationList);
		setListAdapter(adapter);
		//TODO: Make use of List_view layout
		//TODO: Use real data
	}
	
}
