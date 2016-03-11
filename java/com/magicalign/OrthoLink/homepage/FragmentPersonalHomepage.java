package com.magicalign.OrthoLink.homepage;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vane.demo.R;

public class FragmentPersonalHomepage extends Fragment implements OnClickListener {
	
	private View rootView;
	private Context mContext;
	private LinearLayout mLlTitle;

	private LinearLayout mLlCase,mLlLecture,mLlDynamic,mLlPublish;
	
	private FragmentCase mFgCase;
	private FragmentLecture mFgLecture;
	private FragmentDynamic mFgDynamic;
	private FragmentPublish mFgPublish;
	private TextView mTvCase, mTvLecture, mTvDynamic, mTvPublish;
	private View mVCase,mVLecture,mVDynamic,mVPublish;
	


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.personal_homepage, null);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mContext = this.getActivity();
		initView();
		setListener();

		clickCaseBtn();
	}

	private void setListener() {
		// TODO Auto-generated method stub

		mLlCase.setOnClickListener(this);
		mLlLecture.setOnClickListener(this);
		mLlDynamic.setOnClickListener(this);
		mLlPublish.setOnClickListener(this);

	}

	private void initView() {

		mLlCase = (LinearLayout) rootView.findViewById(R.id.homepage_case_title);
		mLlLecture = (LinearLayout) rootView.findViewById(R.id.homepage_lecture_title);
		mLlDynamic = (LinearLayout) rootView.findViewById(R.id.homepage_dynamic_title);
		mLlPublish = (LinearLayout) rootView.findViewById(R.id.homepage_public_title);
		
		mTvCase = (TextView) rootView.findViewById(R.id.homepage_case_title_txt);
		mTvLecture = (TextView) rootView.findViewById(R.id.homepage_lecture_title_txt);
		mTvDynamic = (TextView) rootView.findViewById(R.id.homepage_dynamic_title_txt);
		mTvPublish = (TextView) rootView.findViewById(R.id.homepage_publish_title_txt);
		
		mVCase = (View) rootView.findViewById(R.id.homepage_case_title_view);
		mVLecture = (View) rootView.findViewById(R.id.homepage_lecture_title_view);
		mVDynamic = (View) rootView.findViewById(R.id.homepage_dynamic_title_view);
		mVPublish = (View) rootView.findViewById(R.id.homepage_publish_title_view);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
//		case R.id.homepage_case_orthodontics:
//			 startActivity(new Intent(getActivity(), Orthodotics.class));
//			break;
		case R.id.homepage_case_title:
			clickCaseBtn();
			break;
		case R.id.homepage_lecture_title:
			clickLectureBtn();
			break;
		case R.id.homepage_dynamic_title:
			clickDynamicBtn();
			break;
		case R.id.homepage_public_title:
			clickPublishBtn();
			break;
		default:
			break;
		}
	}
	
	
	private void changeTextState(int nIndex){
		switch(nIndex){
		case 1:
			mTvCase.setTextColor(0xFF17B4EC);
			mVCase.setBackgroundColor(0xFF17B4EC);
			
			mTvLecture.setTextColor(0xFF805E5E);
			mVLecture.setBackgroundColor(0xFF949494);
			
			mTvDynamic.setTextColor(0xFF805E5E);
			mVDynamic.setBackgroundColor(0xFF949494);
			
			mTvPublish.setTextColor(0xFF805E5E);
			mVPublish.setBackgroundColor(0xFF949494);
			break;
		case 2:
			mTvCase.setTextColor(0xFF805E5E);
			mVCase.setBackgroundColor(0xFF949494);
			
			mTvLecture.setTextColor(0xFF17B4EC);
			mVLecture.setBackgroundColor(0xFF17B4EC);
			
			mTvDynamic.setTextColor(0xFF805E5E);
			mVDynamic.setBackgroundColor(0xFF949494);
			
			mTvPublish.setTextColor(0xFF805E5E);
			mVPublish.setBackgroundColor(0xFF949494);
			break;
		case 3:
			mTvCase.setTextColor(0xFF805E5E);
			mVCase.setBackgroundColor(0xFF949494);
			
			mTvLecture.setTextColor(0xFF805E5E);
			mVLecture.setBackgroundColor(0xFF949494);
			
			mTvDynamic.setTextColor(0xFF17B4EC);
			mVDynamic.setBackgroundColor(0xFF17B4EC);
			
			mTvPublish.setTextColor(0xFF805E5E);
			mVPublish.setBackgroundColor(0xFF949494);
			break;
		case 4:
			mTvCase.setTextColor(0xFF805E5E);
			mVCase.setBackgroundColor(0xFF949494);
			
			mTvLecture.setTextColor(0xFF805E5E);
			mVLecture.setBackgroundColor(0xFF949494);
			
			mTvDynamic.setTextColor(0xFF805E5E);
			mVDynamic.setBackgroundColor(0xFF949494);
			
			mTvPublish.setTextColor(0xFF17B4EC);
			mVPublish.setBackgroundColor(0xFF17B4EC);
			break;
		default:
			break;
		}
	}
	
	private void clickCaseBtn(){
		// 实例化Fragment页面
		mFgCase = new FragmentCase();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_homepage_content, mFgCase);
		// 事务管理提交
		fragmentTransaction.commit();
		
		changeTextState(1);

	}
	
	private void clickLectureBtn(){
		// 实例化Fragment页面
		mFgLecture = new FragmentLecture();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_homepage_content, mFgLecture);
		// 事务管理提交
		fragmentTransaction.commit();
		
		changeTextState(2);
	}
	
	private void clickDynamicBtn(){
		// 实例化Fragment页面
		mFgDynamic = new FragmentDynamic();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_homepage_content, mFgDynamic);
		// 事务管理提交
		fragmentTransaction.commit();
		
		changeTextState(3);
	}
	
	private void clickPublishBtn(){
		// 实例化Fragment页面
		mFgPublish = new FragmentPublish();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_homepage_content, mFgPublish);
		// 事务管理提交
		fragmentTransaction.commit();
		
		changeTextState(4);
	}
	
	
}