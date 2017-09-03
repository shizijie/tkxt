package org.pfw.framework.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.h2.constant.SysProperties;

import com.opensymphony.xwork2.ActionSupport;

import org.pfw.framework.dao.security.UserDao;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.service.security.UserService;
import org.pfw.framework.util.PFWSecurityUtils;
import org.pfw.framework.wjgl.dao.TwjwtDao;
import org.pfw.framework.wjgl.domain.ContactWebTopic;
import org.pfw.framework.wjgl.domain.Csjilu;
import org.pfw.framework.wjgl.domain.Csrw;
import org.pfw.framework.wjgl.domain.Taoti;
import org.pfw.framework.wjgl.domain.Ttcsjg;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjjgtxr;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.domain.Xuesheng;
import org.pfw.framework.wjgl.service.CsrwService;
import org.pfw.framework.wjgl.service.TaotiService;
import org.pfw.framework.wjgl.service.TtcsjgService;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.pfw.framework.wjgl.service.TwjjgService;
import org.pfw.framework.wjgl.service.TwjjgtxrService;
import org.pfw.framework.wjgl.service.TwjwtService;
import org.pfw.framework.wjgl.service.TwjyhctjService;
import org.pfw.framework.wjgl.service.XueshengService;
import org.pfw.framework.xtgl.dao.DictDao;
import org.pfw.framework.xtgl.domain.Dict;
import org.springframework.beans.factory.annotation.Autowired;


@Results( { @Result(name =ActionSupport.LOGIN,location="login.action", type = "redirect"),
			@Result(name="reload",location="home!index2.action",type = "redirect")
		})
public class ExamAction extends ActionSupport{
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
	private TaotiService taotiService;
	@Autowired
	private TtcsjgService ttService;
	@Autowired
	private XueshengService xsService;
	@Autowired
	protected DictDao dictDao;
	
	private List<Twjfafl> allfl;
	private User usr;

	private String faflid;
	private String ttid;
	private List<Twjwt> listwt;
	private Ttcsjg ttcsjg;
	//private 
	private Twjfafl fl;




	public Twjfafl getFl() {
		return fl;
	}



	public void setFl(Twjfafl fl) {
		this.fl = fl;
	}



	public Ttcsjg getTtcsjg() {
		return ttcsjg;
	}



	public void setTtcsjg(Ttcsjg ttcsjg) {
		this.ttcsjg = ttcsjg;
	}



	public List<Twjwt> getListwt() {
		return listwt;
	}



	public void setListwt(List<Twjwt> listwt) {
		this.listwt = listwt;
	}






	public String getTtid() {
		return ttid;
	}



	public void setTtid(String ttid) {
		this.ttid = ttid;
	}



	public String getFaflid() {
		return faflid;
	}



	public void setFaflid(String faflid) {
		this.faflid = faflid;
	}



	public User getUsr() {
		return usr;
	}
	


	public void setUsr(User usr) {
		this.usr = usr;
	}
	public List<Twjfafl> getAllfl() {
		return allfl;
	}
	public void setAllfl(List<Twjfafl> allfl) {
		this.allfl = allfl;
	}

	public String flselect()
	{
		usr = usrService.getUserByLoginName(PFWSecurityUtils.getCurrentUserName());
		allfl = flService.findAll();
		return "flselect";
	}
	public String zjlx() throws Exception
	{
		String flid = Struts2Utils.getParameter("flid");
		if(StringUtils.isNotEmpty(flid))
		{
			usr = usrService.getUserByLoginName(PFWSecurityUtils.getCurrentUserName());
			List wtls2 = twjwtService.getWtByfl(flid);
			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Twjwt.class, "id","wtlx","wtmc","bz","hdxlist","sfyx","isorder","wtmcFileName");
			String json=JSON.toJSONString(wtls2,filter);
			resultinfo = this.generateJson(null, true, "查询成功", json);
		}
		return "zjlx";
		
	}
	public List wtls;
	public List getWtls() {
		return wtls;
	}
	public void setWtls(List wtls) {
		this.wtls = wtls;
	}
	public String resultinfo;
	public String getResultinfo() {
		return resultinfo;
	}
	public void setResultinfo(String resultinfo) {
		this.resultinfo = resultinfo;
	}
	/**
	 * 模拟测试
	 * @return
	 * @throws Exception
	 */
	public String mncs() throws Exception
	{	
		faflid = Struts2Utils.getParameter("flid");
		fl=flService.getById(Integer.parseInt(faflid));
		usr = usrService.getUserByLoginName(PFWSecurityUtils.getCurrentUserName());
		List<Twjwt> wtls2 = twjwtService.getRandwt(fl);
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Twjwt.class, "id","wtlx","wtmc","bz","hdxlist","sfyx","isorder","wtmcFileName");
		String json=JSON.toJSONString(wtls2,filter);
		resultinfo = this.generateJson(null, true, "查询成功", json);
		return "mncs";
	}
	
	
	@Autowired
	private CsrwService csrwService;
	private Csrw csrw;
	public Csrw getCsrw() {
		return csrw;
	}
	public void setCsrw(Csrw csrw) {
		this.csrw = csrw;
	}
	/**
	 * 任务测试
	 * @return
	 * @throws Exception
	 */
	public String rwcs() throws Exception
	{
		usr = usrService.getUserByLoginName(PFWSecurityUtils.getCurrentUserName());
		String rwid = Struts2Utils.getParameter("rwid");
		csrw = csrwService.getById(rwid);
		Twjjgtxr record=wjjgtxrservice.getRecord(usr,csrw);
		if(record!=null){
			return "reload";
		}else{
			List wtls2 = new ArrayList(csrw.getTmlist());
			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Twjwt.class, "id","wtlx","wtmc","bz","hdxlist","sfyx","isorder","wtmcFileName");
			String json=JSON.toJSONString(wtls2,filter);
			List<ContactWebTopic> listTopic=new ArrayList(csrw.getSetContactWebTopic());
			SimplePropertyPreFilter filter2 = new SimplePropertyPreFilter(ContactWebTopic.class,"id","topicName","textField","setTopicContent","topicType");
			String json2=JSON.toJSONString(listTopic,filter2);
			//System.out.println(json2);
			resultinfo = this.generateJson(null, true, "查询成功", "{\"json1\":"+json+",\"json2\":"+json2+"}");
			return "rwcs";
		}
	}
	
	
	/**
	 * 错题练习
	 * @return
	 * @throws Exception
	 */
	public String ctlx() throws Exception
	{
		usr = usrService.getUserByLoginName(PFWSecurityUtils.getCurrentUserName());
		List wtls2 = yhctService.getCtbyusrname(PFWSecurityUtils.getCurrentUserName());
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Twjwt.class, "id","wtlx","wtmc","bz","hdxlist","sfyx","isorder","wtmcFileName");
		String json=JSON.toJSONString(wtls2,filter);
		resultinfo = this.generateJson(null, true, "查询成功", json);
		return "ctlx";
	}
	
	/**
	 *根据课程id获取套题 
	 * 
	 */
	public void gettaoti()throws Exception{
		String flid=Struts2Utils.getParameter("flid");
		List<Taoti> list=taotiService.getttlist(flid);
		String json=Struts2Utils.listToJson(list, "id","ttmc","remark");
		Struts2Utils.renderText(json);
	}
	
	/**
	 *根据课程id获取章节 
	 */
	public void getzhangjie()throws Exception{
		String kcid=Struts2Utils.getParameter("kcid");
		Twjfafl fl=flService.getById(Integer.parseInt(kcid));
		List<Twjfafl> list=new ArrayList<Twjfafl>(fl.getChildren());
		List<Twjfafl> list1=new ArrayList<Twjfafl>();
		for(Twjfafl wtfl:list){
			List<Object[]> obj=twjwtService.countDict(wtfl);
			String tiliang="";
			for(Object[] ob:obj){
				tiliang+=ob[0].toString()+":"+ob[1].toString()+",";
			}
			if(tiliang.length()==0){
				continue;
			}
			tiliang=tiliang.substring(0,tiliang.length()-1);
			wtfl.setTiliang(tiliang);
			list1.add(wtfl);
		}
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Twjfafl.class, "id","flmc","tiliang","sxh","tiliang");
		String json=JSON.toJSONString(list1,filter);
		Struts2Utils.renderText(json);
	
	}
	
	/**
	 *根据套题id进入套题测试界面 
	 *
	 */
	public String ttcs()throws Exception{
		ttid=Struts2Utils.getParameter("ttid");
		Taoti taoti=taotiService.getById(ttid);
		List<Twjwt> list = new ArrayList<Twjwt>(taoti.getTmlist());
		String tmpstr="";
		Ttcsjg tt=ttService.findbyxh(PFWSecurityUtils.getCurrentUserName(),ttid);			//查询是否有过做题记录
		int ttsl=0;
		String[] remark=taoti.getRemark().split(",");
		for(int i=0;i<remark.length;i++){
			String[] tm=remark[i].split("\\|");
			ttsl+=Integer.parseInt(tm[1]);
		}
		if(tt!=null&&Integer.parseInt(tt.getZtzl())<ttsl){
			ttcsjg=tt;
			String[] str1= tt.getZtjg().split("/");
			for(String s:str1){
				String[] str2=s.split(",");
				for(int i=0;i<list.size();i++){
					if(list.get(i).getId()==Integer.parseInt(str2[0])){
						if(str2[1].equals("0")){
							list.get(i).setSfyx("F");
							list.get(i).setTtbz(str2[2]);
							break;
						}
						if(str2[1].equals("1")){
							list.get(i).setSfyx("T");
							list.get(i).setTtbz(str2[2]);
							break;
						}
					}
				}
			}
			for(int i=0;i<list.size();i++){
				list.get(i).setSxh(i+1);
			}
			listwt=list;
		}
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Twjwt.class, "id","wtlx","wtmc","bz","hdxlist","sfyx","ttbz","isorder","wtmcFileName");
		String json=JSON.toJSONString(list,filter);
		resultinfo = this.generateJson(null, true, "查询成功", json);
		return "ttcs";
	}
	
	/**
	 *保存套题回答结果 
	 * 
	 */
	public void savettcsjg()throws Exception{
		String jg=Struts2Utils.getParameter("jg");
		System.out.println(jg);
		String ttid=Struts2Utils.getParameter("ttid");
		String zql=Struts2Utils.getParameter("zql");
		String cwl=Struts2Utils.getParameter("cwl");
		if(StringUtils.isNotEmpty(jg)){
			Xuesheng xs=xsService.getByXh(PFWSecurityUtils.getCurrentUserName());
			Ttcsjg entity=new Ttcsjg();
			usr = usrService.getUserByLoginName(PFWSecurityUtils.getCurrentUserName());
			entity.setZtr(xs);
			
			entity.setZtzql(zql);
			entity.setZtcwl(cwl);
			entity.setZtzl(Integer.parseInt(zql)+Integer.parseInt(cwl)+"");
			Date date=new Date();
			SimpleDateFormat sp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			entity.setZttime(sp.format(date));
			entity.setZtkm(taotiService.getById(ttid));
			Ttcsjg tt=ttService.findbyxh(PFWSecurityUtils.getCurrentUserName(),ttid);
			if(tt==null){
				entity.setZtjg(jg);
				ttService.save(entity);
			}else{
				String[] remark=taotiService.getById(ttid).getRemark().split(",");
				int zl=0;
				//计算套题题数
				for(int i=0;i<remark.length;i++){
					String num[]=remark[i].split("\\|");
					zl+=Integer.parseInt(num[1]);
				}
				if(Integer.parseInt(tt.getZtzl())==zl){
					entity.setZtjg(jg);
				}else{
					entity.setZtjg(tt.getZtjg()+"/"+jg);
				}
				entity.setId(tt.getId());
				ttService.update(entity);
			}
		}
		Struts2Utils.renderText("保存成功！");
		
	}
	
	/**
	 *清空答题进度 
	 */
	public void qingkong()throws Exception{
		String id=Struts2Utils.getParameter("id");
		Ttcsjg tt=ttService.findbyxh(PFWSecurityUtils.getCurrentUserName(),id);
		if(tt!=null){
			ttService.deleteById(tt.getId());
		}
		Struts2Utils.renderText("清空成功！！");
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
