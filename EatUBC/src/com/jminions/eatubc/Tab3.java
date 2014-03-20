package com.jminions.eatubc;

import com.jminions.eatubc.R;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Tab3 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.activity_tab3, container, false);
		
		return rootView;
	}
	public void PlaceOrderClick(View view){
		String strb = String.valueOf(TabsInitActivity.Price); 
		/*strb.append( "Total Price = " + String.valueOf(TabsInitActivity.Price)); 
		String strb2 = strb.toString();
		LinearLayout finalprice = (LinearLayout) getActivity().findViewById(R.id.finalprice);
		//TextView report_line = (TextView) getActivity().findViewById(R.id.PriceFinal);
		TextView report_line = new TextView(getActivity());
		report_line.setText(strb); 
		finalprice.addView(report_line);*/
		Log.i(strb, "asfdsdf");
	}
}