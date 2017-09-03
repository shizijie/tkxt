package org.pfw.framework.wjgl.web;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.service.security.UserService;
import org.pfw.framework.util.PFWSecurityUtils;
import org.pfw.framework.wjgl.domain.Banji;
import org.pfw.framework.wjgl.domain.ContactWebTopic;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjjg;
import org.pfw.framework.wjgl.domain.Twjjgtxr;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.service.BanjiService;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.pfw.framework.wjgl.service.TwjjgService;
import org.pfw.framework.wjgl.service.TwjjgtxrService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;


@Results( { 
	@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl", type = "freemarker"),
	@Result(name="success",location="/wjgl/twjjgtxr.ftl",type = "freemarker"),
	@Result(name ="txrwj",location="/wjgl/twjjgtxr-txrwj.ftl", type = "freemarker"),
	@Result(name ="txrwjdf",location="/wjgl/twjjgtxr-txrwjdf.ftl", type = "freemarker"),
	@Result(name ="rywjdf",location="/wjgl/twjjgtxr-rywjdf.ftl", type = "freemarker")
	})

public class TwjjgtxrAction extends CrudActionSupport<Twjjgtxr>{
	
	@Autowired
	private TwjjgtxrService entityservice;
	@Autowired
	private TwjjgService wjjgservice;
	@Autowired
	private TwjfaflService twjfaflService;
	@Autowired
	private UserService usrService;
	@Autowired
	protected BanjiService bjService;
	
	//private List menus;
	
	private String famc;
	
	private List<Twjfafl> menus;
	
	private List<Banji> listbj;

	public List<Banji> getListbj() {
		return listbj;
	}

	public void setListbj(List<Banji> listbj) {
		this.listbj = listbj;
	}
	
	
	public List<Twjfafl> getMenus() {
		return menus;
	}

	public void setMenus(List<Twjfafl> menus) {
		this.menus = menus;
	}

	public String getFamc() {
		return famc;
	}

	public void setFamc(String famc) {
		this.famc = famc;
	}


	
	@Override
	public String delete() throws Exception {
		
		try{
			if(checks!=null&&checks.size()>0){
				entityservice.delete(checks);
			}
			Struts2Utils.renderText("删除成功");
		}catch(Exception e)
		{
			e.printStackTrace();
			Struts2Utils.renderText("删除失败,数据有关联,要想删除,请先删除关联数据。");
		}
		
		return null;
		
	}


	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		String banji=Struts2Utils.getParameter("banji");
		if(StringUtils.isNotEmpty(banji)){
			PropertyFilter p=new PropertyFilter("EQS_xuesheng.bjid.id",banji);
			filters.add(p);
		}
	
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("dxsj");
			page.setOrder(Page.DESC);
		}
		page = entityservice.findPage(page, filters);
		menus = twjfaflService.findAll();
		listbj=bjService.findAll();
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity = (Twjjgtxr) entityservice.getById(id);
		} else {
			entity = new Twjjgtxr();
		}
	}


	/**
	 * 查看填写人问卷
	 * @return
	 */
	private User usr;
	private String resultinfo;
	private String txrinfo;
	private List<Twjjg> wjjgls;
	
	public List<Twjjg> getWjjgls() {
		return wjjgls;
	}

	public void setWjjgls(List<Twjjg> wjjgls) {
		this.wjjgls = wjjgls;
	}

	public String getTxrinfo() {
		return txrinfo;
	}

	public void setTxrinfo(String txrinfo) {
		this.txrinfo = txrinfo;
	}

	public String getResultinfo() {
		return resultinfo;
	}
	public void setResultinfo(String resultinfo) {
		this.resultinfo = resultinfo;
	}
	public User getUsr() {
		return usr;
	}
	public void setUsr(User usr) {
		this.usr = usr;
	}

	public String txrwj() throws Exception
	{
		usr = usrService.getUserByLoginName(PFWSecurityUtils.getCurrentUserName());

		entity = entityservice.getById(id);
			
		wjjgls = wjjgservice.getWjjglsBytxrid(id);
		List<Twjwt> wtls1=new ArrayList<Twjwt>();
		List<ContactWebTopic> wtls2=new ArrayList<ContactWebTopic>();
		for(Twjjg jg:wjjgls){
			if(null!=jg.getWtid()){
				wtls1.add(jg.getWtid());
			}else if(null!=jg.getContactWebTopic()){
				wtls2.add(jg.getContactWebTopic());
			}
		}
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Twjwt.class, "id","wtlx","wtmc","bz","hdxlist","sfyx","isorder");
		String json1=JSON.toJSONString(wtls1,filter);
		SimplePropertyPreFilter filter2 = new SimplePropertyPreFilter(ContactWebTopic.class,"id","topicName","textField","setTopicContent","topicType");
		String json2=JSON.toJSONString(wtls2,filter2);
		resultinfo = this.generateJson(null, true, "查询成功", "{\"json1\":"+json1+",\"json2\":"+json2+"}");
		
		//tmpstr = Struts2Utils.beanToJson1(entity, "txr","tmzs","zqsl","cwsl","dxsj");
		//txrinfo = this.generateJson(null, true, "查询成功", tmpstr);
		
		//wjjgxqls = wjjgxqservice.getWjjgxqByTxr(id);//得到这个人填写问卷的结果详情
		
		return "txrwj";
	}
	/**
	 * 产生app需要的json字符串公共类
	 * 
	 * @param JsonPStr
	 * @param isSuccess
	 * @param promptinfo
	 * @param resultinfo
	 * @return
	 */
	public String generateJson(String JsonPStr,boolean isSuccess,String promptinfo,String resultinfo,String...extStr){
		String returnStr = "";
		if(StringUtils.isEmpty(resultinfo))
		{
			resultinfo = "{}";
		}
		String extstrs = "";
		for(String tmps : extStr)
		{
			extstrs += tmps + ",";
		}
		
		if(StringUtils.isNotEmpty(JsonPStr))
		{
			returnStr = JsonPStr+"({"+extstrs+"\"success\":\""+isSuccess+"\",\"promptinfo\":\""+promptinfo+"\",\"resultinfo\":"+resultinfo+"})";
			
		}else{
			returnStr = "{"+extstrs+"\"success\":\""+isSuccess+"\",\"promptinfo\":\""+promptinfo+"\",\"resultinfo\":"+resultinfo+"}";
			
		}
		System.out.println("===="+returnStr);
		return returnStr;
	}
	
	/**
	 * 查看填写人问卷得分
	 * @return
	 */
	public String txrwjdf()
	{
		entity = entityservice.getById(id);
		//wjjgxqls = wjjgxqservice.getWjjgxqByTxr(id);//得到这个人填写问卷的结果详情
		
		return "txrwjdf";
	}
	
	/**
	 * 填写人问卷得分入口
	 * @return
	 */
	public String rywjdf()
	{
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		
		String val1 = Struts2Utils.getParameter("sydckhui");
		if(StringUtils.isNotEmpty(val1))
		{
			List ls1 = Struts2Utils.split(val1, ",");
			PropertyFilter p1 = new PropertyFilter("INC_wjid.id",ls1);
			filters.add(p1);
		}
		
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = entityservice.findPage(page, filters);
		
		List<Twjjgtxr> tmpls = page.getResult();
		List<Twjjgtxr> tmpls2 = new ArrayList<Twjjgtxr>();
		for(Twjjgtxr txr : tmpls)
		{
			String tmpdf = entityservice.getZdfByTxr(txr);
			txr.setIpdz(tmpdf);//临时将ipdz字段设置为得分，方便在前台查询界面中显示
			tmpls2.add(txr);
		}
		page.setResult(tmpls2);
		
		return "rywjdf";
	}

	@Override
	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
