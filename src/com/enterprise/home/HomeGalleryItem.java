package com.enterprise.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.enterprise.R;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class HomeGalleryItem extends RelativeLayout{

	private Context _context;
	public HomeGalleryItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		_context = context;
		init();
	}

	public HomeGalleryItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		_context = context;
		init();
	}

	public HomeGalleryItem(Context context) {
		super(context);
		_context = context;
		init();
	}

	@ViewInject(R.id.home_gallery_iv)ImageView _ivCarousel;
	@ViewInject(R.id.home_gallery_pointImage)ImageView _ivPoint;
	private void init() {
			View view = LayoutInflater.from(_context).inflate(R.layout.home_gallery_item, null);
			addView(view);
			ViewUtils.inject(this);
	}

	public void setData(String url,int position){
		BitmapUtils.create(_context).display(_ivCarousel, url);
		int resid=0;
		switch (position){
			case 0:
				resid=R.drawable.focus_point_1;
				break;
			case 1:
				resid=R.drawable.focus_point_2;
				break;
			case 2:
				resid=R.drawable.focus_point_3;
				break;
			case 3:
				resid=R.drawable.focus_point_4;
				break;
			case 4:
				resid=R.drawable.focus_point_5;
				break;
		}
		_ivPoint.setBackgroundResource(resid);
	}
}
