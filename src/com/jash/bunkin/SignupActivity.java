package com.jash.bunkin;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupActivity extends Activity {
	
	protected EditText mUserName;
	protected EditText mPassword;
	protected EditText mEmail;
	protected Button mSignUpButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signup);
		
		mUserName = (EditText) findViewById(R.id.signup_userName);
		mPassword = (EditText) findViewById(R.id.signup_Password);
		mEmail = (EditText) findViewById(R.id.signup_Email);
		mSignUpButton = (Button) findViewById(R.id.signUpButton);
		
		mSignUpButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String UserName = mUserName.getText().toString().trim();
				String Password = mPassword.getText().toString().trim();
				String Email = mEmail.getText().toString().trim();
				
				if(UserName.isEmpty() || Password.isEmpty() || Email.isEmpty()){
					AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
					builder.setMessage(R.string.signup_error_msg)
						   .setTitle(R.string.signup_error_title)
						   .setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				}
				else{
					
				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.signup, menu);
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
