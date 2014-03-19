package com.jminions.eatubc;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.app.ExpandableListActivity;

public class Tab2 extends Fragment {
	private static final String arrGroupElements[] = { "Item1", "Item2", "Item3",
		"Item4", "Item5", "Item6", "Item7", "Item8", "Item9" };
		/**
		* strings for child elements
		*/
		private static final String arrChildElements[][] = {
		{ "Details1 A","Details1 B", "Details1 C" },
		{ "Details2 B", "Details2 C" },
		{ "Details3 C" },
		{ "Details4 A","Details4 B", "Details4 C" },
		{ "Details5 A","Details5 B", "Details5 C" },
		{ "Details6 A","Details6 B", "Details6 C" },
		{ "Details7" },
		{ "Details8" },
		{ "Details9" }
		};

	private ExpandableListAdapter mAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	View rootView = inflater.inflate(R.layout.activity_tab2, container, false);
    ExpandableListView elv = (ExpandableListView) rootView.findViewById(R.id.list);
    elv.setAdapter( new ExpandableListAdapter(getActivity()));
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
	if (convertView == null) {
	LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	convertView = inflater.inflate(
	R.layout.article_list_child_item_layout, null);
	}
	TextView yourSelection = (TextView) convertView.findViewById(R.id.articleContentTextView);
	yourSelection.setText(arrChildElements[groupPosition][childPosition]);
	return convertView;
	}
	@Override
	public int getChildrenCount(int groupPosition) {
	return arrChildElements[groupPosition].length;
	}
	@Override
	public Object getGroup(int groupPosition) {
	return null;
	}
	@Override
	public int getGroupCount() {
	return arrGroupElements.length;
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
	groupName.setText(arrGroupElements[groupPosition]);
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
	
	private static final String NAME = "NAME";
	private static final String IS_EVEN = "IS_EVEN";

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);

	    List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
	    List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
	    for (int i = 0; i < 10; i++) {
	        Map<String, String> curGroupMap = new HashMap<String, String>();
	        groupData.add(curGroupMap);
	        curGroupMap.put(NAME, "Group " + i);
	        curGroupMap.put(IS_EVEN, (i % 2 == 0) ? "This group is even" : "This group is odd");

	        List<Map<String, String>> children = new ArrayList<Map<String, String>>();
	        for (int j = 0; j < 2; j++) {
	            Map<String, String> curChildMap = new HashMap<String, String>();
	            children.add(curChildMap);
	            curChildMap.put(NAME, "Child " + j);
	            curChildMap.put(IS_EVEN, (j % 2 == 0) ? "This child is even" : "This          child     is odd");
	        }
	        childData.add(children);
	    }
	    ExpandableListView lv = (ExpandableListView) getActivity().findViewById(R.id.expandableListView1);
	    // Set up our adapter
	    mAdapter = new SimpleExpandableListAdapter(
	            getActivity(),
	            groupData,
	            R.layout.list_group,
	            new String[] { NAME, IS_EVEN },
	            new int[] { R.id.listHeader1, R.id.listHeader1 },
	            childData,
	            android.R.layout.simple_expandable_list_item_2,
	            new String[] { NAME, IS_EVEN },
	            new int[] { R.id.listHeader1, R.id.listHeader1 }
	            );
	    lv.setAdapter(mAdapter);
	}
}