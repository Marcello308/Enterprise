package com.enterprise.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.enterprise.R;
import com.enterprise.base.HttpBaseService;
import com.enterprise.constants.APIConstants;
import com.enterprise.model.ArticleDetail;
import com.enterprise.model.Menu;
import com.enterprise.utils.LTToolUtil;
import com.enterprise.utils.exception.LTDBException;
import com.enterprise.utils.exception.LTHttpException;
import com.enterprise.utils.exception.LTParserException;
import com.enterprise.utils.http.IWebService;
import com.enterprise.utils.http.LTHttpRequestMessage;
import com.enterprise.utils.http.LTHttpUrl;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class EnterpriseServices extends HttpBaseService implements IWebService{
	
	 public String home_cache = null;
	
		public static EnterpriseServices INSTANCE  = new EnterpriseServices();
		public static EnterpriseServices getInstance(){
			return INSTANCE;
		}
		@Override
		public Object httpPost(LTHttpRequestMessage httpMessage)
				throws HttpException, IOException {
			
			switch (httpMessage.getHttpType()) {
			case MENU:{
				String url = LTHttpUrl.getUrl(httpMessage.getHttpType(),
						httpMessage.getUrlParamList());
				return getMenu(url);}
			case HOME_ARTICLE:{
				String url = LTHttpUrl.getUrl(httpMessage.getHttpType(),
						httpMessage.getUrlParamList());
				return getHomeArticle(url);}
			case ARTICLE_DETAIL:{
				String url = APIConstants.API_DEFAULT_HOST+httpMessage.getOtherParmas(APIConstants.PARAM_ARTICLE_DETAIL_URL);
				return getArticleDetail(url);
			}
			case WATERFALL:{
				
			}
			default:
				break;
			}
			return null;
		}
		
		/**
		 *   获取文章详细
		 * @param url
		 * @return
		 * @throws IOException 
		 * @throws HttpException 
		 */
		private ArticleDetail getArticleDetail(String url) throws HttpException, IOException {
			String ret = post(url, null);
			if (!LTToolUtil.isNull(ret)) {
			
			Document doc = Jsoup.parse(ret);
			String title = doc.select("div.newstitle").first().text();
			String info = doc.select("div.info").first().text();
			String con = doc.select("div.con").first().text();
			ArticleDetail article= new  ArticleDetail(title, info, con);
		    return article;
			}else{
					throw new HttpException(LTToolUtil
							.getResourceString(R.string.http_response_null));
			}
		}
		/**
		 *获取首页文章列表
		 * @param url
		 * @return
		 * @throws HttpException
		 * @throws IOException
		 */
		private List<Menu> getHomeArticle(String url) throws HttpException, IOException {
			String ret = "";
			if(home_cache==null){
				 ret = post(url, null);
				 home_cache = ret;
			}else{
				ret = home_cache;
			}
			if (!LTToolUtil.isNull(ret)) {
				final List<Menu> menuList = new ArrayList<Menu>();
				Document doc = Jsoup.parse(ret);
            	Elements es = doc.select("a.newslist_time");
				for(Element e1 : es){
					Menu menu = new Menu();
					menu.name = e1.text();
					menu.href = e1.attr("href");
					menuList.add(menu);
				}
				return menuList;
			}else{
					throw new HttpException(LTToolUtil
							.getResourceString(R.string.http_response_null));
			}
		}
		/**
		 *   获取菜单
		 * @param url
		 * @return
		 * @throws IOException 
		 * @throws HttpException 
		 */
		private List<Menu> getMenu(String url) throws HttpException, IOException {
			
			String ret = "";
			if(home_cache==null){
				 ret = post(url, null);
				 home_cache = ret;
			}else{
				ret = home_cache;
			}
			
			if (!LTToolUtil.isNull(ret)) {
				final List<Menu> menuList = new ArrayList<Menu>();
				Document doc = Jsoup.parse(ret);
            	Element content = doc.select(".mainmenuiner").first();
            	Elements ea = content.getElementsByTag("a");
				for(Element e1 : ea){
					Menu menu = new Menu();
					menu.name = e1.text();
					menu.href = e1.attr("href");
					menuList.add(menu);
				}
				return menuList;
			}else{
					throw new HttpException(LTToolUtil
							.getResourceString(R.string.http_response_null));
			}
		}
		
		
		
}
