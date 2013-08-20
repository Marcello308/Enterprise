package com.enterprise.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enterprise.R;
import com.enterprise.model.Menu;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class HomeListItem extends RelativeLayout{

	private Context _context;
	public HomeListItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		_context = context;
		init();
	}

	public HomeListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		_context = context;
		init();
	}

	public HomeListItem(Context context) {
		super(context);
		_context = context;
		init();
	}

	@ViewInject(id = R.id.home_list_text)TextView _tvName;
	private void init() {
			View view = LayoutInflater.from(_context).inflate(R.layout.home_list_item, null);
			addView(view);
			ViewUtils.inject(this);
	}

	public void setData(Menu menu){
		_tvName.setText(menu.name);
	}
}
