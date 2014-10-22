package com.example.shopwithme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class HomeScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		getActionBar().setTitle("Home");
		TextView tv_1 = (TextView) findViewById(R.id.Post_1);
	    registerForContextMenu(tv_1);
	    TextView tv_2 = (TextView) findViewById(R.id.Post_2);
	    registerForContextMenu(tv_2);
	    TextView tv_3 = (TextView) findViewById(R.id.Post_3);
	    registerForContextMenu(tv_3);
		
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.content_menu, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_message) {
			Intent intent = new Intent(this, MessageActivity.class);
	        startActivity(intent);
		}
		else if (id == R.id.action_reply) {
			Intent intent = new Intent(this, ConversationActivity.class);
	        startActivity(intent);
		}
		else if (id == R.id.action_poster_profile) {
			Intent intent = new Intent(this, ProfileActivity.class);
	        startActivity(intent);
		}
		
		return super.onContextItemSelected(item);
	}



	



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_home) {
			return true;
		}
		else if (id == R.id.action_post) {
			Intent intent = new Intent(this, NewPostActivity.class);
			startActivity(intent);
			return true;
		}
		else if (id == R.id.action_profile) {
			Intent intent = new Intent(this, ProfileActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void Filter(View view) {
		Intent intent = new Intent(this, FilterActivity.class);
		startActivity(intent);
	}

    
}

//What's next
//1. When click the "reply" under each post, it will go to the reply screen.
//2. When click the "message" under each post, it will go to the message screen and start chatting.
//3. When click the "profile" under each post, it will go to the user profile screen and view the profile of the poster.
//4. When click the "post" at the bottom, it will go to the new post screen.
//5. When click the "profile" at the bottom, it will go to the user's profile screen. 
