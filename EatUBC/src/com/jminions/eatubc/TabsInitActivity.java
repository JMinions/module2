package com.jminions.eatubc;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class TabsInitActivity extends FragmentActivity implements
		ActionBar.TabListener {
	
	public static int amountOrdered[][]={{0,0,0},{0,0,0}};
	
	public static double arrChildPrice[][] = {
		{ 4.75, 5.50, 5.50 }, 
		{1.50, 1.50, 1.50},
	};
	
	public static String arrGroupElements[] = { "Food", "Drink" };
	/**
	 * strings for child elements
	 */
	public static String arrChildElements[][] = {
			{ 	"Wednesday Special - Hamburger and fries \n $4.75", 
				"Hamburger Deluxe \n $5.50",
				"Cheeseburger Deluxe \n $5.50" },
			{ 	"Mountain Dew Can \n $1.50", 
				"Pepsi Can \n $1.50", 
				"Rootbeer Can \n $1.50" }, };
	public final static String EXTRA_MESSAGE = "com.jminions.eatubc.MESSAGE";
	public static int[] order = new int[20];
	
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	
	//Facebook implementation
	private Fragment mainFragment;
	
	// Tab titles
	private String[] tabs = { "Menu", "Special", "Place Order" };
	public static EditText[][] menu;

	protected static double Price = 0.00;
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabs_init);
		
		if (savedInstanceState == null) {
	        // Add the fragment on initial activity setup
	        mainFragment = new Fragment();
	        getSupportFragmentManager()
	        .beginTransaction()
	        .add(android.R.id.content, mainFragment)
	        .commit();
	    } else {
	        // Or set the fragment from restored state info
	        mainFragment = (Fragment) getSupportFragmentManager()
	        .findFragmentById(android.R.id.content);
	    }
		
		// Show the Up button in the action bar.
		setupActionBar();
		// Initialization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}
		
		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
			

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tabs_init, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/*public void foodMenu(View view){
		Intent Food_Menu_Activity = new Intent(this, FoodMenuActivity.class);
		startActivity(Food_Menu_Activity);
	} */
	
}
