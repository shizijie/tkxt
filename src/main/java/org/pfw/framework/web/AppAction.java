package org.pfw.framework.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.pfw.framework.dao.security.UserDao;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.service.security.UserService;
import org.pfw.framework.util.PFWSecurityUtils;
import org.pfw.framework.utils.DateUtil;
import org.pfw.framework.utils.PFWEncrypt;
import org.pfw.framework.wjgl.dao.TwjwtDao;
import org.pfw.framework.wjgl.dao.TwjwxsjDao;
import org.pfw.framework.wjgl.dao.TwjyhctjDao;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjjg;
import org.pfw.framework.wjgl.domain.Twjjgtxr;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.domain.Twjwthdx;
import org.pfw.framework.wjgl.domain.Twjyhctj;
import org.pfw.framework.wjgl.service.BanjiService;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.pfw.framework.wjgl.service.TwjjgService;
import org.pfw.framework.wjgl.service.TwjjgtxrService;
import org.pfw.framework.wjgl.service.TwjwtService;
import org.pfw.framework.wjgl.service.TwjyhctjService;
import org.pfw.framework.wjgl.service.XueshengService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.opensymphony.xwork2.ActionSupport;

public class AppAction extends ActionSupport {
	
	@Autowired
	private TwjfaflService flService;
	@Autowired
	private TwjwtService twjwtService;
	@Autowired
	private TwjjgtxrService wjjgtxrservice;
	@Autowired
	private TwjjgService wjjgservice;
	@Autowired
	private UserDao usDao;
	@Autowired
	private TwjwtDao wjwtdao;
	@Autowired
	private TwjyhctjService yhctService;
	@Autowired
	private UserService usrService;
	@Autowired
	protected BanjiService bjservice;
	@Autowired
	private XueshengService xsService;
	@Autowired
	private TwjfaflService faflService;
	@Autowired
	private TwjjgService twjjgservice;
	@Autowired
	protected TwjyhctjService ctjService;
	@Autowired
	protected TwjyhctjDao wjyhctjDao;
	
	/**
	 * 处理用户登录
	 */
	public void dologin()
	{
		//报文格式
		String JsonPStr = Struts2Utils.getParameter("callback");
		boolean isSuccess = true;
		String promptinfo = "";
		String resultinfo = "";
		
		try{
			
			//JbxxJdbcSyn jdbcSyn=new JbxxJdbcSyn();
			
			String loginName = Struts2Utils.getParameter("loginName");
			String pssword = Struts2Utils.getParameter("password");
			String usertype = Struts2Utils.getParameter("usertype");//1 学生、2企业
			
			//tmp
			usertype = "1";
			
			User usrtmp = null;
			if(!StringUtils.isEmpty(loginName)&&!StringUtils.isEmpty(pssword)&&!StringUtils.isEmpty(usertype)){
				//学生登录查询视图表中的数据。
				if("1".equals(usertype)){
					/**真实环境需要打开******start***************/
					//usrtmp=jdbcSyn.findUser(loginName,PFWEncrypt.getMD5(pssword));
					/**真实环境需要打开******end*****************/
					
					/**供公司本地测试使用，真实环境需要注销******start***************/
					Criterion crit = Restrictions.eq("loginName", loginName);
					Criterion crit1 = Restrictions.eq("password", PFWEncrypt.getMD5(pssword));
					usrtmp = usDao.findUnique(crit,crit1);
					/**供公司本地测试使用，真实环境需要注销******end***************/
				}else if("2".equals(usertype)){//企业登录查询应用系统中的用户表
					Criterion crit = Restrictions.eq("loginName", loginName);
					Criterion crit1 = Restrictions.eq("password", PFWEncrypt.getMD5(pssword));
					
					usrtmp = usDao.findUnique(crit,crit1);
					
				}else{
					isSuccess = false;
					promptinfo = "无此用户类型，请重新选择！";
				}
				
				if(usrtmp == null)
				{
					isSuccess = false;
					promptinfo = "登录失败，用户名、密码不正确";
				}else{
					resultinfo = Struts2Utils.beanToJson1(usrtmp, "loginName","name","roleList[id,jsdm,name]");
					//resultinfo = Struts2Utils.beanToJson1(usrtmp, "loginName","name");
				}
			}else{
				isSuccess = false;
				promptinfo = "请输入完用户名、密码、用户类型，再登录！";
			}
			
			
		}catch(Exception e)
		{
			e.printStackTrace();
			isSuccess = false;
			promptinfo = "操作发生错误，请联系管理员";
		}
		Struts2Utils.renderJson(this.generateJson(JsonPStr, isSuccess, promptinfo, resultinfo));
	}
	
	/**
	 * if返回得到所有的分类
	 * else返回某一分类
	 */
	public void getallfl()
	{
		//报文格式
		String JsonPStr = Struts2Utils.getParameter("callback");
		boolean isSuccess = true;
		String promptinfo = "";
		String resultinfo = "";
		String kcid=Struts2Utils.getParameter("kcid");
		if(StringUtils.isEmpty(kcid)){
			try {
				List<Twjfafl> allfllist = flService.findAll();
				for(Twjfafl tw:allfllist){
					tw.setBz(ctjService.getNumber(tw));
				}
				SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Twjfafl.class, "id","flmc","sxh","bz","tiliang");
				resultinfo=JSON.toJSONString(allfllist,filter);
				promptinfo = "查询成功";
			} catch (Exception e) {
				e.printStackTrace();
				isSuccess = false;
				promptinfo = "操作发生错误，请联系管理员";
			}
			Struts2Utils.renderJson(this.generateJson(JsonPStr, isSuccess,promptinfo, resultinfo));
		}else{
			try {
				Twjfafl fl=flService.getById(Integer.parseInt(kcid));
				for(Twjfafl tw:fl.getChildren()){
					String str="";
					List<Object[]> obj=twjwtService.countDict(tw);
					if(obj.size()>0){
						int j=0;
						for(Object[] ob:obj){
							j++;
							if(j==1){
								str+=ob[0].toString().substring(0,1)+":"+ob[1].toString();
							}else{
								str+=","+ob[0].toString().substring(0,1)+":"+ob[1].toString();
							}
						}
					}
					tw.setBz(str);
				}
				SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Twjfafl.class, "id","flmc","sxh","bz","children");
				resultinfo=JSON.toJSONString(fl,filter);
				//resultinfo = Struts2Utils.beanToJson1(fl, "id","flmc","sxh","children[id,flmc,sxh,bz]");
				promptinfo = "查询成功";
			} catch (Exception e) {
				e.printStackTrace();
				isSuccess = false;
				promptinfo = "操作发生错误，请联系管理员";
			}
			Struts2Utils.renderJson(this.generateJson(JsonPStr, isSuccess,promptinfo, resultinfo));
		}
		
	}
	public void test()
	{
		
		String loginName = "admin";
		String wtid = "4028a48154b357da0154b35a623a0003";
		
		yhctService.save(loginName, wtid);
	}
	/**
	 * 每批得到5题
	 */
	public void getNexwt()
	{
		//报文格式
		String JsonPStr = Struts2Utils.getParameter("callback");
		boolean isSuccess = true;
		String promptinfo = "";
		String resultinfo = "";
		
		String ttcount = "0";
		try {
			
			String flid = Struts2Utils.getParameter("flid");
			String pageNo = Struts2Utils.getParameter("pageNo");
			
			int pageNum = 1;
			if(StringUtils.isNotEmpty(pageNo))
			{
				pageNum = Integer.parseInt(pageNo);
			}
			Page pg = new AppPage();
			pg.setPageNo(pageNum);
			pg.setPageSize(5);
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
			if(StringUtils.isNotEmpty(flid))
			{
				
				//PropertyFilter p1 = new PropertyFilter("EQS_faflid.id",flid);
				//filters.add(p1);
				//pg = twjwtService.findPage(pg, filters);//该章节下所有题目
				
				 wjwtdao.findPage(pg, "select t from Twjwt t where t.faflid.id=?", Integer.parseInt(flid));
				 
				ttcount = String.valueOf(pg.getTotalCount());
				List<Twjwt> wtls = pg.getResult();
				
				resultinfo = Struts2Utils.listToJson(wtls, "id","wtlx","wtmc","bz","hdxlist[id,dadm,damc,sfzqda]");
				promptinfo = "查询成功";
			}else{
				isSuccess = false;
				promptinfo = "没有选择分类";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
			promptinfo = "操作发生错误，请联系管理员";
		}
		Struts2Utils.renderJson(this.generateJson(JsonPStr, isSuccess,
				promptinfo, resultinfo,"\"totalCount\":"+"\""+ttcount+"\""));
		
	}
	
	
	
	/**
	 * 章节练习生成题目
	 */
	public void getZjlxtm()
	{
		//报文格式
		String JsonPStr = Struts2Utils.getParameter("callback");
		boolean isSuccess = true;
		String promptinfo = "";
		String resultinfo = "";
		
		try {
			
			String flid = Struts2Utils.getParameter("flid");
			if(StringUtils.isNotEmpty(flid))
			{
				//List wtls = twjwtService.getRandwt(flid, 10);
				List<Twjwt> wtls = twjwtService.getWtByfl(flid);//该章节下所有题目
				int i=0;
				for(Twjwt wt:wtls){
					i++;
					wt.setSxh(i);
					wt.setSfyx("false");
				}
				SimplePropertyPreFilter filter=new SimplePropertyPreFilter(Twjwt.class,"id","wtlx","wtmc","bz","sxh","wtmcFileName","sfyx","hdxlist","remark","isorder","first","inputValue");
				resultinfo=JSON.toJSONString(wtls,filter);
				//resultinfo = Struts2Utils.listToJson(wtls, "id","wtlx","wtmc","bz","sxh","wtmcFileName","sfyx","hdxlist[id,dadm,damc,sfzqda,bz,damcFileName]");
				promptinfo = "查询成功";
			}else{
				isSuccess = false;
				promptinfo = "没有选择分类";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
			promptinfo = "操作发生错误，请联系管理员";
		}
		Struts2Utils.renderJson(this.generateJson(JsonPStr, isSuccess,
				promptinfo, resultinfo));
		
	}
	
	
	/**
	 * 模拟测试
	 */
	public void getRandwt()
	{
		//报文格式
		String JsonPStr = Struts2Utils.getParameter("callback");
		boolean isSuccess = true;
		String promptinfo = "";
		String resultinfo = "";
		
		String ttcount = "0";
		try {
			
			String tms = Struts2Utils.getParameter("tms");
			String cxscflg = Struts2Utils.getParameter("cxscflg");
			if(StringUtils.isNotEmpty(tms))
			{
				//List wtls = twjwtService.getRandwt(flid, 10);
				String ids = "";
				
				if(StringUtils.isNotEmpty(cxscflg) && cxscflg.equals("0"))
				{
					ids = twjwtService.getRandwtId(Integer.parseInt(tms));//所有题目
					Struts2Utils.getSession().setAttribute("idsRand", ids);
				}else{
					ids = (String)Struts2Utils.getSession().getAttribute("idsRand");
				}
				
				String pageNo = Struts2Utils.getParameter("pageNo");
				int pageNum = 1;
				if(StringUtils.isNotEmpty(pageNo))
				{
					pageNum = Integer.parseInt(pageNo);
				}
				Page pg = new AppPage();
				pg.setPageNo(pageNum);
				pg.setPageSize(5);
				//pg.setPageSize(Integer.parseInt(tms));
				
				wjwtdao.findPage(pg, "select t from Twjwt t where t.id in ("+ids+")");
				 
				ttcount = String.valueOf(pg.getTotalCount());
				List<Twjwt> wtls = pg.getResult();
				SimplePropertyPreFilter filter=new SimplePropertyPreFilter(Twjwt.class,"id","wtlx","wtmc","bz","sxh","wtmcFileName","sfyx","hdxlist","remark","isorder","first","inputValue");
				resultinfo=JSON.toJSONString(wtls,filter);
				//resultinfo = Struts2Utils.listToJson(wtls, "id","wtlx","wtmc","bz","hdxlist[id,dadm,damc,sfzqda]");
				
				promptinfo = "查询成功";
			}else{
				isSuccess = false;
				promptinfo = "没有传入合法的题目数";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
			promptinfo = "操作发生错误，请联系管理员";
		}
		Struts2Utils.renderJson(this.generateJson(JsonPStr, isSuccess,
				promptinfo, resultinfo,"\"totalCount\":"+"\""+ttcount+"\""));
	}
	
	/**
	 * 错题练习
	 */
	public void getCtlxdwt()
	{
		//报文格式
		String JsonPStr = Struts2Utils.getParameter("callback");
		boolean isSuccess = true;
		String promptinfo = "";
		String resultinfo = "";
		
		String ttcount = "0";
		try {
			
			String ids = Struts2Utils.getParameter("ids");
			if(StringUtils.isNotEmpty(ids))
			{
				String pageNo = Struts2Utils.getParameter("pageNo");
				int pageNum = 1;
				if(StringUtils.isNotEmpty(pageNo))
				{
					pageNum = Integer.parseInt(pageNo);
				}
				Page pg = new AppPage();
				pg.setPageNo(pageNum);
				pg.setPageSize(5);
				
				wjwtdao.findPage(pg, "select t from Twjwt t where t.id in ("+ids+")");
				 
				ttcount = String.valueOf(pg.getTotalCount());
				List<Twjwt> wtls = pg.getResult();
				
				SimplePropertyPreFilter filter=new SimplePropertyPreFilter(Twjwt.class,"id","wtlx","wtmc","bz","sxh","wtmcFileName","sfyx","hdxlist");
				resultinfo=JSON.toJSONString(wtls,filter);
				//resultinfo = Struts2Utils.listToJson(wtls, "id","wtlx","wtmc","bz","hdxlist[id,dadm,damc,sfzqda]");
				
				promptinfo = "查询成功";
			}else{
				isSuccess = true;
				promptinfo = "没有错题";
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
			promptinfo = "操作发生错误，请联系管理员";
		}
		Struts2Utils.renderJson(this.generateJson(JsonPStr, isSuccess,
				promptinfo, resultinfo,"\"totalCount\":"+"\""+ttcount+"\""));
	}
	
	/**
	 * 保存结果
	 */
	public void savejg()
	{
		//报文格式
		String JsonPStr = Struts2Utils.getParameter("callback");
		boolean isSuccess = true;
		String promptinfo = "";
		String resultinfo = "";
		
		try {
			
			String ipdz = PFWSecurityUtils.getIpAddr();
			String utidstr = Struts2Utils.getParameter("utidstr");
			String resultSelectStr = Struts2Utils.getParameter("resultSelectStr");
			
			String faflSelect = Struts2Utils.getParameter("faflSelect");
			System.out.println(faflSelect);
			//时间
			DateUtil  du = new DateUtil();
	    	String dateStr = du.format(du.CHN_DATE_TIME_EXTENDED_FORMAT); 
	    	
			//保存问卷填写人
			Twjjgtxr tmpwjjgtxr = new Twjjgtxr();
			tmpwjjgtxr.setDxsj(dateStr); //时间
			tmpwjjgtxr.setIpdz(ipdz);	 //ip
			
			if(StringUtils.isNotEmpty(faflSelect))
			{
				tmpwjjgtxr.setFaflid(flService.getById(Integer.parseInt(faflSelect)));
			}
			wjjgtxrservice.save(tmpwjjgtxr);
			
			utidstr = utidstr.substring(0,utidstr.length()-1);
			List tmpls = Struts2Utils.split(utidstr, ",");
			System.out.println(tmpls.size());
			List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
			PropertyFilter p1 = new PropertyFilter("INC_id",tmpls);
			filters.add(p1);
			Page page = new Page();
			page.setPageSize(50);
			page = twjwtService.findPage(page, filters);
			
			List<Twjwt> wtlist = page.getResult();
			int flgi = 0;
			for (Twjwt tki : wtlist) {
				
				Twjjg tr = new Twjjg();
				
				tr.setWtid(tki);
				tr.setTxrid(tmpwjjgtxr);
				tr.setBz(flgi);
				
				//得到问题描述json
				String wtjson = Struts2Utils.beanToJson1(tki, "wtlx","wtmc","bz","hdxlist[id,dadm,damc,sfzqda,bz]");
				tr.setWtms(wtjson);
				
				String yhhdstr = "";
				String zqdastr = "";
				for(Twjwthdx tmphdx : tki.getHdxlist())
				{
					if(StringUtils.isNotEmpty(tmphdx.getSfzqda()) && tmphdx.getSfzqda().equals("1"))
						zqdastr += tmphdx.getDadm() + ",";
					
					if(resultSelectStr.indexOf(tmphdx.getId()) >= 0)
					{
						yhhdstr += tmphdx.getDadm() + ",";
					}
				}
				if(!yhhdstr.equals(""))
					tr.setYhhd(yhhdstr.substring(0,yhhdstr.length()-1));
				
				if(!yhhdstr.equals("") && !zqdastr.equals(""))
				{
					if(yhhdstr.equals(zqdastr))
						tr.setSfzq("1");
					else
						tr.setSfzq("0");
				}else{
					tr.setSfzq("0");
				}
				
				flgi++;

				wjjgservice.save(tr);

			}
			promptinfo = "保存成功";
			
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
			promptinfo = "操作发生错误，请联系管理员";
		}
		Struts2Utils.renderJson(this.generateJson(JsonPStr, isSuccess,
				promptinfo, resultinfo));
		
	}
	
	/**
	 * 得到Client端无效时间json 
	 */
	@Autowired
	private TwjwxsjDao twjlsyjdao;
	public void getwxsjjson()
	{
		//报文格式
		String JsonPStr = Struts2Utils.getParameter("callback");
		boolean isSuccess = true;
		String promptinfo = "";
		String resultinfo = "";
		
		try {
			List syjls = twjlsyjdao.getAll();
			resultinfo = Struts2Utils.listToJson(syjls, "sjlx","zhourq","zhourq2","kssj","jssj");
			promptinfo = "查询成功";
			
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
			promptinfo = "操作发生错误，请联系管理员";
		}
		Struts2Utils.renderJson(this.generateJson(JsonPStr, isSuccess,
				promptinfo, resultinfo));
	}
	
	/**
	 * 验证老师用机 
	 */
	public void yzlsyj()
	{
		//报文格式
		String JsonPStr = Struts2Utils.getParameter("callback");
		boolean isSuccess = false;
		String promptinfo = "";
		String resultinfo = "";
		
		try {
			String uname = Struts2Utils.getParameter("uname");
			String psswd = Struts2Utils.getParameter("psswd");
			
			if(StringUtils.isNotEmpty(uname) && StringUtils.isNotEmpty(psswd))
			{
				List syjls = twjlsyjdao.find(" from Twjlsyj where zh = ? and mm = ?", uname,psswd);
				if(syjls != null && syjls.size() > 0)
				{
					isSuccess = true;
					promptinfo = "查询成功";
				}else{
					isSuccess = false;
					promptinfo = "用户名、密码不正确";
				}
			}else{
				isSuccess = false;
				promptinfo = "用户名、密码不能为空";
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
			promptinfo = "操作发生错误，请联系管理员";
		}
		Struts2Utils.renderJson(this.generateJson(JsonPStr, isSuccess,
				promptinfo, resultinfo));
	}
	
	/**
	 * 提取模拟测试题目
	 * @author  xl 
	 * @date 创建时间：2017年3月23日 上午11:02:46 
	 * @Description  wtlx存测试者所选答案   sfyx存测试者是否填写过   
	 * @parameter   
	 * @return
	 */
	public void dotest()throws Exception{
		//报文格式
		String JsonPStr = Struts2Utils.getParameter("callback");
		boolean isSuccess = false;
		String promptinfo = "";
		String resultinfo = "";
		try {
			String kcid=Struts2Utils.getParameter("kcid");
			if(StringUtils.isNotEmpty(kcid)){
				Twjfafl kc=flService.getById(Integer.parseInt(kcid));
				List<Twjwt> listtm=twjwtService.getRandwt(kc);
				int i=0;
				for(Twjwt tw:listtm){
					i++;
					tw.setSxh(i);
					tw.setSfyx("false");
				}
				SimplePropertyPreFilter filter=new SimplePropertyPreFilter(Twjwt.class,"id","wtlx","wtmc","bz","sxh","wtmcFileName","sfyx","hdxlist","remark","isorder","first","inputValue");
				resultinfo=JSON.toJSONString(listtm,filter);
				promptinfo = "查询成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
			promptinfo = "操作发生错误，请联系管理员";
		}
		Struts2Utils.renderJson(this.generateJson(JsonPStr, isSuccess,
				promptinfo, resultinfo));
	}
	
	/**
	 * 手机端保存结果
	 * @author  xl 
	 * @date 创建时间：2017年3月28日 下午4:12:28 
	 * @Description 
	 * @parameter   resultSelectStr  题目间以/隔开 
	 * @return
	 */
	public void bcjg()throws Exception{
		String ipdz = PFWSecurityUtils.getIpAddr();          //ip
		String utidstr = Struts2Utils.getParameter("utidstr");         //题目id
		String resultSelectStr = Struts2Utils.getParameter("resultSelectStr");		//答题结果
		String faflSelect = Struts2Utils.getParameter("faflSelect");	//课程id
		DateUtil  du = new DateUtil();
    	String dateStr = du.format(du.CHN_DATE_TIME_EXTENDED_FORMAT);  //时间
    	String name =Struts2Utils.getParameter("name");
		String grade=Struts2Utils.getParameter("grade");
		Twjjgtxr tmpwjjgtxr = new Twjjgtxr();
		tmpwjjgtxr.setDxsj(dateStr);		//时间
		tmpwjjgtxr.setIpdz(ipdz);			//ip
		tmpwjjgtxr.setTxr(name); //填写人
		User usr = usrService.getUserByLoginName(name);
		tmpwjjgtxr.setSjh(usr.getName());//暂时借手机号来用一下
		tmpwjjgtxr.setXuesheng(xsService.getByXh(name));  //学生
		tmpwjjgtxr.setGrade(grade);
		if(StringUtils.isNotEmpty(faflSelect))
		{
			tmpwjjgtxr.setFaflid(faflService.getById(Integer.parseInt(faflSelect)));  //课程
		}
		
		utidstr = utidstr.substring(0,utidstr.length()-1);			//除去,
		List<String> tmpls = Struts2Utils.split(utidstr, ",");
		tmpwjjgtxr.setTmzs(tmpls.size());
		String cwts = Struts2Utils.getParameter("cwts");
		tmpwjjgtxr.setCwsl(Integer.parseInt(cwts));
		String zqts = Struts2Utils.getParameter("zqts");
		tmpwjjgtxr.setZqsl(Integer.parseInt(zqts));
		wjjgtxrservice.save(tmpwjjgtxr);
		int flgi = 0;
		//所有题目id
		for (String wtidtmp : tmpls) {
			Twjwt tki = twjwtService.getById(wtidtmp);
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
			twjjgservice.save(tr);
			

		}
		bcct();
		
	}
	
	/**
	 * 保存错题
	 * @author  xl 
	 * @date 创建时间：2017年3月29日 下午1:50:36 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public void bcct()throws Exception{
		try {
			String ctid=Struts2Utils.getParameter("ctid");
			String name =Struts2Utils.getParameter("name");
			if(StringUtils.isNotEmpty(ctid)&&StringUtils.isNotEmpty(name)){
				String[] str=ctid.split(",");
				for(int i=0;i<str.length;i++){
					if(StringUtils.isNotEmpty(str[i])){
						wjyhctjDao.getSession().createSQLQuery("delete from T_WJGL_YHCTJ  where userid = '"+usrService.getUserByLoginName(name).getId()+"' and wtid='"+str[i]+"'").executeUpdate();
						Twjyhctj ctj=new Twjyhctj();
						ctj.setCwwt(twjwtService.getById(str[i]));
						ctj.setUser(usrService.getUserByLoginName(name));
						ctjService.save(ctj);
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	/**
	 * 获取错题库题目
	 * @author  xl 
	 * @date 创建时间：2017年3月29日 下午5:15:50 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public void getctlxtm()throws Exception{
		String JsonPStr = Struts2Utils.getParameter("callback");
		boolean isSuccess = false;
		String promptinfo = "";
		String resultinfo = "";
		try {
			String flid=Struts2Utils.getParameter("flid");
			String name=Struts2Utils.getParameter("name");
			if(StringUtils.isNotEmpty(flid)&&StringUtils.isNotEmpty(name)){
				String id=usrService.getUserByLoginName(name).getId();
				String hql="select r.cwwt from Twjyhctj r where r.user.id= '"+id+"' and r.cwwt.faflid.fparent.id= '"+flid+"' order by r.id";
				List<Twjwt> list=wjyhctjDao.getSession().createQuery(hql.toString()).list();
				int i=0;
				for(Twjwt tw:list){
					i++;
					System.out.println(i);
					tw.setSxh(i);
					tw.setSfyx("false");
				}
				SimplePropertyPreFilter filter=new SimplePropertyPreFilter(Twjwt.class,"id","wtlx","wtmc","bz","sxh","wtmcFileName","sfyx","hdxlist","remark","isorder","first","inputValue");
				resultinfo=JSON.toJSONString(list,filter);
				promptinfo = "查询成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			isSuccess = false;
			promptinfo = "操作发生错误，请联系管理员";
		}
		Struts2Utils.renderJson(this.generateJson(JsonPStr, isSuccess,
				promptinfo, resultinfo));
		
	}
	
	/**
	 * 删除错题
	 * @author  xl 
	 * @date 创建时间：2017年3月30日 下午12:02:14 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public void delct()throws Exception{
		String id=Struts2Utils.getParameter("id");
		yhctService.delct(id);
	}
	
	/**
	 * 产生app需要的json字符串公共类
	 * 
	 * @param JsonPStr
	 * @param isSuccess
	 * @param promptinfo
	 * @param resultinfo
	 * @return
	 */
	public String generateJson(String JsonPStr,boolean isSuccess,String promptinfo,String resultinfo,String...extStr){
		String returnStr = "";
		if(StringUtils.isEmpty(resultinfo))
		{
			resultinfo = "{}";
		}
		String extstrs = "";
		for(String tmps : extStr)
		{
			extstrs += tmps + ",";
		}
		
		if(StringUtils.isNotEmpty(JsonPStr))
		{
			returnStr = JsonPStr+"({"+extstrs+"\"success\":\""+isSuccess+"\",\"promptinfo\":\""+promptinfo+"\",\"resultinfo\":"+resultinfo+"})";
			
		}else{
			returnStr = "{"+extstrs+"\"success\":\""+isSuccess+"\",\"promptinfo\":\""+promptinfo+"\",\"resultinfo\":"+resultinfo+"}";
			
		}
		System.out.println("===="+returnStr);
		return returnStr;
	}
	
}
