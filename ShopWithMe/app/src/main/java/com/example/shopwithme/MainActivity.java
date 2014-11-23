package com.example.shopwithme;

import com.example.shopwithme.R;
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

public class MainActivity extends Activity {

    public static final String UserPref = "UserPref" ;
    public static final String PostPref = "PostPref";
    public static final String name = "nameKey";
    public static final String pass = "passwordKey";
    public static final String postKey = "postKey";
    SharedPreferences sharedpreferences;

    private EditText userName;
    private EditText password;
    private String userNameText;
    private String passwordText;

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
        userNameText = userName.getText().toString().trim();
        passwordText = password.getText().toString().trim();
        if (userNameText.equals("") || passwordText.equals("")){
            FragmentManager fm = getFragmentManager();
            AlterFragment alter = new AlterFragment();
            alter.setRetainInstance(true);
            alter.show(fm, "fragment_alter");
        }
        else {
            sharedpreferences=getSharedPreferences(UserPref,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.clear();
            editor.putString(name, userNameText);
            editor.putString(pass, passwordText);
            editor.commit();
            initPost();
            Intent intent = new Intent(this, HomeScreenActivity.class);
            startActivity(intent);
        }
	}

    public void initPost(){
        sharedpreferences=getSharedPreferences(PostPref,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", "sue");
        map.put("post", "I need to shop for some shoes. Would anyone like to join?");
        map.put("image", R.drawable.sue);
        JSONObject jsonObject = new JSONObject(map);
        String jsonString = jsonObject.toString();
        editor.putString("post_1", jsonString);

        map = new HashMap<String, Object>();
        map.put("name", "nick");
        map.put("post", "I'm looking for some good music. Anyone want to go to a record store?");
        map.put("image", R.drawable.nick);
        jsonObject = new JSONObject(map);
        jsonString = jsonObject.toString();
        editor.putString("post_2", jsonString);

        map = new HashMap<String, Object>();
        map.put("name", "mary");
        map.put("post", "Anyone want to go clothes shopping? I found a new store on Liberty.");
        map.put("image", R.drawable.mary);
        jsonObject = new JSONObject(map);
        jsonString = jsonObject.toString();
        editor.putString("post_3", jsonString);

        map = new HashMap<String, Object>();
        map.put("name", "jason");
        map.put("post", "I want to find some good cajun food. Anyone want to check out midtown with me?");
        map.put("image", R.drawable.jason);
        jsonObject = new JSONObject(map);
        jsonString = jsonObject.toString();
        editor.putString("post_4", jsonString);

        map = new HashMap<String, Object>();
        map.put("name", "mike");
        map.put("post", "I'm looking for some good music. Anyone want to go to a record store?");
        map.put("image", R.drawable.mike);
        jsonObject = new JSONObject(map);
        jsonString = jsonObject.toString();
        editor.putString("post_5", jsonString);
        editor.commit();
    }
}

//What's next
//1. check whether the username and password are stored in the database
//2. if not, try forget password to retrieve the password back, go to the password reset screen
//3. or, sign up new accout, go to the new account screen
