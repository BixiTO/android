package com.bixito;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.bixito.station.BikeStation;

public class StationDetailActivity extends Activity {

	BikeStation bikeStation = null;
	TextView stationPopTextView = null;
	int stationRank = -1;
	int stationRankChange = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.station_details);
		
		Log.d("DEBUG", "Got: " + getIntent().getExtras().size() + " number of Parcels.");
		bikeStation = (BikeStation) getIntent().getExtras().getParcelable("com.bixito.station.BikeStation");
		
		TextView textView1 = (TextView) findViewById(R.id.textView1);
		textView1.setText("Station Name = " + bikeStation.getStationName());
		
		stationPopTextView = (TextView) findViewById(R.id.stationPopTextView);
		
		String stationPopUrl = getString(R.string.station_pop_json_url).replace("STATION_ID", Integer.toString(bikeStation.getStationId()));
		new LoadStationPopularityTask().execute(new String[] { stationPopUrl });
		
	}
	
	/**
	 * Loads the selected station's popularity by using a json call to the ruby database
	 * @author andrew
	 *
	 */
	private class LoadStationPopularityTask extends AsyncTask<String, Void, String> {
		String jsonData = "";
		
		@Override
		protected String doInBackground(String... urls) {
			try{
				HttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(urls[0]);
				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity entity = httpResponse.getEntity();
				
				if(entity != null){
					jsonData = EntityUtils.toString(entity);
				}
			}
			catch (ClientProtocolException e){
				Log.e("ERROR", e.getMessage());
				stationPopTextView.setText(getString(R.string.station_pop_error));
			}
			catch (IOException e){
				Log.e("ERROR", e.getMessage());
				stationPopTextView.setText(getString(R.string.station_pop_error));
			}
			
			return jsonData;
		}
		
		@Override
		protected void onPostExecute(String result){
			try{
				//Parse the json data
				JSONObject jsonObject = new JSONObject(result);

				stationRank = jsonObject.getInt("rank");
				stationRankChange = jsonObject.getInt("upordown");
				
				if(stationRank > 25)
					stationPopTextView.setText(getString(R.string.station_pop_low));
				else
					stationPopTextView.setText(stationRank + " (" + stationRankChange + ")");
				
				
			}
			catch(JSONException e){
				Log.e("ERROR", e.getMessage());
				stationPopTextView.setText(getString(R.string.station_pop_error));

			}
		}
		
		
	}

}
