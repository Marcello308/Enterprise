package com.enterprise.services;

import com.enterprise.base.HttpBaseService;

public class EnterpriseServices extends HttpBaseService{
		public static EnterpriseServices INSTANCE  = new EnterpriseServices();
		public static EnterpriseServices getInstance(){
			return INSTANCE;
		}
		
		
		
}
