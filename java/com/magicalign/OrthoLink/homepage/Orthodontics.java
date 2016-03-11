package com.magicalign.OrthoLink.homepage;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.magicalign.OrthoLink.CustomView.GifShowView;
import com.vane.demo.R;

public class Orthodontics extends Activity {

	private LinearLayout mLlBack;
	private LinearLayout mLlCaseOverview, mLlIntraoralFilm, mLlXrayFilm,
			mLlDesignAnimation;
	private LinearLayout mLlOrthodoticsContent, mLlAnimationContent;
	private TextView mTvCaseOverview, mTvIntraoralFilm, mTvDesignAnimation,
			mTvXrayFilm, mSkBarProgress;
	private SeekBar mSkBarGifPlay;
	private GifShowView mGsvToothAnimationSimulation;
	private ImageView mImvGifPlayImage, mImvGifPauseImage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orthodontics_case);

		initComponent();
	}

	private void initComponent() {
		mLlBack = (LinearLayout) findViewById(R.id.menu_arrow_back);
		mLlOrthodoticsContent = (LinearLayout) findViewById(R.id.layout_orthodotics_content);
		mLlAnimationContent = (LinearLayout) findViewById(R.id.layout_animation_content);
		mLlCaseOverview = (LinearLayout) findViewById(R.id.layout_case_overview);
		mLlIntraoralFilm = (LinearLayout) findViewById(R.id.layout_intraoral_film);
		mLlDesignAnimation = (LinearLayout) findViewById(R.id.layout_design_animation);
		mLlXrayFilm = (LinearLayout) findViewById(R.id.layout_xray_film);

		mSkBarProgress = (TextView) findViewById(R.id.seekbar_progress);

		mTvCaseOverview = (TextView) findViewById(R.id.layout_case_overview_txt);
		mTvIntraoralFilm = (TextView) findViewById(R.id.layout_intraoral_film_txt);
		mTvDesignAnimation = (TextView) findViewById(R.id.layout_design_animation_txt);
		mTvXrayFilm = (TextView) findViewById(R.id.layout_xray_film_txt);

		mSkBarGifPlay = (SeekBar) findViewById(R.id.gif_play_seekbar);

		mGsvToothAnimationSimulation = (GifShowView) findViewById(R.id.tooth_animation_simulation);

		mImvGifPlayImage = (ImageView) findViewById(R.id.gif_play_image);
		mImvGifPauseImage = (ImageView) findViewById(R.id.gif_pause_image);

		// 测试，初始化时先将方案动画的内容隐藏
		mLlAnimationContent.setVisibility(8);

		// 传递SeekBar对象
		mGsvToothAnimationSimulation.getSeekBar(mSkBarGifPlay);
		// 将播放和暂停按钮对象传递给GifShowView对象
		mGsvToothAnimationSimulation.getPlayAndPauseObj(mImvGifPlayImage,
				mImvGifPauseImage);

		mLlBack.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();

			}
		});

		mLlCaseOverview.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mLlOrthodoticsContent.setVisibility(0);
				mLlAnimationContent.setVisibility(8);
				mLlOrthodoticsContent.setBackgroundDrawable(getResources()
						.getDrawable(
								R.drawable.orthodotics_case_overview_content));
				mTvCaseOverview.setTextSize(14);
				mTvIntraoralFilm.setTextSize(12);
				mTvDesignAnimation.setTextSize(12);
				mTvXrayFilm.setTextSize(12);
			}
		});

		mLlIntraoralFilm.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mLlOrthodoticsContent.setVisibility(0);
				mLlAnimationContent.setVisibility(8);
				mLlOrthodoticsContent.setBackgroundDrawable(getResources()
						.getDrawable(
								R.drawable.orthodotics_intraoral_film_content));
				mTvCaseOverview.setTextSize(12);
				mTvIntraoralFilm.setTextSize(14);
				mTvDesignAnimation.setTextSize(12);
				mTvXrayFilm.setTextSize(12);
			}
		});

		mLlDesignAnimation.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// 做方案动画测试注释掉该语句
				/*
				 * mLlOrthodoticsContent .setBackgroundDrawable(getResources()
				 * .getDrawable(
				 * R.drawable.orthodotics_design_animation_content));
				 */
				mLlAnimationContent.setVisibility(0);
				mLlOrthodoticsContent.setVisibility(8);

				mTvCaseOverview.setTextSize(12);
				mTvIntraoralFilm.setTextSize(12);
				mTvDesignAnimation.setTextSize(14);
				mTvXrayFilm.setTextSize(12);
			}
		});

		mLlXrayFilm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mLlOrthodoticsContent.setVisibility(0);
				mLlAnimationContent.setVisibility(8);
				mLlOrthodoticsContent.setBackgroundDrawable(getResources()
						.getDrawable(R.drawable.orthodotics_xray_film_content));
				mTvCaseOverview.setTextSize(12);
				mTvIntraoralFilm.setTextSize(12);
				mTvDesignAnimation.setTextSize(12);
				mTvXrayFilm.setTextSize(14);
			}
		});

		// 为SeekBar添加滑动事件
		mSkBarGifPlay.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				mGsvToothAnimationSimulation.setGifPlayProgress(progress);
				// Toast.makeText(getBaseContext(), Integer.toString(progress),
				// Toast.LENGTH_SHORT).show();
				int skBarProgress = (int) (progress * 1.0 / 255 * 100);
				mSkBarProgress.setText(Integer.toString(skBarProgress) + "%");

			}
		});

		// 为播放按钮添加监听器
		mImvGifPlayImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				mImvGifPlayImage.setVisibility(8);
				mImvGifPauseImage.setVisibility(0);
				mGsvToothAnimationSimulation.palyGif();

			}
		});

		// 为暂停按钮添加监听器
		mImvGifPauseImage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mImvGifPauseImage.setVisibility(8);
				mImvGifPlayImage.setVisibility(0);
				mGsvToothAnimationSimulation.pauseGif();
				// Toast.makeText(getBaseContext(), "clickPause",
				// Toast.LENGTH_SHORT).show();

			}
		});

	}

}