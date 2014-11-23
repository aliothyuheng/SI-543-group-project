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
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_post) {
            Intent intent = new Intent(this, NewPostActivity.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_profile) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setupProfile(String name){
        userSharedpreference = getSharedPreferences(MainActivity.UserPref,
                Context.MODE_PRIVATE);
        userName = userSharedpreference.getString(MainActivity.name, "");
        nameText = (EditText) findViewById(R.id.nameText);
        nameText.setText(userName);
        userImage = (ImageView) findViewById(R.id.user_profile_image);
        //automatically change the user's name based on the text passed in
        Resources res = getResources();
        //automatically change the user's image based on the name passed in
        if (name.equals("sue")) {
            userImage.setImageDrawable(res.getDrawable(R.drawable.sue));
        } else if (name.equals("nick")) {
            userImage.setImageDrawable(res.getDrawable(R.drawable.nick));
        } else if (name.equals("mary")) {
            userImage.setImageDrawable(res.getDrawable(R.drawable.mary));
        } else if (name.equals("jason")) {
            userImage.setImageDrawable(res.getDrawable(R.drawable.jason));
        } else {
            userImage.setImageDrawable(res.getDrawable(R.drawable.mike));
        }
    }
}
