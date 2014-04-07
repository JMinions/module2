package com.jminions.eatubc;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.AsyncFacebookRunner.RequestListener;
import com.facebook.android.Facebook.DialogListener;
import com.facebook.android.FacebookError;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import android.view.View;

import android.widget.Button;

import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

public class FacebookLogin extends Activity{
	
	private static String APP_ID = "636751449730696";
	
	public static String Name;

	public String Email;

	static StringBuilder strb = new StringBuilder("");
	

	public Facebook facebook = new Facebook(APP_ID);

	private AsyncFacebookRunner mAsyncRunner;
	String FILENAME = "AndroidSSO_data";
	private SharedPreferences mPrefs;

	// Buttons
	Button btnFbLogin;
	//Button btnFbGetProfile;
	Button btnFbLogOut;
	//Button btnShowAccessTokens;
	TextView WelcomeString;
	ImageView ImgFbLogin;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facebook_login);


		ImgFbLogin = (ImageView) findViewById(R.id.fblogin);
		mAsyncRunner = new AsyncFacebookRunner(facebook);
		
		/**
		 * Login button Click event
		 * */
		ImgFbLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//getProfileInformation();
				LogInOrOutToFacebook();
			}
		});
		
		

	}

	/**                       
	 * Function to login or out to facebook          
	 * */      
	@SuppressWarnings("deprecation")
		public void LogInOrOutToFacebook() {
		
			mPrefs = getPreferences(MODE_PRIVATE);
			String access_token = mPrefs.getString("access_token", null);
			long expires = mPrefs.getLong("access_expires", 0);

			if (access_token != null) {
				facebook.setAccessToken(access_token);
			}

			if (!facebook.isSessionValid()) {
				//Intent intent = new Intent (this, RestaurantList.class);
				//startActivity(intent);
				facebook.authorize(this,
						new String[] { "email", "publish_stream","publish_actions", "manage_friendlists" },
						new DialogListener() {

							@Override
							public void onCancel() {
								// Function to handle cancel event
							}

							@Override
							public void onComplete(Bundle values) {
								// Function to handle complete event
								// Edit Preferences and update facebook acess_token
								SharedPreferences.Editor editor = mPrefs.edit();
								editor.putString("access_token",
										facebook.getAccessToken());
								editor.putLong("access_expires",
										facebook.getAccessExpires());
								editor.commit();
								
								getProfileInformation();
								
								
							}

							@Override
							public void onError(DialogError error) {
								// Function to handle error

							}

							@Override
							public void onFacebookError(FacebookError fberror) {
								

							}
						}
				
				);
				
				if (expires != 0) {
					facebook.setAccessExpires(expires);
					//getProfileInformation();
				}
			}
			else {
				Intent intent = new Intent (this, RestaurantList.class);
				startActivity(intent);
				/*try {
					
					facebook.logout(getApplicationContext());
					
					Toast.makeText(FacebookLogin.this, "isSessionValid in logout", Toast.LENGTH_SHORT).show();
					//ImgFbLogin.setImageResource(R.drawable.facebook_login_button);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
			}
		
		
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		facebook.authorizeCallback(requestCode, resultCode, data);
	}

	
	
	/**
	 * Get Profile information by making request to Facebook Graph API
	 * */
	@SuppressWarnings("deprecation")
	public void getProfileInformation() {
		Intent intent = new Intent (this, RestaurantList.class);
		startActivity(intent);
		mAsyncRunner.request("me", new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {
				Log.d("Profile", response);
				String json = response;
				try {
					// Facebook Profile JSON data
					JSONObject profile = new JSONObject(json);
					
					// getting name of the user
					final String name = profile.getString("name");
					Name = name;
					
					// getting email of the user
					final String email = profile.getString("email");
					Email = email;
					
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							Toast.makeText(getApplicationContext(), "Name: " + Name + "\nEmail: " + Email, Toast.LENGTH_LONG).show();
						}

					});

					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onIOException(IOException e, Object state) {
			}

			@Override
			public void onFileNotFoundException(FileNotFoundException e,
					Object state) {
			}

			@Override
			public void onMalformedURLException(MalformedURLException e,
					Object state) {
			}

			@Override
			public void onFacebookError(FacebookError e, Object state) {
			}
		});
	}

	
	
}