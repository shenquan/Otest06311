package com.magicalign.OrthoLink.homepage;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vane.demo.R;

public class FragmentCase extends Fragment implements OnClickListener {
	
	private View rootView;
	private Context mContext;
	
	private LinearLayout mLlOrthodotics;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.personal_homepage_case, null);

		return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mContext = this.getActivity();
		initView();
		setListener();

	}

	private void setListener() {
		// TODO Auto-generated method stub
		mLlOrthodotics.setOnClickListener(this);
	}

	private void initView() {
		mLlOrthodotics = (LinearLayout) rootView.findViewById(R.id.homepage_case_orthodontics);

	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.homepage_case_orthodontics:
			 startActivity(new Intent(getActivity(), Orthodontics.class));
			break;
		default:
			break;
		}
	}
}