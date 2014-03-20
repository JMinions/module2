package com.jminions.eatubc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tab3 extends Fragment {

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
		}
			}
		indexi = 0;
		}
		return order_tab;
	}
}