package com.jminions.eatubc;



import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Facebook.DialogListener;


import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressWarnings("deprecation")
public class Tab3 extends Fragment {

	public AsyncFacebookRunner mAsyncRunner;
	private static String APP_ID = "636751449730696"; // Replace with your App ID
	private Facebook facebook = new Facebook(APP_ID);

	
	public final static String EXTRA_MESSAGE = "com.jminions.eatubc.MESSAGE";
	
	StringBuilder strb = new StringBuilder("");
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final LinearLayout order_tab = new LinearLayout(getActivity());
		order_tab.setOrientation(LinearLayout.VERTICAL);

		final LinearLayout price_tab = new LinearLayout(getActivity());
		price_tab.setOrientation(LinearLayout.VERTICAL);

		strb.append(FacebookLogin.Name + "\n---------------------------------------");
		
		int indexi = 0;
		int indexj = 0;
		
		for (indexj = 0; indexj < 3; indexj++) {
			for (indexi = 0; indexi < 2; indexi++) {
				if (TabsInitActivity.amountOrdered[indexi][indexj] != 0) {
					TextView temp = new TextView(getActivity());
					TextView amount = new TextView(getActivity());
					temp.setText(TabsInitActivity.arrChildElements[indexi][indexj]);
					strb.append(TabsInitActivity.arrChildElements[indexi][indexj]+"\n");
					amount.setText("Amount:" + TabsInitActivity.amountOrdered[indexi][indexj]
							+ "\n-----------------------------------");
					strb.append("Amount:" + TabsInitActivity.amountOrdered[indexi][indexj]
							+ "\n-----------------------------------\n");
					price_tab.addView(temp);
					price_tab.addView(amount);
					temp.setWidth(100);
					amount.setWidth(100);
				}
			}
			indexi = 0;
		}

		final TextView price = new TextView(getActivity());
		price.setText("Thank You " + FacebookLogin.Name + "\n---------------------------------------" + "\n" + 
		"Total Price: $" + String.valueOf(TabsInitActivity.Price
				+ "\n-----------------------------------"));
		strb.append("Total Price: $" + String.valueOf(TabsInitActivity.Price
				+ "\n-----------------------------------\n"));
		price_tab.addView(price);
		price.setWidth(100);
		order_tab.addView(price_tab);
		
		
		Button btnCancel = new Button(getActivity()); 
	    btnCancel.setText("Cancel Order"); 
	    order_tab.addView(btnCancel); 
	    btnCancel.setOnClickListener(new Button.OnClickListener() {  
        public void onClick(View v)
            {
        	
        	for (int indexj = 0; indexj < 3; indexj++) {
    			for (int indexi = 0; indexi < 2; indexi++) {
    				TabsInitActivity.amountOrdered[indexi][indexj] = 0;
    			}
        	}
        	TabsInitActivity.Price = 0.00;
        	order_tab.removeView(price_tab);


        	order_tab.invalidate();
        	strb.setLength(0);
            }
         });



	    Button btnPlace = new Button(getActivity());
	    btnPlace.setText("Place Order"); 
	    order_tab.addView(btnPlace);

	    final Button btnPostToWall = new Button(getActivity());
	    order_tab.addView(btnPostToWall);
	    btnPostToWall.setVisibility(View.INVISIBLE);

	    btnPlace.setOnClickListener(new Button.OnClickListener() {
	    	public void onClick(View v)
	    	{
	    		 	btnPostToWall.setVisibility(View.VISIBLE);
	    			btnPostToWall.setText("Post on Facebook!");
	    			
	    			Intent intent = new Intent(getActivity(), FoodMenuActivity.class);
	    			startActivity(intent);
	    			String message = strb.toString();
	    			intent.putExtra(EXTRA_MESSAGE, message);
	    			
	    			btnPostToWall.setOnClickListener( new Button.OnClickListener() {
	    				@Override
	    				public void onClick(View v) {
	    					postToWall("abcdefg test");
	    				}
	    			}
	    			);

	    	}
	    });


		return order_tab;

}

	public void postToWall(final String message) {
		facebook.dialog(getActivity(), "feed", new DialogListener() {

			@Override
			public void onFacebookError(FacebookError e) {
			}

			@Override
			public void onError(DialogError e) {
			}

			@Override
			public void onComplete(Bundle values) {
				values.putString("message", message);
			}

			@Override
			public void onCancel() {
			}
		});
	}
}