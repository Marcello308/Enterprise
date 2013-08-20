package com.enterprise.home;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ListView;

import com.enterprise.R;
import com.enterprise.model.Menu;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.ResponseStream;
import com.slidingmenu.lib.SlidingMenu;

@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment{

	private Context _context;
	private  SlidingMenu _menu ;
	public HomeFragment(Context _context, SlidingMenu menu) {
		super();
		this._context = _context;
		this._menu = menu;
	}
	private Gallery _gallery;
	private HomeGalleryAdapter _galleryAdapter;
	private HomeListAdapter _listAdapter;
	private ListView _listView;
	private ImageButton _btnTop;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.home_fragment, null);
			_gallery = (Gallery) view.findViewById(R.id.home_gallery);
			_galleryAdapter = new HomeGalleryAdapter(_context);
			_gallery.setAdapter(_galleryAdapter);
			
			_btnTop = (ImageButton) view.findViewById(R.id.home_top_btn);
			_btnTop.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					_menu.toggle();
				}
			});
			
			_listView = (ListView) view.findViewById(R.id.home_listview);
			_listAdapter = new HomeListAdapter(_context);
			_listView.setAdapter(_listAdapter);
			setListData();
			
			
		return view;
	}
	private void setListData() {
		
		final List<Menu> menuList = new ArrayList<Menu>();
		
		HttpUtils http = new HttpUtils();
        http.send(HttpRequest.HttpMethod.GET,
            "http://www.tmppq.com",
            new RequestCallBack<String>(){
                @Override
                public void onLoading(long total, long current) {
                } 

                @Override
                public void onSuccess(String result) {
                	Document doc = Jsoup.parse(result);
                	Elements es = doc.select("a.newslist_time");
    				for(Element e1 : es){
    					Menu menu = new Menu();
    					menu.name = e1.text();
    					menu.href = e1.attr("href");
    					menuList.add(menu);
    				}
    				_listAdapter.setData(menuList);
                }
                @Override
                public void onStart() {
                }
        });
	}
}
