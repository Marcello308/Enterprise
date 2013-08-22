package com.enterprise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.widget.RelativeLayout;

import com.enterprise.base.BaseActivity;
import com.enterprise.home.HomeFragment;
import com.enterprise.menu.MenuView;
import com.enterprise.model.Menu;
import com.enterprise.services.EnterpriseServices;
import com.enterprise.utils.http.LTHttpError;
import com.enterprise.utils.http.LTHttpRequestMessage;
import com.enterprise.utils.http.LTHttpType.RequestType;
import com.slidingmenu.lib.SlidingMenu;

public class HomeActivity extends BaseActivity {

	private RelativeLayout _root;
	private SlidingMenu menu ;
	private  MenuView menuView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 requestWindowFeature(Window.FEATURE_NO_TITLE);           //设置标题栏样式
		setContentView(R.layout.home_main);
		
		initMenu();
	
		HomeFragment fragment = new HomeFragment(this,menu);
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.home_layout, fragment);
		transaction.commit();
	}

	/**
	 *  初始化菜单
	 */
	private void initMenu() {
		    menu = new SlidingMenu(this);
	        menu.setMode(SlidingMenu.LEFT);
	        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
	        menu.setShadowWidthRes(R.dimen.shadow_width);
	        menu.setShadowDrawable(R.drawable.shadow);
	        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
	        menu.setFadeDegree(0.35f);
	        menu.setBehindWidth(350);
	        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
	        
	        menuView = new MenuView(this);
	        menu.setMenu(menuView);
	        
	        
	        
	       final List<Menu> menuList = new ArrayList<Menu>();
	        LTHttpRequestMessage message = new LTHttpRequestMessage(RequestType.MENU, 	null, null, _handler, HTTP_RESPONSE_MENU, EnterpriseServices.getInstance());
	        loadDataWithMessage("正在加载首页栏目.....", message);
	     
	       /* http.send(HttpRequest.HttpMethod.GET,
	            "http://www.tmppq.com",
	            new RequestCallBack<String>(){
	                @Override
	                public void onLoading(long total, long current) {
	                } 

	                @Override
	                public void onSuccess(String result) {
	                	Document doc = Jsoup.parse(result);
	                	Element content = doc.select(".mainmenuiner").first();
	                	Elements ea = content.getElementsByTag("a");
	    				for(Element e1 : ea){
	    					Menu menu = new Menu();
	    					menu.name = e1.text();
	    					menu.href = e1.attr("href");
	    					menuList.add(menu);
	    				}
	    				
	    				menuView.setData(menuList);
	                }

	                @Override
	                public void onStart() {
	                }
	        });*/
	        
	}
	private static final int HTTP_RESPONSE_MENU =0;
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
			case HTTP_RESPONSE_MENU:
				List<Menu> menuList = (List<Menu>) msg.obj;
				menuView.setData(menuList);
				break;
			default:
				break;
			}
		}
	};
}
