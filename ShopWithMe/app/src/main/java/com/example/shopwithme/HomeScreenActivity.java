package com.example.shopwithme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HomeScreenActivity extends Activity {
    private ListView listview;

    private ArrayList<HashMap<String, Object>> postList = new ArrayList<HashMap<String, Object>>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        getActionBar().setTitle("Home");
        listview = (ListView) findViewById(R.id.my_list_view);
        //initialize the list
        setList();
        //set up a customAdapter
        CustomAdapter postAdapter = new CustomAdapter(this, postList, R.layout.my_list_item,
                new String[]{"name", "post", "budget", "category", "location", "image"},
                new int[]{R.id.name, R.id.post, R.id.budget, R.id.category, R.id.location, R.id.user_image});
        //add the customAdapter to the listview
        listview.setAdapter(postAdapter);
    }

    //create option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    //set the option menu item function
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_home) {
            initPostList();
            Intent intent = new Intent(this, HomeScreenActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_post) {
            Intent intent = new Intent(this, NewPostActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_profile) {
            Intent intent = new Intent(this, profile_edit.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_filter) {
            Intent intent = new Intent(this, FilterActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    static class ViewHolder {
        //create a view holder class to hold all the views of one post
        public TextView name;
        public TextView post;
        public TextView budget;
        public TextView category;
        public TextView locationText;
        public ImageView userImage;
        public ImageButton replyButton;
    }

    /**
    //setup the initialized value for the post list
    public void initList() {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", "sue");
        map.put("post", "I need to shop for some shoes. Would anyone like to join?");
        map.put("image", R.drawable.sue);
        postList.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "nick");
        map.put("post", "I'm looking for some good music. Anyone want to go to a record store?");
        map.put("image", R.drawable.nick);
        postList.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "mary");
        map.put("post", "Anyone want to go clothes shopping? I found a new store on Liberty.");
        map.put("image", R.drawable.mary);
        postList.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "jason");
        map.put("post", "I want to find some good cajun food. Anyone want to check out midtown with me?");
        map.put("image", R.drawable.jason);
        postList.add(map);

        map = new HashMap<String, Object>();
        map.put("name", "mike");
        map.put("post", "I'm looking for some good music. Anyone want to go to a record store?");
        map.put("image", R.drawable.mike);
        postList.add(map);
    }
    **/

    //set up the customAdpater
    public class CustomAdapter extends BaseAdapter {
        private ArrayList<HashMap<String, Object>> mAppList;
        private LayoutInflater mInflater;
        private Context mContext;
        private String[] keyString;
        private int[] valueViewID;
        private ViewHolder holder;

        //set up the fram of customAdapter
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
                convertView = mInflater.inflate(R.layout.my_list_item, null);
                holder = new ViewHolder();
                holder.name = (TextView) convertView.findViewById(valueViewID[0]);
                holder.post = (TextView) convertView.findViewById(valueViewID[1]);
                holder.budget = (TextView) convertView.findViewById(valueViewID[2]);
                holder.category = (TextView) convertView.findViewById(valueViewID[3]);
                holder.locationText = (TextView) convertView.findViewById(valueViewID[4]);
                holder.userImage = (ImageView) convertView.findViewById(valueViewID[5]);
                holder.replyButton = (ImageButton) convertView.findViewById(R.id.replyButton);
                convertView.setTag(holder);
            }

            //put information into holder
            HashMap<String, Object> appInfo = mAppList.get(position);
            if (appInfo != null) {
                String posterName = (String) appInfo.get(keyString[0]);
                String postContent = (String) appInfo.get(keyString[1]);
                String budgetContent = (String) appInfo.get(keyString[2]);
                String categoryContent = (String) appInfo.get(keyString[3]);
                String locationContent = (String) appInfo.get(keyString[4]);
                int imageId = (Integer) appInfo.get(keyString[5]);
                holder.name.setText(posterName);
                holder.post.setText(postContent);
                holder.budget.setText(budgetContent);
                holder.category.setText(categoryContent);
                holder.locationText.setText(locationContent);
                holder.userImage.setImageDrawable(holder.userImage.getResources().getDrawable(imageId));
                //set up pop menu for reply button
                setPopup(holder.replyButton, posterName, postContent);
                //set up view profile for name text
                viewProfile(holder.name, posterName);
                viewProfile(holder.userImage, posterName);
            }
            return convertView;
        }
    }

    public void setPopup(final ImageView iv, final String poserName, final String postToPass) {
        //setup popup menu for the button
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(HomeScreenActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.content_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    //setup onclick listener for the pop up menu
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.action_message) {
                            Intent intent = new Intent(HomeScreenActivity.this, MessageActivity.class);
                            startActivity(intent);
                        } else if (id == R.id.action_reply) {
                            Intent intent = new Intent(HomeScreenActivity.this, ConversationActivity.class);
                            intent.putExtra("post", postToPass);
                            intent.putExtra("poster", poserName);
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
    public void viewProfile(final View view, final String posterName) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, Profile_display.class);
                //pass the user name to the profile display screen
                intent.putExtra("UserName", posterName);
                startActivity(intent);
            }
        });
    }

    public void setList(){
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.PostPref,
                Context.MODE_PRIVATE);
        String jsonString;
        String mapKey;
        HashMap<String, Object> map;
        int number = sizeOfSharedPrefs(sharedpreferences);
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
                if (map.get("display").equals(true)){
                    postList.add(map);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static int sizeOfSharedPrefs(SharedPreferences inputSharedpreference){
        Map<String, ?> allEntries = inputSharedpreference.getAll();
        return allEntries.size();
    }

    public void initPostList(){
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.PostPref,
                Context.MODE_PRIVATE);
        String jsonString;
        String mapKey;
        HashMap<String, Object> map;
        int number = sizeOfSharedPrefs(sharedpreferences);
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
