package com.jminions.eatubc;

import java.util.HashMap;
import java.util.Map;

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
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;


public class MainActivity extends Activity {

   public static EditText  username=null;
   private EditText  password=null;
   private EditText  user;
   private EditText  pass;
   private TextView attempts;
   private TextView newaccount;
   private Button login;
   int counter = 3;
   
  
   Map accounts = new HashMap();
   
   //Facebook///////////////////////
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
   protected void onCreate(Bundle savedInstanceState) {
	   try{
		   accounts.put("admin","admin");
		   accounts.put("a","b");
	   }catch(Exception e){
			System.out.println(e.toString());
	   }
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      username = (EditText)findViewById(R.id.editText1);
      password = (EditText)findViewById(R.id.editText2);
      attempts = (TextView)findViewById(R.id.textView5);
      attempts.setText(Integer.toString(counter));
      login = (Button)findViewById(R.id.button1);
      newaccount = (Button)findViewById(R.id.textView6);
      
      newaccount.setOnClickListener(new OnClickListener() {
    	  
    	  public void onClick(View view){
    		 
    		initiatepopup();
    		
    	  }
    	  
      });
      
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

	public void login(View view) {
		if (password.getText().toString()
				.equals(accounts.get(username.getText().toString()))) {
			Toast.makeText(getApplicationContext(), "Redirecting...",
					Toast.LENGTH_SHORT).show();
			Intent intent = new Intent(this, MainMenu.class);
			startActivity(intent);
		} else {
			Toast.makeText(getApplicationContext(), "Wrong Credentials",
					Toast.LENGTH_SHORT).show();
			attempts.setBackgroundColor(Color.RED);
			counter--;
			attempts.setText(Integer.toString(counter));
			if (counter == 0) {
				login.setEnabled(false);
			}
		}

	}
	
<<<<<<< HEAD
	public void connectionClick(View view){
		Intent connection = new Intent(this, FoodMenuActivity.class);
		startActivity(connection);
		}
	




=======
	
>>>>>>> vga-merge
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
   PopupWindow register;
   public void initiatepopup(){
	   try { 	
	
		// We need to get the instance of the LayoutInflater 
		LayoutInflater inflater = (LayoutInflater) MainActivity.this 
		.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		View layout = inflater.inflate(R.layout.register,(ViewGroup)findViewById(R.id.popup_element)); 
		user = (EditText)layout.findViewById(R.id.username);
		pass = (EditText)layout.findViewById(R.id.password);
		register = new PopupWindow(layout, 500, 500, true); 
		register.showAtLocation(layout, Gravity.CENTER, 0, 0);
		register.setFocusable(true);
		register.setBackgroundDrawable(new BitmapDrawable());
		register.setOutsideTouchable(true);
		register.showAsDropDown(newaccount,0,0);
		} catch (Exception e) { 
		e.printStackTrace(); 
		}	   
   }
   public void dismiss(View view){
	   try{
		if(accounts.containsKey(user.getText().toString())){
			 Toast.makeText(getApplicationContext(), "That Username is already in use",
				      Toast.LENGTH_SHORT).show();
			register.dismiss();
		}else if(user.getText().toString() == "null"){
			register.dismiss();
	   }
		accounts.put(user.getText().toString(),pass.getText().toString());
		register.dismiss();
	   }
	   catch(Exception e){
		   System.out.println(e.toString());
		   
	   }
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
