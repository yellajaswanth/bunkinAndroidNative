package com.jash.bunkin.viewFragments;



import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.jash.bunkin.MainActivity;
import com.jash.bunkin.ParseConstants;
import com.jash.bunkin.R;
import com.jash.bunkin.listviewfeed.adapter.FeedListAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;


public class HomeFragment extends ListFragment {
	
	private static final String TAG = MainActivity.class.getSimpleName();   
	protected List<ParseObject> mFeed;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.home_feed,
				container, false);
        return rootView;
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		
		getActivity().setProgressBarIndeterminateVisibility(true);
		
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Post");		
		query.addDescendingOrder(ParseConstants.KEY_CREATED_AT);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> feed, ParseException e) {
				getActivity().setProgressBarIndeterminateVisibility(false);
				
				if (e == null) {
					// We found feed!
					mFeed = feed;					
					FeedListAdapter adapter = new FeedListAdapter(
							getListView().getContext(), 
							mFeed);
					//Log.e(TAG, mFeed.toString());
					setListAdapter(adapter);
					getListView().setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> parent, View v,
								int position, long id) {
							Toast.makeText(getActivity(), String.valueOf(position)  + ":" + String.valueOf(id) , Toast.LENGTH_LONG).show();
							
						}
					});
										
				}			
				
			}	
			
		});
			
	}
	
	
	
	
}
