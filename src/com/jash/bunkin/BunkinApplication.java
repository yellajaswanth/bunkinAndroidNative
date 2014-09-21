package com.jash.bunkin;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class BunkinApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "JxXHgO8hUWKuXV36SQdb27XLSCCgEYigtrzijxOb",
				"zeRNX8C442fNihWT2BBus6mRDyL6jcB3SPkRinpC");

	}

}
