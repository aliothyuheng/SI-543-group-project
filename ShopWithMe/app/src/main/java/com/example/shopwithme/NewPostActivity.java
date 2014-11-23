package com.example.shopwithme;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class NewPostActivity extends Activity {
    SharedPreferences postSharedpreference;
    SharedPreferences userSharedpreference;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_post);
		getActionBar().setTitle("New Post");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_post, menu);
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
			return true;
		}
		else if (id == R.id.action_profile) {
			Intent intent = new Intent(this, profile_edit.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void PosttoHomeScreen(View view) {
        EditText newPost = (EditText) findViewById(R.id.editPost);
        String userName;
        String post;
        if (newPost.getText().toString().equals("")) {
            FragmentManager fm = getFragmentManager();
            ReplyFragment alter = new ReplyFragment();
            alter.setRetainInstance(true);
            alter.show(fm, "fragment_reply");
        }
        else {
            Intent intent = new Intent(this, HomeScreenActivity.class);
            postSharedpreference = getSharedPreferences(MainActivity.PostPref,
                    Context.MODE_PRIVATE);
            userSharedpreference = getSharedPreferences(MainActivity.UserPref,
                    Context.MODE_PRIVATE);
            userName = userSharedpreference.getString(MainActivity.name, "");
            SharedPreferences.Editor editor = postSharedpreference.edit();
            post = newPost.getText().toString();
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", userName);
            map.put("post", post);
            if (userName.equals("sue")) {
                map.put("image", R.drawable.sue);
            } else if (userName.equals("nick")) {
                map.put("image", R.drawable.nick);
            } else if (userName.equals("mary")) {
                map.put("image", R.drawable.mary);
            } else if (userName.equals("jason")) {
                map.put("image", R.drawable.jason);
            } else {
                map.put("image", R.drawable.mike);;
            }
            JSONObject jsonObject = new JSONObject(map);
            String jsonString = jsonObject.toString();
            int number = sizeOfSharedPrefs(postSharedpreference);
            number ++;
            editor.putString("post_"+number, jsonString);
            editor.commit();
            startActivity(intent);
        }
	}

    public int sizeOfSharedPrefs(SharedPreferences inputSharedpreference){
        Map<String, ?> allEntries = inputSharedpreference.getAll();
        return allEntries.size();
    }
	
// Selection from spinners and text from location box get sent to filtering database, where from 
// users get filter criteria to search for on home screen
// Text that user writes in the post box gets sent to home screen
}
