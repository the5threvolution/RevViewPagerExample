package com.the5threvolution.androidapps.revviewpagerexample;

import com.the5threvolution.androidapps.revviewpagerexample.RevViewPagerExampleActivity.FirstPageFragmentListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FirstSectionFragment2 extends Fragment {
	public static FirstPageFragmentListener pageListener;
	
	public static Fragment newInstance(FirstPageFragmentListener listener) {
		FirstSectionFragment2 frag = new FirstSectionFragment2();
		pageListener = listener;
		return frag;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		Fragment fragment = getFragmentManager().findFragmentByTag("NewFragmentTag");
	    if(fragment == null) fragment = new Fragment();
	    
		
		View rootView = inflater.inflate(R.layout.fragment_rev_view_pager_example_dummy, container, false);
		TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
		dummyTextView.setText("This is replaced.!!!");
		dummyTextView.setTextSize(50);
		return rootView;
	}
}
