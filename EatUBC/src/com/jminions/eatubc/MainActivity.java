package com.jminions.eatubc;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

   private EditText  username=null;
   private EditText  password=null;
   private EditText  user;
   private EditText  pass;
   private TextView attempts;
   private TextView newaccount;
   private Button login;
   int counter = 3;
   
  
   Map accounts = new HashMap();
 
   
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
   }

   public void login(View view){
      if(password.getText().toString().equals(accounts.get(username.getText().toString()))){
      Toast.makeText(getApplicationContext(), "Redirecting...", 
      Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(this, MainMenu.class);
		startActivity(intent);	 
   }	
   else{
      Toast.makeText(getApplicationContext(), "Wrong Credentials",
      Toast.LENGTH_SHORT).show();
      attempts.setBackgroundColor(Color.RED);	
      counter--;
      attempts.setText(Integer.toString(counter));
      if(counter==0){
         login.setEnabled(false);
      }

<<<<<<< HEAD
	}
	
	public void connectionClick(View view){
		Intent connection = new Intent(this, FoodMenuActivity.class);
		startActivity(connection);
	}
=======
   }
>>>>>>> testing

}
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
		}
		accounts.put(user.getText().toString(),pass.getText().toString());
		register.dismiss();
	   }
	   catch(Exception e){
		   System.out.println(e.toString());
		   
	   }
	}
}
