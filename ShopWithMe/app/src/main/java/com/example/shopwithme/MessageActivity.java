package com.example.shopwithme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MessageActivity extends Activity {

    //a list class type must be used when using a list view
    //list items will be added programatically
    //List<Map<String, String>> personName = new ArrayList<~>();
    private ListView listview;
    private ArrayList<HashMap<String, Object>> postList = new ArrayList<HashMap<String, Object>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		getActionBar().setTitle("Message");
        listview = (ListView) findViewById(R.id.message_list_view);

        //initialize the list
        initList();
        //registerForContextMenu ((ListView) findViewById(R.id.listView));
        //ListView personNameListView = (ListView) findViewById(R.id.listView);
        SimpleAdapter postAdapter = new SimpleAdapter(this, postList, R.layout.message_list_item, new String[]
                {"name", "post"},
                    new int[] {R.id.name, R.id.post});

        listview.setAdapter(postAdapter);
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
			Intent intent = new Intent(this, profile_edit.class);
			startActivity(intent);
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	public void ConversationScreen(View view) {
		Intent intent = new Intent(this, MessageActivity.class);
		startActivity(intent);
	}
    public void initList () {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Sue");
        map.put("post", "Hi, I'd really like to meet up with you for some shoe shopping. Any interest?");
        postList.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Mary");
        map.put("post", "Sure! Where would you like to go?");
        postList.add(map);

    }


	// Text that user writes in message box appears on same screen and gets sent to directed user
	// user can click on another user's name in order to go to their profile page	
}
