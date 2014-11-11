package com.example.shopwithme;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import android.widget.ListView;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConversationActivity extends Activity {

    private EditText replyText;
    private ListView replyListView;

    private ArrayList<HashMap<String, Object>> replyList = new ArrayList<HashMap<String, Object>>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conversation);
		getActionBar().setTitle("Conversation");
        replyListView = (ListView) findViewById(R.id.reply_list_view);
        
        initList();

        CustomAdapter replyAdapter = new CustomAdapter(this, replyList, R.layout.reply_list_item,
                new String[] {"name", "reply"}, new int[] {R.id.name, R.id.reply});
        replyListView.setAdapter(replyAdapter);


        Intent intent = getIntent();
        TextView tx = (TextView) findViewById(R.id.post);
        String postContent = intent.getStringExtra("post");
        String posterName = intent.getStringExtra("poster");
        tx.setText(posterName + ": " + postContent);
/**
		TextView tv_2 = (TextView) findViewById(R.id.nicks_reply);
	    registerForContextMenu(tv_2);
	    TextView tv_3 = (TextView) findViewById(R.id.sarahs_reply);
	    registerForContextMenu(tv_3); **/
	}



    /**
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.conversation_content_menu, menu);
		super.onCreateContextMenu(menu, v, menuInfo);
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_message) {
			Intent intent = new Intent(this, MessageActivity.class);
	        startActivity(intent);
		}
		else if (id == R.id.action_poster_profile) {
			Intent intent = new Intent(this, Profile_display.class);
	        startActivity(intent);
		}
		
		return super.onContextItemSelected(item);
    }
 **/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.conversation, menu);
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

    static class ViewHolder {
        //create a view holder class to hold all the views of one post
        public TextView name;
        public TextView reply;
    }

    private void initList() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", "Nick");
        map.put("reply", "Yes! What time and where?");
        replyList.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "Lisa");
        map.put("reply", "Can I tag along too?");
        replyList.add(map);
    }

    public class CustomAdapter extends BaseAdapter {
        private ArrayList<HashMap<String, Object>> mAppList;
        private LayoutInflater mInflater;
        private Context mContext;
        private String[] keyString;
        private int[] valueViewID;
        private ViewHolder holder;

        public CustomAdapter(Context c, ArrayList<HashMap<String, Object>> appList, int resource,
                             String[] from, int[] to) {
            mAppList = appList;
            mContext = c;
            mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            keyString = new String[from.length];
            valueViewID = new int[to.length];
            System.arraycopy(from, 0, keyString, 0, from.length);
            System.arraycopy(to, 0, valueViewID, 0, to.length);
        }

        @Override
        public int getCount() {
            return mAppList.size();
        }

        @Override
        public Object getItem(int position) {
            return mAppList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            holder = null;

            //set up the specific views for holder class
            if (convertView != null) {
                holder = (ViewHolder) convertView.getTag();
            } else {
                convertView = mInflater.inflate(R.layout.reply_list_item, null);
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(valueViewID[0]);
                holder.reply = (TextView) convertView.findViewById(valueViewID[1]);
                convertView.setTag(holder);
            }

            //put information into holder
            HashMap<String, Object> appInfo = mAppList.get(position);
            if (appInfo != null) {
                String posterName = (String) appInfo.get(keyString[0]);
                String replyContent = (String) appInfo.get(keyString[1]);

                holder.name.setText(posterName);
                holder.reply.setText(replyContent);
                //set up view profile for name text
                viewProfile(holder.name);
            }
            return convertView;
        }
    }

    public void viewProfile(final TextView tx) {
        tx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConversationActivity.this, Profile_display.class);
                String name = tx.getText().toString();
                //pass the user name to the profile display screen
                intent.putExtra("UserName", name);
                startActivity(intent);
            }
        });
    }

    public void Reply(View view) {
        replyText = (EditText) findViewById(R.id.replyBox);
        if (replyText.getText().toString().equals("")) {
            FragmentManager fm = getFragmentManager();
            ReplyFragment alter = new ReplyFragment();
            alter.setRetainInstance(true);
            alter.show(fm, "fragment_reply");
        }
        else {
            Intent intent = new Intent(this, ConversationActivity.class);
            startActivity(intent);
        }
    }
	
	// text from reply box gets put on screen when user submits it
	// user can click on another user's name in order to go to their profile page
}
