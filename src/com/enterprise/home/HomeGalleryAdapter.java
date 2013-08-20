package com.enterprise.home;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class HomeGalleryAdapter extends BaseAdapter{

	private Context _context;
    private String[] datas ={"http://www.tmppq.com/advs/pics/20100723/1279871873.jpg",
    		"http://www.tmppq.com/advs/pics/20100723/1279871876.jpg",
    		"http://www.tmppq.com/advs/pics/20100723/1279871879.jpg"};
	public HomeGalleryAdapter(Context _context) {
		super();
		this._context = _context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {

		HomeGalleryItem item =null;
		if(view==null){
			item = new HomeGalleryItem(_context);
			view = item;
		}else{
			item = (HomeGalleryItem) view;
		}
		item.setData(datas[position],position);
		return item;
	}
}
