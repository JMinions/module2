package com.jminions.eatubc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tab3 extends Fragment {
	
	StringBuilder strb = new StringBuilder("");
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final LinearLayout order_tab = new LinearLayout(getActivity());
		order_tab.setOrientation(LinearLayout.VERTICAL);
		
		final LinearLayout price_tab = new LinearLayout(getActivity());
		price_tab.setOrientation(LinearLayout.VERTICAL);
		
		int indexi = 0;
		int indexj = 0;

		for (indexj = 0; indexj < 3; indexj++) {
			for (indexi = 0; indexi < 2; indexi++) {
				if (TabsInitActivity.amountOrdered[indexi][indexj] != 0) {
					TextView temp = new TextView(getActivity());
					TextView amount = new TextView(getActivity());
					temp.setText(TabsInitActivity.arrChildElements[indexi][indexj]);
					amount.setText("Amount:" + TabsInitActivity.amountOrdered[indexi][indexj]
							+ "\n-----------------------------------");
					price_tab.addView(temp);
					price_tab.addView(amount);
					temp.setWidth(100);
					amount.setWidth(100);
				}
			}
			indexi = 0;
		}
		
		final TextView price = new TextView(getActivity());
		price.setText("Total Price: $" + String.valueOf(TabsInitActivity.Price
				+ "\n-----------------------------------"));
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
//        	order_tab.removeView(price);
//        	order_tab.removeView(temp);
//        	order_tab.removeView(amount);
        	//order_tab.removeAllViews();
        	order_tab.invalidate();

            }
         });
		
		
		Button btn = new Button(getActivity()); 
	    btn.setText("Place Order"); 
	    order_tab.addView(btn); 
	    
	    
		return order_tab;
	}
	public void PlaceOrderClick(View view){
		//Do this when user clicks Place Order Button
		LinearLayout order_final = new LinearLayout(getActivity());
		TextView final_order = new TextView(getActivity());
		final_order.setText(strb);
		order_final.addView(final_order);
		
	}
}