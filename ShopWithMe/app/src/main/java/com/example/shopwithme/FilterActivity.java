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
import android.widget.Spinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FilterActivity extends Activity {
    private SharedPreferences postSharedpreference;
    private Spinner categorySpinner;
    private Spinner locationSpinner;
    private Spinner budgetSpinner;
    private String[] categoryList = {"Clothing", "Food", "Music", "Shoes"};
    private String[] budgetList = {"$", "$$", "$$$"};
    private String[] locationList = {"Ann Arbor", "New York", "Chicago"};
    private String category;
    private String location;
    private String budget;
    private boolean hasElement=false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter);
		getActionBar().setTitle("Filter");
        categorySpinner = (Spinner) findViewById(R.id.categoryFilterSpinner);
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
        locationSpinner = (Spinner) findViewById(R.id.locationFilterSpinner);
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

        budgetSpinner = (Spinner) findViewById(R.id.budgetFilterSpinner);
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
		getMenuInflater().inflate(R.menu.filter, menu);
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
            return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void BackToHome(View view) {
		Intent intent = new Intent(this, HomeScreenActivity.class);
        postSharedpreference = getSharedPreferences(MainActivity.PostPref,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = postSharedpreference.edit();
        String jsonString;
        String mapKey;
        HashMap<String, Object> map;
        int number = HomeScreenActivity.sizeOfSharedPrefs(postSharedpreference);
        for (int i=number; i>=1; i--) {
            mapKey = "post_" + i;
            jsonString = postSharedpreference.getString(mapKey, "");
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
                if (map.get("location").equals(location) && map.get("budget").equals(budget)
                        && map.get("category").equals(category)){
                    map.put("display", true);
                    hasElement = true;
                }
                else{
                    map.put("display", false);
                }
                jsonObject = new JSONObject(map);
                jsonString = jsonObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            editor.putString(mapKey, jsonString);
        }
        editor.commit();
        if (hasElement == true){
            startActivity(intent);
        }
        else{
            FragmentManager fm = getFragmentManager();
            FilterAlterFragment alter = new FilterAlterFragment();
            alter.setRetainInstance(true);
            alter.show(fm, "fragment_filter_alter");
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
}

//What's next
//After user hit submit, it will sort out the posts that meet the criteria and show on the home screen
