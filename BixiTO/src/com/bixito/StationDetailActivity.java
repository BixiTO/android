package com.bixito;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bixito.station.BikeStation;

public class StationDetailActivity extends Activity {

	BikeStation bikeStation = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.station_details);
		
		Log.d("DEBUG", "Got: " + getIntent().getExtras().size() + " number of Parcels.");
		bikeStation = (BikeStation) getIntent().getExtras().getParcelable("com.bixito.station.BikeStation");
		
		TextView textView1 = (TextView) findViewById(R.id.textView1);
		textView1.setText("Station Name = " + bikeStation.getStationName());
		
	}

}
