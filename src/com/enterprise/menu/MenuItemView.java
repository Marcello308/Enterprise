package com.enterprise.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.enterprise.R;
import com.enterprise.model.Menu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MenuItemView extends RelativeLayout{

	private Context _context;
	public MenuItemView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this._context = context;
		init();
	}

	public MenuItemView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this._context = context;
		init();
	}

	public MenuItemView(Context context) {
		super(context);
		this._context = context;
		init();
	}
	@ViewInject(R.id.menu_item_tvTitle)TextView _tvTitle;
	private void init(){
		View view = LayoutInflater.from(_context).inflate(R.layout.menu_item, null);
		addView(view);
		ViewUtils.inject(this);
	}

	public void setData(Menu item) {
		_tvTitle.setText(item.name);
	}
}
