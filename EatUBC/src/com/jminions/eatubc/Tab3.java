package com.jminions.eatubc;

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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout order_tab = new LinearLayout(getActivity());
		order_tab.setOrientation(LinearLayout.VERTICAL);
		int indexi = 0;
		int indexj = 0;
		for (indexj = 0; indexj < 3; indexj++) {
			for (indexi = 0; indexi < 2; indexi++) {
				if (TabsInitActivity.amountOrdered[indexi][indexj] != 0) {
					TextView temp = new TextView(getActivity());
					temp.setText(TabsInitActivity.arrChildElements[indexi][indexj]);
					order_tab.addView(temp);
					temp.setWidth(100);
				}
			}
			indexi = 0;
		}
		
		TextView price = new TextView(getActivity());
		price.setText("Total Price: $" + String.valueOf(TabsInitActivity.Price));
		order_tab.addView(price);
		price.setWidth(100);
		
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
            }
         });
		
		
		Button btn = new Button(getActivity()); 
	    btn.setText("Place Order"); 
	    order_tab.addView(btn); 
	    
	    
		return order_tab;
	}
}