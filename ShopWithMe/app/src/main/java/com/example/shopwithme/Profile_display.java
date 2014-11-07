package com.example.shopwithme;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class Profile_display extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_display);
        getActionBar().setTitle("Profile");
        //get the intent from the home screen
        Intent intent = getIntent();
        if (intent != null) {
            String name = intent.getStringExtra("UserName");
            setupProfile(name);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_display, menu);
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

    public void setupProfile(String name){
        TextView tx = (TextView) findViewById(R.id.profile_name);
        ImageView image = (ImageView) findViewById(R.id.user_photo);
        //automatically change the user's name based on the text passed in
        tx.setText("Name: " + name);
        Resources res = getResources();
        //automatically change the user's image based on the name passed in
        if (name.equals("Sue")) {
            image.setImageDrawable(res.getDrawable(R.drawable.sue));
        } else if (name.equals("Nick")) {
            image.setImageDrawable(res.getDrawable(R.drawable.nick));
        } else if (name.equals("Mary")) {
            image.setImageDrawable(res.getDrawable(R.drawable.mary));
        } else if (name.equals("Jason")) {
            image.setImageDrawable(res.getDrawable(R.drawable.jason));
        } else if (name.equals("Mike")) {
            image.setImageDrawable(res.getDrawable(R.drawable.mike));
        }
    }
}
