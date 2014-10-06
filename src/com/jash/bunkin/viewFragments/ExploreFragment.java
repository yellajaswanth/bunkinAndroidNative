package com.jash.bunkin.viewFragments;


import java.util.List;

import com.jash.bunkin.MainActivity;
import com.jash.bunkin.ParseConstants;
import com.jash.bunkin.R;
import com.jash.bunkin.listviewfeed.adapter.BunkinListAdapter;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ExploreFragment extends ListFragment {
	
	private static final String TAG = MainActivity.class.getSimpleName();   
	protected List<ParseObject> mBunkins;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.explore_feed,
				container, false);

		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		getActivity().setProgressBarIndeterminateVisibility(true);
		
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Bunkin");		
		query.addDescendingOrder(ParseConstants.KEY_CREATED_AT);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> bunkin, ParseException e) {
				getActivity().setProgressBarIndeterminateVisibility(false);
				
				if (e == null) {
					// We found feed!
					mBunkins = bunkin;					
					BunkinListAdapter adapter = new BunkinListAdapter(
							getListView().getContext(), 
							mBunkins);
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
