package com.magicalign.OrthoLink.exam;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.vane.demo.R;

public class ChooseExam extends Activity {

	private EditText mEtSearch;
	private Button mBtnSearch;
	private ExamTypeAdapter adapter;
	private ListView mLvExamType;
	private List<String> list;
	private LayoutInflater inflater;
	private DataAdapter dataAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_exam);

		initView();
		setListener();
		getData();
		dataAdapter = new DataAdapter();
		mLvExamType.setAdapter(dataAdapter);

		mLvExamType.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(ChooseExam.this, list.get(position),
						Toast.LENGTH_SHORT).show();
				Intent i = new Intent(ChooseExam.this, ExamDetial.class);
				i.putExtra("type", list.get(position));
				startActivity(i);

			}
		});

	}

	private void setListener() {
		mBtnSearch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void initView() {
		mEtSearch = (EditText) findViewById(R.id.exam_search);
		mBtnSearch = (Button) findViewById(R.id.exam_search_btn);
		mLvExamType = (ListView) findViewById(R.id.exam_type);

	}

	private List<String> getData() {
		list = new ArrayList<String>();
		list.add("口腔执业医师考试");
		list.add("国家执业医师资格考试");
		list.add("卫生专业技术资格考试");
		list.add("医学研究生入学考试(专业基础和综合课程)");
		list.add("全国医学博士外语统一考试");
		list.add("临床助理医师从业考试");
		list.add("中医执业医师实践技能专业考试");
		list.add("国际眼科医师基础知识和临床知识考试");
		list.add("全国采供血机构人员岗位培训考核");
		list.add("初级护士卫生资格复试");
		return list;
	}

	public class DataAdapter extends BaseAdapter {

		public DataAdapter() {
			super();
			inflater = LayoutInflater.from(ChooseExam.this);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
			if (view == null) {
				view = inflater.inflate(R.layout.choose_exam_items, parent,
						false);
				ViewHolder viewHolder = new ViewHolder();
				viewHolder.content = (TextView) view
						.findViewById(R.id.choose_exam_name);
				view.setTag(viewHolder);
			}
			ViewHolder viewHolder = (ViewHolder) view.getTag();
			viewHolder.content.setText(list.get(position));
			return view;
		}

	}

	private class ViewHolder {
		private TextView content;
	}
}
