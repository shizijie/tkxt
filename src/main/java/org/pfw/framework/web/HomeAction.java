package org.pfw.framework.web;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.core.util.PFWConfigUtil;
import org.pfw.framework.domain.security.Resource;
import org.pfw.framework.domain.security.Role;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.flow.service.TFlowSjdqztService;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.service.security.ResourceService;
import org.pfw.framework.service.security.UserService;
import org.pfw.framework.util.PFWSecurityUtils;
import org.pfw.framework.utils.DateUtil;
import org.pfw.framework.wjgl.domain.Csrw;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjjgtxr;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.domain.Xuesheng;
import org.pfw.framework.wjgl.service.CsrwService;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.pfw.framework.wjgl.service.TwjjgtxrService;
import org.pfw.framework.wjgl.service.TwjwtService;
import org.pfw.framework.wjgl.service.TwjyhctjService;
import org.pfw.framework.wjgl.service.XueshengService;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;



@Results( { @Result(name =ActionSupport.LOGIN,location="login.action", type = "redirect")})
public class HomeAction extends ActionSupport{
	private static final long serialVersionUID = -2628011648052951372L;
	private List menus;
	private String parentId;
	private List lctxs;
	private String zzxxmc;
	private List urlmenus;
	private Resource curResource;
	private Resource curResource2;
	

	private User usr;
	// 用户的照片
	public String userPhoto;
	//题目分类统计json
	private String tmfltjjsonstr;
	
	@Autowired
	private UserService usrService;
	@Autowired
	private TFlowSjdqztService tfdqztservice;
	@Autowired
	private TwjwtService wjwtService;
	@Autowired
	private TwjfaflService flService;
	
	
	public Resource getCurResource2() {
		return curResource2;
	}

	public void setCurResource2(Resource curResource2) {
		this.curResource2 = curResource2;
	}
	public String getTmfltjjsonstr() {
		return tmfltjjsonstr;
	}

	public void setTmfltjjsonstr(String tmfltjjsonstr) {
		this.tmfltjjsonstr = tmfltjjsonstr;
	}
	public String getUserPhoto() {
		return userPhoto;
	}

	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	public User getUsr() {
		return usr;
	}

	public void setUsr(User usr) {
		this.usr = usr;
	}
	public Resource getCurResource() {
		return curResource;
	}

	public void setCurResource(Resource curResource) {
		this.curResource = curResource;
	}

	public List getUrlmenus() {
		return urlmenus;
	}

	public void setUrlmenus(List urlmenus) {
		this.urlmenus = urlmenus;
	}

	public List getMenus() {
		return menus;
	}

	public List getLctxs() {
		return lctxs;
	}

	public void setLctxs(List lctxs) {
		this.lctxs = lctxs;
	}
	
	public String getZzxxmc() {
		return zzxxmc;
	}

	public void setZzxxmc(String zzxxmc) {
		this.zzxxmc = zzxxmc;
	}




	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 主入口
	 * @return
	 */
	public String index() {
		
		Properties poperties = PFWConfigUtil.getInstance().getProperties(); // 获取properties文件的数据
		zzxxmc = (String) poperties.get("school.name"); // 得到properties文件中name为school.name的值
		
		/*
		usr = usrService.getUserByLoginName(PFWSecurityUtils.getCurrentUserName());
		Set<Role> tmpls = usr.getRoleList();
		for (Role role : tmpls)
		{
			String rolejsdm = role.getJsdm();
			if (!StringUtils.isEmpty(rolejsdm))
			{
				if(rolejsdm.equals("kjztgly"))
					return gly();
			}
		}
		*/
		
		return index2();
	}
	
	public String gly()
	{
		usr = usrService.getUserByLoginName(PFWSecurityUtils.getCurrentUserName());
		
		
		String rootUrl = Struts2Utils.getSession().getServletContext()
				.getRealPath("/");
		String photoUrl = rootUrl + "mainface/image/userphoto/" + PFWSecurityUtils.getCurrentUserName()
				+ ".jpg";
		File fphoto = new File(photoUrl);
		if (fphoto.exists())
			userPhoto = PFWSecurityUtils.getCurrentUserName() + ".jpg";
		
		
		if(Struts2Utils.getSession().getAttribute("menuSess") != null)
		{
			menus = (List)Struts2Utils.getSession().getAttribute("menuSess");
		}else{
			menus=resourceService.getMenus(null);
			Struts2Utils.getSession().setAttribute("menuSess", menus);
		}
		return "gly";
	}
	
	/**
	 * 学生工作台
	 * @return
	 */
	private Page page = new Page(15);
	private Page page3 = new Page(15);
	private List<Twjjgtxr> wdrwcsjl;

	@Autowired
	private TwjjgtxrService wjjgtxrservice;
	@Autowired
	private TwjyhctjService yhctservice;
	@Autowired
	private TwjfaflService faflService;
	@Autowired
	private XueshengService xsService;
	@Autowired
	private CsrwService csrwService;
	
	public List<Twjjgtxr> getWdrwcsjl() {
		return wdrwcsjl;
	}

	public void setWdrwcsjl(List<Twjjgtxr> wdrwcsjl) {
		this.wdrwcsjl = wdrwcsjl;
	}
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
	public Page getPage3() {
		return page3;
	}

	public void setPage3(Page page3) {
		this.page3 = page3;
	}
	
	public String xsIndex()
	{
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		//得到我的错题数量用于显示
		Page page2 = new Page();
		filters = new ArrayList<PropertyFilter>();
		PropertyFilter tmppt = new PropertyFilter("EQS_user.loginName",PFWSecurityUtils.getCurrentUserName());
		filters.add(tmppt);
		page2 = yhctservice.findPage(page2, filters);
		Struts2Utils.getRequest().setAttribute("yhcts", page2.getTotalCount());
		
		//变量暂时借来用用，存所有问题分类
		lctxs = faflService.findAll();
		
		DateUtil  du = new DateUtil();
		parentId = "";//这个变量借来用一下，存时间
		parentId = du.format(du.CHN_DATE_TIME_EXTENDED_FORMAT); 
		
		//我的测试任务
		Xuesheng tmpxs = xsService.getByXh(PFWSecurityUtils.getCurrentUserName());
		if(null==tmpxs){
			return "norole";
		}
		String xsbjid = tmpxs.getBjid().getId();
		filters = new ArrayList<PropertyFilter>();
		tmppt = new PropertyFilter("LIKES_testStu",tmpxs.getId());
		filters.add(tmppt);
		page3.setOrderBy("kssj");
		page3.setOrder(Page.DESC);
		page3.setPageSize(15);
		page3 = csrwService.findPage(page3, filters);
		/*List<Csrw> list=page3.getResult();
		for(int i=0;i<list.size();i++){
			List<Object[]> obj=wjwtService.countNum(list.get(i).getId());
			String str="";
			if(obj.size()>0){
				int j=0;
				for(Object[] ob:obj){
					j++;
					if(j==1){
						str+=ob[0].toString()+":"+ob[1].toString();
					}else{
						str+=","+ob[0].toString()+":"+ob[1].toString();
					}
				}
			}
			list.get(i).setZjxx(str);
		}
		page3.setResult(list);*/
		wdrwcsjl = wjjgtxrservice.getByLoginName(PFWSecurityUtils.getCurrentUserName());
		
		//我的测试记录
		tmppt = new PropertyFilter("EQS_txr",PFWSecurityUtils.getCurrentUserName());
		filters = new ArrayList<PropertyFilter>();
		filters.add(tmppt);
		page.setOrderBy("dxsj");
		page.setOrder(Page.DESC);
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("DXSJ");
			page.setOrder(Page.ASC);
		}
		page.setPageSize(15);
		page = wjjgtxrservice.findPage(page, filters);
		
		
		
		
		return "xueshen";
	}
	
	
	/**
	 * 我的工作台
	 * @return
	 */
	public String index2() {
		
		Properties poperties = PFWConfigUtil.getInstance().getProperties(); // 获取properties文件的数据
		zzxxmc = (String) poperties.get("school.name"); // 得到properties文件中name为school.name的值
		
		usr = usrService.getUserByLoginName(PFWSecurityUtils.getCurrentUserName());
		
		String rootUrl = Struts2Utils.getSession().getServletContext()
				.getRealPath("/");
		String photoUrl = rootUrl + "mainface/image/userphoto/" + PFWSecurityUtils.getCurrentUserName()
				+ ".jpg";
		File fphoto = new File(photoUrl);
		if (fphoto.exists())
			userPhoto = PFWSecurityUtils.getCurrentUserName() + ".jpg";
		
		if(null!=usr){
			Set<Role> tmpls = usr.getRoleList();
			for (Role t1 : tmpls) {
				String tmp1 = t1.getJsdm();
				// 如果该用户角色中有学生角色
				if (StringUtils.isNotEmpty(tmp1) && tmp1.equals("stuRole")) {
					// 如果该用户只有一种学生角色
					if (tmpls.size() == 1)
						return xsIndex();
				}
			}
		}
		
		menus=resourceService.getMenus(null);
		/*
		if(Struts2Utils.getSession().getAttribute("menuSess") != null)
		{
			menus = (List)Struts2Utils.getSession().getAttribute("menuSess");
		}else{
			menus=resourceService.getMenus(null);
			Struts2Utils.getSession().setAttribute("menuSess", menus);
		}
		*/
		
		//题库管理功能菜单
		curResource = resourceService.getById("tikuguanli");
		//题库管理功能菜单
		curResource2 = resourceService.getById("datigl");
		
		//题库量统计
		List<Twjfafl> zjfl=flService.findkc();
		tmfltjjsonstr = "";
		for(int i=0;i<zjfl.size();i++)
		{
			String tms = String.valueOf(wjwtService.countCourse(zjfl.get(i)).toString());
			tmfltjjsonstr += "['"+zjfl.get(i).getFlmc()+"',"+tms+"],";
		}
		if(!tmfltjjsonstr.equals(""))
			tmfltjjsonstr = tmfltjjsonstr.substring(0,tmfltjjsonstr.length()-1);
		return "index2";
	}
	
	/**
	 * 消息中心
	 * @return
	 */
	public String messagecenter()
	{
		Properties poperties = PFWConfigUtil.getInstance().getProperties(); // 获取properties文件的数据
		zzxxmc = (String) poperties.get("school.name"); // 得到properties文件中name为school.name的值
		
		return "messagecenter";
	}
	
	
	/**
	 * 应用中心-树形
	 * @return
	 */
	public String app()
	{
		Properties poperties = PFWConfigUtil.getInstance().getProperties(); // 获取properties文件的数据
		zzxxmc = (String) poperties.get("school.name"); // 得到properties文件中name为school.name的值
		
		menus=resourceService.getMenus(null);
		
		return "app";
	}
	
	/**
	 * 应用中心-扁平化图标
	 * @return
	 */
	public String app2()
	{
		Properties poperties = PFWConfigUtil.getInstance().getProperties(); // 获取properties文件的数据
		zzxxmc = (String) poperties.get("school.name"); // 得到properties文件中name为school.name的值
		
		usr = usrService.getUserByLoginName(PFWSecurityUtils.getCurrentUserName());
		String rootUrl = Struts2Utils.getSession().getServletContext()
				.getRealPath("/");
		String photoUrl = rootUrl + "mainface/image/userphoto/" + PFWSecurityUtils.getCurrentUserName()
				+ ".jpg";
		File fphoto = new File(photoUrl);
		if (fphoto.exists())
			userPhoto = PFWSecurityUtils.getCurrentUserName() + ".jpg";
		
		curResource = resourceService.getById(parentId);
		
		menus = resourceService.getMenus(null);
		/*
		if(Struts2Utils.getSession().getAttribute("menuSess") != null)
		{
			menus = (List)Struts2Utils.getSession().getAttribute("menuSess");
		}else{
			menus=resourceService.getMenus(null);
			Struts2Utils.getSession().setAttribute("menuSess", menus);
		}
		*/
		//直接安层次显示菜单树，不考虑urlmenu情况
		//urlmenus = resourceService.getAllUrlMenus();
		
		return "app2";
	}
	
	/**
	 * 系统地图
	 * @return
	 */
	public String systemmap()
	{
		Properties poperties = PFWConfigUtil.getInstance().getProperties(); // 获取properties文件的数据
		zzxxmc = (String) poperties.get("school.name"); // 得到properties文件中name为school.name的值
		
		return "systemmap";
	}
	
	/**
	 * FAQ
	 * @return
	 */
	public String faq()
	{
		Properties poperties = PFWConfigUtil.getInstance().getProperties(); // 获取properties文件的数据
		zzxxmc = (String) poperties.get("school.name"); // 得到properties文件中name为school.name的值
		
		return "faq";
	}	
	
	/**
	 * home-header文件
	 * @return
	 */
	public String header()
	{
		return "header";
	}

	/**
	 * 加载特定模块下的所有子模块
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public String menus() {
		menus = resourceService.getMenus(parentId);
		return "menus";
	}

	private Date date;
	public Date getDate() {
		return date;
	}
	public String welcome() {
		try
		{
			lctxs = tfdqztservice.getShtxByYh(PFWSecurityUtils.getCurrentUserName(), "");
			date=new Date(System.currentTimeMillis());
		}catch(Exception e)
		{
			e.printStackTrace();
			Struts2Utils.setErrorInfo(e.getMessage());
			return ERROR;
		}
		return "welcome";
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

}
