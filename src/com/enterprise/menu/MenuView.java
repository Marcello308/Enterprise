package com.enterprise.menu;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.enterprise.R;
import com.enterprise.model.Menu;

public class MenuView extends RelativeLayout{

	private Context _context;
	public MenuView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this._context = context;
		init();
	}

	public MenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		this._context = context;
		init();
	}

	public MenuView(Context context) {
		super(context);
		this._context = context;
		init();	
	}
	private MenuAdapter _adapter;
	private Menu _selectMenu;
	private ListView _list;
	private void init(){
		View view =LayoutInflater.from(_context).inflate(R.layout.menu_main, null);
		addView(view);
		_list = (ListView) view.findViewById(R.id.menu_list);
		_adapter = new MenuAdapter(_context);
		_list.setAdapter(_adapter);
		
		
		
		
		_list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				if(position!=0){
					View view0 = (View) parent.getChildAt(0); 
					view0.setBackgroundColor(Color.TRANSPARENT);
				}
				//选中高亮
				  if (((ListView)parent).getTag() != null){
					  ((View)((ListView)parent).getTag()).setBackgroundColor(Color.TRANSPARENT);
              }
              ((ListView)parent).setTag(view);
              view.setBackgroundResource(R.drawable.menu_item_bg);
              
              
              
              
//              view.setBackgroundColor(Color.GRAY);
//              view.setBackgroundColor(Color.parseColor("#422C2B"))view;
			}
		});
	}
	public void setData(List<Menu> list){
		_adapter.setData(list);
	}
	
}
