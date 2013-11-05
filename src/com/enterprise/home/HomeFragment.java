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
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ListView;

import com.enterprise.R;
import com.enterprise.base.BaseFragment;
import com.enterprise.model.Menu;
import com.enterprise.services.EnterpriseServices;
import com.enterprise.utils.http.LTHttpError;
import com.enterprise.utils.http.LTHttpRequestMessage;
import com.enterprise.utils.http.LTHttpType.RequestType;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.ResponseStream;
import com.slidingmenu.lib.SlidingMenu;

@SuppressLint("ValidFragment")
public class HomeFragment extends BaseFragment{

	private Context _context;
	private  SlidingMenu _menu ;
	public HomeFragment(Context _context, SlidingMenu menu) {
		super(_context);
		this._context = _context;
		this._menu = menu;
	}
	public HomeFragment(Context _context) {
		super(_context);
	}
	public HomeFragment() {
		super();
	}
	private HomeListAdapter _listAdapter;
	private ListView _listView;
	private ImageButton _btnTop;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.home_fragment, null);

			_btnTop = (ImageButton) view.findViewById(R.id.home_top_btn);
			_btnTop.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					_menu.toggle();
				}
			});
			
			_listView = (ListView) view.findViewById(R.id.home_listview);
			_listAdapter = new HomeListAdapter(_context);
			_listView.setAdapter(_listAdapter);
			_listView.setOnItemClickListener(_event);
			
			
	        final List<Menu> menuList = new ArrayList<Menu>();
	        LTHttpRequestMessage message = new LTHttpRequestMessage(RequestType.HOME_ARTICLE, 	null, null, _handler, HTTP_RESPONSE_HOME_ARTICLE, EnterpriseServices.getInstance());
	        loadDataWithMessage("正在加载首页动态.....", message);
	        
		return view;
	}
	private OnItemClickListener _event;
	public void setListViewOnItemClick(OnItemClickListener event){
		_event = event;
	}
	
	private static final int HTTP_RESPONSE_HOME_ARTICLE=0;
	private Handler _handler  = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			dismissProgressDialog();
			if (msg == null || msg.obj == null) {
				showMessage(R.string.loading_data_failed);
				return;
			}
			if (msg.obj instanceof LTHttpError) {
				LTHttpError error = (LTHttpError) msg.obj;
				showMessage(error.errorMessage);
				return;
			}
			switch (msg.what) {
			case HTTP_RESPONSE_HOME_ARTICLE:
				List<Menu> menuList = (List<Menu>) msg.obj;
				_listAdapter.setData(menuList);
				break;
			default:
				break;
			}
		}
	};
}
