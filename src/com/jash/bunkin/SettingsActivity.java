package com.jash.bunkin;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class SettingsActivity extends Activity {
	
	Button mFbButton;
	TextView mStatus;
	final ParseUser user = ParseUser.getCurrentUser();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		mFbButton = (Button) findViewById(R.id.ConnectFB);
		mStatus = (TextView)findViewById(R.id.fb_connect_status);
		mFbButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				try {
			        PackageInfo info = getPackageManager().getPackageInfo(
			                "com.example.project", PackageManager.GET_SIGNATURES); //Your package name here
			        for (Signature signature : info.signatures) {
			            MessageDigest md = MessageDigest.getInstance("SHA");
			            md.update(signature.toByteArray());
			            Log.v("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
			            }
			    } catch (NameNotFoundException e) {
			    } catch (NoSuchAlgorithmException e) {
			    }
				
				/*if (!ParseFacebookUtils.isLinked(user)) {
					  ParseFacebookUtils.link(user, SettingsActivity.this, new SaveCallback() {
					    @Override
					    public void done(ParseException ex) {
					      if (ParseFacebookUtils.isLinked(user)) {
					    	  mStatus.setText("Connected");
					      }
					    }						
					  });
					}*/			
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
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
}
