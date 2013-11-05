package com.enterprise.module;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import com.enterprise.R;
import com.enterprise.base.BaseActivity;
import com.enterprise.constants.APIConstants;
import com.enterprise.model.ArticleDetail;
import com.enterprise.model.Menu;
import com.enterprise.services.EnterpriseServices;
import com.enterprise.utils.http.LTHttpError;
import com.enterprise.utils.http.LTHttpRequestMessage;
import com.enterprise.utils.http.LTHttpType.RequestType;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class  ArticleDetailActivity extends BaseActivity{

	private  Context _context;
	private Menu _menu;
	
	@ViewInject(R.id.ad_title)
	TextView tv_title;
	
	@ViewInject(R.id.ad_info)
	TextView tv_info;
	
	@ViewInject(R.id.ad_detail)
	TextView tv_detail;
	
	@ViewInject(R.id.ad_top_btn)
	ImageButton ib_back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.module_article_detail);
		
		ViewUtils.inject(this);		
		
		Intent intent = getIntent();
		if(intent != null){
			Bundle bundle = intent.getBundleExtra("data");
			_menu =  (Menu) bundle.getSerializable("data");
		}
		
	        LTHttpRequestMessage message = new LTHttpRequestMessage(RequestType.ARTICLE_DETAIL, null, null, _handler, HTTP_RESPONSE_ARTICLE, EnterpriseServices.getInstance());
	        Map<String, Object> map = new HashMap<String, Object>();
	        map.put(APIConstants.PARAM_ARTICLE_DETAIL_URL, _menu.href);
	        message.setOtherParmas(map);
	        loadDataWithMessage("正在加载文章信息.....", message);
	}
	
	@OnClick(R.id.ad_top_btn)
	public void backClick(View v) {
		 onBackPressed();
	}
	private static final int HTTP_RESPONSE_ARTICLE =0;
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
			case HTTP_RESPONSE_ARTICLE:
				ArticleDetail detail = (ArticleDetail) msg.obj;
				tv_title.setText( detail.title);
				tv_info.setText( detail.info);
				tv_detail.setText( detail.articleDetail);
				break;
			default:
				break;
			}
		}
	};
}
