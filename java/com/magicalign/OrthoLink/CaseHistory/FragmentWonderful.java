package com.magicalign.OrthoLink.CaseHistory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.magicalign.OrthoLink.CustomView.ImageCycleView;
import com.magicalign.OrthoLink.CustomView.ImageCycleView.ImageCycleViewListener;
import com.vane.demo.R;

public class FragmentWonderful extends Fragment implements OnClickListener {
	private int[] adpic = { R.drawable.ad_one, R.drawable.ad_two };

	private View rootView;
	private ImageCycleView mAdView;
	private LinearLayout mLlWonderfulCase;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.case_history_wonderful, null);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		setListener();
	}

	private void setListener() {
		mLlWonderfulCase.setOnClickListener(this);
	}

	private void initView() {
		mAdView = (ImageCycleView) rootView.findViewById(R.id.adView);
		mAdView.setImageResources(adpic, mAdCycleViewListener);

		mLlWonderfulCase = (LinearLayout) rootView
				.findViewById(R.id.case_history_wonderful_case);
	}

	private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

		@Override
		public void onImageClick(int position, View imageView) {

		}

		@Override
		public void displayImage(int position, ImageView imageView) {
			imageView.setImageResource(adpic[position]);
		}
	};

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.case_history_wonderful_case:
			startActivity(new Intent(getActivity(), WonderfulCase.class));
			break;
		default:
			break;
		}
	}
}
