package com.magicalign.OrthoLink.friend;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.vane.demo.R;

public class Friend extends FragmentActivity implements OnClickListener {
	private FragmentChat mFgChat;
	private FragmentContact mFgContact;
	private FragmentDynamic mFgDynamic;
	private FragmentMy mFgMy;
	// 布局对象
	private LinearLayout mFlChat, mFlContact, mFlDynamic, mFlMy;

	// 图片对象
	private ImageView mIvChat, mIvContact, mIvDymanic, mIvMy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_friend);
		initView();
		setListener();
		// 默认点击了聊天按钮
		clickContactBtn();
	}

	private void initView() {
		mFlChat = (LinearLayout) findViewById(R.id.layout_friend_chat);
		mFlContact = (LinearLayout) findViewById(R.id.layout_friend_contact);
		mFlDynamic = (LinearLayout) findViewById(R.id.layout_friend_dynamic);
		mFlMy = (LinearLayout) findViewById(R.id.layout_friend_my);

		mIvChat = (ImageView) findViewById(R.id.image_friend);
		mIvContact = (ImageView) findViewById(R.id.image_find);
		mIvDymanic = (ImageView) findViewById(R.id.image_order);
		mIvMy = (ImageView) findViewById(R.id.image_headline);

	}

	private void setListener() {
		mFlChat.setOnClickListener(this);
		mFlContact.setOnClickListener(this);
		mFlDynamic.setOnClickListener(this);
		mFlMy.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		// 点击聊天
		case R.id.layout_friend_chat:
			clickChatBtn();
			break;
		// 点击通讯录
		case R.id.layout_friend_contact:
			clickContactBtn();
			break;
		// 点击动态
		case R.id.layout_friend_dynamic:
			clickDynamicBtn();
			break;
		// 点击我的
		case R.id.layout_friend_my:
			clickMyBtn();
			break;

		default:
			break;
		}
	}

	private void clickMyBtn() {
		// TODO Auto-generated method stub
		mFgMy = new FragmentMy();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.friend_frame_content, mFgMy);
		// 事务管理提交
		fragmentTransaction.commit();
	}

	private void clickDynamicBtn() {
		// TODO Auto-generated method stub
		mFgDynamic = new FragmentDynamic();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.friend_frame_content, mFgDynamic);
		// 事务管理提交
		fragmentTransaction.commit();
	}

	private void clickContactBtn() {
		// TODO Auto-generated method stub
		mFgContact = new FragmentContact();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.friend_frame_content, mFgContact);
		// 事务管理提交
		fragmentTransaction.commit();

	}

	private void clickChatBtn() {

		mFgChat = new FragmentChat();
		// 得到Fragment事务管理器
		FragmentTransaction fragmentTransaction = this
				.getSupportFragmentManager().beginTransaction();
		// 替换当前的页面
		fragmentTransaction.replace(R.id.friend_frame_content, mFgChat);
		// 事务管理提交
		fragmentTransaction.commit();

	}
}
