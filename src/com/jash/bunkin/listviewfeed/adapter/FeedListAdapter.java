package com.jash.bunkin.listviewfeed.adapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jash.bunkin.MainActivity;
import com.jash.bunkin.ParseConstants;
import com.jash.bunkin.R;
import com.parse.ParseObject;
import com.squareup.picasso.Picasso;


 
public class FeedListAdapter extends ArrayAdapter<ParseObject> {  
	
	
	protected Context mContext;
	protected List<ParseObject> mFeed;
    private LayoutInflater inflater;
	private static final String TAG = MainActivity.class.getSimpleName(); 
	
	public FeedListAdapter(Context context, List<ParseObject> feed) {
		super(context, R.layout.feed_item, feed);
		mContext = context;
		mFeed = feed;
	}
	
	@Override
	public boolean isEnabled(int position)
	{
		return true;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		
		
		if (convertView == null) {
			convertView = inflater.from(mContext).inflate(R.layout.feed_item, null);			
		}
		
		holder = new ViewHolder();
		holder.displayPicView = (ImageView)convertView.findViewById(R.id.profilePic);
		holder.usernameLabel = (TextView)convertView.findViewById(R.id.name);
		holder.statusLabel = (TextView)convertView.findViewById(R.id.txtStatusMsg);
		holder.timeStampView = (TextView)convertView.findViewById(R.id.timestamp);
		holder.photoView = (ImageView)convertView.findViewById(R.id.feedImage1);
		
		
		
		ParseObject feed = mFeed.get(position);	
		
		Picasso.with(mContext).load("http://i.imgur.com/NLBJRC9.jpg").into(holder.displayPicView);
		
		
		
		holder.usernameLabel.setText(feed.getString("username"));
		holder.statusLabel.setText(feed.getString("status"));
		holder.timeStampView.setText("Not Available");
		holder.photoView.setVisibility(View.GONE);
		
		
		return convertView;
	}
	
	private static class ViewHolder {
		ImageView displayPicView;
		TextView usernameLabel;
		TextView statusLabel;
		TextView timeStampView;
		ImageView photoView;
	}
 
}