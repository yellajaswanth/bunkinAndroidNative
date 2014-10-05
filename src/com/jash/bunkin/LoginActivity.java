package com.jash.bunkin;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

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
import android.widget.TextView;

public class LoginActivity extends Activity {
	
	protected TextView mSignUpTextView;
	protected EditText mUserName;
	protected EditText mPassword;
	protected EditText mEmail;
	protected Button mLoginButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS); //Progressbar appears on the actionbar
		setContentView(R.layout.activity_login);
		
		mUserName = (EditText) findViewById(R.id.userNameField);
		mPassword = (EditText) findViewById(R.id.passwordField);
		mEmail = (EditText) findViewById(R.id.signup_Email);
		mLoginButton = (Button) findViewById(R.id.LoginButton);		
		mSignUpTextView = (TextView) findViewById(R.id.signUpText);
		
		//On Click Signup
		mSignUpTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
				startActivity(intent);				
			}
		});
		
		//On click Login
		mLoginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String UserName = mUserName.getText().toString().trim();
				String Password = mPassword.getText().toString().trim();				
				
				if(UserName.isEmpty() || Password.isEmpty()){
					//TODO: Missing Signup Details
					AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
					builder.setMessage(R.string.login_error_msg)
						   .setTitle(R.string.login_error_title)
						   .setPositiveButton(android.R.string.ok, null);
					AlertDialog dialog = builder.create();
					dialog.show();
				}
				else{
					//Login
					setProgressBarIndeterminateVisibility(true);
					ParseUser.logInInBackground(UserName, Password, new LogInCallback() {
						
						@Override
						public void done(ParseUser user, ParseException e) {
							setProgressBarIndeterminate(false);
							if(e == null){
								Intent intent = new Intent(LoginActivity.this, MainActivity.class);
								intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
								intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TASK);
								startActivity(intent);
							}else{
								//error
								AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
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
