package com.jminions.eatubc;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RestaurantList extends Activity{
	
	public final static String EXTRA_MESSAGE = "com.jminions.eatubc.MESSAGE";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant_list);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void PitPubClick(View view){
		Intent Tabs_Init_Activity = new Intent(this, TabsInitActivity.class);
		startActivity(Tabs_Init_Activity);
	}
}