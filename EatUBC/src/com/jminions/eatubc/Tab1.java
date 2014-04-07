package com.jminions.eatubc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class Tab1 extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View rootView = inflater.inflate(R.layout.activity_tab1, container,
				false);
		ExpandableListView elv = (ExpandableListView) rootView
				.findViewById(R.id.list);
		elv.setAdapter(new ExpandableListAdapter(getActivity()));
		return rootView;
	}

	public class ExpandableListAdapter extends BaseExpandableListAdapter {
		private Context myContext;

		public ExpandableListAdapter(Context context) {
			myContext = context;
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return null;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return 0;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			final int groupp = groupPosition;
			final int childp = childPosition;
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) myContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(
						R.layout.article_list_child_item_layout, null);
			}

			// LinearLayout test = (LinearLayout)
			// findViewById(R.id.LinearLayout1);
			final TextView yourSelection = (TextView) convertView
					.findViewById(R.id.articleContentTextView);
			yourSelection
					.setText(TabsInitActivity.arrChildElements[groupPosition][childPosition]);
			Button button = (Button) convertView.findViewById(R.id.button1);
			button.setOnClickListener(new OnClickListener() {

				public void onClick(View view) {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							myContext);
					builder.setMessage(TabsInitActivity.arrChildElements[groupp][childp]);
					builder.setPositiveButton("Confirm Order",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									TabsInitActivity.amountOrdered[groupp][childp] = TabsInitActivity.amountOrdered[groupp][childp] + 1;
									TabsInitActivity.Price += TabsInitActivity.arrChildPrice[groupp][childp];
									notifyDataSetChanged();
								}
							});
					builder.setNegativeButton("Back",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
					AlertDialog alertDialog = builder.create();
					alertDialog.show();

				}
			});
			// TabsInitActivity.menu[groupPosition][childPosition] = (EditText)
			// convertView.findViewById(R.id.editText1);
			return convertView;
		}

		@Override
		public int getChildrenCount(int groupPosition) {
			return TabsInitActivity.arrChildElements[groupPosition].length;
		}

		@Override
		public Object getGroup(int groupPosition) {
			return null;
		}

		@Override
		public int getGroupCount() {
			return TabsInitActivity.arrGroupElements.length;
		}

		@Override
		public long getGroupId(int groupPosition) {
			return 0;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) myContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(
						R.layout.article_list_item_layout, null);
			}
			TextView groupName = (TextView) convertView
					.findViewById(R.id.articleHeaderTextView);
			groupName.setText(TabsInitActivity.arrGroupElements[groupPosition]);
			return convertView;
		}

		@Override
		public boolean hasStableIds() {
			return false;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}
}