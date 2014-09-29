package com.jash.bunkin.actions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.jash.bunkin.MainActivity;
import com.jash.bunkin.R;
import com.jash.bunkin.SignupActivity;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class ComposeActivity extends Activity {
	
	protected EditText status;	
	public static final String TAG = ComposeActivity.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compose);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compose, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();		
		if (id == R.id.action_send) {
			status = (EditText)findViewById(R.id.status_text_box);
			
			if(status.getText().toString().matches("")){
				
				Toast.makeText(this, "No status text to update", Toast.LENGTH_LONG).show();
			}
			else{
				
				ParseAnalytics.trackAppOpened(getIntent());
				ParseUser currentUser = ParseUser.getCurrentUser();			
				
				
				ParseObject myPost = new ParseObject("Post");
				myPost.put("username", currentUser.getUsername().toString());
				myPost.put("status", status.getText().toString());
				
				myPost.saveInBackground(new SaveCallback() {
					
					@Override
					public void done(ParseException e) {
						
						if(e==null) {
							Log.d(TAG, "Yayyy, Successful!!!");
							Intent intent = new Intent(ComposeActivity.this, MainActivity.class);							
							startActivity(intent);
						}
					}
				});
			}
			
		}
		return super.onOptionsItemSelected(item);
	}
}
