package com.enterprise;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.enterprise.home.HomeFragment;
import com.enterprise.menu.MenuView;
import com.enterprise.model.Menu;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.slidingmenu.lib.SlidingMenu;

public class HomeActivity extends Activity {

	private RelativeLayout _root;
	private  SlidingMenu menu ;
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
	        
	        final MenuView menuView = new MenuView(this);
	        menu.setMenu(menuView);
	        
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
	        });
	        
	}
}