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
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MessageActivity extends Activity {

    //a list class type must be used when using a list view
    //list items will be added programatically
    //List<Map<String, String>> personName = new ArrayList<~>();
    private ListView listview;
    private ArrayList<HashMap<String, Object>> messageList = new ArrayList<HashMap<String, Object>>();
    private EditText message;
    private String userName;
    SimpleAdapter messageAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		getActionBar().setTitle("Message");
        listview = (ListView) findViewById(R.id.message_list_view);
        //registerForContextMenu ((ListView) findViewById(R.id.listView));
        //ListView personNameListView = (ListView) findViewById(R.id.listView);
        messageAdapter = new SimpleAdapter(this, messageList, R.layout.message_list_item, new String[]
                {"name", "post"},
                    new int[] {R.id.name, R.id.post});

        listview.setAdapter(messageAdapter);
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

    public void message(View view) {
        message = (EditText) findViewById(R.id.messageBox);
        if (message.getText().toString().equals("")) {
            FragmentManager fm = getFragmentManager();
            ReplyFragment alter = new ReplyFragment();
            alter.setRetainInstance(true);
            alter.show(fm, "fragment_reply");
        }
        else {
            SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.UserPref,
                    Context.MODE_PRIVATE);
            userName = sharedpreferences.getString(MainActivity.name, "");
            String replyContent = message.getText().toString();
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", userName);
            map.put("post", replyContent);
            messageList.add(map);
            messageAdapter.notifyDataSetChanged();

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


	// Text that user writes in message box appears on same screen and gets sent to directed user
	// user can click on another user's name in order to go to their profile page	
}
