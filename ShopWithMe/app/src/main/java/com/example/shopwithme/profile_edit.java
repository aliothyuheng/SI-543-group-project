package com.example.shopwithme;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class profile_edit extends Activity {

    private String userName;
    private EditText nameText;
    private ImageView userImage;
    private SharedPreferences userSharedpreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        getActionBar().setTitle("Profile");
        setupProfile();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_edit, menu);
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

    public void setupProfile(){
        userSharedpreference = getSharedPreferences(MainActivity.UserPref,
                Context.MODE_PRIVATE);
        userName = userSharedpreference.getString(MainActivity.name, "");
        nameText = (EditText) findViewById(R.id.nameText);
        nameText.setText(userName);
        userImage = (ImageView) findViewById(R.id.user_profile_image);
        //automatically change the user's name based on the text passed in
        Resources res = getResources();
        //automatically change the user's image based on the name passed in
        if (userName.equals("sue")) {
            userImage.setImageDrawable(res.getDrawable(R.drawable.sue));
        } else if (userName.equals("nick")) {
            userImage.setImageDrawable(res.getDrawable(R.drawable.nick));
        } else if (userName.equals("mary")) {
            userImage.setImageDrawable(res.getDrawable(R.drawable.mary));
        } else if (userName.equals("jason")) {
            userImage.setImageDrawable(res.getDrawable(R.drawable.jason));
        } else {
            userImage.setImageDrawable(res.getDrawable(R.drawable.mike));
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
