package com.enterprise.home;

import java.util.ArrayList;
import java.util.List;

import com.enterprise.model.Menu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class HomeListAdapter extends BaseAdapter{

	private Context _context;
	private List<Menu> _lists;
	public HomeListAdapter(Context _context) {
		super();
		this._context = _context;
		_lists = new ArrayList<Menu>();
	}

	public void setData(List<Menu> lists){
		this._lists = lists;
		this.notifyDataSetChanged();
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _lists.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return _lists.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		HomeListItem item =null;
		if(view==null){
			item = new HomeListItem(_context);
			view = item;
		}else{
			item = (HomeListItem) view;
		}
		item.setData(_lists.get(position));
		return item;
	}
}
