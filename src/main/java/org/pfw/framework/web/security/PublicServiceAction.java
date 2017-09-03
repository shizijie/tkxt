package org.pfw.framework.web.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.pfw.framework.dao.security.ResourceDao;
import org.pfw.framework.domain.security.Resource;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
public class PublicServiceAction extends ActionSupport {

	@Autowired
	private ResourceDao resourceDao;
	
	private String pageurl;
	
	
	static final Map<String,List<Resource>> urlMap =new HashMap<String, List<Resource>>();
	//迭代后的资源对象
	private List<Resource> resourcelist =new ArrayList<Resource>();
	public String getUrlJosn() throws Exception
	{
		//根据页面的action路径查询当前位置的list
		//迭代处资源 的所有父类资源
		pageurl =Struts2Utils.getParameter("pageurl");
		resourcelist=urlMap.get(pageurl);
		if(resourcelist!=null&&resourcelist.size()>0){
			
		}else{
			resourcelist=new ArrayList<Resource>();
			List<Resource> resources = findResourceUrl(pageurl);
			if(resources!=null&&resources.size()>0){
				Resource resource=resources.get(0);
				//写一个迭代方法然后把所有的父类资源装在一个list 中
				 resourcelist.add(resource);
				 recursionResource(resource);
			}
			urlMap.put(pageurl, resourcelist);
			
		}
		JSONArray arr = new JSONArray();
		// 遍历
		for (Resource resource : resourcelist) {
			JSONObject json = new JSONObject();
			json.put("name", resource.getName());
			json.put("value", resource.getValue());
			arr.add(json);
		}
		JsonConfig config = new JsonConfig(); 
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);		
		Struts2Utils.renderText(arr.toString());
		return null;
	}
	

	//递归(recursion)查询一个对象，直到没有父类对象为止
	public void recursionResource(Resource resource){
		 if(resource.getParent()!=null){
			 String reid=resource.getParent().getId();
			 Resource resource1=findResource(reid);
			 resourcelist.add(resource1);
			 recursionResource(resource1);
		 }
	}
	
	public List<Resource> findResourceUrl(String ResourceUrl) {
		Resource resource =new Resource();
		String hql="from Resource r where r.value='"+ResourceUrl+"'";
		
		return resourceDao.getSession().createQuery(hql).list();
	}

	public Resource findResource(String resourceid) {
		Resource re=resourceDao.get(resourceid);
		
		return re;
	}
	
	public static Map<String, List<Resource>> getUrlmap() {
		return urlMap;
	}
	public String getPageurl() {
		return pageurl;
	}


	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}


	public List<Resource> getResourcelist() {
		return resourcelist;
	}


	public void setResourcelist(List<Resource> resourcelist) {
		this.resourcelist = resourcelist;
	}
}
