package com.example.shopwithme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MessageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		getActionBar().setTitle("Message");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.message, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_home) {
			Intent intent = new Intent(this, HomeScreenActivity.class);
			startActivity(intent);
		}
		else if (id == R.id.action_post) {
			Intent intent = new Intent(this, NewPostActivity.class);
			startActivity(intent);
		}
		else if (id == R.id.action_profile) {
			Intent intent = new Intent(this, ProfileActivity.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void ConversationScreen(View view) {
		Intent intent = new Intent(this, MessageActivity.class);
		startActivity(intent);
	}
	
	// Text that user writes in message box appears on same screen and gets sent to directed user
	// user can click on another user's name in order to go to their profile page	
}
