package com.the5threvolution.androidapps.revviewpagerexample;

import java.util.Locale;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class RevViewPagerExampleActivity extends FragmentActivity implements
		ActionBar.TabListener {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rev_view_pager_example);

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);

		// When swiping between different sections, select the corresponding
		// tab. We can also use ActionBar.Tab#select() to do this if we have
		// a reference to the Tab.
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter. Also specify this Activity object, which implements
			// the TabListener interface, as the callback (listener) for when
			// this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rev_view_pager_example, menu);
		return true;
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	// http://stackoverflow.com/questions/7723964/replace-fragment-inside-a-viewpager
	// mdelolmo's solution modified slightly
	// StackOverflow solution
		public class SectionsPagerAdapter extends FragmentPagerAdapter {
		    public final class FirstPageListener implements FirstPageFragmentListener {
		        public void onSwitchToNextFragment() {
		            mFragmentManager.beginTransaction().remove(mFragmentAtPos0)
		                    .commit();
		            if (mFragmentAtPos0 instanceof FirstSectionFragment1){
		                mFragmentAtPos0 = FirstSectionFragment2.newInstance(listener);
		            }else { // Instance of NextFragment
		                mFragmentAtPos0 = FirstSectionFragment1.newInstance(listener);
		            }
		            notifyDataSetChanged();
		        }
		    }

		    FirstPageListener listener = new FirstPageListener();;
		    private Fragment mFragmentAtPos0;
		    private FragmentManager mFragmentManager;
		    private int currentPosition;

		    public SectionsPagerAdapter(FragmentManager fm) {
		        super(fm);
		        mFragmentManager = fm;
		        currentPosition = 0;
		    }

		    @Override
		    public int getCount() {
		        return 3;
		    }

		    @Override
		    public int getItemPosition(Object object) {
		        if (object instanceof FirstSectionFragment1 && mFragmentAtPos0 instanceof FirstSectionFragment2)
		            return POSITION_NONE;
		        if (object instanceof FirstSectionFragment2 && mFragmentAtPos0 instanceof FirstSectionFragment1)
		            return POSITION_NONE;
		        return POSITION_UNCHANGED;
		    }

		    @Override
		    public Fragment getItem(int position) {
		        if (position == 0) { // Position where you want to replace fragments
		            if (mFragmentAtPos0 == null) {
		                mFragmentAtPos0 = FirstSectionFragment1.newInstance(listener);
		            }
		            currentPosition = 0;
		            return mFragmentAtPos0;
		        }
		        if (position == 1) {
		        	Fragment fragment = new DummySectionFragment();
					Bundle args = new Bundle();
					args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
					fragment.setArguments(args);
					currentPosition = 1;
					return fragment;
		        }
		        if (position == 2) {
		        	Fragment fragment = new DummySectionFragment();
					Bundle args = new Bundle();
					args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
					fragment.setArguments(args);
					currentPosition = 2;
					return fragment;
		        }

		        return null;
		    }
		    
		    @Override
			public CharSequence getPageTitle(int position) {
				Locale l = Locale.getDefault();
				switch (position) {
				case 0:
					return getString(R.string.title_section1).toUpperCase(l);
				case 1:
					return getString(R.string.title_section2).toUpperCase(l);
				case 2:
					return getString(R.string.title_section3).toUpperCase(l);
				}
				return null;
			}
		    
		    public boolean onBackPressed() {
		    	if(currentPosition == 0) {
		    		if(mFragmentAtPos0 instanceof FirstSectionFragment2) {
		    			FirstSectionFragment2.pageListener.onSwitchToNextFragment();
		    			return true;
		    		}
		    	}
		    	
		    	return false;
		    }
		}

		@Override
		public void onBackPressed() {
			if(!mSectionsPagerAdapter.onBackPressed()) {
				super.onBackPressed();
			}
		}
		
		public interface FirstPageFragmentListener {
		    void onSwitchToNextFragment();
		}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(
					R.layout.fragment_rev_view_pager_example_dummy, container,
					false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

}
