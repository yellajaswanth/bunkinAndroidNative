package com.jash.bunkin;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class FriendsActivity extends ListActivity {

	public static final String TAG = FriendsActivity.class.getSimpleName();
	protected List<ParseUser> mUsers;
	protected ParseRelation<ParseUser> mFriendsRelation;
	protected ParseUser mCurrentUser;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_friends);
		
		getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE); 
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		mCurrentUser = ParseUser.getCurrentUser();
		mFriendsRelation = mCurrentUser.getRelation(ParseConstants.KEY_FRIENDS_RELATION);
		
		
		setProgressBarIndeterminateVisibility(true);
		ParseQuery<ParseUser> query = ParseUser.getQuery();
		query.orderByAscending(ParseConstants.KEY_USERNAME);
		query.setLimit(1000);
		
		
		query.findInBackground(new FindCallback<ParseUser>() {
			
			@Override
			public void done(List<ParseUser> users, ParseException e) {
				// Success
				setProgressBarIndeterminateVisibility(false);
				if(e == null){
					//success
					mUsers = users;
					String[] userNames = new String[mUsers.size()];
					int i =0;
					for(ParseUser user: mUsers){
						userNames[i] = user.getUsername();
						i++;
					}
					ArrayAdapter<String> adapter = new ArrayAdapter<String>
					(FriendsActivity.this, android.R.layout.simple_list_item_checked, userNames);
					setListAdapter(adapter);
					
					addFriendCheckMarks();
				}
				else{
					//error
					Log.e(TAG, e.getMessage());
					AlertDialog.Builder builder = new AlertDialog.Builder(FriendsActivity.this);
					builder.setMessage(e.getMessage())
						   .setTitle(R.string.error_title)
						   .setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				}
				
				
			}
		});
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.friends, menu);
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
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		if (getListView().isItemChecked(position)) {
			// add friend
			mFriendsRelation.add(mUsers.get(position));			
		}
		else {
			// remove friend
			mFriendsRelation.remove(mUsers.get(position));
		}
		mCurrentUser.saveInBackground(new SaveCallback() {
			@Override
			public void done(ParseException e) {
				if (e != null) {
					Log.e(TAG, e.getMessage());
				}
				else{
					Log.e(TAG,e.getMessage());
				}
			}
		});
	}
	
	protected void addFriendCheckMarks() {
		// TODO Auto-generated method stub
		mFriendsRelation.getQuery().findInBackground(new FindCallback<ParseUser>() {

			@Override
			public void done(List<ParseUser> friends, ParseException e) {
				if(e==null){
					//success
					for(int i=0; i<mUsers.size();i++){
						ParseUser user = mUsers.get(i);
						
						for(ParseUser friend: friends){
							if(friend.getObjectId().equals(user.getObjectId())){
								getListView().setItemChecked(i, true);
							}
						}
					}
				}
				else{
					//error
					Log.e(TAG,e.getMessage());
				}
				
			}
		});
	}
}
