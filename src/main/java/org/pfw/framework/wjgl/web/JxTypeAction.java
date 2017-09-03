package org.pfw.framework.wjgl.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.wjgl.domain.JxType;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.wjgl.service.JxTypeService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name="success",location="/wjgl/JxType-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/wjgl/JxType-input.ftl", type = "freemarker")
	})
public class JxTypeAction extends CrudActionSupport<JxType> {
	@Autowired
	protected JxTypeService entityService;
	
	private List<JxType> menus=new ArrayList<JxType>();

	public List<JxType> getMenus() {
		return menus;
	}

	public void setMenus(List<JxType> menus) {
		this.menus = menus;
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
		menus=entityService.getParent();
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
		menus=entityService.getParent();
		page = entityService.findPage(page, filters);
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity = (JxType) entityService.getById(id);
		} else {
			entity = new JxType();
		}
		
	}

	@Override
	public String save() throws Exception {
		String jdlx = "";
		String idParent=entity.getFparent().getId();
		if(StringUtils.isBlank(idParent)){
			jdlx = "1";
			entity.setFparent(null);
		}else
		{
			JxType jxtype=entityService.getById(idParent);
			String entjdlx = jxtype.getJdtype();
			
			if(!StringUtils.isEmpty(entjdlx) && entjdlx.equals("1")){
				jdlx = "2";
			}
			if(!StringUtils.isEmpty(entjdlx) && entjdlx.equals("2")){
				jdlx = "3";
			}
		}
		if(!jdlx.equals("")){
			entity.setJdtype(jdlx);
		}
		entity.setTypeName(entity.getTypeName().replaceAll(":","：").replaceAll(",", "，"));
		if (!StringUtils.isEmpty(id))
		{
			entityService.update(entity);
		}else{
			entityService.save(entity);
		}
		Struts2Utils.setPromptInfoToReq("保存成功!!");
		
		return RELOAD;
	}
}
