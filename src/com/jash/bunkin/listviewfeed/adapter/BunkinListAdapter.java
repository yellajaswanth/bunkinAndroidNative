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


 
public class BunkinListAdapter extends ArrayAdapter<ParseObject> {  
	
	
	protected Context mContext;
	protected List<ParseObject> mBunkins;
    private LayoutInflater inflater;
	private static final String TAG = MainActivity.class.getSimpleName(); 
	
	public BunkinListAdapter(Context context, List<ParseObject> bunkin) {
		super(context, R.layout.bunk_item, bunkin);
		mContext = context;
		mBunkins = bunkin;
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
			convertView = inflater.from(mContext).inflate(R.layout.bunk_item, null);			
		}
		
		holder = new ViewHolder();
		
		holder.bunkName = (TextView)convertView.findViewById(R.id.bunk_name);
		holder.bunkDateTime = (TextView)convertView.findViewById(R.id.bunk_day_date);
		holder.bunkinLocation = (TextView)convertView.findViewById(R.id.bunk_location);
		
		
		
		
		ParseObject bunkin = mBunkins.get(position);
		
		holder.bunkName.setText(bunkin.getString("bunkinName"));
		holder.bunkDateTime.setText(bunkin.getString("bunkinTime") + " " + bunkin.getString("bunkinDate"));
		holder.bunkinLocation.setText(bunkin.getString("bunkinLocation"));
		
		return convertView;
	}
	
	private static class ViewHolder {	
		TextView bunkName;
		TextView bunkDateTime;
		TextView bunkinLocation;	
	}
 
}