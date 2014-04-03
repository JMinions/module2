package com.jminions.eatubc;


import android.app.Activity;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;


public class MainActivity extends Activity{
	
	//Intent intent = new Intent(this, DisplayOrderActivity.class);
	public final static String EXTRA_MESSAGE = "com.jminions.eatubc.MESSAGE";

	EditText order_line_1, order_line_2, order_line_3, order_line_4;
	
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void OnOrderClick(View view) {
		
		Intent intent = new Intent(this, RestaurantList.class);
		startActivity(intent);	 

	}
	public void foodMenu(View view){
		Intent Food_Menu_Activity = new Intent(this, FoodMenuActivity.class);
		startActivity(Food_Menu_Activity);
	} 
}

