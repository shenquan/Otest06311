package com.magicalign.OrthoLink.CaseHistory;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.vane.demo.R;

public class WonderfulCase extends Activity {

	private LinearLayout mLlBack;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_case_history_wonderful_case);

		initComponent();
	}
	
	private void initComponent(){
		mLlBack = (LinearLayout)findViewById(R.id.wonderfulcase_menu_arrow_back);

		mLlBack.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				
			}
		});
		
	}

}