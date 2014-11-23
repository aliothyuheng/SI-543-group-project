package com.example.shopwithme;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;


public class profile_edit extends Activity {

    private String userName;
    Button saveButton;
    EditText nameText;
    EditText locationText;
    EditText interestText;
    Spinner budgetSpinner;

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
}
