package com.jash.bunkin.actions;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.jash.bunkin.LoginActivity;
import com.jash.bunkin.MainActivity;
import com.jash.bunkin.R;
import com.jash.bunkin.SignupActivity;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class CreateBunkin extends Activity {
	
	protected EditText bunkinName;
	protected EditText bunkinDetails;
	protected EditText bunkinLocation;
	protected EditText bunkinDate;
	protected EditText bunkinTime;
	public static final String TAG = CreateBunkin.class.getSimpleName();
	
	final Calendar cal = Calendar.getInstance();
	int mYear = cal.get(Calendar.YEAR);
	int mMonth = cal.get(Calendar.MONTH);
	int mDay = cal.get(Calendar.DAY_OF_MONTH);
	
	String minutes;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_bunkin);
		
		
		bunkinName = (EditText)findViewById(R.id.bunkin_event_name);
		bunkinDetails = (EditText)findViewById(R.id.bunkin_details);
		bunkinLocation = (EditText)findViewById(R.id.bunkin_place);
		bunkinDate = (EditText)findViewById(R.id.bunkin_date);
		bunkinTime = (EditText)findViewById(R.id.bunkin_time);
		final DatePickerDialog dpd = new DatePickerDialog(this,
		        new DatePickerDialog.OnDateSetListener() {
		 
		            @Override
		            public void onDateSet(DatePicker view, int year,
		                    int monthOfYear, int dayOfMonth) {
		            	bunkinDate.setText(dayOfMonth + "-"
		                        + (monthOfYear + 1) + "-" + year);
		 
		            }
		        }, mYear, mMonth, mDay);
		
		bunkinLocation.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				
			}
		});	
		
		bunkinDate.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dpd.show();
			}
		});
		
		bunkinDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if(hasFocus)
					dpd.show();			
			}
		});
		
		bunkinTime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				int hour = cal.get(Calendar.HOUR_OF_DAY);
	            int minute = cal.get(Calendar.MINUTE);
	            TimePickerDialog mTimePicker;
	            mTimePicker = new TimePickerDialog(CreateBunkin.this, new TimePickerDialog.OnTimeSetListener() {
	                @Override
	                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
	                	minutes = String.format("%02d", selectedMinute);
	                	bunkinTime.setText( selectedHour + ":" + minutes);
	                }
	            }, hour, minute, true);//Yes 24 hour time
	            mTimePicker.setTitle("Select Time");
	            mTimePicker.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_bunkin, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_send) {
			setProgressBarIndeterminateVisibility(true);
			bunkinName = (EditText)findViewById(R.id.bunkin_event_name);
			bunkinDetails = (EditText)findViewById(R.id.bunkin_details);
			bunkinLocation = (EditText)findViewById(R.id.bunkin_place);
			bunkinDate = (EditText)findViewById(R.id.bunkin_date);
			bunkinTime = (EditText)findViewById(R.id.bunkin_time);
			
			if(bunkinName.getText().toString().matches("")){
				
				Toast.makeText(this, "Set Bunkin Name", Toast.LENGTH_LONG).show();
			} else if(bunkinLocation.getText().toString().matches("")){
				Toast.makeText(this, "Set Location", Toast.LENGTH_LONG).show();
			} else if(bunkinDate.getText().toString().matches("")){
				Toast.makeText(this, "Set Date", Toast.LENGTH_LONG).show();
			} else{

				
				ParseAnalytics.trackAppOpened(getIntent());
				ParseUser currentUser = ParseUser.getCurrentUser();			
				
				
				ParseObject myPost = new ParseObject("Bunkin");
				myPost.put("username", currentUser.getUsername().toString());
				myPost.put("bunkinName", bunkinName.getText().toString());
				myPost.put("bunkinLocation", bunkinLocation.getText().toString());
				myPost.put("bunkinDate", bunkinDate.getText().toString());
				myPost.put("bunkinTime", bunkinTime.getText().toString());
				
				
				myPost.saveInBackground(new SaveCallback() {
					
					@Override
					public void done(ParseException e) {
						setProgressBarIndeterminateVisibility(false);
						if(e==null) {
							Log.d(TAG, "Yayyy, Successful!!!");
							finish();
						}
					}
				});
			
			}			
			
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
