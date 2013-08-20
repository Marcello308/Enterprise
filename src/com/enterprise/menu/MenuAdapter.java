package com.enterprise.menu;

import java.util.ArrayList;
import java.util.List;

import com.enterprise.model.Menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MenuAdapter extends BaseAdapter{

	private Context _context;
	private List<Menu> _list;
	public MenuAdapter(Context _context) {
		super();
		this._context = _context;
		_list = new ArrayList<Menu>();
	}

	public void setData(List<Menu> list){
		_list = list;
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return _list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View view, ViewGroup arg2) {
		MenuItemView item = null;
		if(view==null){
			item = new MenuItemView(_context);
			view = item;
		}else{
			item = (MenuItemView) view;
		}
		Menu menu = _list.get(arg0);
		item.setData(menu);
		return item;
	}
		
}
