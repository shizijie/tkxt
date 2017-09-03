package org.pfw.framework.wjgl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.util.PFWSecurityUtils;
import org.pfw.framework.wjgl.domain.JxType;
import org.pfw.framework.wjgl.domain.Jxtm;
import org.pfw.framework.wjgl.service.JxTypeService;
import org.pfw.framework.wjgl.service.JxtmService;
import org.pfw.framework.wjgl.service.JxtxService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name="success",location="/wjgl/jxtm-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/wjgl/jxtm-input.ftl", type = "freemarker")
	})
public class JxtmAction extends CrudActionSupport<Jxtm> {
	@Autowired
	protected JxtmService entityService;
	@Autowired
	private JxtxService jxtxService;
	@Autowired
	private JxTypeService jxTypeService;

	private List menus;
	
	private File wttpFile;
	private String wttpFileFileName;
	private List<JxType> allfl;
	
	
	public List<JxType> getAllfl() {
		return allfl;
	}

	public void setAllfl(List<JxType> allfl) {
		this.allfl = allfl;
	}

	public File getWttpFile() {
		return wttpFile;
	}

	public void setWttpFile(File wttpFile) {
		this.wttpFile = wttpFile;
	}

	public String getWttpFileFileName() {
		return wttpFileFileName;
	}

	public void setWttpFileFileName(String wttpFileFileName) {
		this.wttpFileFileName = wttpFileFileName;
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
		menus=jxtxService.findAll();
		allfl=jxTypeService.getParent();
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		//List<PropertyFilter> filters = HibernateWebUtils
		//		.buildPropertyFilters(Struts2Utils.getRequest());
		//设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("id");
			page.setOrder(Page.ASC);
		}
		List<PropertyFilter> filters=new ArrayList<PropertyFilter>();
		String id=Struts2Utils.getParameter("filter_EQI_txid.id");
		String type=Struts2Utils.getParameter("filter_EQI_type.id");
		if(StringUtils.isNotEmpty(id)){
			PropertyFilter p=new PropertyFilter("EQS_txid.id",id);
			filters.add(p);
		}
		if(StringUtils.isNotEmpty(type)){
			PropertyFilter p=new PropertyFilter("EQS_type.id",type);
			filters.add(p);
		/*	List<JxType> listtype=jxTypeService.getListById(type);
			List<String> list=new ArrayList<String>();
			for(JxType jx:listtype){
				list.add(jx.getId());
			}
			if(list!=null&&list.size()>0){
				PropertyFilter p=new PropertyFilter("INC_type.id",list);
				filters.add(p);
			}else{
				PropertyFilter p=new PropertyFilter("EQS_type.id","0");
				filters.add(p);
			}*/
		}
		allfl=jxTypeService.getParent();
		page = entityService.findPage(page, filters);
		menus=jxtxService.findAll();
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity = (Jxtm) entityService.getById(id);
		} else {
			entity = new Jxtm();
		}
		
	}

	@Override
	public String save() throws Exception {
		
		//保存问题图片
		if (null != wttpFile) {
			
			//上传图片并保存
			String path = ServletActionContext.getServletContext().getRealPath(
					"");
			InputStream inputStream = new FileInputStream(wttpFile);
			OutputStream outputStream = new FileOutputStream(path
					+ "\\userfiles\\kjzt\\wtimg\\" + wttpFileFileName);
			byte bt[] = new byte[1024];
			int count = 0;
			while ((count = inputStream.read(bt)) > 0) {
				outputStream.write(bt, 0, count);
			}
			inputStream.close();
			outputStream.close();
			//上传图片并保存结束
			//重新命名文件，防止图片重名
			User user = PFWSecurityUtils.getCurrentUser();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
			String fileName = user.getLoginName() + format.format(new Date())+"0";
			String ext = FilenameUtils.getExtension(wttpFileFileName);
			File file = new File(path + "\\userfiles\\kjzt\\wtimg\\" + wttpFileFileName);
			file.renameTo(new File(path + "\\userfiles\\kjzt\\wtimg\\" + fileName + "."
					+ ext));
			
			entity.setTmFileName(fileName + "." + ext);
		}
		
		if (!StringUtils.isEmpty(id))
		{
			entityService.update(entity);
		}else{
			
			entityService.save(entity);
		}
		
		
		Struts2Utils.setPromptInfoToReq("保存成功!!");
		
		return RELOAD;
	}

	public List getMenus() {
		return menus;
	}

	public void setMenus(List menus) {
		this.menus = menus;
	}
}
