package org.pfw.framework.web;

import java.util.Properties;
import java.util.Set;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.core.util.PFWConfigUtil;
import org.pfw.framework.domain.security.Role;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.service.security.UserService;
import org.pfw.framework.util.PFWSecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
@Results
({
	@Result(name =ActionSupport.LOGIN,location="login.action", type = "redirect"),
	@Result(name="index",location="login.ftl",type = "freemarker"),
	//@Result(name = CrudActionSupport.RELOAD, location = "home!index2.action", type = "redirect") 
	@Result(name = CrudActionSupport.RELOAD, location="/common/noresource.ftl",type = "freemarker")
})
public class IndexAction extends ActionSupport{
	private static final long serialVersionUID = 8033204650474571617L;
	private String zzxxmc;
	
	@Autowired
	UserService usservice;
	
	public String getZzxxmc() {
		return zzxxmc;
	}
	public void setZzxxmc(String zzxxmc) {
		this.zzxxmc = zzxxmc;
	}

	@Override
	public String execute() throws Exception {
		User user = PFWSecurityUtils.getCurrentUser();
		
		Properties poperties = PFWConfigUtil.getInstance().getProperties(); // 获取properties文件的数据
		zzxxmc = (String) poperties.get("school.name"); // 得到properties文件中name为school.name的值
		
		if (null == user||(user!=null&&user.getLoginName().equals("roleAnonymous"))) {
			return "index";
//			return LOGIN;
		} else {
			/* 界面上功能按钮的权限功能，暂时注释掉
			User us1 = usservice.getUserByLoginName(user.getUsername());
			Set<Role> tmpls = us1.getRoleList();
			String curUserAuths = "";
			for(Role t1 : tmpls)
			{
				String tmpstr = t1.getAuthNams();
				if(tmpstr != null && tmpstr.indexOf("A_ALLADU_BUTTON_HIDDEN") >= 0)
				{
					curUserAuths = "A_ALLADU_BUTTON_HIDDEN";
					break;
				}
			}
			
			Struts2Utils.getSession().setAttribute("curUserAuths", curUserAuths);
			*/
			
			return CrudActionSupport.RELOAD;
		}
	}
}
