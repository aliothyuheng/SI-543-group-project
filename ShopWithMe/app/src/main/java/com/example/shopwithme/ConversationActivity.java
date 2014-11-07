package com.example.shopwithme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.TextView;

public class ConversationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conversation);
		getActionBar().setTitle("Conversation");

        Intent intent = getIntent();
        TextView tx = (TextView) findViewById(R.id.post);
        String postContent = intent.getStringExtra("post");
        String posterName = intent.getStringExtra("poster");
        tx.setText(posterName + ": " + postContent);
/**
		TextView tv_2 = (TextView) findViewById(R.id.nicks_reply);
	    registerForContextMenu(tv_2);
	    TextView tv_3 = (TextView) findViewById(R.id.sarahs_reply);
	    registerForContextMenu(tv_3); **/
	}

/**
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.conversation_content_menu, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_message) {
			Intent intent = new Intent(this, MessageActivity.class);
	        startActivity(intent);
		}
		else if (id == R.id.action_poster_profile) {
			Intent intent = new Intent(this, Profile_display.class);
	        startActivity(intent);
		}
		
		return super.onContextItemSelected(item);
    }
 **/

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
		}
		else if (id == R.id.action_post) {
			Intent intent = new Intent(this, NewPostActivity.class);
			startActivity(intent);
		}
		else if (id == R.id.action_profile) {
			Intent intent = new Intent(this, profile_edit.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	// text from reply box gets put on screen when user submits it
	// user can click on another user's name in order to go to their profile page
}
