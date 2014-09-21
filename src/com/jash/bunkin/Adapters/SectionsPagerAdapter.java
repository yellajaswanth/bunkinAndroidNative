package com.jash.bunkin.Adapters;

import java.util.Locale;

import com.jash.bunkin.R;
import com.jash.bunkin.R.string;
import com.jash.bunkin.viewFragments.ExploreFragment;
import com.jash.bunkin.viewFragments.HomeFragment;
import com.jash.bunkin.viewFragments.ProfileFragment;
import com.jash.bunkin.viewFragments.SearchFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
	
	protected Context mContext;

	public SectionsPagerAdapter(Context context, FragmentManager fm) {
		super(fm);
		mContext = context;
	}


	@Override
	public Fragment getItem(int position) {
		// getItem is called to instantiate the fragment for the given page.
		// Return a DummySectionFragment (defined as a static inner class
		// below) with the page number as its lone argument.
		
		switch(position) {
			case 0:
				return new HomeFragment();
			case 1:
				return new ExploreFragment();
			case 2:
				return new SearchFragment();
			case 3:
				return new ProfileFragment();
		}

		return null;
	}

	@Override
	public int getCount() {
		return 4;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return mContext.getString(R.string.title_section1).toUpperCase(l);
		case 1:
			return mContext.getString(R.string.title_section2).toUpperCase(l);
		case 2:
			return mContext.getString(R.string.title_section3).toUpperCase(l);
		case 3:
			return mContext.getString(R.string.title_section4).toUpperCase(l);
		}
		return null;
	}
}