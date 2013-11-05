package com.enterprise.module;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import com.enterprise.R;
import com.enterprise.base.BaseActivity;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class WaterfallActivity extends BaseActivity{

	@ViewInject(R.id.waterfall_top_btn)
	ImageButton ib_back;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.module_waterfall);
		
		ViewUtils.inject(this);	
		
	}
	
	@OnClick(R.id.waterfall_top_btn)
	public void backClick(View v) {
		 onBackPressed();
	}
}
