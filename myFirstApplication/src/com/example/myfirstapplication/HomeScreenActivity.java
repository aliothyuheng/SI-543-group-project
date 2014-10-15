//homeScreen Activity
//Created by Yuheng Chen

package com.example.myfirstapplication;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeScreenActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
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
		if (id == R.id.action_settings) {
			return true;
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
