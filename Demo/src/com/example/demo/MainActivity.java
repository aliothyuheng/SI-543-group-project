package com.example.demo;

import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final String POST = "MyPostPreference";
	TextView tv_1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SharedPreferences setting = getSharedPreferences(POST, 0);
		
		this.tv_1 = (TextView) this.findViewById(R.id.textview_1);
		//this.tv_2 = (TextView) this.findViewById(R.id.textview_2);
		//this.tv_3 = (TextView) this.findViewById(R.id.textview_3);
		Map<String, ?> allEntries = setting.getAll();
		for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
			tv_1.setText(entry.getValue().toString());
		} 
		//tv_1.setText(setting.getString("post", "null"));
		//tv_2.setText("");
		//tv_3.setText("");
		/*for (int x = 1; x < 50; x++){
			tv_1.append("Name\n");
			tv_1.append("\n");
			tv_2.append("Tags\n");
			tv_2.append("\n");
			tv_3.append("Post\n");
			tv_3.append("\n");
		}*/
		Button btn = (Button)findViewById(R.id.button1);
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent(getApplicationContext(), ListActivity.class);
				startActivity(intent);
			}
		}); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
