package com.jash.bunkin.viewFragments;




import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;


import com.jash.bunkin.MainActivity;
import com.jash.bunkin.R;
import com.jash.bunkin.UserFriends;


public class ProfileFragment extends ListFragment {

	protected Button showFriends;
	public static final String TAG = MainActivity.class.getSimpleName();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.profile_view,
				container, false);
		
		showFriends = (Button) rootView.findViewById(R.id.userFriends);
		
		showFriends.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				Log.i(TAG,getActivity().toString());
				Intent intent = new Intent(getActivity(), UserFriends.class);
				startActivity(intent);				
			}
		});

		return rootView;
	}
	

}
