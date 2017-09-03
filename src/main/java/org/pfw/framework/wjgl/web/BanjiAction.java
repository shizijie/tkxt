package org.pfw.framework.wjgl.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.wjgl.domain.Banji;
import org.pfw.framework.wjgl.domain.Xuesheng;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.wjgl.service.BanjiService;
import org.pfw.framework.wjgl.service.XueshengService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name="success",location="/wjgl/banji-list.ftl",type = "freemarker"),
		@Result(name="fpxs",location="/wjgl/banji-fpxs.ftl",type = "freemarker"),
		@Result(name ="input",location="/wjgl/banji-input.ftl", type = "freemarker")
	})
public class BanjiAction extends CrudActionSupport<Banji> {
	@Autowired
	protected BanjiService entityService;
	@Autowired
	protected XueshengService xsService;
	
	private List<Xuesheng> xslst;
	
	private List<Xuesheng> xslst2;

	public List<Xuesheng> getXslst2() {
		return xslst2;
	}

	public void setXslst2(List<Xuesheng> xslst2) {
		this.xslst2 = xslst2;
	}

	public List<Xuesheng> getXslst() {
		return xslst;
	}

	public void setXslst(List<Xuesheng> xslst) {
		this.xslst = xslst;
	}

	@Override
	public String delete() throws Exception {
		
		if(checks!=null&&checks.size()>0){
			entityService.delete(checks);
		}
		Struts2Utils.renderText("删除成功");
		return null;
	}

	@Override
	public String input() throws Exception {
		return INPUT;
	}

	@Override
	public String list() throws Exception {
	
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		page = entityService.findPage(page, filters);
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity = (Banji) entityService.getById(id);
		} else {
			entity = new Banji();
		}
		
	}

	@Override
	public String save() throws Exception {
		System.out.println(Struts2Utils.getSession().getAttribute("token"));
		if (!StringUtils.isEmpty(id))
		{
			entityService.update(entity);
		}else{
			entityService.save(entity);
		}
		Struts2Utils.setPromptInfoToReq("保存成功!!");
		
		return RELOAD;
	}
	
	public String fpxs() throws Exception
	{
		if (!StringUtils.isEmpty(id))
		{
			entity = (Banji) entityService.getById(id);
			xslst = xsService.findxsnotinbj(id);
			xslst2 = xsService.findxsinbj(id);
			
			return "fpxs";
		}
		return null;
	}
	
	public String dofpxs() throws Exception
	{
		if(checks!=null&&checks.size()>0)
		{
			if(!StringUtils.isEmpty(id))
			{
				Banji bjtmp = new Banji();
				bjtmp.setId(id);
				for(String tmpstr : checks)
				{
					Xuesheng xstmp = xsService.getById(tmpstr);
					xstmp.setBjid(bjtmp);
					xsService.update(xstmp);
				}
			}
			
		}
		Struts2Utils.setPromptInfoToReq("学生分配成功!!");
		return RELOAD;
	}
}
