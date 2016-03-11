package com.magicalign.OrthoLink.CaseHistory;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.magicalign.OrthoLink.clickarea.MainActivity;
import com.vane.demo.R;

public class CaseHistory extends Fragment {

	private ViewPager vPager = null;
	/**
	 * 代表选项卡下的下划线的imageview
	 */
	private ImageView mImvCursor = null;
	/**
	 * 选项卡下划线长度
	 */
	private static int lineWidth = 0;

	/**
	 * 偏移量 （手机屏幕宽度/3-选项卡长度）/2
	 */
	private static int offset = 0;

	/**
	 * 选项卡总数
	 */
	private static final int TAB_COUNT = 4;
	/**
	 * 当前显示的选项卡位置
	 */
	private int nCurrentIndex = 0;

	/**
	 * 选项卡标题
	 */
	private TextView mTvCaseWonderful, mTvCaseKinds, mTvCaseSearch,
			mTvCaseComment;

	private LinearLayout mLlMainTitle;
	private RelativeLayout mRlCommonTitle;
	private LinearLayout mLlSearchTitle;
	private View rootView;

	private TextView title;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.case_history, container, false);
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		mLlMainTitle = (LinearLayout) getActivity().findViewById(
				R.id.main_title);
		mLlMainTitle.setVisibility(8);

		mRlCommonTitle = (RelativeLayout) rootView
				.findViewById(R.id.case_common_title);
		mLlSearchTitle = (LinearLayout) rootView
				.findViewById(R.id.case_search_title);

		title = (TextView) rootView.findViewById(R.id.title);
		title.setText("病例库");

		vPager = (ViewPager) rootView.findViewById(R.id.vPager);

		initImageView();
		mTvCaseWonderful = (TextView) rootView
				.findViewById(R.id.case_wanderful);
		mTvCaseKinds = (TextView) rootView.findViewById(R.id.case_kinds);
		mTvCaseSearch = (TextView) rootView.findViewById(R.id.case_search);
		mTvCaseComment = (TextView) rootView.findViewById(R.id.case_comment);
		final TextView[] titles = { mTvCaseWonderful, mTvCaseKinds,
				mTvCaseSearch, mTvCaseComment };

		vPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
			@Override
			public int getCount() {
				return TAB_COUNT;
			}

			@Override
			public Fragment getItem(int index)// 直接创建fragment对象并返回
			{
				switch (index) {
				case 0:
					return new FragmentWonderful();
				case 1:
					return new FragmentKinds();
				case 2:
					return new FragmentSearch();
				case 3:
					return new FragmentComment();
				}
				return null;
			}
		});
		vPager.setOnPageChangeListener(new OnPageChangeListener() {
			int one = offset * 2 + lineWidth;// 页卡1 -> 页卡2 偏移量

			@Override
			public void onPageSelected(int index)// 设置标题的颜色以及下划线的移动效果
			{
				Animation animation = new TranslateAnimation(one
						* nCurrentIndex, one * index, 0, 0);
				animation.setFillAfter(true);
				animation.setDuration(300);
				mImvCursor.startAnimation(animation);
				titles[nCurrentIndex].setTextColor(Color.BLACK);
				titles[index].setTextColor(0xFF17B4EC);

				switch (index) {
				case 0:
					mRlCommonTitle.setVisibility(0);
					mLlSearchTitle.setVisibility(8);
					break;
				case 1:
					mRlCommonTitle.setVisibility(0);
					mLlSearchTitle.setVisibility(8);
					break;
				case 2:
					mRlCommonTitle.setVisibility(8);
					mLlSearchTitle.setVisibility(0);
					break;
				case 3:
					mRlCommonTitle.setVisibility(0);
					mLlSearchTitle.setVisibility(8);
					break;
				case 4:
					mRlCommonTitle.setVisibility(0);
					mLlSearchTitle.setVisibility(8);
					break;
				}

				nCurrentIndex = index;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int index) {
			}
		});

	}

	private void initImageView() {
		mImvCursor = (ImageView) rootView.findViewById(R.id.cursor);
		// 获取图片宽度
		lineWidth = BitmapFactory.decodeResource(getResources(),
				R.drawable.line).getWidth();
		// // 获取屏幕宽度
		int screenWidth = MainActivity.dm.widthPixels;
		Matrix matrix = new Matrix();
		offset = (int) ((screenWidth / (float) TAB_COUNT - lineWidth) / 2);
		matrix.postTranslate(offset, 0);
		// 设置初始位置
		mImvCursor.setImageMatrix(matrix);
	}
}
