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
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.facebook_login);

		btnFbLogin = (Button) findViewById(R.id.btn_fblogin);
		mAsyncRunner = new AsyncFacebookRunner(facebook);
		WelcomeString = (TextView) findViewById(R.id.textView1);
		btnFbLogOut = (Button) findViewById(R.id.btn_fb_logout);
		
		//getProfileInformation();
		
		
		//WelcomeString.setText("Name: " + Name + "\n" + "Email: " + Email);
		
		/**
		 * Login button Click event
		 * */
		btnFbLogin.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//getProfileInformation();
				loginToFacebook();
			}
		});
		
		btnFbLogOut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				logoutFromFacebook();
			}
		});

	}

	/**
	 * Function to login into facebook
	 * */
	@SuppressWarnings("deprecation")
		public void loginToFacebook() {
		
			mPrefs = getPreferences(MODE_PRIVATE);
			String access_token = mPrefs.getString("access_token", null);
			long expires = mPrefs.getLong("access_expires", 0);

			if (access_token != null) {
				facebook.setAccessToken(access_token);
			}

			if (!facebook.isSessionValid()) {
				Intent intent = new Intent (this, RestaurantList.class);
				startActivity(intent);
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
								btnFbLogOut.setVisibility(View.VISIBLE);
								getProfileInformation();
								//btnFbLogin.setVisibility(View.INVISIBLE);
								
							}

							@Override
							public void onError(DialogError error) {
								// Function to handle error

							}

							@Override
							public void onFacebookError(FacebookError fberror) {
								// Function to handle Facebook errors

							}
						}
				
				);
				
				if (expires != 0) {
					facebook.setAccessExpires(expires);
					//getProfileInformation();
				}
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

	/**
	 * Function to post to facebook wall
	 * */
	@SuppressWarnings("deprecation")
	public void postToWall() {
		// post on user's wall.
		facebook.dialog(this, "feed", new DialogListener() {

			@Override
			public void onFacebookError(FacebookError e) {
			}

			@Override
			public void onError(DialogError e) {
			}

			@Override
			public void onComplete(Bundle values) {
			}

			@Override
			public void onCancel() {
			}
		});

	}
	/**
	 * Function to Logout user from Facebook
	 * */
	@SuppressWarnings("deprecation")
	public void logoutFromFacebook() {
		mAsyncRunner.logout(this, new RequestListener() {
			@Override
			public void onComplete(String response, Object state) {
				//Log.d("Logout from Facebook", response);
				if (Boolean.parseBoolean(response) == true) {
					runOnUiThread(new Runnable() {

						@Override
						public void run() {
							// make Login button visible
							btnFbLogin.setVisibility(View.VISIBLE);

							// making all remaining buttons invisible
							btnFbLogOut.setVisibility(View.INVISIBLE);
						}

					});

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