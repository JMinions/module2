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

	public void onOrderClick(View view) {
		
		order_line_1 = (EditText) findViewById(R.id.editText1);
		order_line_2 = (EditText) findViewById(R.id.editText2);
		order_line_3 = (EditText) findViewById(R.id.editText3);
		order_line_4 = (EditText) findViewById(R.id.editText4);
		
		Intent intent = new Intent(this, TabsInitActivity.class);
		 
		 
		int quantity_1, quantity_2, quantity_3, quantity_4;

		// Get the quantity of each item ordered from the
		// appropriate input field. If the user did not enter
		// anything, assume 0

		try {
			quantity_1 = Integer.parseInt(order_line_1.getText().toString());
		} catch (NumberFormatException nfe) {
			quantity_1 = 0;
		}
		try {
			quantity_2 = Integer.parseInt(order_line_2.getText().toString());
		} catch (NumberFormatException nfe) {
			quantity_2 = 0;
		}
		try {
			quantity_3 = Integer.parseInt(order_line_3.getText().toString());
		} catch (NumberFormatException nfe) {
			quantity_3 = 0;
		}
		try {
			quantity_4 = Integer.parseInt(order_line_4.getText().toString());
		} catch (NumberFormatException nfe) {
			quantity_4 = 0;
		}

		// Sum up the total quantity of items ordered

		int total_items = quantity_1 + quantity_2 + quantity_3 + quantity_4;

		// This is BAD! Hardcoding prices is a bad idea.

		int total_price = quantity_1 * 6 + quantity_2 * 3 + quantity_3 * 7
				+ quantity_4 * 4;

		// Create a string that will hold the confirmation // message.

		StringBuilder strb = new StringBuilder("");
		strb.append("Total items ordered = " + total_items + "\n");
		strb.append("Total Cost = " + total_price + "\n");
		
		String message = strb.toString();
		//intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);	 
				
		// Rather than writing to a pre-allocated field on the
		// screen, create a new view, and write to it.

		LinearLayout top_linear_layout = (LinearLayout) findViewById(R.id.LinearLayout1);

		TextView report_line = new TextView(this);
		report_line.setText(strb);
		top_linear_layout.addView(report_line);
		
		
		
		
		
		

	}

}

