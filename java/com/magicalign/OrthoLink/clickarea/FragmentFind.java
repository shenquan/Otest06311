package com.magicalign.OrthoLink.clickarea;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vane.demo.R;

public class FragmentFind extends Fragment implements OnClickListener {
	private View mRootView;
	private Context mContext;
	private LinearLayout mLlTitle;
	private LinearLayout mLlRealExam;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mRootView = inflater.inflate(R.layout.fragment_find, null);

		return mRootView;
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
		mLlRealExam.setOnClickListener(this);

	}

	private void initView() {
		mLlTitle = (LinearLayout) getActivity().findViewById(R.id.main_title);
		mLlRealExam = (LinearLayout) mRootView.findViewById(R.id.realexam);

		mLlTitle.setVisibility(8);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.realexam:
			// startActivity(new Intent(getActivity(), RealExam.class));
			break;

		default:
			break;
		}
	}
}
