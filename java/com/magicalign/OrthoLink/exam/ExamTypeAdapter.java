package com.magicalign.OrthoLink.exam;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vane.demo.R;
public class ExamTypeAdapter extends BaseAdapter {

	private ArrayList<String> datas;
	private ChooseExam mContext;

	private LayoutInflater inflater;

	public ExamTypeAdapter(ChooseExam context, ArrayList<String> datas) {
		// TODO Auto-generated constructor stub
		super();
		this.mContext = context;
		this.datas = datas;

		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		final ViewHolder viewHolder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.choose_exam_items, null);
			viewHolder = new ViewHolder();
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.ExamName.setText(datas.get(position));
		return convertView;
	}

	class ViewHolder {
		private TextView ExamName;
	}

}
