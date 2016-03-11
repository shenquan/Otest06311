package com.magicalign.OrthoLink.clickarea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.magicalign.OrthoLink.friend.Friend;
import com.magicalign.OrthoLink.homepage.FragmentPersonalHomepage;
import com.vane.demo.R;

public class MainActivity extends FragmentActivity implements OnClickListener {
	// 定义Fragment页面
	private FragmentFriend fg_Friend;
	private FragmentHomepage fg_Find;
	private FragmentOrder fg_Order;
	private FragmentHeadline fg_HeadLine;
	private FragmentFind fg_CaseHistory;
	private FragmentPersonalHomepage mFgPersonalHomepage;

	// 定义布局对象
	private LinearLayout CaseHistoryFl, OrderFl, HeadLineFl;
	private LinearLayout FriendFl;
	private LinearLayout mLlAvatar;

	// 定义图片组件对象
	private ImageView FriendIv, CaseHistoryIv, OrderIv, HeadLineIv;

	// 定义按钮图片组件
	private ImageView toggleImageView;
	private TextView HostPage;

	// 定义PopupWindow
	private PopupWindow popWindow;
	// 获取手机屏幕分辨率的类
	public static DisplayMetrics dm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_menu);

		initView();

		initData();

		// 初始化默认为选中点击了“动态”按钮
		clickToggleBtn();
		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
	}

	/**
	 * 初始化组件
	 */
	private void initView() {
		// 实例化布局对象
		FriendFl = (LinearLayout) findViewById(R.id.layout_friend);
		OrderFl = (LinearLayout) findViewById(R.id.layout_order);
		HeadLineFl = (LinearLayout) findViewById(R.id.layout_headline);
		CaseHistoryFl = (LinearLayout) findViewById(R.id.layout_case_history);
		mLlAvatar = (LinearLayout) findViewById(R.id.layout_avatar);

		// 实例化图片组件对象
		FriendIv = (ImageView) findViewById(R.id.image_friend);
		CaseHistoryIv = (ImageView) findViewById(R.id.image_case_history);
		OrderIv = (ImageView) findViewById(R.id.image_order);
		HeadLineIv = (ImageView) findViewById(R.id.image_headline);

		// 实例化按钮图片组件
		toggleImageView = (ImageView) findViewById(R.id.toggle_btn);
		HostPage = (TextView) findViewById(R.id.hostpage_btn);

	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// 给布局对象设置监听
		FriendFl.setOnClickListener(this);
		CaseHistoryFl.setOnClickListener(this);
		OrderFl.setOnClickListener(this);
		HeadLineFl.setOnClickListener(this);

		// 给按钮图片设置监听
		toggleImageView.setOnClickListener(this);
		mLlAvatar.setOnClickListener(this);

	}

	/**
	 * 点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 点击朋友按钮
		case R.id.layout_friend:
			clickAtBtn();
			break;
		// 病历（发现）
		case R.id.layout_case_history:
			clickCaseHistory();
			break;
		// 预约
		case R.id.layout_order:
			clickOrderBtn();
			break;
		// 点击头条按钮
		case R.id.layout_headline:
			clickHeadLineBtn();
			break;
		// 点击中间主按钮
		case R.id.toggle_btn:
			clickToggleBtn();
			break;
		// 点击头像按钮
		case R.id.layout_avatar:
			clickAvatarBtn();
			break;

		}
	}

	// 点击头像
	private void clickAvatarBtn() {
		// 清除后台栈
		clearBackStack();
		// 实例化Fragment页面
		mFgPersonalHomepage = new FragmentPersonalHomepage();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_content, mFgPersonalHomepage);
		// 事务管理提交
		fragmentTransaction.commit();
	}

	// 点击朋友按钮 by HSQ
	private void clickAtBtn() {
		// 清除后台栈
		clearBackStack();
		startActivity(new Intent(MainActivity.this, Friend.class));
		// // 实例化Fragment页面
		// fg_Friend = new FragmentFriend();
		// // 得到Fragment事务管理器
		// FragmentTransaction fragmentTransaction = this
		// .getSupportFragmentManager().beginTransaction();
		// // 替换当前的页面
		// fragmentTransaction.replace(R.id.frame_content, fg_Friend);
		// // 事务管理提交
		// fragmentTransaction.commit();
		// // 改变选中状态
		// FriendFl.setSelected(true);
		// FriendIv.setSelected(true);
		//
		// CaseHistoryFl.setSelected(false);
		// CaseHistoryIv.setSelected(false);
		//
		// OrderFl.setSelected(false);
		// OrderIv.setSelected(false);
		//
		// HeadLineFl.setSelected(false);
		// HeadLineIv.setSelected(false);
		// HostPage.setSelected(false);
	}

	/**
	 * 点击了“病例（发现）”按钮
	 */
	private void clickCaseHistory() {
		// 清除后台栈
		clearBackStack();
		// 实例化Fragment页面
		fg_CaseHistory = new FragmentFind();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_content, fg_CaseHistory);
		// 事务管理提交
		fragmentTransaction.commit();
		// 朋友
		FriendFl.setSelected(false);
		FriendIv.setSelected(false);
		// 病例
		CaseHistoryFl.setSelected(true);
		CaseHistoryIv.setSelected(true);
		// 预约
		OrderFl.setSelected(false);
		OrderIv.setSelected(false);
		// 头条
		HeadLineFl.setSelected(false);
		HeadLineIv.setSelected(false);
		// “主页”2个字
		HostPage.setSelected(false);
	}

	/**
	 * 点击了“预约”按钮
	 */
	private void clickOrderBtn() {
		// 清除后台栈
		clearBackStack();

		// 实例化Fragment页面
		fg_Order = new FragmentOrder();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_content, fg_Order);
		// 事务管理提交
		fragmentTransaction.commit();

		FriendFl.setSelected(false);
		FriendIv.setSelected(false);

		CaseHistoryFl.setSelected(false);
		CaseHistoryIv.setSelected(false);

		OrderFl.setSelected(true);
		OrderIv.setSelected(true);

		HeadLineFl.setSelected(false);
		HeadLineIv.setSelected(false);
		HostPage.setSelected(false);
	}

	/**
	 * 点击了“头条”按钮 论坛 by HSQ
	 */
	private void clickHeadLineBtn() {
		// 清除后台栈
		clearBackStack();
		// 实例化Fragment页面
		fg_HeadLine = new FragmentHeadline();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_content, fg_HeadLine);
		// 事务管理提交
		fragmentTransaction.commit();

		FriendFl.setSelected(false);
		FriendIv.setSelected(false);

		CaseHistoryFl.setSelected(false);
		CaseHistoryIv.setSelected(false);

		OrderFl.setSelected(false);
		OrderIv.setSelected(false);

		HeadLineFl.setSelected(true);
		HeadLineIv.setSelected(true);
		HostPage.setSelected(false);
	}

	/**
	 * 点击主页
	 */
	private void clickToggleBtn() {
		// 清除后台栈
		clearBackStack();
		// showPopupWindow(toggleImageView);
		// 改变按钮显示的图片为按下时的状态
		fg_Find = new FragmentHomepage();
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.frame_content, fg_Find);
		// 事务管理提交
		fragmentTransaction.commit();
		FriendFl.setSelected(false);
		FriendIv.setSelected(false);

		CaseHistoryFl.setSelected(false);
		CaseHistoryIv.setSelected(false);

		OrderFl.setSelected(false);
		OrderIv.setSelected(false);

		HeadLineFl.setSelected(false);
		HeadLineIv.setSelected(false);

		HostPage.setSelected(true);

	}

	/**
	 * 改变显示的按钮图片为正常状态
	 */
	private void changeButtonImage() {
		HostPage.setSelected(false);
	}

	/**
	 * 显示PopupWindow弹出菜单
	 */
	// private void showPopupWindow(View parent) {
	// if (popWindow == null) {
	// LayoutInflater layoutInflater = (LayoutInflater)
	// getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	//
	// View view = layoutInflater.inflate(R.layout.popwindow_layout, null);
	// dm = new DisplayMetrics();
	// getWindowManager().getDefaultDisplay().getMetrics(dm);
	// // 创建一个PopuWidow对象
	// popWindow = new PopupWindow(view, dm.widthPixels,
	// LinearLayout.LayoutParams.WRAP_CONTENT);
	// }
	// // 使其聚集 ，要想监听菜单里控件的事件就必须要调用此方法
	// popWindow.setFocusable(true);
	// // 设置允许在外点击消失
	// popWindow.setOutsideTouchable(true);
	// // 设置背景，这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
	// popWindow.setBackgroundDrawable(new BitmapDrawable());
	// // PopupWindow的显示及位置设置
	// // popWindow.showAtLocation(parent, Gravity.FILL, 0, 0);
	// popWindow.showAsDropDown(parent, 0,0);
	//
	// popWindow.setOnDismissListener(new OnDismissListener() {
	// @Override
	// public void onDismiss() {
	// // 改变显示的按钮图片为正常状态
	// changeButtonImage();
	// }
	// });
	//
	// // 监听触屏事件
	// popWindow.setTouchInterceptor(new OnTouchListener() {
	// public boolean onTouch(View view, MotionEvent event) {
	// // 改变显示的按钮图片为正常状态
	// changeButtonImage();
	// popWindow.dismiss();
	// return false;
	// }
	// });
	// }
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
			super.onBackPressed();
		} else {
			getSupportFragmentManager().popBackStack();
		}
	}

	// 清除后台栈
	private void clearBackStack() {
		if (getSupportFragmentManager().getFragments() != null
				&& getSupportFragmentManager().getBackStackEntryCount() > 0) {
			getSupportFragmentManager().popBackStackImmediate(null,
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
	}

}
