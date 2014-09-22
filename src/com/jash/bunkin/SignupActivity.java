package com.jash.bunkin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignupActivity extends Activity {
	
	protected EditText mUserName;
	protected EditText mPassword;
	protected EditText mEmail;
	protected Button mSignUpButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setContentView(R.layout.activity_signup);
		
		mUserName = (EditText) findViewById(R.id.signup_userName);
		mPassword = (EditText) findViewById(R.id.signup_Password);
		mEmail = (EditText) findViewById(R.id.signup_Email);
		mSignUpButton = (Button) findViewById(R.id.LoginButton);
		
		mSignUpButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String UserName = mUserName.getText().toString().trim();
				String Password = mPassword.getText().toString().trim();
				String Email = mEmail.getText().toString().trim();
				
				if(UserName.isEmpty() || Password.isEmpty() || Email.isEmpty()){
					//TODO: Missing Signup Details
					AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
					builder.setMessage(R.string.signup_error_msg)
						   .setTitle(R.string.signup_error_title)
						   .setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				}
				else{
					//TODO: Create new user.. Facebook oAuth
					setProgressBarIndeterminateVisibility(true);
					ParseUser newUser = new ParseUser();
					newUser.setUsername(UserName);
					newUser.setPassword(Password);
					newUser.setEmail(Email);
					newUser.signUpInBackground(new SignUpCallback() {
						
						@Override
						public void done(ParseException e) {
							setProgressBarIndeterminateVisibility(false);
							if(e == null){
								
								//success
								Intent intent = new Intent(SignupActivity.this, MainActivity.class);
								intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
								intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
								startActivity(intent);
							}
							else{
								AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
								builder.setMessage(e.getMessage())
									   .setTitle(R.string.login_error_title)
									   .setPositiveButton(android.R.string.ok, null);
								AlertDialog dialog = builder.create();
								dialog.show();
							}
							
						}
					});
					
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
