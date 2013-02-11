package com.bixito;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.xml.sax.SAXException;

import android.app.ListFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.bixito.station.BikeStation;
import com.bixito.station.StationParser;

public class ListViewFragment extends ListFragment {
	
	static private StationParser stationParser;
	static private ArrayList<BikeStation> stationList;
	ProgressDialog dialog;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Check if the savedInstanceState is null if so, initialize it
		if(savedInstanceState == null){
		}
		
		
		//Get the bixi data to parse
		try {
			stationParser = new StationParser(getString(R.string.bike_station_data_url));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		loadStationList();
		
	}
	
	private boolean loadStationList(){
		new stationListLoader().execute();
		return true;
	}
	
	private class stationListLoader extends AsyncTask<String,Void,Void>{

		protected void onPreExecute(){
			dialog = ProgressDialog.show(getActivity(), 
					getString(R.string.loading), 
					getString(R.string.station_data_loading_dialog), 
					true);
		}
		
		@Override
		protected Void doInBackground(String... arg0) {
			try {
				stationList = stationParser.getStationList();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		protected void onPostExecute(Void unused){
			dialog.dismiss();
			ListViewAdapter adapter = new ListViewAdapter(getActivity(), stationList);
			setListAdapter(adapter);
			//TODO: Make use of List_view layout

		}
		
	
		
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		BikeStation selectedStation = stationList.get(position);

		Intent i = new Intent(getActivity(), StationDetailActivity.class);
		i.putExtra("com.bixito.station.BikeStation", selectedStation);
		startActivity(i);
		
		
	}
	
}
