package com.jash.bunkin;

import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;

public class UserFriends extends ListActivity {
	
	public static final String TAG = UserFriends.class.getSimpleName();
	
	protected ParseUser mCurrentUser;
	protected ParseRelation<ParseUser> mFriendsRelation;
	protected List<ParseUser> mFriends;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_friends);	
		
		
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		mCurrentUser = ParseUser.getCurrentUser();
		mFriendsRelation = mCurrentUser.getRelation(ParseConstants.KEY_FRIENDS_RELATION);
		

		// TODO Auto-generated method stub
		ParseQuery<ParseUser> query =  mFriendsRelation.getQuery();
		query.addAscendingOrder(ParseConstants.KEY_USERNAME);
		query.findInBackground(new FindCallback<ParseUser>() {

			@Override
			public void done(List<ParseUser> friends, ParseException e) {
				
				if(e==null){
					mFriends = friends;
	
					String[] userNames = new String[mFriends.size()];
					int i =0;
					for(ParseUser user: mFriends){
						userNames[i] = user.getUsername();
						i++;
					}
					ArrayAdapter<String> adapter = new ArrayAdapter<String>
					(UserFriends.this, android.R.layout.simple_list_item_1, userNames);
					setListAdapter(adapter);
				}
				else{
					Log.e(TAG, e.getMessage());
					AlertDialog.Builder builder = new AlertDialog.Builder(UserFriends.this);
					builder.setMessage(e.getMessage())
						   .setTitle(R.string.error_title)
						   .setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				}
				
			}
		});
	
	}
}
