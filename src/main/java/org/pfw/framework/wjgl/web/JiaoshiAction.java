package org.pfw.framework.wjgl.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.wjgl.domain.Jiaoshi;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.wjgl.service.JiaoshiService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name="success",location="/wjgl/jiaoshi-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/wjgl/jiaoshi-input.ftl", type = "freemarker")
	})
public class JiaoshiAction extends CrudActionSupport<Jiaoshi> {
	@Autowired
	protected JiaoshiService entityService;

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
			entity = (Jiaoshi) entityService.getById(id);
		} else {
			entity = new Jiaoshi();
		}
		
	}

	@Override
	public String save() throws Exception {
		if (!StringUtils.isEmpty(id))
		{
			entityService.update(entity);
		}else{
			entityService.save(entity);
		}
		Struts2Utils.setPromptInfoToReq("保存成功!!");
		
		return RELOAD;
	}
	
	/**
	 * 返回用户的json数据
	 */
	public String getJsJsonStr() throws Exception
	{
		String tmpUsrJsonStr = "";
		
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		Page pg = new Page();
		pg.setPageSize(100);
		pg = entityService.findPage(pg, filters);
		
		List ls = pg.getResult();
		
		tmpUsrJsonStr = Struts2Utils.listToJson(ls, "id","xm","gh");
		
		Struts2Utils.renderText(tmpUsrJsonStr);
		
		return null;
	}
}
