package com.jminions.eatubc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tab3 extends Fragment {
	
	StringBuilder strb = new StringBuilder("");
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout order_tab = new LinearLayout(getActivity());
		int indexi = 0;
		int indexj = 0;
		for (indexj = 0; indexj< 3;indexj++ ){
		for (indexi = 0; indexi< 2;indexi++ ){
		if(TabsInitActivity.amountOrdered[indexi][indexj] != 0){
			TextView temp = new TextView(getActivity());
			temp.setText(TabsInitActivity.arrChildElements[indexi][indexj]);
			order_tab.addView(temp);
			temp.setWidth(100);
			strb.append(temp + "\n");
		}
			}
		indexi = 0;
		}
		TextView price = new TextView(getActivity());
		price.setText(String.valueOf(TabsInitActivity.Price));
		strb.append(String.valueOf(TabsInitActivity.Price));
		order_tab.addView(price);
		price.setWidth(100);
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