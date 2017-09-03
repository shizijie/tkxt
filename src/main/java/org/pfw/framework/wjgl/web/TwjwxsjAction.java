package org.pfw.framework.wjgl.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.wjgl.domain.Twjwxsj;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.wjgl.service.TwjwxsjService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name="success",location="/wjgl/twjwxsj-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/wjgl/twjwxsj-input.ftl", type = "freemarker")
	})
public class TwjwxsjAction extends CrudActionSupport<Twjwxsj> {
	@Autowired
	protected TwjwxsjService entityService;
	

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
			entity = (Twjwxsj) entityService.getById(id);
		} else {
			entity = new Twjwxsj();
		}
		
	}

	@Override
	public String save() throws Exception {
		if (!StringUtils.isEmpty(id))
		{
			String sjlx = entity.getSjlx();
			if(sjlx.equals("1"))
			{
				//固定时间
				String zhourqtmpstr = entity.getZhourq();
				zhourqtmpstr =  zhourqtmpstr.substring(0,zhourqtmpstr.indexOf(","));
				entity.setZhourq(zhourqtmpstr.trim());
				String zhourqtmpstr2 = entity.getZhourq2();
				zhourqtmpstr2 =  zhourqtmpstr2.substring(0,zhourqtmpstr2.indexOf(","));
				entity.setZhourq2(zhourqtmpstr2.trim());
			}
			if(sjlx.equals("2"))
			{
				//临时时间
				String zhourqtmpstr = entity.getZhourq();
				zhourqtmpstr =  zhourqtmpstr.substring(zhourqtmpstr.indexOf(",")+1);
				entity.setZhourq(zhourqtmpstr.trim());
				String zhourqtmpstr2 = entity.getZhourq2();
				zhourqtmpstr2 =  zhourqtmpstr2.substring(zhourqtmpstr2.indexOf(",")+1);
				entity.setZhourq2(zhourqtmpstr2.trim());
			}
			
			entityService.update(entity);
		}else{
			String sjlx = entity.getSjlx();
			if(StringUtils.isNotEmpty(sjlx))
			{
				if(sjlx.equals("1"))
				{
					//固定时间
					String zhourqtmpstr = entity.getZhourq();
					zhourqtmpstr =  zhourqtmpstr.substring(0,zhourqtmpstr.indexOf(","));
					entity.setZhourq(zhourqtmpstr.trim());
					String zhourqtmpstr2 = entity.getZhourq2();
					zhourqtmpstr2 =  zhourqtmpstr2.substring(0,zhourqtmpstr2.indexOf(","));
					entity.setZhourq2(zhourqtmpstr2.trim());
				}
				if(sjlx.equals("2"))
				{
					//临时时间
					String zhourqtmpstr = entity.getZhourq();
					zhourqtmpstr =  zhourqtmpstr.substring(zhourqtmpstr.indexOf(",")+1);
					entity.setZhourq(zhourqtmpstr.trim());
					String zhourqtmpstr2 = entity.getZhourq2();
					zhourqtmpstr2 =  zhourqtmpstr2.substring(zhourqtmpstr2.indexOf(",")+1);
					entity.setZhourq2(zhourqtmpstr2.trim());
				}
			}
			entityService.save(entity);
		}
		Struts2Utils.setPromptInfoToReq("保存成功!!");
		
		return RELOAD;
	}
}
