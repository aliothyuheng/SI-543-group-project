package com.example.shopwithme;

import com.example.shopwithme.R;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    private EditText userName;
    private EditText password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
	
	public void Login(View view) {
        userName = (EditText) findViewById(R.id.UserNameTextbox);
        password = (EditText) findViewById(R.id.PasswordTextbox);
        if (userName.getText().toString().equals("") || password.getText().toString().equals("")){
            FragmentManager fm = getFragmentManager();
            AlterFragment alter = new AlterFragment();
            alter.setRetainInstance(true);
            alter.show(fm, "fragment_alter");
        }
        else {
            Intent intent = new Intent(this, HomeScreenActivity.class);
            startActivity(intent);
        }
	}
}

//What's next
//1. check whether the username and password are stored in the database
//2. if not, try forget password to retrieve the password back, go to the password reset screen
//3. or, sign up new accout, go to the new account screen
