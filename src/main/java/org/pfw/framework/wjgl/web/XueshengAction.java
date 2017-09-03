package org.pfw.framework.wjgl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.domain.security.Department;
import org.pfw.framework.domain.security.Role;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.service.security.UserService;
import org.pfw.framework.util.PFWSecurityUtils;
import org.pfw.framework.wjgl.domain.Banji;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.domain.Twjwthdx;
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
		@Result(name="success",location="/wjgl/xuesheng-list.ftl",type = "freemarker"),
		@Result(name ="tmimp",location="/wjgl/xuesheng-tmimp.ftl", type = "freemarker"),
		@Result(name ="input",location="/wjgl/xuesheng-input.ftl", type = "freemarker"),
		@Result(name="download",type="stream",
		params={"contentType","application/octet-stream;charset=utf-8","contentDisposition","attachment;filename=${wjname}","inputName","downloadFile","bufferSize","102410"}
		)
	})
public class XueshengAction extends CrudActionSupport<Xuesheng> {
	@Autowired
	protected XueshengService entityService;
	@Autowired
	protected BanjiService bjservice;
	@Autowired
	protected UserService usservice;
	
	private List<Banji> bjlist;
	private String dzurl;
	private String wjname;
	private List<String> errList;
	private int errcont = 0;
	private int succont = 0;
	private String drfjFileName;
	private File drfj;
	
	
	public String getDrfjFileName() {
		return drfjFileName;
	}

	public void setDrfjFileName(String drfjFileName) {
		this.drfjFileName = drfjFileName;
	}

	public File getDrfj() {
		return drfj;
	}

	public void setDrfj(File drfj) {
		this.drfj = drfj;
	}

	public List<String> getErrList() {
		return errList;
	}

	public void setErrList(List<String> errList) {
		this.errList = errList;
	}

	public int getErrcont() {
		return errcont;
	}

	public void setErrcont(int errcont) {
		this.errcont = errcont;
	}

	public int getSuccont() {
		return succont;
	}

	public void setSuccont(int succont) {
		this.succont = succont;
	}

	public String getDzurl() {
		return dzurl;
	}

	public void setDzurl(String dzurl) {
		this.dzurl = dzurl;
	}

	public String getWjname() {
		return wjname;
	}

	public void setWjname(String wjname) {
		this.wjname = wjname;
	}

	public List<Banji> getBjlist() {
		return bjlist;
	}

	public void setBjlist(List<Banji> bjlist) {
		this.bjlist = bjlist;
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
		bjlist = bjservice.findAll();
		return INPUT;
	}

	@Override
	public String list() throws Exception {
	
		List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(Struts2Utils.getRequest());
		bjlist = bjservice.findAll();
		
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
			entity = (Xuesheng) entityService.getById(id);
		} else {
			entity = new Xuesheng();
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
	 * 导入界面
	 * @return
	 */
	public String tmimp()
	{
		String proStr = (String) Struts2Utils.getSession().getAttribute(
				"promptInfo");
		if (StringUtils.isNotEmpty(proStr)) {
			Struts2Utils.getRequest().setAttribute("promptInfo",
					Struts2Utils.getSession().getAttribute("promptInfo"));
		}
		Struts2Utils.getSession().removeAttribute("promptInfo");
		
		return "tmimp";
	}
	
	
	public void getListStu()throws Exception{
		String bjId=Struts2Utils.getParameter("bjId");
		if(StringUtils.isNotBlank(bjId)){
			List<Xuesheng> list=entityService.findxsinbj(bjId);
			Struts2Utils.renderText(Struts2Utils.listToJson(list, "id","xm"));
		}
	}
	/**
	 * 题目导入模板下载
	 */
	public String drportDate() throws Exception
	{
		
		try {
			String url = Struts2Utils.getSession().getServletContext()
					.getRealPath("/userfiles/imp");

			String scdz = "\\kjzt\\学生导入Excel模板.xls";
			dzurl = url + scdz;
			File file = new File(url + scdz);
			if (!file.exists()) {
				Struts2Utils.setPromptInfo("下载文件不存在，请联系管理人员！");
				return tmimp();
			}
			
			wjname=new String("学生导入Excel模板.xls".getBytes(),"ISO8859-1");
					
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return "download";

	}
	/**
	 * 下载模板公用方法
	 * @return
	 * @throws Exception
	 */
	public InputStream getDownloadFile() throws Exception
    {
		  
		 File file = new File(dzurl);
		 InputStream is =null ;
		 if(file.exists()){
		 is= new FileInputStream(file); 
		 }
		 return is;  
    }
	
	/**
	 * 执行导入
	 * @return
	 */
	public String dotjimp() {

		try {
			
			succont = 0;
			errcont = 0;
			
			FileInputStream input = new FileInputStream(drfj);
			POIFSFileSystem sf = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(sf);
			HSSFSheet sheet = wb.getSheetAt(0);

			errList = new ArrayList<String>();
			List<Banji> allbj = bjservice.findAll();
			
			for (int row = 1; row <= sheet.getLastRowNum(); row++) {
				// 数据是否正确
				boolean rgflg = true;

				Xuesheng xs = new Xuesheng();
				xs.setBz(PFWSecurityUtils.getCurrentUserName()+","+"admin");//把当前导入人及管理admin保存下来用于区别学生的管理权权
				
				// 取得行
				HSSFRow rowObj = sheet.getRow(row);
				if (rowObj == null) {
					continue;
				}

				// 一行共10列
				for (int i = 0; i < 4; i++) {
					String value = null;
					HSSFCell cell = rowObj.getCell(i);
					if (cell != null) 
					{
						String cellValue = rowObj.getCell(i).toString().trim();
						if (StringUtils.isBlank(cellValue)) 
						{
							cell = null;
						}
					}
					
					// 学号
					if (i == 0) 
					{
						// 必填
						if (cell != null) {
							value = rowObj.getCell(i).toString().trim();
							value = value.replace("\"", "\'");
							if (value.length() > 20) {

								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，长度不超过20个字符");
								rgflg = false;
							}
						} else {
							errList.add("第" + (row + 1) + "行，第" + (i + 1)
									+ "列，必添项未填");
							rgflg = false;
						}
						// 验证成功
						if (rgflg) 
						{
							xs.setXh(value.substring(value.indexOf("、")+1));
						}
					}
					
					// 姓名
					if (i == 1) 
					{
						// 必填
						if (cell != null) {
							value = rowObj.getCell(i).toString().trim();
							value = value.replace("\"", "\'");
							if (value.length() > 20) {

								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，长度不超过20个字符");
								rgflg = false;
							}
						} else {
							errList.add("第" + (row + 1) + "行，第" + (i + 1)
									+ "列，必添项未填");
							rgflg = false;
						}
						// 验证成功
						if (rgflg) 
						{
							xs.setXm(value.substring(value.indexOf("、")+1));
						}
					}
					
					// 性别
					if (i == 2) 
					{
						// 必填
						if (cell != null) {
							value = rowObj.getCell(i).toString().trim();
							value = value.replace("\"", "\'");
							if (value.length() > 3) {

								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，长度不超过3个字符");
								rgflg = false;
							}
						}
						// 验证成功
						if (rgflg) 
						{
							xs.setXb(value.substring(value.indexOf("、")+1));
						}
					}
					
					// 班级
					if (i == 3) 
					{
						String bjid = "";
						// 必填
						if (cell != null) {
							value = rowObj.getCell(i).toString().trim();
							value = value.replace("\"", "\'");
							for(Banji tmpbj : allbj)
							{
								if(tmpbj.getBjmc().equals(value))
								{
									bjid = tmpbj.getId();
								}
							}
							if(bjid.equals(""))
							{
								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，班级不存在");
								rgflg = false;
							}
							
						} else {
							errList.add("第" + (row + 1) + "行，第" + (i + 1)
									+ "列，必添项未填");
							rgflg = false;
						}
						// 验证成功
						if (rgflg) 
						{
							if(!bjid.equals(""))
							{
								Banji tt = new Banji();
								tt.setId(bjid);
								xs.setBjid(tt);
							}
						}
					}
					
				}
				
				if (rgflg) {
					Xuesheng yczxs = entityService.getByXh(xs.getXh());
					if(yczxs != null && StringUtils.isNotEmpty(yczxs.getId()))
					{
						yczxs.setXm(xs.getXm());
						yczxs.setXb(xs.getXb());
						yczxs.setBjid(xs.getBjid());
						entityService.update(yczxs);
					}else{
						entityService.save(xs);
						
						User xsuser = new User();
						xsuser.setLoginName(xs.getXh());
						xsuser.setName(xs.getXm());
						xsuser.setPassword("698d51a19d8a121ce581499d7b701668");
						xsuser.setEmail("1@1.com");
						xsuser.setEnabled(true);
						xsuser.setAccountNonExpired(true);
						xsuser.setCredentialsNonExpired(true);
						xsuser.setAccountNonLocked(true);
						Department dp = new Department();
						dp.setId("01");
						xsuser.setDepartment(dp);
						Set role = new HashSet();
						Role rl = new Role();
						rl.setId("4028a48154a2eab00154a2f9f6130002");//固定学生角色id
						role.add(rl);
						xsuser.setRoleList(role);
						usservice.save(xsuser);
						
					}
					succont++;
				}else{
					errcont++;
				}

			}

			return "tmimp";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "tmimp";
	}
}
