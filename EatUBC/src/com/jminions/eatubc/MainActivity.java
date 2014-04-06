package com.jminions.eatubc;


import android.app.Activity;
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
		
		Intent intent = new Intent(this, FacebookLogin.class);
		startActivity(intent);	 

	}

}

