package com.example.shopwithme;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

public class HomeScreenActivity extends Activity {
    ImageButton button_1;
    ImageButton button_2;
    ImageButton button_3;
    ImageButton button_4;
    ImageButton button_5;
    TextView tx_1;
    TextView tx_2;
    TextView tx_3;
    TextView tx_4;
    TextView tx_5;

	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        getActionBar().setTitle("Home");

        //set up button listener for all the image button. If click on the image button,
        //a pop up menu will display
        button_1 = (ImageButton) findViewById(R.id.reply_1);
        button_2 = (ImageButton) findViewById(R.id.reply_2);
        button_3 = (ImageButton) findViewById(R.id.reply_3);
        button_4 = (ImageButton) findViewById(R.id.reply_4);
        button_5 = (ImageButton) findViewById(R.id.reply_5);
        tx_1 = (TextView) findViewById(R.id.name_1);
        tx_2 = (TextView) findViewById(R.id.name_2);
        tx_3 = (TextView) findViewById(R.id.name_3);
        tx_4 = (TextView) findViewById(R.id.name_4);
        tx_5 = (TextView) findViewById(R.id.name_5);


        //call the setup popup menu function for the button
        setPopup(button_1);
        setPopup(button_2);
        setPopup(button_3);
        setPopup(button_4);
        setPopup(button_5);

        //call view profile function to alter the profile screen
        viewProfile(tx_1);
        viewProfile(tx_2);
        viewProfile(tx_3);
        viewProfile(tx_4);
        viewProfile(tx_5);
    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_home) {
			return true;
		}
		else if (id == R.id.action_post) {
			Intent intent = new Intent(this, NewPostActivity.class);
			startActivity(intent);
			return true;
		}
		else if (id == R.id.action_profile) {
			Intent intent = new Intent(this, ProfileActivity.class);
			startActivity(intent);
		}
        else if (id == R.id.action_filter) {
            Intent intent = new Intent(this, FilterActivity.class);
            startActivity(intent);
        }
		return super.onOptionsItemSelected(item);
	}

    public void setPopup(View view){
        //setup popup menu for the button
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(HomeScreenActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.content_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    //setup onclick listener for the pop up menu
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.action_message) {
                            Intent intent = new Intent(HomeScreenActivity.this, MessageActivity.class);
                            startActivity(intent);
                        } else if (id == R.id.action_reply) {
                            Intent intent = new Intent(HomeScreenActivity.this, ConversationActivity.class);
                            startActivity(intent);
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });
    }

    //set up a onclick listener on the textview
    public void viewProfile(final TextView tx){
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, Profile_display.class);
                String name = tx.getText().toString();
                //pass the user name to the profile display screen
                intent.putExtra("UserName", name);
                startActivity(intent);
            }
        });
    }

}

