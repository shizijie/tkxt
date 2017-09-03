package org.pfw.framework.wjgl.web;


import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.wjgl.dao.TwjfaflDao;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.springframework.beans.factory.annotation.Autowired;


@SuppressWarnings("serial")
@Results( { @Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl", type = "freemarker"),
	@Result(name="success",location="/wjgl/twjfafl.ftl",type = "freemarker"),
	@Result(name ="input",location="/wjgl/twjfafl-input.ftl", type = "freemarker"),
	@Result(name="listallfl",location="/wjgl/listallfl.ftl",type = "freemarker"),
	@Result(name="jsonTree",location="/wjgl/jsonTree.ftl",type = "freemarker")
	})
public class TwjfaflAction extends CrudActionSupport<Twjfafl>{
	
	@Autowired
	private TwjfaflService twjfaflService;
	@Autowired
	private TwjfaflDao twjfafldao;

	private List<Twjfafl> allfl;
	private List<Twjfafl> menus;
	private String jsonTree = "";

	private String isjxzt="";
	
	public String getIsjxzt() {
		return isjxzt;
	}
	public void setIsjxzt(String isjxzt) {
		this.isjxzt = isjxzt;
	}
	public String getJsonTree() {
		return jsonTree;
	}
	public void setJsonTree(String jsonTree) {
		this.jsonTree = jsonTree;
	}
	public List<Twjfafl> getMenus() {
		return menus;
	}
	public void setMenus(List<Twjfafl> menus) {
		this.menus = menus;
	}
	public List<Twjfafl> getAllfl() {
		return allfl;
	}
	public void setAllfl(List<Twjfafl> allfl) {
		this.allfl = allfl;
	}
	
	@Override
	public String delete() throws Exception {
		if(checks!=null&&checks.size()>0){
			twjfaflService.delete(checks);
		}
		Struts2Utils.renderText("删除成功");
		return null;
	}

	@Override
	public String input() throws Exception {
		if(isjxzt == null || "".equals(isjxzt)){
			menus = twjfaflService.findFparent();
		}else{
			menus = twjfaflService.findFparentByBz();
		}
		return INPUT;
	}
	
	@Override
	public String list() throws Exception {
		if(StringUtils.isNotEmpty(Struts2Utils.getRequest().getParameter("isjxzt"))){
			isjxzt=Struts2Utils.getRequest().getParameter("isjxzt");
		}
		if(isjxzt == null || "".equals(isjxzt)){
			menus = twjfaflService.findFparent();
		}else{
			menus = twjfaflService.findFparentByBz();
		}
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());

		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("sxh,flmc");
			page.setOrder(Page.ASC+","+Page.ASC);
		}
		page = twjfaflService.findPage(page, filters);
		return SUCCESS;
	}
	

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id) && !id.equals("0")) {
			entity = (Twjfafl) twjfaflService.getById(Integer.parseInt(id));
		} else {
			entity = new Twjfafl();
		}
	}

	@Override
	public String save() throws Exception {
		
		if(isjxzt == null || "".equals(isjxzt)){
			
		}else{
			entity.setBz("1");
			isjxzt="1";
		}
		if (!StringUtils.isEmpty(id) && !id.equals("0"))
		{
			String jdlx = "";
			int fid = entity.getFparent().getId();
			
			if(fid ==0 )
			{
				jdlx = "1";
				entity.setFparent(null);
			}
				
			else
			{	
				twjfafldao.getSession().clear();
				Twjfafl twj=twjfaflService.getById(fid);
				String entjdlx = twj.getJdtype();
				
				if(!StringUtils.isEmpty(entjdlx) && entjdlx.equals("1")){
					jdlx = "2";
				}
				if(!StringUtils.isEmpty(entjdlx) && entjdlx.equals("2")){
					jdlx = "3";
				}
			}
			if(!jdlx.equals(""))
				entity.setJdtype(jdlx);
			
			twjfaflService.update(entity);
			
		}else
		{
			int fid = entity.getFparent().getId();
			if(fid == 0)
			{
				entity.setFparent(null);
			}
			
			String jdlx = "";
			if(fid ==0 )
				jdlx = "1";
			else
			{
				Twjfafl twj=twjfaflService.getById(fid);
				String entjdlx = twj.getJdtype();
				
				if(!StringUtils.isEmpty(entjdlx) && entjdlx.equals("1")){
					jdlx = "2";
				}
				if(!StringUtils.isEmpty(entjdlx) && entjdlx.equals("2")){
					jdlx = "3";
				}

			}
			if(!jdlx.equals(""))
				entity.setJdtype(jdlx);
			twjfaflService.save(entity);

		}
		
		Struts2Utils.setPromptInfoToReq("保存成功");
		Struts2Utils.getRequest().setAttribute("isjxzt", isjxzt);
		return RELOAD;
	}
	
	public String listallfl() throws Exception
	{
		allfl = twjfaflService.findAll();
		return "listallfl";
	}
	
	/**
	 *获取所有分类 
	 */
	public String getallfl()throws Exception{
		jsonTree=twjfaflService.geiallfl();
		return "jsonTree";
	}
	
	public String test()throws Exception{
		jsonTree=Struts2Utils.listToJson(twjfaflService.findAll(), "id","fparent[id]","flmc");
		System.out.println(jsonTree);
		return "jsonTree";
	}
	
}
