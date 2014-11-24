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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NewPostActivity extends Activity {
    private SharedPreferences postSharedpreference;
    private SharedPreferences userSharedpreference;
    private Spinner categorySpinner;
    private Spinner locationSpinner;
    private Spinner budgetSpinner;
    private String[] categoryList = {"Clothing", "Food", "Music", "Shoes"};
    private String[] budgetList = {"$", "$$", "$$$"};
    private String[] locationList = {"Ann Arbor", "New York", "Chicago"};
    private String category;
    private String location;
    private String budget;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_post);
		getActionBar().setTitle("New Post");
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, categoryList);
        categorySpinner.setAdapter(categoryAdapter);
        categorySpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = categorySpinner.getSelectedItemPosition();
                        category = categoryList[position];
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                }
        );
        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        ArrayAdapter<String> locationAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, locationList);
        locationSpinner.setAdapter(locationAdapter);
        locationSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = locationSpinner.getSelectedItemPosition();
                        location = locationList[position];
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                }
        );

        budgetSpinner = (Spinner) findViewById(R.id.budgetSpinner);
        ArrayAdapter<String> budgetAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, budgetList);
        budgetSpinner.setAdapter(budgetAdapter);
        budgetSpinner.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> arg0, View arg1,
                                               int arg2, long arg3) {
                        int position = budgetSpinner.getSelectedItemPosition();
                        budget = budgetList[position];
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> arg0) {
                        // TODO Auto-generated method stub
                    }
                }
        );

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
            initPostList();
			startActivity(intent);
            return true;
		}
		else if (id == R.id.action_post) {
            Intent intent = new Intent(this, NewPostActivity.class);
            startActivity(intent);
			return true;
		}
		else if (id == R.id.action_profile) {
			Intent intent = new Intent(this, profile_edit.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void PosttoHomeScreen(View view) {
        initPostList();
        EditText newPost = (EditText) findViewById(R.id.editPost);
        String post = newPost.getText().toString();
        String userName;
        if (post.equals("") || location.equals("") || category.equals("") || budget.equals("")) {
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
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", userName);
            map.put("post", post);
            map.put("location", location);
            map.put("category", category);
            map.put("budget", budget);
            map.put("display", true);
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
            int number = HomeScreenActivity.sizeOfSharedPrefs(postSharedpreference);
            number ++;
            editor.putString("post_"+number, jsonString);
            editor.commit();
            startActivity(intent);
        }
	}

    public void initPostList(){
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.PostPref,
                Context.MODE_PRIVATE);
        String jsonString;
        String mapKey;
        HashMap<String, Object> map;
        int number = HomeScreenActivity.sizeOfSharedPrefs(sharedpreferences);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        for (int i=number; i>=1; i--) {
            mapKey = "post_" + i;
            jsonString = sharedpreferences.getString(mapKey, "");
            JSONObject jsonObject;
            try {
                jsonObject = new JSONObject(jsonString);
                Iterator<String> keysItr = jsonObject.keys();
                map = new HashMap<String, Object>();
                while(keysItr.hasNext()) {
                    String key = keysItr.next();
                    Object value = jsonObject.get(key);
                    map.put(key, value);
                }
                map.put("display", true);
                jsonObject = new JSONObject(map);
                jsonString = jsonObject.toString();

            } catch (JSONException e) {
                e.printStackTrace();
            }
            editor.putString(mapKey, jsonString);
        }
        editor.commit();
    }


// Selection from spinners and text from location box get sent to filtering database, where from 
// users get filter criteria to search for on home screen
// Text that user writes in the post box gets sent to home screen
}
