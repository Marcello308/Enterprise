package com.enterprise.base;

import android.app.Application;

public class BaseApplication extends Application{

	private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }
    
}
