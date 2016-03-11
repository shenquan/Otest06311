package com.magicalign.OrthoLink.CaseHistory;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vane.demo.R;

public class FragmentSearch extends Fragment {

	private View rootView;	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		rootView = inflater.inflate(R.layout.case_history_search, null);

		return rootView;		
	}

}
