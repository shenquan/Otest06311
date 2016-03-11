package com.magicalign.OrthoLink.exam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.vane.demo.R;
public class ExamLibrary extends Fragment implements OnClickListener {
	private View mRootView;

	private LinearLayout mLlpratice;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.activity_exam_library, container, false);
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
		mLlpratice = (LinearLayout) mRootView.findViewById(R.id.practicell);
		mLlpratice.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.practicell:
			startActivity(new Intent(getActivity(), ChooseExam.class));
			break;

		default:
			break;
		}

	}

}
