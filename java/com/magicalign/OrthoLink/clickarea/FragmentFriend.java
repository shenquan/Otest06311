package com.magicalign.OrthoLink.clickarea;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magicalign.OrthoLink.friend.Friend;
import com.vane.demo.R;

public class FragmentFriend extends Fragment {
	private View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		startActivity(new Intent(getActivity(), Friend.class));
		getActivity().setVisible(false);
		return inflater.inflate(R.layout.fragment_friend, null);
	}

}
