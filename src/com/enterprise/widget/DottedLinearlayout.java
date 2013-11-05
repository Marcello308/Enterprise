package com.enterprise.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class DottedLinearlayout extends LinearLayout{

	public DottedLinearlayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public DottedLinearlayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public DottedLinearlayout(Context context) {
		super(context);
		init();
	}
	private void init() {
		this.setWillNotDraw(false);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		   Paint paint = new Paint();      
	        paint.setStyle(Paint.Style.STROKE);      
	        paint.setColor(Color.DKGRAY);      
	        Path path = new Path();           
	        path.moveTo(0, 0);      
	        path.lineTo(getWidth(),0);            
	        PathEffect effects = new DashPathEffect(new float[]{5,5,5,5},1);      
	        paint.setPathEffect(effects);      
	        canvas.drawPath(path, paint);      
	        
	        path.moveTo(0, getHeight()-1);      
	        path.lineTo(getWidth()-1,getHeight()-1);
	        canvas.drawPath(path, paint); 
	        
	        path.moveTo(getWidth()-1, 0);      
	        path.lineTo(getWidth()-1,getHeight()-1);
	        canvas.drawPath(path, paint); 
	        
	        path.moveTo(0, 0);      
	        path.lineTo(0,getHeight()-1);
	        canvas.drawPath(path, paint); 
	}
}
