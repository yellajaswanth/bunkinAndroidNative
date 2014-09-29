package com.jash.bunkin;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import com.jash.bunkin.listviewfeed.volley.LruBitmapCache;
import com.parse.Parse;

public class BunkinApplication extends Application {
	
	public static final String TAG = BunkinApplication.class.getSimpleName();
	 
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    LruBitmapCache mLruBitmapCache;
 
    private static BunkinApplication mInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		Parse.initialize(this, "JxXHgO8hUWKuXV36SQdb27XLSCCgEYigtrzijxOb",
				"zeRNX8C442fNihWT2BBus6mRDyL6jcB3SPkRinpC");
		
		mInstance = this;

	}
	
	public static synchronized BunkinApplication getInstance() {
        return mInstance;
    }
 
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
 
        return mRequestQueue;
    }
    
    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (mImageLoader == null) {
            getLruBitmapCache();
            mImageLoader = new ImageLoader(this.mRequestQueue, mLruBitmapCache);
        }
 
        return this.mImageLoader;
    }
 
    public LruBitmapCache getLruBitmapCache() {
        if (mLruBitmapCache == null)
            mLruBitmapCache = new LruBitmapCache();
        return this.mLruBitmapCache;
    }
 
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
 
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
 
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

}
