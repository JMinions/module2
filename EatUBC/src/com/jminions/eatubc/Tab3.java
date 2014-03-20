package com.jminions.eatubc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Tab3 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		String message = TabsInitActivity.arrChildElements[TabsInitActivity.groupPos][TabsInitActivity.groupPos];
		
		/*TextView textView = new TextView(getActivity());
	    textView.setTextSize(20);
	    textView.setText(message);
*/
	    // Set the text view as the activity layout
	    TableRow.LayoutParams params = new TableRow.LayoutParams(600, 100);
        TableLayout table = new TableLayout(getActivity());
        
       for( int i = 0; i < TabsInitActivity.total; i++) {
            TableRow row = new TableRow(getActivity());
            //for (int j = 0; j < 3; j++) {
                TextView text = new TextView(getActivity());
                text.setLayoutParams(params);
                text.setText(message);
                row.addView(text);
            //}
            table.addView(row);
            
        }
      

		//View rootView = inflater.inflate(R.layout.activity_tab3, container, false);
		
		return table;
	}
	
	
}