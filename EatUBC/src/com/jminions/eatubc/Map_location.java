package com.jminions.eatubc;

import android.app.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map_location extends Activity {
	private GoogleMap googleMap;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_location);
 
        try {
            // Loading map
            initilizeMap();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.map_location, menu);
		return true;
	}
 
    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();
            googleMap.setMyLocationEnabled(true);
             
            // create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(49.2674821,-123.2501161)).title("Pit Pub Burger Bar");
            MarkerOptions marker2 = new MarkerOptions().position(new LatLng(49.266791,-123.242526)).title("UBC Village Mcdonalds");
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            // adding marker
            googleMap.addMarker(marker);
            googleMap.addMarker(marker2);

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getApplicationContext(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }

        }
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }
 
}
