package com.bixito;

import java.util.ArrayList;
import java.util.Comparator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bixito.station.BikeStation;

public class ListViewAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<BikeStation> bikeStations;
	
	public ListViewAdapter(Context context, ArrayList<BikeStation> bStations){
		//save the current activity context		
		this.context = context;
		
		//sort the BikeStation's by name
		java.util.Collections.sort(bStations, new MyComparator());
		
		this.bikeStations = bStations;
	}
	
	//custom comparator used to sort BikeStation's by name
	private class MyComparator implements Comparator<BikeStation> {
	    @Override
	    public int compare(BikeStation o1, BikeStation o2) {
	        return o1.getStationName().compareTo(o2.getStationName());
	    }
	}
	
	/**
	 * Returns the number of bike stations.
	 */
	public int getCount() {
		return bikeStations.size();
	}

	public Object getItem(int position) {
		return bikeStations.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		BikeStation station = bikeStations.get(position);
		
		//If the view for the item hasn't been created yet, create it
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_item, null);
		}

		//Display data
		TextView text1 = (TextView) convertView.findViewById(R.id.text1);
		text1.setText(station.getStationName());
		
		TextView text2 = (TextView) convertView.findViewById(R.id.text2);
		text2.setText(station.getTerminalName());
		
		
		return convertView;
		
	}

}
