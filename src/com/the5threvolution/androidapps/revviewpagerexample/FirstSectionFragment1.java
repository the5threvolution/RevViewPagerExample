package com.the5threvolution.androidapps.revviewpagerexample;

//import com.the5threvolution.androidapps.RevPomodoro.RevPomodoro.CalendarPageFragmentListener;
//import com.the5threvolution.androidapps.RevPomodoro.RevPomodoro.SectionsPagerAdapter.CalendarPageListener;

import com.the5threvolution.androidapps.revviewpagerexample.RevViewPagerExampleActivity.FirstPageFragmentListener;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class FirstSectionFragment1 extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	public static FirstPageFragmentListener pageListener;
	
	public FirstSectionFragment1() {
	}

	public static Fragment newInstance(FirstPageFragmentListener listener) {
		FirstSectionFragment1 frag = new FirstSectionFragment1();
		pageListener = listener;
		return frag;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_1_view, container, false);
		
		Button next = (Button)rootView.findViewById(R.id.Button01);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	FirstSectionFragment1.pageListener.onSwitchToNextFragment();
            }

        });
        
		return rootView;
	}
}