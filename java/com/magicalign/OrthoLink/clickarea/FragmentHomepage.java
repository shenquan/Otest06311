package com.magicalign.OrthoLink.clickarea;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.magicalign.OrthoLink.CaseHistory.CaseHistory;
import com.magicalign.OrthoLink.exam.ExamType;
import com.vane.demo.R;

public class FragmentHomepage extends Fragment implements OnClickListener {

	private View mRootView;
	private Context mContext;
	private LinearLayout mLlBlog, mLlCooperation, mLlBaike, mLlRecruit,
			mLlRecruitStu, mLlDownload;
	private Toast mToast;
	private LinearLayout mLlTitle;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_homepage, container,
				false);
		return mRootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		mContext = this.getActivity();
		initView();
		setLinstener();
	}

	// 初始化界面
	private void initView() {
		mLlTitle = (LinearLayout) getActivity().findViewById(R.id.main_title);
		mLlTitle.setVisibility(0);

		mLlBlog = (LinearLayout) mRootView.findViewById(R.id.blog);
		mLlCooperation = (LinearLayout) mRootView
				.findViewById(R.id.cooperation);
		mLlBaike = (LinearLayout) mRootView.findViewById(R.id.baike);
		mLlRecruit = (LinearLayout) mRootView.findViewById(R.id.recruit);
		mLlRecruitStu = (LinearLayout) mRootView.findViewById(R.id.recruitstu);
		mLlDownload = (LinearLayout) mRootView.findViewById(R.id.download);

		mRootView.findViewById(R.id.circle_academic).setOnClickListener(this);
		mRootView.findViewById(R.id.circle_case_history).setOnClickListener(
				this);
		mRootView.findViewById(R.id.circle_cooperation)
				.setOnClickListener(this);
		mRootView.findViewById(R.id.circle_exam).setOnClickListener(this);

		mRootView.findViewById(R.id.circle_mall).setOnClickListener(this);
		mRootView.findViewById(R.id.circle_order).setOnClickListener(this);
		mRootView.findViewById(R.id.circle_expert).setOnClickListener(this);

	}

	// 设置监听器
	private void setLinstener() {
		mLlBlog.setOnClickListener(this);
		mLlCooperation.setOnClickListener(this);
		mLlBaike.setOnClickListener(this);
		mLlRecruit.setOnClickListener(this);
		mLlRecruitStu.setOnClickListener(this);
		mLlDownload.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.blog:
			Toast.makeText(mContext, "blog", 1).show();
			break;
		case R.id.cooperation:

			Toast.makeText(mContext, "cooperation", 1).show();
			break;
		case R.id.baike:

			Toast.makeText(mContext, "baike", 1).show();
			break;
		case R.id.recruit:

			Toast.makeText(mContext, "recruit", 1).show();
			break;
		case R.id.recruitstu:

			Toast.makeText(mContext, "recruitstu", 1).show();
			break;
		case R.id.download:

			Toast.makeText(mContext, "download", 1).show();
			break;

		case R.id.circle_academic:
			mToast = Toast.makeText(mContext, "circle_academic",
					Toast.LENGTH_SHORT);
			mToast.show();
			break;
		case R.id.circle_case_history:
			CaseHistory caseHistory = new CaseHistory();
			FragmentTransaction caseHistoryft = getFragmentManager()
					.beginTransaction();
			caseHistoryft.replace(R.id.frame_content, caseHistory);
			caseHistoryft.addToBackStack(null);
			caseHistoryft.commit();
			break;
		case R.id.circle_cooperation:
			mToast = Toast.makeText(mContext, "circle_cooperation",
					Toast.LENGTH_SHORT);
			mToast.show();
			break;
		case R.id.circle_exam:
			// startActivity(new Intent(getActivity(), ChooseExam.class));
			ExamType realTest = new ExamType();
			FragmentTransaction ft = getFragmentManager().beginTransaction();
			ft.replace(R.id.frame_content, realTest);
			ft.addToBackStack(null);
			ft.commit();

			break;
		case R.id.circle_expert:
			mToast = Toast.makeText(mContext, "circle_expert",
					Toast.LENGTH_SHORT);
			mToast.show();
			break;
		case R.id.circle_mall:
			mToast = Toast
					.makeText(mContext, "circle_mall", Toast.LENGTH_SHORT);
			mToast.show();
			break;
		case R.id.circle_order:
			mToast = Toast.makeText(mContext, "circle_order",
					Toast.LENGTH_SHORT);
			mToast.show();
			break;
		default:
			break;
		}
		// mToast.show();
	}
}
