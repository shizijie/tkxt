package org.pfw.framework.wjgl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
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
import org.pfw.framework.wjgl.dao.TwjwthdxDao;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.domain.Twjwthdx;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.pfw.framework.wjgl.service.TwjwtService;
import org.pfw.framework.wjgl.service.TwjwthdxService;
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.xtgl.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;

@Results
({
		@Result(name = CrudActionSupport.RELOAD, location = "/util/winopen.ftl", type = "freemarker"),
		@Result(name = "success", location = "/wjgl/twjwt.ftl", type = "freemarker"),
		@Result(name ="list2",location="/wjgl/catalogtask.ftl", type = "freemarker"),
		@Result(name ="txwj",location="/wjgl/twjwt-txwj.ftl", type = "freemarker"),
		@Result(name ="tmimp",location="/wjgl/twjwt-tmimp.ftl", type = "freemarker"),
		@Result(name ="list2reload",location="supervision/supervision_item!list1.action?taskBatchID=${taskBatchID}", type = "redirect"),
		@Result(name = "input", location = "/wjgl/twjwt-input.ftl", type = "freemarker"),
		@Result(name="download",type="stream",
		params={"contentType","application/octet-stream;charset=utf-8","contentDisposition","attachment;filename=${wjname}","inputName","downloadFile","bufferSize","102410"}
		)
})
public class TwjwtAction extends CrudActionSupport<Twjwt> {

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
	private String[] oldimg;
	private List<Dict> dict;
	private List<Twjfafl> listKp=new ArrayList<Twjfafl>();
	private List<Twjfafl> allKp=new ArrayList<Twjfafl>();
	


	public List<Twjfafl> getListKp() {
		return listKp;
	}

	public void setListKp(List<Twjfafl> listKp) {
		this.listKp = listKp;
	}

	public List<Twjfafl> getAllKp() {
		return allKp;
	}

	public void setAllKp(List<Twjfafl> allKp) {
		this.allKp = allKp;
	}

	public List<Dict> getDict() {
		return dict;
	}

	public void setDict(List<Dict> dict) {
		this.dict = dict;
	}

	public String[] getOldimg() {
		return oldimg;
	}

	public void setOldimg(String[] oldimg) {
		this.oldimg = oldimg;
	}

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

	@Autowired
	private DictService dictService;
	@Override
	public String input() throws Exception {

		txJcpl = new ArrayList<String>();
		for (int i = 1; i <= 12; i++)
			txJcpl.add(new Integer(i).toString());

		jfgzM = twjwtService.getJfgzMap();

		allfl=twjfaflService.findkc();
		menus = twjfaflService.findAll();
		dict=dictService.findAllByDM("tx");
		if(StringUtils.isNotEmpty(id)){
			if(StringUtils.isNotEmpty(twjwtService.getById(id).getKnowledgePoint())){
				String[] str=twjwtService.getById(id).getKnowledgePoint().split(",");
				for(int i=0;i<str.length;i++){
					Twjfafl fl=twjfaflService.getByName(str[i],twjwtService.getById(id));
					listKp.add(fl);
				}
				Twjfafl tw=twjfaflService.getById(twjwtService.getById(id).getFaflid().getFparent().getId());
				allKp=twjfaflService.getALLKnow(tw);
			}
		}
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
		page = twjwtService.findPage(page, filters);
		jfgzM = twjwtService.getJfgzMap();
		menus = twjfaflService.findAll();
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
	private File damcFile6;
	private String damcFile6FileName;
	
	public File getDamcFile6() {
		return damcFile6;
	}

	public void setDamcFile6(File damcFile6) {
		this.damcFile6 = damcFile6;
	}

	public String getDamcFile6FileName() {
		return damcFile6FileName;
	}

	public void setDamcFile6FileName(String damcFile6FileName) {
		this.damcFile6FileName = damcFile6FileName;
	}

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

	@Autowired
	private TwjwthdxDao wthdxDao;
	
	@Override
	public String save() throws Exception {
		if (!StringUtils.isEmpty(id) && !id.equals("0")) {
			
			//删除问题回答项
			List<Twjwthdx> tt2 = new ArrayList<Twjwthdx>(
					entity.getHdxlist());
			entity.setHdxlist(null);
			twjwtService.update(entity);
			
			if (tt2 != null && tt2.size() > 0) {
				for (Twjwthdx tmphkx : tt2) {
					twjwthdxService.deleteById(String.valueOf(tmphkx.getId()));
				}
			}
			
			//保存问题图片
			if (null != wtmcFile) {
				entity.setWtmcFileName("");

				
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
				String fileName = user.getLoginName() + format.format(new Date())+"wt";
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
					
					if(i == 0)
					{	
						//保存问题图片
						if (null != damcFile0) {
							tmphkx.setDamcFileName("");
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
							String fileName = user.getLoginName() + format.format(new Date())+"0";
							String ext = FilenameUtils.getExtension(damcFile0FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile0FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}else{
							tmphkx.setDamcFileName(oldimg[0]);
						}
					}
					if(i == 1)
					{
						//保存问题图片
						if (null != damcFile1) {
							tmphkx.setDamcFileName("");
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
							String fileName = user.getLoginName() + format.format(new Date())+"1";
							String ext = FilenameUtils.getExtension(damcFile1FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile1FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}else{
							tmphkx.setDamcFileName(oldimg[1]);
						}
					}
					if(i == 2)
					{
						//保存问题图片
						if (null != damcFile2) {
							tmphkx.setDamcFileName("");
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
							String fileName = user.getLoginName() + format.format(new Date())+"2";
							String ext = FilenameUtils.getExtension(damcFile2FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile2FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}else{
							tmphkx.setDamcFileName(oldimg[2]);
						}
					}
					if(i == 3)
					{
						//保存问题图片
						if (null != damcFile3) {
							tmphkx.setDamcFileName("");
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
							String fileName = user.getLoginName() + format.format(new Date())+"3";
							String ext = FilenameUtils.getExtension(damcFile3FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile3FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}else{
							tmphkx.setDamcFileName(oldimg[3]);
						}
					}
					if(i == 4)
					{
						//保存问题图片
						if (null != damcFile4) {
							tmphkx.setDamcFileName("");
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
							String fileName = user.getLoginName() + format.format(new Date())+"4";
							String ext = FilenameUtils.getExtension(damcFile4FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile4FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}else{
							tmphkx.setDamcFileName(oldimg[4]);
						}
					}
					if(i == 5)
					{
						//保存问题图片
						if (null != damcFile5) {
							tmphkx.setDamcFileName("");
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
							String fileName = user.getLoginName() + format.format(new Date())+"5";
							String ext = FilenameUtils.getExtension(damcFile5FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile5FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}else{
							tmphkx.setDamcFileName(oldimg[5]);
						}
					}
					
					if(i == 6)
					{
						//保存问题图片
						if (null != damcFile6) {
							tmphkx.setDamcFileName("");
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile6);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile6FileName);
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
							String fileName = user.getLoginName() + format.format(new Date())+"6";
							String ext = FilenameUtils.getExtension(damcFile6FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile6FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}else{
							tmphkx.setDamcFileName(oldimg[6]);
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
				String fileName = user.getLoginName() + format.format(new Date())+"wt";
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
							String fileName = user.getLoginName() + format.format(new Date())+"0";
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
							String fileName = user.getLoginName() + format.format(new Date())+"1";
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
							String fileName = user.getLoginName() + format.format(new Date())+"2";
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
							String fileName = user.getLoginName() + format.format(new Date())+"3";
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
							String fileName = user.getLoginName() + format.format(new Date())+"4";
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
							String fileName = user.getLoginName() + format.format(new Date())+"5";
							String ext = FilenameUtils.getExtension(damcFile5FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile5FileName);
							file.renameTo(new File(path + "\\userfiles\\kjzt\\daimg\\" + fileName + "."
									+ ext));
							tmphkx.setDamcFileName(fileName + "." + ext);
						}
					}
					
					if(i == 6)
					{
						//保存问题图片
						if (null != damcFile6) {
							//上传图片并保存
							String path = ServletActionContext.getServletContext().getRealPath(
									"");
							InputStream inputStream = new FileInputStream(damcFile6);
							OutputStream outputStream = new FileOutputStream(path
									+ "\\userfiles\\kjzt\\daimg\\" + damcFile6FileName);
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
							String fileName = user.getLoginName() + format.format(new Date())+"6";
							String ext = FilenameUtils.getExtension(damcFile6FileName);
							File file = new File(path + "\\userfiles\\kjzt\\daimg\\" + damcFile6FileName);
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
	 * 根据章节id获取所有知识点
	 * @author  xl 
	 * @date 创建时间：2017年4月14日 下午4:46:12 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public void getKnowledge()throws Exception{
		String flid=Struts2Utils.getParameter("id");
		Twjfafl tw=twjfaflService.getById(twjfaflService.getById(Integer.parseInt(flid)).getFparent().getId());
		List<Twjfafl> list=twjfaflService.getALLKnow(tw);
		if(list!=null&&list.size()>0){
			Struts2Utils.renderText(Struts2Utils.listToJson(list, "id","flmc","fparent[flmc]"));
		}else{
			Struts2Utils.renderText("");
		}
	}
	
	/**
	 * 批量导入题目
	 * @author  xl 
	 * @date 创建时间：2017年4月17日 下午5:13:58 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public String intoTopic()throws Exception{
		try {
			succont=0;   //正确条数
			errcont=0;   //错误条数
			FileInputStream in=new FileInputStream(drfj);
			POIFSFileSystem sf=new POIFSFileSystem(in);
			HSSFWorkbook book=new HSSFWorkbook(sf);
			HSSFSheet sheet=book.getSheetAt(0);         //获取第一个表单
			
			errList=new ArrayList<String>();          //错误信息集合
			List<Twjfafl> allType=twjfaflService.findAll();
			//row=0为标题
			for(int row=1;row<=sheet.getLastRowNum();row++){  //row行
				//行为空跳至下一个
				HSSFRow rowObj = sheet.getRow(row);
				if(rowObj == null||rowObj.getCell(0)==null||StringUtils.isBlank(rowObj.getCell(0).toString())){
					continue;
				}
				
				boolean ok=true;
				Twjwt topic=new Twjwt();
				List<Twjwthdx> hdxls=new ArrayList<Twjwthdx>();   //选项信息
				String zqdaStr="";
				topic.setSxh(row);	 //记录顺序号
				topic.setJfgzdm("1");// 默认记分规则
				DecimalFormat df = new DecimalFormat("0");
				for(int column=0;column<14;column++){
					String value = null;
					//验证是否为空
					HSSFCell cell = rowObj.getCell(column);
					if(cell==null){
						if((column<=6&&column>10)&&StringUtils.isBlank(cell.toString().trim())){
							errList.add("第" + (row + 1) + "行，第" + (column + 1)+ "列，必添项未填");
							ok = false;
						}
					}else{
						switch (rowObj.getCell(column).getCellType()) {
			            case HSSFCell.CELL_TYPE_NUMERIC :
			            	value=df.format(cell.getNumericCellValue());
			                break;
			            case HSSFCell.CELL_TYPE_STRING :
			            	value = cell.getRichStringCellValue().getString();
			                break;
			            case HSSFCell.CELL_TYPE_FORMULA :
			            	value = cell.getCellFormula();
			                break;
						}
						
					}
					if(ok){
						//课程  章节
						if(column==0){
							if (value.length() > 300) {
								errList.add("第" + (row + 1) + "行，第" + (column + 1)+ "列，长度不超过300个字符");
								ok = false;
							}
							if(ok){
								boolean flsearch = false;
								//循环验证课程
								for (Twjfafl tmpfl : allType) {
									if (tmpfl.getFlmc().equals(value)) {
										Twjfafl fl=new Twjfafl();
										flsearch = true;
										fl=tmpfl;
										value = rowObj.getCell(1).toString().trim();
										//循环验证章节
										if (value.length() > 300) {
											errList.add("第" + (row + 1) + "行，第" + (column + 2)
													+ "列，长度不超过300个字符");
											ok = false;
										}
										if(ok){
											boolean have=false;
											if(fl.getChildren()!=null&&fl.getChildren().size()>0){
												for(Twjfafl wtfl:fl.getChildren()){
													if(wtfl.getFlmc().equals(value)){
														have=true;
														topic.setFaflid(wtfl);
														//验证知识点
														List<Twjfafl> listfl=twjfaflService.getALLKnow(wtfl.getFparent());
														if(listfl!=null&&listfl.size()>0){
															List<String> strlist=new ArrayList<String>();
															for(Twjfafl strfl:listfl){
																strlist.add(strfl.getFlmc());
															}
															value=rowObj.getCell(14).toString().trim();
															if (value.length() > 1000) {
																errList.add("第" + (row + 1) + "行，第" + (column + 2)
																		+ "列，长度不超过1000个字符");
																ok = false;
																break;
															}
															if(StringUtils.isBlank(value)){
																value="NULL";
															}else{
																String[] str=value.split(",");
																for(int i=0;i<str.length;i++){
																	if(!strlist.contains(str[i])){
																		errList.add("第" + (row + 1) + "行，第" + (column + 15)+ "列，未找到您填写的知识点");
																		ok = false;
																		break;
																	}
																}
															}
															if(ok){
																topic.setKnowledgePoint(value);
															}
														}else{
															errList.add("第" + (row + 1) + "行，第" + (column + 15)+ "列，未找到您填写的知识点");
															ok = false;
															break;
														}
													}
												}
											}else{
												errList.add("第" + (row + 1) + "行，第" + (column + 2)+ "列，未找到您填写的课程章节");
												ok = false;
											}
											if (!have) {
												errList.add("第" + (row + 1) + "行，第" + (column + 2)+ "列，未找到您填写的课程章节");
												ok = false;
											}
										}
										
									}
								}
								if (!flsearch) {
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，未找到您填写的课程");
									ok = false;
								}
							}
						}
						//题目
						if(column==2){
							if (value.length() > 500) {
								errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，长度不超过500个字符");
								ok = false;
							}
							if(ok){
								topic.setWtmc(value);
							}
						}
						//解析
						if(column==3){
							if(StringUtils.isBlank(value)){
								errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列为空");
								ok = false;
							}else{
								if (value.length() > 160) {
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，长度不超过160个字符");
									ok = false;
								}
							}
							if(ok){
								topic.setBz(value);
							}
							
						}
						//正确答案
						if(column==4){
							if(StringUtils.isBlank(value)){
								errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列为空");
								ok = false;
							}else{
								if (value.length() > 10) {
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，长度不超过10个字符");
									ok = false;
								}
							}
							if(ok){
								zqdaStr = value;
								if(zqdaStr.indexOf("无序")>=0&&zqdaStr.indexOf("有序")==-1){
									topic.setIsorder("0");
								}
							}
						}
						//A B C D E F g
						if(column>=5&&column<=11){
							if(StringUtils.isNotBlank(value)){
								if (value.length() > 200) {
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，长度不超过200个字符");
									ok = false;
								}
								if(ok){
									Twjwthdx tmphdx = new Twjwthdx();
									tmphdx.setDadm(sheet.getRow(0).getCell(column).toString().trim().substring(2,3));
									tmphdx.setDamc(value);
									tmphdx.setWtid(topic);
									tmphdx.setSfzqda(zqdaStr.indexOf(sheet.getRow(0).getCell(column).toString().trim().substring(2,3))>=0 ? "1" : "0");
									tmphdx.setBz((column-5)+"");
									tmphdx.setDafs("0");
									hdxls.add(tmphdx);
								}
							}
						}
						//题型
						if(column==12){
							if (value.length() > 32) {
								errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，长度不超过32个字符");
								ok = false;
							}
							if(ok){
								boolean have=false;
								for(Dict dict:dictService.findAllByDM("tx")){
									if(dict.getName().equals(value)){
										have=true;
										topic.setWtlx(value);
									}
								}
								if(!have){
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，未找到您填写的题型");
									ok=false;
								}
							}
						}
						//难易程度
						if(column==13){
							if (value.length() > 6) {
								errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，长度不超过6个字符");
								ok = false;
							}
							if(ok){
								boolean have=false;
								if(value.equals("容易")){
									topic.setNycd("A");
									have=true;
								}
								if(value.equals("一般")){
									topic.setNycd("B");
									have=true;
								}
								if(value.equals("中等")){
									topic.setNycd("C");
									have=true;
								}
								if(value.equals("较难")){
									topic.setNycd("D");
									have=true;
								}
								if(value.equals("困难")){
									topic.setNycd("E");
									have=true;
								}
								if(value.equals("无")){
									topic.setNycd("NULL");
									have=true;
								}
								if(StringUtils.isBlank(value)){
									topic.setNycd("一般");
									have=true;
								}
								if(!have){
									errList.add("第" + (row + 1) + "行，第" + (column + 1) + "列，未找到您填写的难度");
									ok=false;
								}
							}
						}
					}
				}
				if(ok){
					topic.setHdxlist(new HashSet<Twjwthdx>(hdxls));
					twjwtService.save(topic);
					succont++;
				}else{
					errcont++;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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
			//最后一行行号sheet.getLastRowNum()
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
										for(Twjfafl wtfl:fl.getChildren()){
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
     * JSON字符串特殊字符处理，比如：“\A1;1300” 
     * @param s 
     * @return String 
     */  
	public String string2Json(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '\"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '/':
				sb.append("\\/");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
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
