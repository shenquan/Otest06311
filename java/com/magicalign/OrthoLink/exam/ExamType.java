package com.magicalign.OrthoLink.exam;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vane.demo.R;

public class ExamType extends Fragment implements OnClickListener {

	private LinearLayout mLlTitle;

	private LinearLayout mLlSimulation;

	private View mRootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_find, container, false);
		return mRootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		mLlTitle = (LinearLayout) getActivity().findViewById(R.id.main_title);
		mLlTitle.setVisibility(8);

		mLlSimulation = (LinearLayout) mRootView
				.findViewById(R.id.simulation_exam);
		mLlSimulation.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.simulation_exam:
			ExamLibrary library = new ExamLibrary();
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.frame_content, library);
			ft.addToBackStack(null);
			ft.commit();
			break;

		default:
			break;
		}

	}
}
