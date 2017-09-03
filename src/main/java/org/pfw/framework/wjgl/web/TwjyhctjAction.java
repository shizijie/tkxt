package org.pfw.framework.wjgl.web;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.service.security.UserService;
import org.pfw.framework.util.PFWSecurityUtils;
import org.pfw.framework.wjgl.dao.TwjyhctjDao;
import org.pfw.framework.wjgl.domain.Twjyhctj;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.wjgl.service.TwjyhctjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name="success",location="/wjgl/twjyhctj-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/wjgl/twjyhctj-input.ftl", type = "freemarker")
	})
public class TwjyhctjAction extends CrudActionSupport<Twjyhctj> {
	@Autowired
	protected TwjyhctjService entityService;
	@Autowired
	protected TwjyhctjDao wjyhctjDao;
	@Autowired
	protected UserService usrService;

	@Override
	public String delete() throws Exception {
		
		if(checks!=null&&checks.size()>0){
			entityService.delete(checks);
		}
		Struts2Utils.renderText("删除成功");
		return null;
	}
	
	public void delete2() throws Exception {
		
		try
		{
			String loginame = PFWSecurityUtils.getCurrentUserName();
			User tmpuser = usrService.getUserByLoginName(loginame);
			String wtid = Struts2Utils.getParameter("wtid");
			//先删除
			Session s = wjyhctjDao.getSession();
			Transaction tran = s.beginTransaction();
			s.createSQLQuery("delete from T_WJGL_YHCTJ  where userid = '"+tmpuser.getId()+"' and wtid='"+wtid+"'").executeUpdate();
			tran.commit();
			s.close();
			Struts2Utils.renderText("删除成功");
		}catch(Exception e)
		{
			Struts2Utils.renderText("从题库中删除时发生了异常");
		}
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
			entity = (Twjyhctj) entityService.getById(id);
		} else {
			entity = new Twjyhctj();
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
	
	public void save2() throws Exception 
	{
		try
		{
			String loginame = PFWSecurityUtils.getCurrentUserName();
			User tmpuser = usrService.getUserByLoginName(loginame);
			String wtid = Struts2Utils.getParameter("wtid");
			//先删除
			wjyhctjDao.getSession().createSQLQuery("delete from T_WJGL_YHCTJ  where userid = '"+tmpuser.getId()+"' and wtid='"+wtid+"'").executeUpdate();
			if(StringUtils.isNotEmpty(loginame) && StringUtils.isNotEmpty(wtid))
			{
				entityService.save(loginame, wtid);
				Struts2Utils.renderText("保存成功");
			}
		}catch(Exception e)
		{
			Struts2Utils.renderText("保存时发生了异常");
		}
		
	}
}
