package com.example.shopwithme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ConversationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conversation);
		getActionBar().setTitle("Conversation");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.conversation, menu);
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
			return true;
		}
		else if (id == R.id.action_post) {
			Intent intent = new Intent(this, NewPostActivity.class);
			startActivity(intent);
			return true;
		}
		else if (id == R.id.action_profile) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	// text from reply box gets put on screen when user submits it
	// user can click on another user's name in order to go to their profile page
}
