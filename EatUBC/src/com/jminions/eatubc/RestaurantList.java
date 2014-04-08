package com.jminions.eatubc;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RestaurantList extends Activity {

	public final static String EXTRA_MESSAGE = "com.jminions.eatubc.MESSAGE";
	

	String username = MainActivity.username.getText().toString();

	public static int RestaurantNumber = 1;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// This call will result in better error messages if you
		// try to do things in the wrong thread.
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());

		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant_list);

		EditText et = (EditText) findViewById(R.id.RecvdMessage);
		et.setKeyListener(null);
		//et.setVisibility(View.INVISIBLE);
		et = (EditText) findViewById(R.id.error_message_box);
		et.setKeyListener(null);
		et.setVisibility(View.INVISIBLE);
		et = (EditText) findViewById(R.id.MessageText);
		et.setKeyListener(null);
		et.setVisibility(View.INVISIBLE);

		// Set up a timer task. We will use the timer to check the
		// input queue every 500 ms

		TCPReadTimerTask tcp_task = new TCPReadTimerTask();
		Timer tcp_timer = new Timer();
		tcp_timer.schedule(tcp_task, 3000, 500);



	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void McDonaldsClick(View view) {
		Intent intent = new Intent(this, TabsInitActivity.class);
		RestaurantNumber = 2;
		EditText menuItems = (EditText) findViewById(R.id.RecvdMessage);
		String McDonalds = menuItems.getText().toString();
		System.out.println("TEST2" + McDonalds);
		intent.putExtra(EXTRA_MESSAGE, McDonalds);
		startActivity(intent);
	}

	public void PitPubClick(View view) {

		
		Intent Tabs_Init_Activity = new Intent(this, TabsInitActivity.class);
		RestaurantNumber = 1;
		EditText menuItems = (EditText) findViewById(R.id.RecvdMessage);
		String burgerBar = menuItems.getText().toString();

		System.out.println("TEST1 " + burgerBar);
		
		Tabs_Init_Activity.putExtra(EXTRA_MESSAGE, burgerBar);
		startActivity(Tabs_Init_Activity);
	}
	public void temp(View view){
		sendMessage(view);
		Intent Tabs_Init_Activity = new Intent(this, TabsInitActivity.class);
		//EditText menuItems = (EditText) findViewById(R.id.RecvdMessage);
		//String burgerBar = menuItems.getText().toString();
		//System.out.println("TEST1" + burgerBar);
		
		Tabs_Init_Activity.putExtra(EXTRA_MESSAGE, "Hello");
		startActivity(Tabs_Init_Activity);
	}
	

	// Route called when the user presses "connect"

	public void openSocket(View view) {
		MyApplication app = (MyApplication) getApplication();
		TextView msgbox = (TextView) findViewById(R.id.error_message_box);

		// Make sure the socket is not already opened

		if (app.sock != null && app.sock.isConnected() && !app.sock.isClosed()) {
			msgbox.setText("Socket already open");
			return;
		}

		// open the socket. SocketConnect is a new subclass
		// (defined below). This creates an instance of the subclass
		// and executes the code in it.

		new SocketConnect().execute((Void) null);

	}

	// Called when the user wants to send a message

	public void sendMessage(View view) {
		MyApplication app = (MyApplication) getApplication();

		// Get the message from the box


		EditText et = (EditText) findViewById(R.id.MessageText);
		String msg = et.getText().toString();

		// Create an array of bytes. First byte will be the
		// message length, and the next ones will be the message

		byte buf[] = new byte[msg.length() + 1];
		buf[0] = (byte) msg.length();
		System.arraycopy(msg.getBytes(), 0, buf, 1, msg.length());

		// Now send through the output stream of the socket

		OutputStream out;
		try {
			out = app.sock.getOutputStream();
			try {
				out.write(buf, 0, msg.length() + 1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Called when the user closes a socket

	public void closeSocket(View view) {
		MyApplication app = (MyApplication) getApplication();
		Socket s = app.sock;
		try {
			s.getOutputStream().close();
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Construct an IP address from the four boxes

	public String getConnectToIP() {
		String addr = "";
		EditText text_ip;
		text_ip = (EditText) findViewById(R.id.ip1);
		addr += "192"; //text_ip.getText().toString();
		text_ip = (EditText) findViewById(R.id.ip2);
		addr += "." + "168"; //text_ip.getText().toString();
		text_ip = (EditText) findViewById(R.id.ip3);
		addr += "." + "1"; //text_ip.getText().toString();
		text_ip = (EditText) findViewById(R.id.ip4);
		addr += "." + "141"; //text_ip.getText().toString();
		return addr;
	}

	// Gets the Port from the appropriate field.

	public Integer getConnectToPort() {
		Integer port = 50002;
		/*EditText text_port;

		text_port = (EditText) findViewById(R.id.port);
		port = Integer.parseInt(text_port.getText().toString());*/

		return port;
	}

	// This is the Socket Connect asynchronous thread. Opening a socket
	// has to be done in an Asynchronous thread in Android. Be sure you
	// have done the Asynchronous Tread tutorial before trying to understand
	// this code.

	public class SocketConnect extends AsyncTask<Void, Void, Socket> {

		// The main parcel of work for this thread. Opens a socket
		// to connect to the specified IP.

		protected Socket doInBackground(Void... voids) {
			Socket s = null;
			String ip = getConnectToIP();
			Integer port = getConnectToPort();

			try {
				s = new Socket(ip, port);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return s;
		}

		// After executing the doInBackground method, this is
		// automatically called, in the UI (main) thread to store
		// the socket in this app's persistent storage

		protected void onPostExecute(Socket s) {
			MyApplication myApp = (MyApplication) RestaurantList.this
					.getApplication();
			myApp.sock = s;
		}
	}

	// This is a timer Task. Be sure to work through the tutorials
	// on Timer Tasks before trying to understand this code.
	public class TCPReadTimerTask extends TimerTask {
		public void run() {

			MyApplication app = (MyApplication) getApplication();

			if (app.sock != null && app.sock.isConnected()
					&& !app.sock.isClosed()) {

				try {
					InputStream in = app.sock.getInputStream();

					// See if any bytes are available from the Middleman

					int bytes_avail = in.available();
					if (bytes_avail > 0) {

						// If so, read them in and create a string

						byte buf[] = new byte[bytes_avail];
						in.read(buf);

						final String s = new String(buf, 0, bytes_avail,
								"US-ASCII");

						// As explained in the tutorials, the GUI can not be
						// updated in an asyncrhonous task. So, update the GUI
						// using the UI thread.

						runOnUiThread(new Runnable() {
							public void run() {
								EditText et = (EditText) findViewById(R.id.RecvdMessage);
								et.setText(s);								
							}
						});

					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
