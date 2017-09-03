package org.pfw.framework.wjgl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.domain.Twjwthdx;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.pfw.framework.wjgl.service.TwjwtService;
import org.pfw.framework.wjgl.service.TwjwthdxService;
import org.springframework.beans.factory.annotation.Autowired;

@Results
({
		@Result(name = CrudActionSupport.RELOAD, location = "/util/winopen.ftl", type = "freemarker"),
		@Result(name = "success", location = "/wjgl/jxzt.ftl", type = "freemarker"),
		@Result(name ="list2",location="/wjgl/catalogtask.ftl", type = "freemarker"),
		@Result(name ="txwj",location="/wjgl/twjwt-txwj.ftl", type = "freemarker"),
		@Result(name ="tmimp",location="/wjgl/twjwt-tmimp.ftl", type = "freemarker"),
		@Result(name ="list2reload",location="supervision/supervision_item!list1.action?taskBatchID=${taskBatchID}", type = "redirect"),
		@Result(name = "input", location = "/wjgl/jxzt-input.ftl", type = "freemarker"),
		@Result(name="download",type="stream",
		params={"contentType","application/octet-stream;charset=utf-8","contentDisposition","attachment;filename=${wjname}","inputName","downloadFile","bufferSize","102410"}
		)
})
public class JxzttkAction extends CrudActionSupport<Twjwt> {

	private static final long serialVersionUID = -6765606367040622488L;
	@Autowired
	private TwjwtService twjwtService;//supervisionItemService
	@Autowired
	private TwjfaflService twjfaflService;//scs
	@Autowired
	private TwjwthdxService twjwthdxService;
	
	private String taskBatchID;
	private String wjname;
	private List menus;
	private String drfjFileName;
	private File drfj;
	private String dzurl;
	private List<String> errList;
	private int errcont = 0;
	private int succont = 0;
	private List<Twjfafl> allfl;
	
	public List<Twjfafl> getAllfl() {
		return allfl;
	}

	public void setAllfl(List<Twjfafl> allfl) {
		this.allfl = allfl;
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
	public String getWjname() {
		return wjname;
	}

	public void setWjname(String wjname) {
		this.wjname = wjname;
	}
	public List getMenus() {
		return menus;
	}
	
	private List menus2;
	public List getMenus2() {
		return menus2;
	}	
	
	private Map<String, String> jfgzM;
	private List<String> txJcpl;
	private String dwfs2;
	private String dwfs4;
	private String dwfs3_jlsx_1;
	private String dwfs3_jlxx_1;
	private String dwfs3_1;
	private String dwfs3_jlsx_2;
	private String dwfs3_jlxx_2;
	private String dwfs3_2;
	private String dwfs3_jlsx_3;
	private String dwfs3_jlxx_3;
	private String dwfs3_3;
	private String dwfs3_jlsx_4;
	private String dwfs3_jlxx_4;
	private String dwfs3_4;
	private String dwfs3_jlsx_5;
	private String dwfs3_jlxx_5;
	private String dwfs3_5;
	private String dwfs3_jlsx_6;
	private String dwfs3_jlxx_6;
	private String dwfs3_6;
	private String jcpl;
	private String faflSelect;
	

	public String getFaflSelect() {
		return faflSelect;
	}

	public void setFaflSelect(String faflSelect) {
		this.faflSelect = faflSelect;
	}

	public String getTaskBatchID() {
		return taskBatchID;
	}

	public void setTaskBatchID(String taskBatchID) {
		this.taskBatchID = taskBatchID;
	}

	public String getJcpl() {
		return jcpl;
	}

	public void setJcpl(String jcpl) {
		this.jcpl = jcpl;
	}

	public String getDwfs3_jlsx_1() {
		return dwfs3_jlsx_1;
	}

	public void setDwfs3_jlsx_1(String dwfs3Jlsx_1) {
		dwfs3_jlsx_1 = dwfs3Jlsx_1;
	}

	public String getDwfs3_jlxx_1() {
		return dwfs3_jlxx_1;
	}

	public void setDwfs3_jlxx_1(String dwfs3Jlxx_1) {
		dwfs3_jlxx_1 = dwfs3Jlxx_1;
	}

	public String getDwfs3_1() {
		return dwfs3_1;
	}

	public void setDwfs3_1(String dwfs3_1) {
		this.dwfs3_1 = dwfs3_1;
	}

	public String getDwfs3_jlsx_2() {
		return dwfs3_jlsx_2;
	}

	public void setDwfs3_jlsx_2(String dwfs3Jlsx_2) {
		dwfs3_jlsx_2 = dwfs3Jlsx_2;
	}

	public String getDwfs3_jlxx_2() {
		return dwfs3_jlxx_2;
	}

	public void setDwfs3_jlxx_2(String dwfs3Jlxx_2) {
		dwfs3_jlxx_2 = dwfs3Jlxx_2;
	}

	public String getDwfs3_2() {
		return dwfs3_2;
	}

	public void setDwfs3_2(String dwfs3_2) {
		this.dwfs3_2 = dwfs3_2;
	}

	public String getDwfs3_jlsx_3() {
		return dwfs3_jlsx_3;
	}

	public void setDwfs3_jlsx_3(String dwfs3Jlsx_3) {
		dwfs3_jlsx_3 = dwfs3Jlsx_3;
	}

	public String getDwfs3_jlxx_3() {
		return dwfs3_jlxx_3;
	}

	public void setDwfs3_jlxx_3(String dwfs3Jlxx_3) {
		dwfs3_jlxx_3 = dwfs3Jlxx_3;
	}

	public String getDwfs3_3() {
		return dwfs3_3;
	}

	public void setDwfs3_3(String dwfs3_3) {
		this.dwfs3_3 = dwfs3_3;
	}

	public String getDwfs3_jlsx_4() {
		return dwfs3_jlsx_4;
	}

	public void setDwfs3_jlsx_4(String dwfs3Jlsx_4) {
		dwfs3_jlsx_4 = dwfs3Jlsx_4;
	}

	public String getDwfs3_jlxx_4() {
		return dwfs3_jlxx_4;
	}

	public void setDwfs3_jlxx_4(String dwfs3Jlxx_4) {
		dwfs3_jlxx_4 = dwfs3Jlxx_4;
	}

	public String getDwfs3_4() {
		return dwfs3_4;
	}

	public void setDwfs3_4(String dwfs3_4) {
		this.dwfs3_4 = dwfs3_4;
	}

	public String getDwfs3_jlsx_5() {
		return dwfs3_jlsx_5;
	}

	public void setDwfs3_jlsx_5(String dwfs3Jlsx_5) {
		dwfs3_jlsx_5 = dwfs3Jlsx_5;
	}

	public String getDwfs3_jlxx_5() {
		return dwfs3_jlxx_5;
	}

	public void setDwfs3_jlxx_5(String dwfs3Jlxx_5) {
		dwfs3_jlxx_5 = dwfs3Jlxx_5;
	}

	public String getDwfs3_5() {
		return dwfs3_5;
	}

	public void setDwfs3_5(String dwfs3_5) {
		this.dwfs3_5 = dwfs3_5;
	}

	public String getDwfs3_jlsx_6() {
		return dwfs3_jlsx_6;
	}

	public void setDwfs3_jlsx_6(String dwfs3Jlsx_6) {
		dwfs3_jlsx_6 = dwfs3Jlsx_6;
	}

	public String getDwfs3_jlxx_6() {
		return dwfs3_jlxx_6;
	}

	public void setDwfs3_jlxx_6(String dwfs3Jlxx_6) {
		dwfs3_jlxx_6 = dwfs3Jlxx_6;
	}

	public String getDwfs3_6() {
		return dwfs3_6;
	}

	public void setDwfs3_6(String dwfs3_6) {
		this.dwfs3_6 = dwfs3_6;
	}

	public String getDwfs4() {
		return dwfs4;
	}

	public void setDwfs4(String dwfs4) {
		this.dwfs4 = dwfs4;
	}

	public String getDwfs2() {
		return dwfs2;
	}

	public void setDwfs2(String dwfs2) {
		this.dwfs2 = dwfs2;
	}

	public List<String> getTxJcpl() {
		return txJcpl;
	}

	public void setTxJcpl(List<String> txJcpl) {
		this.txJcpl = txJcpl;
	}

	public Map<String, String> getJfgzM() {
		return jfgzM;
	}

	public void setJfgzM(Map<String, String> jfgzM) {
		this.jfgzM = jfgzM;
	}

	@Override
	public String delete() throws Exception {
		try
		{
			if (checks != null && checks.size() > 0) {
				twjwtService.delete(checks);
			}
			
			Struts2Utils.renderText("删除成功");
		}catch(Exception e)
		{
			Struts2Utils.renderText("存在引用数据,无法删除");
		}
		
		
		return null;
	}

	
	@Override
	public String input() throws Exception {

		txJcpl = new ArrayList<String>();
		for (int i = 1; i <= 12; i++)
			txJcpl.add(new Integer(i).toString());

		jfgzM = twjwtService.getJfgzMap();

		/*
		List<Twjwtjfgz> tt = new ArrayList<Twjwtjfgz>(entity.getJfgzlist());
		if (entity.getJfgzdm() != null && entity.getJfgzdm().equals("2")) {
			if (tt != null && tt.size() > 0) {
				dwfs2 = tt.get(0).getDwfs();
			}
		}
		
		if (entity.getJfgzdm() != null && entity.getJfgzdm().equals("3")) {

				for(int i = 1;i<= tt.size(); i++)
				{
					if(i == 1)
					{
						dwfs3_jlsx_1 = tt.get(i-1).getJlsx();
						dwfs3_jlxx_1 = tt.get(i-1).getJlxx();
						dwfs3_1 =  tt.get(i-1).getDwfs();
 					}
					if(i == 2)
					{
						dwfs3_jlsx_2 = tt.get(i-1).getJlsx();
						dwfs3_jlxx_2 = tt.get(i-1).getJlxx();
						dwfs3_2 =  tt.get(i-1).getDwfs();
 					}
					if(i == 3)
					{
						dwfs3_jlsx_3 = tt.get(i-1).getJlsx();
						dwfs3_jlxx_3 = tt.get(i-1).getJlxx();
						dwfs3_3 =  tt.get(i-1).getDwfs();
 					}
					if(i == 4)
					{
						dwfs3_jlsx_4 = tt.get(i-1).getJlsx();
						dwfs3_jlxx_4 = tt.get(i-1).getJlxx();
						dwfs3_4 =  tt.get(i-1).getDwfs();
 					}
					if(i == 5)
					{
						dwfs3_jlsx_5 = tt.get(i-1).getJlsx();
						dwfs3_jlxx_5 = tt.get(i-1).getJlxx();
						dwfs3_5 =  tt.get(i-1).getDwfs();
 					}
					if(i == 6)
					{
						dwfs3_jlsx_6 = tt.get(i-1).getJlsx();
						dwfs3_jlxx_6 = tt.get(i-1).getJlxx();
						dwfs3_6 =  tt.get(i-1).getDwfs();
 					}					

				}

		}
		
		if (entity.getJfgzdm() != null && entity.getJfgzdm().equals("4")) {
			if (tt != null && tt.size() > 0) {
				dwfs4 = tt.get(0).getDwfs();
			}
		}
		*/
		allfl=twjfaflService.findFparentByBz();
		menus = twjfaflService.findFparentByBz();
		
		return INPUT;
	}

	public String show() {
		try {
			prepareModel();
		} catch (Exception e) {
		}
		return "show";
	}

	@Override
	public String list() throws Exception {
		List<PropertyFilter> filters = HibernateWebUtils
				.buildPropertyFilters(Struts2Utils.getRequest());
		
		// 设置默认排序方式
		if (!page.isOrderBySetted()) {
			page.setOrderBy("faflid.flmc,sxh");
			page.setOrder(Page.ASC+","+Page.ASC);
		}
		PropertyFilter p=new PropertyFilter("EQS_faflid.bz","1");
		//页面内容
		String str;
		Struts2Utils.getParameter("filter_EQI_faflid.id");
		Struts2Utils.getParameter("filter_LIKES_wtmc");
		
//		System.out.println(Struts2Utils.getParameter("filter_EQI_faflid.id")+"%%%"+Struts2Utils.getParameter("filter_LIKES_wtmc"));
		
		List<String> slist= new ArrayList<String>();
		slist.add(Struts2Utils.getParameter("filter_EQI_faflid.id"));
		slist.add(Struts2Utils.getParameter("filter_LIKES_wtmc.id"));
		slist.add("1");
		
		page = twjwtService.findlist(page, slist);
		//计分规则
		jfgzM = twjwtService.getJfgzMap();
		//课程章节
		menus = twjfaflService.findFparentByBz();
		
		return SUCCESS;
	}
	
	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id) && !id.equals("0")) {
			entity = (Twjwt) twjwtService.getById(id);
		} else {
			entity = new Twjwt();
		}
	}

	
	private File wtmcFile;
	private String wtmcFileFileName;
	
	private File damcFile0;
	private String damcFile0FileName;
	private File damcFile1;
	private String damcFile1FileName;
	private File damcFile2;
	private String damcFile2FileName;
	private File damcFile3;
	private String damcFile3FileName;
	private File damcFile4;
	private String damcFile4FileName;
	private File damcFile5;
	private String damcFile5FileName;
	
	public File getWtmcFile() {
		return wtmcFile;
	}

	public void setWtmcFile(File wtmcFile) {
		this.wtmcFile = wtmcFile;
	}

	public String getWtmcFileFileName() {
		return wtmcFileFileName;
	}

	public void setWtmcFileFileName(String wtmcFileFileName) {
		this.wtmcFileFileName = wtmcFileFileName;
	}

	public File getDamcFile0() {
		return damcFile0;
	}

	public void setDamcFile0(File damcFile0) {
		this.damcFile0 = damcFile0;
	}

	public String getDamcFile0FileName() {
		return damcFile0FileName;
	}

	public void setDamcFile0FileName(String damcFile0FileName) {
		this.damcFile0FileName = damcFile0FileName;
	}

	public File getDamcFile1() {
		return damcFile1;
	}

	public void setDamcFile1(File damcFile1) {
		this.damcFile1 = damcFile1;
	}

	public String getDamcFile1FileName() {
		return damcFile1FileName;
	}

	public void setDamcFile1FileName(String damcFile1FileName) {
		this.damcFile1FileName = damcFile1FileName;
	}

	public File getDamcFile2() {
		return damcFile2;
	}

	public void setDamcFile2(File damcFile2) {
		this.damcFile2 = damcFile2;
	}

	public String getDamcFile2FileName() {
		return damcFile2FileName;
	}

	public void setDamcFile2FileName(String damcFile2FileName) {
		this.damcFile2FileName = damcFile2FileName;
	}

	public File getDamcFile3() {
		return damcFile3;
	}

	public void setDamcFile3(File damcFile3) {
		this.damcFile3 = damcFile3;
	}

	public String getDamcFile3FileName() {
		return damcFile3FileName;
	}

	public void setDamcFile3FileName(String damcFile3FileName) {
		this.damcFile3FileName = damcFile3FileName;
	}

	public File getDamcFile4() {
		return damcFile4;
	}

	public void setDamcFile4(File damcFile4) {
		this.damcFile4 = damcFile4;
	}

	public String getDamcFile4FileName() {
		return damcFile4FileName;
	}

	public void setDamcFile4FileName(String damcFile4FileName) {
		this.damcFile4FileName = damcFile4FileName;
	}

	public File getDamcFile5() {
		return damcFile5;
	}

	public void setDamcFile5(File damcFile5) {
		this.damcFile5 = damcFile5;
	}

	public String getDamcFile5FileName() {
		return damcFile5FileName;
	}

	public void setDamcFile5FileName(String damcFile5FileName) {
		this.damcFile5FileName = damcFile5FileName;
	}

	@Override
	public String save() throws Exception {
		
		if (!StringUtils.isEmpty(id) && !id.equals("0")) {
			
			//删除问题回答项
			
			List<Twjwthdx> tt2 = new ArrayList<Twjwthdx>(
					entity.getHdxlist());
			entity.setHdxlist(null);
			if (tt2 != null && tt2.size() > 0) {
				for (Twjwthdx tmphkx : tt2) {
					twjwthdxService.deleteById(String.valueOf(tmphkx.getId()));
				}
			}
			
			
			//保存问题图片
			entity.setWtmcFileName("");
			if (null != wtmcFile) {

				
				//上传图片并保存
				String path = ServletActionContext.getServletContext().getRealPath(
						"");
				InputStream inputStream = new FileInputStream(wtmcFile);
				OutputStream outputStream = new FileOutputStream(path
						+ "\\userfiles\\kjzt\\wtimg\\" + wtmcFileFileName);
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
				String fileName = user.getLoginName() + format.format(new Date());
				String ext = FilenameUtils.getExtension(wtmcFileFileName);
				File file = new File(path + "\\userfiles\\kjzt\\wtimg\\" + wtmcFileFileName);
				file.renameTo(new File(path + "\\userfiles\\kjzt\\wtimg\\" + fileName + "."
						+ ext));
				
				
				entity.setWtmcFileName(fileName + "." + ext);
				
			}
			
			
			//同时保存问题的回答项
			Set<Twjwthdx> hdxlist = new HashSet<Twjwthdx>();
			for(int i = 0 ; i < 10; i++)
			{
				String dadm = Struts2Utils.getParameter("dadm"+i);
				String damc = Struts2Utils.getParameter("damc"+i);
				String sfzqda = Struts2Utils.getParameter("sfzqda"+i);
				String dafs = Struts2Utils.getParameter("dafs"+i);
				if(!StringUtils.isEmpty(dadm) && !StringUtils.isEmpty(damc.trim()) && !StringUtils.isEmpty(sfzqda) && !StringUtils.isEmpty(dafs))
				{
					Twjwthdx tmphkx = new Twjwthdx();
					tmphkx.setDadm(dadm);
					tmphkx.setBz(String.valueOf(i));
					tmphkx.setDamc(damc);
					tmphkx.setSfzqda(sfzqda);
					tmphkx.setDafs(dafs);
					
					//保存问题的图片
					tmphkx.setDamcFileName("");
					if(i == 0)
					{
						//保存问题图片
						if (null != damcFile0) {
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile0);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile0FileName);
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
							String fileName = user.getLoginName() + format.format(new Date());
							String ext = FilenameUtils.getExtension(damcFile0FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile0FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}
					}
					if(i == 1)
					{
						//保存问题图片
						if (null != damcFile1) {
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile1);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile1FileName);
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
							String fileName = user.getLoginName() + format.format(new Date());
							String ext = FilenameUtils.getExtension(damcFile1FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile1FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}
					}
					if(i == 2)
					{
						//保存问题图片
						if (null != damcFile2) {
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile2);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile2FileName);
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
							String fileName = user.getLoginName() + format.format(new Date());
							String ext = FilenameUtils.getExtension(damcFile2FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile2FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}
					}
					if(i == 3)
					{
						//保存问题图片
						if (null != damcFile3) {
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile3);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile3FileName);
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
							String fileName = user.getLoginName() + format.format(new Date());
							String ext = FilenameUtils.getExtension(damcFile3FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile3FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}
					}
					if(i == 4)
					{
						//保存问题图片
						if (null != damcFile4) {
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile4);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile4FileName);
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
							String fileName = user.getLoginName() + format.format(new Date());
							String ext = FilenameUtils.getExtension(damcFile4FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile4FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}
					}
					if(i == 5)
					{
						//保存问题图片
						if (null != damcFile5) {
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile5);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile5FileName);
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
							String fileName = user.getLoginName() + format.format(new Date());
							String ext = FilenameUtils.getExtension(damcFile5FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile5FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}
					}
					
					//保存问题的图片
					tmphkx.setWtid(entity);
					hdxlist.add(tmphkx);
				}
			}
			
			entity.setHdxlist(hdxlist);
			
			twjwtService.update(entity);

		} else {
			
			
			//保存问题图片
			entity.setWtmcFileName("");
			if (null != wtmcFile) {

				
				//上传图片并保存
				String path = ServletActionContext.getServletContext().getRealPath(
						"");
				InputStream inputStream = new FileInputStream(wtmcFile);
				OutputStream outputStream = new FileOutputStream(path
						+ "\\userfiles\\kjzt\\wtimg\\" + wtmcFileFileName);
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
				String fileName = user.getLoginName() + format.format(new Date());
				String ext = FilenameUtils.getExtension(wtmcFileFileName);
				File file = new File(path + "\\userfiles\\kjzt\\wtimg\\" + wtmcFileFileName);
				file.renameTo(new File(path + "\\userfiles\\kjzt\\wtimg\\" + fileName + "."
						+ ext));
				
				
				entity.setWtmcFileName(fileName + "." + ext);
				
			}
			
			
			//同时保存问题的回答项
			Set<Twjwthdx> hdxlist = new HashSet<Twjwthdx>();
			for(int i = 0 ; i < 10; i++)
			{
				String dadm = Struts2Utils.getParameter("dadm"+i);
				String damc = Struts2Utils.getParameter("damc"+i);
				String sfzqda = Struts2Utils.getParameter("sfzqda"+i);
				String dafs = Struts2Utils.getParameter("dafs"+i);
				if(!StringUtils.isEmpty(dadm) && !StringUtils.isEmpty(damc.trim()) && !StringUtils.isEmpty(sfzqda) && !StringUtils.isEmpty(dafs))
				{
					Twjwthdx tmphkx = new Twjwthdx();
					tmphkx.setDadm(dadm);
					tmphkx.setBz(String.valueOf(i));
					tmphkx.setDamc(damc.replace("\"", "'"));
					tmphkx.setSfzqda(sfzqda);
					tmphkx.setDafs(dafs);
					
					//保存问题的图片
					tmphkx.setDamcFileName("");
					if(i == 0)
					{
						//保存问题图片
						if (null != damcFile0) {
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile0);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile0FileName);
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
							String fileName = user.getLoginName() + format.format(new Date());
							String ext = FilenameUtils.getExtension(damcFile0FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile0FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}
					}
					if(i == 1)
					{
						//保存问题图片
						if (null != damcFile1) {
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile1);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile1FileName);
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
							String fileName = user.getLoginName() + format.format(new Date());
							String ext = FilenameUtils.getExtension(damcFile1FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile1FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}
					}
					if(i == 2)
					{
						//保存问题图片
						if (null != damcFile2) {
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile2);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile2FileName);
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
							String fileName = user.getLoginName() + format.format(new Date());
							String ext = FilenameUtils.getExtension(damcFile2FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile2FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}
					}
					if(i == 3)
					{
						//保存问题图片
						if (null != damcFile3) {
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile3);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile3FileName);
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
							String fileName = user.getLoginName() + format.format(new Date());
							String ext = FilenameUtils.getExtension(damcFile3FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile3FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}
					}
					if(i == 4)
					{
						//保存问题图片
						if (null != damcFile4) {
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile4);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile4FileName);
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
							String fileName = user.getLoginName() + format.format(new Date());
							String ext = FilenameUtils.getExtension(damcFile4FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile4FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}
					}
					if(i == 5)
					{
						//保存问题图片
						if (null != damcFile5) {
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile5);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile5FileName);
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
							String fileName = user.getLoginName() + format.format(new Date());
							String ext = FilenameUtils.getExtension(damcFile5FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile5FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}
					}
					
					
					//保存问题的图片
					
					
					tmphkx.setWtid(entity);
					hdxlist.add(tmphkx);
					//twjwthdxService.save(tmphkx);
				}
			}
			entity.setHdxlist(hdxlist);
			
			twjwtService.save(entity);
		}
		
			
		
		Struts2Utils.setPromptInfoToReq("保存成功");

		return RELOAD;
	}
	
	public String getHdxjson()
	{
		try{
			if(StringUtils.isEmpty(id))
			{
				Struts2Utils.renderText("");
				
			}else{
				Twjwt tmpwt = twjwtService.getById(id);
				List tmpls1 = new ArrayList(tmpwt.getHdxlist());
				if(tmpls1 != null && tmpls1.size() > 0)
				{
					String tmpval = Struts2Utils.listToJson(tmpls1, "damc","sfzqda","dafs","bz","damcFileName");
					Struts2Utils.renderText(tmpval);
				}
				
			}
		}catch(Exception e)
		{
			Struts2Utils.renderText("");
		}

		return null;
	}
	
	/**
	 * 填写问卷
	 * @return
	 */
	public String txwj()
	{
		faflSelect = Struts2Utils.getParameter("flid");
		menus = twjwtService.getRandwt(null);
		return "txwj";
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
			List<Twjfafl> allfl = twjfaflService.findAll();
			
			for (int row = 1; row <= sheet.getLastRowNum(); row++) {
				// 数据是否正确
				boolean rgflg = true;

				Twjwt wjwt = new Twjwt();
				List<Twjwthdx> hdxls = new ArrayList<Twjwthdx>();
				String zqdaStr = "";
				
				wjwt.setSxh(row);// 默认显示顺序
				wjwt.setWtlx("1");// 默认单选题
				wjwt.setJfgzdm("1");// 默认记分规则

				// 取得行
				HSSFRow rowObj = sheet.getRow(row);
				if (rowObj == null) {
					continue;
				}

				// 一行共10列
				for (int i = 0; i < 10; i++) {
					String value = null;
					HSSFCell cell = rowObj.getCell(i);
					
					if (cell != null) {

						String cellValue = rowObj.getCell(i).toString().trim();
						if (StringUtils.isBlank(cellValue)) {
							cell = null;
						}
					}

					// wtfl
					if (i == 0) {
						// 必填
						if (cell != null) {
							value = rowObj.getCell(i).toString().trim();
						} else {
							errList.add("第" + (row + 1) + "行，第" + (i + 1)
									+ "列，必添项未填");
							rgflg = false;
						}
						// 是否是合法的分类
						if (rgflg) {
							boolean flsearch = false;
							for (Twjfafl tmpfl : allfl) {
								if (tmpfl.getFlmc().equals(value)) {
									Twjfafl fl=new Twjfafl();
									flsearch = true;
									fl=tmpfl;
									value = rowObj.getCell(i+1).toString().trim();
									if(fl.getChildren()!=null&&fl.getChildren().size()>0){
										System.out.println(fl.getChildren().size());
										for(Twjfafl wtfl:fl.getChildren()){
											System.out.println(wtfl.getFlmc());
											if(wtfl.getFlmc().equals(value)){
												flsearch=true;
												wjwt.setFaflid(wtfl);
											}
										}
									}else{
										errList.add("第" + (row + 2) + "行，第" + (i + 2)
												+ "列，未找到您填写的课程章节");
										rgflg = false;
									}
									
								}
							}
							if (!flsearch) {
								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，未找到您填写的课程");
								rgflg = false;
							}
						}
					}
					
					//问题章节
					if (i == 1) {
						// 必填
						if (cell != null) {
							value = rowObj.getCell(i).toString().trim();
							if (value.length() > 400) {
								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，长度不超过400个字符");
								rgflg = false;
							}
						} else {
							errList.add("第" + (row + 1) + "行，第" + (i + 1)
									+ "列，必添项未填");
							rgflg = false;
						}
						// 验证成功
						/*if (rgflg) {
							boolean flsearch = false;
							if(fl.getChildren()!=null&&fl.getChildren().size()>0){
								System.out.println(fl.getChildren().size());
								for(Twjfafl wtfl:fl.getChildren()){
									System.out.println(wtfl.getFlmc());
									if(wtfl.getFlmc().equals(value)){
										flsearch=true;
										wjwt.setFaflid(wtfl);
									}
								}
							}
							if (!flsearch) {
								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，未找到您填写的课程章节");
								rgflg = false;
							}
						}*/

					}
					
					
					// wtmc
					if (i == 2) {
						// 必填
						if (cell != null) {
							value = rowObj.getCell(i).toString().trim();
							value = value.replace("\"", "\'");
							if (value.length() > 400) {

								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，长度不超过400个字符");
								rgflg = false;
							}
						} else {
							errList.add("第" + (row + 1) + "行，第" + (i + 1)
									+ "列，必添项未填");
							rgflg = false;
						}
						// 验证成功
						if (rgflg) {
							wjwt.setWtmc(value);
							//wjwt.setWtmc(value.substring(value.indexOf("、")+1));
							//去掉1、这样的前辍
							//wjwt.setWtmc(value.substring(value.indexOf("、")+1));
						}

					}

					// wtjx
					if (i == 3) {
						// 必填
						if (cell != null) {
							value = rowObj.getCell(i).toString().trim();
							value = value.replace("\"", "\'");
							if (value.length() > 160) {

								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，长度不超过160个字符");
								rgflg = false;
							}
						}
						// 验证成功
						if (rgflg) {
							wjwt.setBz(value);
							//twjwtService.save(wjwt);
						}

					}

					// 正确答案
					if (i == 4) {
						// 必填
						if (cell != null) {
							value = rowObj.getCell(i).toString().trim();
							if (value.length() > 1) {
								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，长度不超过1个字符");
								rgflg = false;
							}
						} else {
							errList.add("第" + (row + 1) + "行，第" + (i + 1)
									+ "列，必添项未填");
							rgflg = false;
						}
						if (rgflg) {
							zqdaStr = value;
						}
					}
					
					
					// 答案A
					if (i == 5) {
						// 必填
						if (cell != null) {
							value = rowObj.getCell(i).toString().trim();
							value = value.replace("\"", "\'");
							if (value.length() > 100) {
								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，长度不超过100个字符");
								rgflg = false;
							}
						} else {
							errList.add("第" + (row + 1) + "行，第" + (i + 1)
									+ "列，必添项未填");
							rgflg = false;
						}
						if (rgflg) {
							Twjwthdx tmphdx = new Twjwthdx();
							tmphdx.setDadm("A");
							tmphdx.setDamc(value);
							tmphdx.setWtid(wjwt);
							tmphdx.setSfzqda(zqdaStr.equals("A") ? "1" : "0");
							tmphdx.setBz("0");
							tmphdx.setDafs("0");
							tmphdx.setWtid(wjwt);
							hdxls.add(tmphdx);
							//twjwthdxService.save(tmphdx);
						}
					}

					// 答案B
					if (i == 6) {
						// 必填
						if (cell != null) {
							value = rowObj.getCell(i).toString().trim();
							value = value.replace("\"", "\'");
							if (value.length() > 100) {
								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，长度不超过100个字符");
								rgflg = false;
							}
						} else {
							errList.add("第" + (row + 1) + "行，第" + (i + 1)
									+ "列，必添项未填");
							rgflg = false;
						}
						if (rgflg) {
							Twjwthdx tmphdx = new Twjwthdx();
							tmphdx.setDadm("B");
							tmphdx.setDamc(value);
							tmphdx.setWtid(wjwt);
							tmphdx.setSfzqda(zqdaStr.equals("B") ? "1" : "0");
							tmphdx.setBz("1");
							tmphdx.setDafs("0");
							tmphdx.setWtid(wjwt);
							hdxls.add(tmphdx);
							//twjwthdxService.save(tmphdx);
						}
					}

					// 答案C
					if (i == 7) {
						// 必填
						if (cell != null) {
							value = rowObj.getCell(i).toString().trim();
							value = value.replace("\"", "\'");
							if (value.length() > 100) {
								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，长度不超过100个字符");
								rgflg = false;
							} else {
								if (rgflg) 
								{
									Twjwthdx tmphdx = new Twjwthdx();
									tmphdx.setDadm("C");
									tmphdx.setDamc(value);
									tmphdx.setWtid(wjwt);
									tmphdx.setSfzqda(zqdaStr.equals("C") ? "1" : "0");
									tmphdx.setBz("2");
									tmphdx.setDafs("0");
									tmphdx.setWtid(wjwt);
									hdxls.add(tmphdx);
									//twjwthdxService.save(tmphdx);
								}
							}

						}
					}

					// 答案D
					if (i == 8) {
						// 必填
						if (cell != null) {
							value = rowObj.getCell(i).toString().trim();
							value = value.replace("\"", "\'");
							if (value.length() > 100) {
								errList.add("第" + (row + 1) + "行，第" + (i + 1)
										+ "列，长度不超过100个字符");
								rgflg = false;
							} else {
								if (rgflg) 
								{
									Twjwthdx tmphdx = new Twjwthdx();
									tmphdx.setDadm("D");
									tmphdx.setDamc(value);
									tmphdx.setWtid(wjwt);
									tmphdx.setSfzqda(zqdaStr.equals("D") ? "1" : "0");
									tmphdx.setBz("3");
									tmphdx.setDafs("0");
									tmphdx.setWtid(wjwt);
									hdxls.add(tmphdx);
									//twjwthdxService.save(tmphdx);
								}
							}
						}
					}

						// 答案E
						if (i == 9) {
							// 必填
							if (cell != null) {
								value = rowObj.getCell(i).toString().trim();
								value = value.replace("\"", "\'");
								if (value.length() > 100) {
									errList.add("第" + (row + 1) + "行，第"
											+ (i + 1) + "列，长度不超过100个字符");
									rgflg = false;
								} else {
									if (rgflg) 
									{
										Twjwthdx tmphdx = new Twjwthdx();
										tmphdx.setDadm("E");
										tmphdx.setDamc(value);
										tmphdx.setWtid(wjwt);
										tmphdx.setSfzqda(zqdaStr.equals("E") ? "1"
												: "0");
										tmphdx.setBz("4");
										tmphdx.setDafs("0");
										tmphdx.setWtid(wjwt);
										hdxls.add(tmphdx);
										//twjwthdxService.save(tmphdx);
									}
									
								}
							}
						}

						// 答案F
						if (i == 10) {
							// 必填
							if (cell != null) {
								value = rowObj.getCell(i).toString().trim();
								value = value.replace("\"", "\'");
								if (value.length() > 100) {
									errList.add("第" + (row + 1) + "行，第"
											+ (i + 1) + "列，长度不超过100个字符");
									rgflg = false;
								} else {
									if (rgflg) 
									{
										Twjwthdx tmphdx = new Twjwthdx();
										tmphdx.setDadm("F");
										tmphdx.setDamc(value);
										tmphdx.setWtid(wjwt);
										tmphdx.setSfzqda(zqdaStr.equals("F") ? "1"
												: "0");
										tmphdx.setBz("5");
										tmphdx.setDafs("0");
										tmphdx.setWtid(wjwt);
										hdxls.add(tmphdx);
										//twjwthdxService.save(tmphdx);
									}
									
								}
							}
						}
						
				}
				
				if (rgflg) {
					wjwt.setHdxlist(new HashSet<Twjwthdx>(hdxls));
					twjwtService.save(wjwt);
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
	
	/**
	 * 题目导入模板下载
	 */
	public String drportDate() throws Exception
	{
		
		try {
			String url = Struts2Utils.getSession().getServletContext()
					.getRealPath("/userfiles/imp");

			String scdz = "\\kjzt\\题目导入Excel模板.xls";
			dzurl = url + scdz;
			File file = new File(url + scdz);
			if (!file.exists()) {
				Struts2Utils.setPromptInfo("下载文件不存在，请联系管理人员！");
				return tmimp();
			}
			
			wjname=new String("题目导入Excel模板.xls".getBytes(),"ISO8859-1");
					
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

}
