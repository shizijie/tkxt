package org.pfw.framework.wjgl.web;


import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.service.security.UserService;
import org.pfw.framework.util.PFWSecurityUtils;
import org.pfw.framework.utils.DateUtil;
import org.pfw.framework.wjgl.dao.TwjwtDao;
import org.pfw.framework.wjgl.domain.Banji;
import org.pfw.framework.wjgl.domain.ContactWebTopic;
import org.pfw.framework.wjgl.domain.Twjjg;
import org.pfw.framework.wjgl.domain.Twjjgtxr;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.domain.Twjwthdx;
import org.pfw.framework.wjgl.service.BanjiService;
import org.pfw.framework.wjgl.service.ContactWebTopicService;
import org.pfw.framework.wjgl.service.CsrwService;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.pfw.framework.wjgl.service.TwjjgService;
import org.pfw.framework.wjgl.service.TwjjgtxrService;
import org.pfw.framework.wjgl.service.TwjwtService;
import org.pfw.framework.wjgl.service.TwjwthdxService;
import org.pfw.framework.wjgl.service.XueshengService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;


@Results( { @Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl", type = "freemarker"),
	@Result(name="success",location="/wjgl/twjfa.ftl",type = "freemarker"),
	@Result(name ="input",location="/wjgl/twjfa-input.ftl", type = "freemarker"),
	@Result(name ="dttj",location="/wjgl/twjjg-dttj.ftl", type = "freemarker"),
	@Result(name ="bjdttj",location="/wjgl/twjjg-bjdttj.ftl", type = "freemarker"),
	@Result(name ="preview",location="/wjgl/twjfa-yulan.ftl", type = "freemarker"),
	@Result(name ="dofafz",location="/util/winopen.ftl", type = "freemarker")
	})
public class TwjjgAction extends CrudActionSupport<Twjjg>{
	
	@Autowired
	private TwjjgService entityservice;
	@Autowired
	private TwjjgtxrService wjjgtxrservice;
	@Autowired
	private TwjwthdxService wthdxservice;
	@Autowired
	private TwjwtService wjwtservice;
	@Autowired
	private TwjfaflService faflService;
	@Autowired
	private TwjwtDao wjwtdao;
	@Autowired
	private UserService usrService;
	@Autowired
	protected BanjiService bjservice;
	@Autowired
	private XueshengService xsService;
	
	private String resultSelectStr;//所有选择题答案
	private String resultBlankStr;//所有填空答案
	private String tk2;//所有主观题填空
	private List allfl;
	private List<String> selfl;
	private List dttjls;
	private List<Banji> bjlist;
	private List bjdttjjgls;
	
	public List getBjdttjjgls() {
		return bjdttjjgls;
	}

	public void setBjdttjjgls(List bjdttjjgls) {
		this.bjdttjjgls = bjdttjjgls;
	}
	
	public List<Banji> getBjlist() {
		return bjlist;
	}

	public void setBjlist(List<Banji> bjlist) {
		this.bjlist = bjlist;
	}

	public List getDttjls() {
		return dttjls;
	}

	public void setDttjls(List dttjls) {
		this.dttjls = dttjls;
	}

	public List getSelfl() {
		return selfl;
	}

	public void setSelfl(List selfl) {
		this.selfl = selfl;
	}

	public List getAllfl() {
		return allfl;
	}

	public void setAllfl(List allfl) {
		this.allfl = allfl;
	}

	public String getResultSelectStr() {
		return resultSelectStr;
	}

	public void setResultSelectStr(String resultSelectStr) {
		this.resultSelectStr = resultSelectStr;
	}

	public String getResultBlankStr() {
		return resultBlankStr;
	}

	public void setResultBlankStr(String resultBlankStr) {
		this.resultBlankStr = resultBlankStr;
	}

	public String getTk2() {
		return tk2;
	}

	public void setTk2(String tk2) {
		this.tk2 = tk2;
	}


	@Override
	public String delete() throws Exception {
		
		try{
			if(checks!=null&&checks.size()>0){
				entityservice.delete(checks);
			}
			Struts2Utils.setPromptInfo("删除成功");
		}catch(Exception e)
		{
			Struts2Utils.setPromptInfo("删除失败,数据有关联,要想删除,请先删除关联数据。");
		}
		
		return execute();
		
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
		page = entityservice.findPage(page, filters);
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity = (Twjjg) entityservice.getById(id);
		} else {
			entity = new Twjjg();
		}
	}

	public String save()
	{
		return "";
	}
	
	/**
	 * 
	 * @author  xl 
	 * @date 创建时间：2017年5月17日 下午3:16:22 
	 * @Description 
	 * @parameter resultSelectStr:  tkt_12238:hdx_|233232||||:F,tkt_11996:hdx_323|32|32:F,tkt_12039:hdx_32re:F,tkt_12034:hdx_re|eer|:F,tkt_11945:hdx_errrrrr:F,tkt_11947:hdx_eee|fe:F,xzt_12158:hdx_39529:F,xzt_12017:hdx_39119:T,xzt_11958:hdx_38954:F,xzt_12139:hdx_39453:F,xzt_12191:hdx_39633:F,xzt_12241:hdx_40515:F,xzt_12504:hdx_40499:T,xzt_12152:hdx_39504:F,xzt_12216:hdx_39717:T,xzt_11963:hdx_38974|38972|38976:F,xzt_12230:hdx_39778|39775|39776:F,xzt_12166:hdx_39564|39568|39565:T,xzt_12030:hdx_39173|39175|39176:F,xzt_11964:hdx_38977|38979:F
	 * 			    问题类型_问题id : 回答项_答案1|答案2| : 是否正确(F:错误,T:正确)  ,  问题类型_问题id : 回答项_答案1|答案2| : 是否正确(F:错误,T:正确)
	 * @return
	 */
	public String savejg() throws Exception {
		String ipdz = PFWSecurityUtils.getIpAddr();
		DateUtil  du = new DateUtil();
    	String dateStr = du.format(du.CHN_DATE_TIME_EXTENDED_FORMAT); 
		//保存问卷填写人
		Twjjgtxr tmpwjjgtxr = new Twjjgtxr();
		tmpwjjgtxr.setDxsj(dateStr);
		tmpwjjgtxr.setIpdz(ipdz);
		tmpwjjgtxr.setTxr(PFWSecurityUtils.getCurrentUserName());
		
		User usr = usrService.getUserByLoginName(PFWSecurityUtils.getCurrentUserName());
		tmpwjjgtxr.setSjh(usr.getName());//暂时借手机号来用一下
		
		String faflSelect = Struts2Utils.getParameter("faflSelect");
		
		
		tmpwjjgtxr.setSjh(PFWSecurityUtils.getCurrentUser().getName());
		tmpwjjgtxr.setXuesheng(xsService.getByXh(PFWSecurityUtils.getCurrentUserName()));
		if(StringUtils.isNotEmpty(faflSelect))
		{
			tmpwjjgtxr.setFaflid(faflService.getById(Integer.parseInt(faflSelect)));
		}			
		String utidstr = Struts2Utils.getParameter("utidstr");
		
		utidstr = utidstr.substring(0,utidstr.length()-1);
		List<String> tmpls = Struts2Utils.split(utidstr, ",");
		String resultSelectStr = Struts2Utils.getParameter("resultSelectStr");
		System.out.println(resultSelectStr);
		tmpwjjgtxr.setTmzs(tmpls.size());
		String cwts = Struts2Utils.getParameter("cwts");
		tmpwjjgtxr.setCwsl(Integer.parseInt(cwts));
		String zqts = Struts2Utils.getParameter("zqts");
		tmpwjjgtxr.setZqsl(Integer.parseInt(zqts));		
		String grade=Struts2Utils.getParameter("grade");
		tmpwjjgtxr.setGrade(grade);
		wjjgtxrservice.save(tmpwjjgtxr);
		int flgi = 0;
		//所有题目id
		for (String wtidtmp : tmpls) {
			Twjwt tki = wjwtservice.getById(wtidtmp);
			Twjjg tr = new Twjjg();
			
			tr.setWtid(tki);
			tr.setTxrid(tmpwjjgtxr);
			tr.setBz(flgi);
			
			//得到问题描述json
			String wtjson = Struts2Utils.beanToJson1(tki, "wtlx","wtmc","bz","hdxlist[dadm,damc,sfzqda,bz]");
			tr.setWtms(wtjson);
			
			String yhhdstr = "";
			String zqdastr = "";
			float fs = 0;
			String[] str=resultSelectStr.split(",");
			if(tki.getWtlx().equals("填空题")){
				for(String s:str){
					if(StringUtils.isBlank(s)||s.indexOf("tkt_")==-1){
						continue;
					}
					if(s.indexOf(tki.getId()+"")>=0){
						String[] str2=s.split(":");
						tr.setYhhd(str2[1].substring(str2[1].indexOf("hdx_")+4).replaceAll("\\|",","));
						if(str2[2].equals("T")){
							tr.setSfzq("1");
						}else{
							tr.setSfzq("0");
						}
						break;
					}
				}
			}else{
				for(Twjwthdx tmphdx : tki.getHdxlist())
				{
					if(resultSelectStr.indexOf(tmphdx.getId()+"") >= 0)
					{
						yhhdstr += tmphdx.getDadm() + ",";
					}
				}
				if(!yhhdstr.equals("")){
					tr.setYhhd(yhhdstr.substring(0,yhhdstr.length()-1)); //用户回答
				}
				for(String s:str){
					if(StringUtils.isBlank(s)||s.indexOf("tkt_")!=-1){
						continue;
					}
					if(s.indexOf(tki.getId()+"")>=0){
						String[] str2=s.split(":");
						if(str2[2].equals("T")){
							tr.setSfzq("1");
						}else{
							tr.setSfzq("0");
						}
						break;
					}
				}
			}
			
			flgi++;

			entityservice.save(tr);

		}


		Struts2Utils.setPromptInfoToReq("提交成功，感谢您的参与");
		return RELOAD;
	}
	
	@Autowired
	private CsrwService rwService;
	@Autowired
	private ContactWebTopicService contactWebTopicService;
	
	public String savejgcsrw() throws Exception {
		String ipdz = PFWSecurityUtils.getIpAddr();
		DateUtil  du = new DateUtil();
    	String dateStr = du.format(du.CHN_DATE_TIME_EXTENDED_FORMAT); 
		//保存问卷填写人
		Twjjgtxr tmpwjjgtxr = new Twjjgtxr();
		tmpwjjgtxr.setDxsj(dateStr);
		tmpwjjgtxr.setIpdz(ipdz);
		tmpwjjgtxr.setTxr(PFWSecurityUtils.getCurrentUserName());
		
		User usr = usrService.getUserByLoginName(PFWSecurityUtils.getCurrentUserName());
		tmpwjjgtxr.setSjh(usr.getName());//暂时借手机号来用一下
		tmpwjjgtxr.setXuesheng(xsService.getByXh(PFWSecurityUtils.getCurrentUserName()));
		String rwSelect = Struts2Utils.getParameter("rwSelect");
		tmpwjjgtxr.setSjh(PFWSecurityUtils.getCurrentUser().getName());
		String faflid=Struts2Utils.getParameter("faflid");
		tmpwjjgtxr.setFaflid(faflService.getById(Integer.parseInt(faflid)));
		
		if(StringUtils.isNotEmpty(rwSelect))
		{
			wjjgtxrservice.deleteByCSRWid(rwSelect,PFWSecurityUtils.getCurrentUserName());
			tmpwjjgtxr.setCsrw(rwService.getById(rwSelect));
		}			
		String utidstr = Struts2Utils.getParameter("utidstr");
		if(utidstr.length()>0){
			utidstr = utidstr.substring(0,utidstr.length()-1);
			List<String> tmpls = Struts2Utils.split(utidstr, ",");
		}
		String resultSelectStr = Struts2Utils.getParameter("resultSelectStr");
		tmpwjjgtxr.setTmzs(Integer.parseInt(Struts2Utils.getParameter("tmzs")));
		String cwts = Struts2Utils.getParameter("cwts");
		tmpwjjgtxr.setCwsl(Integer.parseInt(cwts));
		String zqts = Struts2Utils.getParameter("zqts");
		tmpwjjgtxr.setZqsl(Integer.parseInt(zqts));		
		String grade=Struts2Utils.getParameter("grade");
		tmpwjjgtxr.setGrade(grade);
		String topicIds=Struts2Utils.getParameter("topicIds");
		wjjgtxrservice.save(tmpwjjgtxr);
		int flgi = 0;
		if(utidstr.length()>0){
			utidstr = utidstr.substring(0,utidstr.length()-1);
			List<String> tmpls = Struts2Utils.split(utidstr, ",");
			for (String wtidtmp : tmpls) {
				Twjwt tki = wjwtservice.getById(wtidtmp);
				Twjjg tr = new Twjjg();
				
				tr.setWtid(tki);
				tr.setTxrid(tmpwjjgtxr);
				tr.setBz(flgi);
				
				//得到问题描述json
				String wtjson = Struts2Utils.beanToJson1(tki, "wtlx","wtmc","bz","hdxlist[dadm,damc,sfzqda,bz]");
				tr.setWtms(wtjson);
				
				String yhhdstr = "";
				String zqdastr = "";
				float fs = 0;
				String[] str=resultSelectStr.split(",");
				if(tki.getWtlx().equals("填空题")){
					for(String s:str){
						if(StringUtils.isBlank(s)||s.indexOf("tkt_")==-1){
							continue;
						}
						if(s.indexOf(tki.getId()+"")>=0){
							String[] str2=s.split(":");
							tr.setYhhd(str2[1].substring(str2[1].indexOf("hdx_")+4).replaceAll("\\|",","));
							if(str2[2].equals("T")){
								tr.setSfzq("1");
							}else{
								tr.setSfzq("0");
							}
							break;
						}
					}
				}else{
					for(Twjwthdx tmphdx : tki.getHdxlist())
					{
						if(resultSelectStr.indexOf(tmphdx.getId()+"") >= 0)
						{
							yhhdstr += tmphdx.getDadm() + ",";
						}
					}
					if(!yhhdstr.equals("")){
						tr.setYhhd(yhhdstr.substring(0,yhhdstr.length()-1)); //用户回答
					}
					for(String s:str){
						if(StringUtils.isBlank(s)||s.indexOf("tkt_")!=-1){
							continue;
						}
						if(s.indexOf(tki.getId()+"")>=0){
							String[] str2=s.split(":");
							if(str2[2].equals("T")){
								tr.setSfzq("1");
							}else if(str2[2].equals("F")){
								tr.setSfzq("0");
							}
							break;
						}
					}
				}
				
				flgi++;
				entityservice.save(tr);
				
			}
			
		}
		if(StringUtils.isNotBlank(topicIds)){
			String[] topicId=topicIds.split(",");
			for(String tid:topicId){
				ContactWebTopic con=contactWebTopicService.getById(tid);
				Twjjg tr = new Twjjg();
				tr.setContactWebTopic(con);
				tr.setTxrid(tmpwjjgtxr);
				tr.setBz(flgi);
				String wtjson = Struts2Utils.beanToJson1(con, "topicName","textField");
				tr.setWtms(wtjson);
				String[] str=resultSelectStr.split(",");
				for(String strid:str){
					if(StringUtils.isBlank(strid)||strid.indexOf("delwtid_")==-1){
						continue;
					}
					if(strid.indexOf(con.getId()+"")>=0){
						String[] str2=strid.split(":");
						if(StringUtils.isBlank(str2[2])){
							
						}else{
							if(str2[2].equals("T")){
								tr.setSfzq("1");
							}else{
								tr.setSfzq("0");
							}
							System.out.println(str2[1]);
							tr.setYhhd(str2[1]);
						}
						break;
					}
					
				}
				flgi++;
				entityservice.save(tr);
			}
			
		}

		Struts2Utils.setPromptInfoToReq("提交成功，感谢您的参与");
		return RELOAD;
	}
	
	public String dttj()
	{
		String proStr = (String) Struts2Utils.getSession().getAttribute(
				"promptInfo");
		if (StringUtils.isNotEmpty(proStr)) {
			Struts2Utils.getRequest().setAttribute("promptInfo",
					Struts2Utils.getSession().getAttribute("promptInfo"));
		}
		Struts2Utils.getSession().removeAttribute("promptInfo");
		allfl = faflService.findAll();
		String substr = Struts2Utils.getParameter("sub");
		if(StringUtils.isNotEmpty(substr))
		{
			String tmpstr = "";
			if(selfl != null && selfl.size() > 0)
			{
				for(String str : selfl)
				{
					tmpstr += "'"+str+"',";
				}
			}
			if(!tmpstr.equals(""))
			{
				tmpstr = tmpstr.substring(0,tmpstr.length()-1);
			}
			dttjls = entityservice.getdttjls(tmpstr);
		}
		
		return "dttj";
	}
	

	public String bjdttj()
	{
		bjlist = bjservice.findAll();
		
		String substr = Struts2Utils.getParameter("sub");
		if(StringUtils.isNotEmpty(substr))
		{
			String ksrq = Struts2Utils.getParameter("ksrq");
			String jsrq = Struts2Utils.getParameter("jsrq");
			String bj = Struts2Utils.getParameter("bj");
			
			bjdttjjgls = entityservice.bjtjdj(ksrq,jsrq,bj);
			
		}
		
		return "bjdttj";
	}
	
	
	public String dttjexpt() throws Exception
	{
		String url = Struts2Utils.getSession().getServletContext()
				.getRealPath("/userfiles/print");
		String scdz = "\\kjzt\\答题统计结果1导出模板.xls";
		File file = new File(url + scdz);
		if (!file.exists()) {
			Struts2Utils.setPromptInfo("下载文件不存在，请联系管理人员！");
			return dttj();
		}
		
		FileInputStream fileInputStream = new FileInputStream(file);
		POIFSFileSystem pOIFSFileSystem = new POIFSFileSystem(
				fileInputStream);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(pOIFSFileSystem);
		
		HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
		hssfCellStyle.setAlignment((short) 2);
		hssfCellStyle.setVerticalAlignment((short) 1);
		hssfCellStyle.setBorderBottom((short) 1);
		hssfCellStyle.setBorderLeft((short) 1);
		hssfCellStyle.setBorderRight((short) 1);
		hssfCellStyle.setBorderTop((short) 1);
		/*
		HSSFFont font = hssfWorkbook.createFont();
		font.setFontName("宋体");
		font.setCharSet((byte) 10);
		font.setColor(HSSFColor.WHITE.index);
		HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
		hssfCellStyle.setFont(font);
		hssfCellStyle.setAlignment((short) 2);
		hssfCellStyle.setVerticalAlignment((short) 1);
		hssfCellStyle.setFillForegroundColor(HSSFColor.GREEN.index);
		hssfCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		hssfCellStyle.setBorderBottom((short) 1);
		hssfCellStyle.setBorderLeft((short) 1);
		hssfCellStyle.setBorderRight((short) 1);
		hssfCellStyle.setBorderTop((short) 1);
		hssfCellStyle.setWrapText(true);
		*/
		
		String tmpstr = "";
		if(selfl != null && selfl.size() > 0)
		{
			for(String str : selfl)
			{
				tmpstr += "'"+str+"',";
			}
		}
		if(!tmpstr.equals(""))
		{
			tmpstr = tmpstr.substring(0,tmpstr.length()-1);
		}
		dttjls = entityservice.getdttjls(tmpstr);
		
		if (dttjls != null && dttjls.size() > 0) 
		{
			HSSFSheet sheet = hssfWorkbook.getSheetAt(0);
			int rownum = 0;
			for(Iterator tmpit = dttjls.iterator();tmpit.hasNext();)
			{
				Object[] tmpobj = (Object[])tmpit.next();
				HSSFRow row = sheet.createRow(rownum+2);
				for (int i = 0; i < tmpobj.length-1; i++)
				{
					HSSFCell cell = row.createCell(i);
					cell.setCellValue(String.valueOf(tmpobj[i]));
					cell.setCellStyle(hssfCellStyle);
				}
				DecimalFormat decimalFormat=new DecimalFormat("");
				HSSFCell cell = row.createCell(5);
				float zql = Float.parseFloat(String.valueOf(tmpobj[3]))/Float.parseFloat(String.valueOf(tmpobj[2]));
				cell.setCellValue(decimalFormat.format(zql*100)+"%");
				cell.setCellStyle(hssfCellStyle);
				cell = row.createCell(6);
				float cwl = Float.parseFloat(String.valueOf(tmpobj[4]))/Float.parseFloat(String.valueOf(tmpobj[2]));
				cell.setCellValue(decimalFormat.format(cwl*100)+"%");
				cell.setCellStyle(hssfCellStyle);
				rownum++;
			}
			int maxRow = rownum+2;
			int maxCol = 7;
			String firC = "";
			String curC = "";
			/*
			// 行合并
			for (int i = 0; i < maxRow; i++) {

				for (int j = 0; j < maxCol; j++) {
					if (j == 0) {
						firC = sheet.getRow(i).getCell(j)
								.getStringCellValue();

					} else {
						curC = sheet.getRow(i).getCell(j)
								.getStringCellValue();
						if (curC.equals(firC)) {
							sheet.addMergedRegion(new CellRangeAddress(i,
									i, j - 1, j));
						}
						firC = curC;
					}
				}
			}
			*/

			// 列合并
			for (int i = 0; i < 1; i++) {

				for (int j = 2; j < maxRow; j++) {
					if (j == 2) {
						firC = sheet.getRow(j).getCell(i)
								.getStringCellValue();
					} else {
						curC = sheet.getRow(j).getCell(i)
								.getStringCellValue();
						if (curC.equals(firC)) {
							sheet.addMergedRegion(new CellRangeAddress(
									j - 1, j, i, i));
						}
						firC = curC;
					}
				}
			}
		}
		
		OutputStream os = Struts2Utils.getResponse().getOutputStream();
		

		try {
			Struts2Utils.getResponse().setContentType(
					"application/x-msdownload");
			Struts2Utils.getResponse().addHeader(
					"Content-Disposition",
					"attachment; fileName="
							+ new String("答题统计结果导出".getBytes(),"ISO8859_1")
							+ ".xls");
			hssfWorkbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			os.flush();
			os.close();
		}

		return null;
	}
	
	
}
