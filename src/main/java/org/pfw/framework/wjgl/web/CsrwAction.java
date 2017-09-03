package org.pfw.framework.wjgl.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Query;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.tools.CsrwToWord;
import org.pfw.framework.tools.WordGenerator;
import org.pfw.framework.wjgl.dao.TwjwtDao;
import org.pfw.framework.wjgl.domain.Banji;
import org.pfw.framework.wjgl.domain.ContactWebTopic;
import org.pfw.framework.wjgl.domain.Csrw;
import org.pfw.framework.wjgl.domain.JxType;
import org.pfw.framework.wjgl.domain.Jxtm;
import org.pfw.framework.wjgl.domain.Taoti;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.domain.Twjwthdx;
import org.pfw.framework.wjgl.domain.Xuesheng;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.wjgl.service.BanjiService;
import org.pfw.framework.wjgl.service.ContactWebTopicService;
import org.pfw.framework.wjgl.service.CsrwService;
import org.pfw.framework.wjgl.service.JxTypeService;
import org.pfw.framework.wjgl.service.JxtmService;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.pfw.framework.wjgl.service.TwjwtService;
import org.pfw.framework.wjgl.service.XueshengService;
import org.pfw.framework.xtgl.dao.DictDao;
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.xtgl.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;

import sun.misc.BASE64Encoder;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name="success",location="/wjgl/csrw-list.ftl",type = "freemarker"),
		@Result(name="sdzj",location="/wjgl/csrw-sdzj.ftl",type = "freemarker"),
		@Result(name="dysj",location="/wjgl/csrw-dysj.ftl",type = "freemarker"),
		@Result(name="kszj",location="/wjgl/csrw-kszj.ftl",type = "freemarker"),
		@Result(name ="input",location="/wjgl/csrw-input.ftl", type = "freemarker")
	})
public class CsrwAction extends CrudActionSupport<Csrw> {
	@Autowired
	protected CsrwService entityService;
	@Autowired
	protected BanjiService bjService;
	@Autowired
	protected TwjfaflService faflService;
	@Autowired
	protected TwjwtService wtService;
	@Autowired
	protected TwjwtDao wtDao;
	@Autowired
	protected DictDao dictDao;
	@Autowired
	private JxTypeService jxTypeService;
	@Autowired
	protected JxtmService jxtmService;
	@Autowired
	protected XueshengService xueshengService;
	@Autowired
	protected ContactWebTopicService contactWebTopicService;
	
	private List<Banji> bjlist;
	private List<Banji> selbjlist;
	private List<Twjwt> dxtmlist;
	private List<Twjwt> yxtmlist;
	private List<Twjfafl> fafllist;
	private Map<String,List<Twjwt>> resmap;
	private List<Dict> dict=new ArrayList<Dict>();
	private List<Csrw> csrw=new ArrayList<Csrw>();
	private List<Map> cswt=new ArrayList<Map>();
	private List<JxType> allfl;
	private String jxztNum;
	private List<Xuesheng> listStu=new ArrayList<Xuesheng>();
	private Map<String,List<Xuesheng>> mapStu=new HashMap<String,List<Xuesheng>>();
	public Map<String, List<Xuesheng>> getMapStu() {
		return mapStu;
	}

	public void setMapStu(Map<String, List<Xuesheng>> mapStu) {
		this.mapStu = mapStu;
	}

	public List<Xuesheng> getListStu() {
		return listStu;
	}

	public void setListStu(List<Xuesheng> listStu) {
		this.listStu = listStu;
	}

	public String getJxztNum() {
		return jxztNum;
	}

	public void setJxztNum(String jxztNum) {
		this.jxztNum = jxztNum;
	}

	public List<JxType> getAllfl() {
		return allfl;
	}

	public void setAllfl(List<JxType> allfl) {
		this.allfl = allfl;
	}

	public List<Map> getCswt() {
		return cswt;
	}

	public void setCswt(List<Map> cswt) {
		this.cswt = cswt;
	}

	public List<Csrw> getCsrw() {
		return csrw;
	}

	public void setCsrw(List<Csrw> csrw) {
		this.csrw = csrw;
	}

	public List<Dict> getDict() {
		return dict;
	}

	public void setDict(List<Dict> dict) {
		this.dict = dict;
	}

	public Map<String, List<Twjwt>> getResmap() {
		return resmap;
	}

	public void setResmap(Map<String, List<Twjwt>> resmap) {
		this.resmap = resmap;
	}

	public List<Twjwt> getYxtmlist() {
		return yxtmlist;
	}

	public void setYxtmlist(List<Twjwt> yxtmlist) {
		this.yxtmlist = yxtmlist;
	}
	
	public List<Twjwt> getDxtmlist() {
		return dxtmlist;
	}

	public void setDxtmlist(List<Twjwt> dxtmlist) {
		this.dxtmlist = dxtmlist;
	}

	public List<Twjfafl> getFafllist() {
		return fafllist;
	}

	public void setFafllist(List<Twjfafl> fafllist) {
		this.fafllist = fafllist;
	}

	public List getBjlist() {
		return bjlist;
	}

	public void setBjlist(List bjlist) {
		this.bjlist = bjlist;
	}

	public List getSelbjlist() {
		return selbjlist;
	}

	public void setSelbjlist(List selbjlist) {
		this.selbjlist = selbjlist;
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
		bjlist = new ArrayList<Banji>();
		bjlist = bjService.findAll();
		fafllist = faflService.findAll();
		if (!StringUtils.isEmpty(id))
		{
			String bjstr = entity.getKsbj();
			if(!StringUtils.isEmpty(bjstr))
			{
				selbjlist = new ArrayList<Banji>();
				List<String> bjlist1 = Struts2Utils.split(bjstr, ",");
				for(String bjid : bjlist1)
				{
					Banji bjobj = bjService.getById(bjid);
					selbjlist.add(bjobj);
					bjlist.remove(bjobj);
					mapStu.put(bjid, xueshengService.findxsinbj(bjid));
				}
			}
		}
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
			entity = (Csrw) entityService.getById(id);
		} else {
			entity = new Csrw();
		}
		
	}

	@Override
	public String save() throws Exception {
		String[] selbjArr = Struts2Utils.getRequest().getParameterValues("selbjstr");
		String selbjstr = "";
		String selbjstrmc = "";
		String stuStr="";
		int number=0;
		String addBox=Struts2Utils.getParameter("addBox");
		if(listStu.size()>0&&null!=listStu){
			for(int i=0,j=listStu.size();i<j;i++){
				if(null!=listStu.get(i)&&StringUtils.isNotBlank(listStu.get(i).getId())){
					stuStr+=listStu.get(i).getId()+",";
					number++;
				}
			}
		}
		if(StringUtils.isNotBlank(addBox)&&number==0){
			Struts2Utils.setPromptInfoToReq("请填写正确!!");
			return RELOAD;
		}
		if(selbjstr != null)
		{	
			for(String tmpstr : selbjArr)
			{
				Banji tmpobj = bjService.getById(tmpstr);
				selbjstrmc += tmpobj.getBjmc() + ",";
				selbjstr += tmpstr + ",";
			}
		}
		if(!selbjstr.equals(""))
			selbjstr = selbjstr.substring(0,selbjstr.length()-1);
		
		if(number==0){
			List<Xuesheng> list=xueshengService.getByIdStr(selbjstr);
			for(Xuesheng xs:list){
				stuStr+=xs.getId()+",";
			}
		}
		
		if(!selbjstrmc.equals(""))
			selbjstrmc = selbjstrmc.substring(0,selbjstrmc.length()-1);
		
		entity.setKsbj(selbjstr);
		entity.setKsbjmcs(selbjstrmc);
		entity.setTestStu(stuStr.substring(0,stuStr.length()-1));
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
	 * 自动组卷
	 * @throws Exception
	 */
	public void autoZj() throws Exception
	{
		String dxtsl = Struts2Utils.getParameter("dxtsl");
		String pdtsl = Struts2Utils.getParameter("pdtsl");
		String csrwid = Struts2Utils.getParameter("csrwid");
		
		if(StringUtils.isNotEmpty(csrwid)&&StringUtils.isNotEmpty(dxtsl)&&StringUtils.isNotEmpty(pdtsl))
		{
			Csrw rw = entityService.getById(csrwid);
			int faflid = rw.getFafl().getId();
			
			List<Twjwt> tmpls = wtService.getRandwt(faflid, Integer.parseInt(dxtsl), Integer.parseInt(pdtsl));
			
			//rw.setDxtsl(dxtsl);
			//rw.setPdtsl(pdtsl);
			rw.setTmlist(new HashSet(tmpls));
			
			entityService.update(rw);
			
			Struts2Utils.renderText("组卷成功");
			
		}
		
	}
	private Page dxtPage;
	private Page pdtPage;
	private Page zjPage;
	
	public Page getZjPage() {
		return zjPage;
	}

	public void setZjPage(Page zjPage) {
		this.zjPage = zjPage;
	}

	public Page getDxtPage() {
		return dxtPage;
	}

	public void setDxtPage(Page dxtPage) {
		this.dxtPage = dxtPage;
	}

	public Page getPdtPage() {
		return pdtPage;
	}

	public void setPdtPage(Page pdtPage) {
		this.pdtPage = pdtPage;
	}

	public String sdzj() throws Exception
	{
		if(StringUtils.isNotEmpty(id))
		{
			entity = (Csrw) entityService.getById(id);
			
			dxtmlist = wtDao.find("select distinct r from Twjwt r ,Csrw t where r.faflid.id = ? and r not in elements(t.tmlist) order by r.id", entity.getFafl().getId());
			yxtmlist = new ArrayList(entity.getTmlist());

			/*
			int pageSizedxt = Integer.parseInt(Struts2Utils
					.getParameter("dxtPage.pageSize") == null ? "15"
					: Struts2Utils.getParameter("dxtPage.pageSize"));
			int pageSizepdt = Integer.parseInt(Struts2Utils
					.getParameter("pdtPage.pageSize") == null ? "15"
					: Struts2Utils.getParameter("pdtPage.pageSize"));
			int pageSizezj = Integer.parseInt(Struts2Utils
					.getParameter("zjPage.pageSize") == null ? "15"
					: Struts2Utils.getParameter("zjPage.pageSize"));
			dxtPage = new Page(pageSizedxt);
			pdtPage = new Page(pageSizepdt);
			zjPage  = new Page(pageSizezj);
			
			List<PropertyFilter> filtersdxt = HibernateWebUtils
					.buildPropertyFilters(Struts2Utils.getRequest());
			List<PropertyFilter> filterspdt = HibernateWebUtils
					.buildPropertyFilters(Struts2Utils.getRequest());
			List<PropertyFilter> filterszj = HibernateWebUtils
					.buildPropertyFilters(Struts2Utils.getRequest());
			//单选题
			dxtPage.setOrderBy("id");
			dxtPage.setOrder(Page.ASC);
			dxtPage = wtDao.findPage(dxtPage, "select distinct r from Twjwt r ,Csrw t where r.faflid.id = ? and r.wtmc like '%单选题%' and r not in elements(t.tmlist) order by r.id", entity.getFafl().getId());
			//判断题
			pdtPage.setOrderBy("id");
			pdtPage.setOrder(Page.ASC);
			pdtPage = wtDao.findPage(pdtPage, "select distinct r from Twjwt r ,Csrw t where r.faflid.id = ? and r.wtmc like '%判断题%' and r not in elements(t.tmlist) order by r.id", entity.getFafl().getId());
			//已选题
			zjPage.setOrderBy("id");
			zjPage.setOrder(Page.ASC);
			zjPage = wtDao.findPage(pdtPage, "select distinct r from Twjwt r ,Csrw t where r.faflid.id = ? and r.wtmc like '%判断题%' and r not in elements(t.tmlist) order by r.id", entity.getFafl().getId());
			*/
		}
		
		return "sdzj";
	}
	/**
	 * 手动组卷保存
	 * @throws Exception
	 */
	public String sdzjSave() throws Exception
	{
		
		if(StringUtils.isNotEmpty(id))
		{
			Csrw rw = entityService.getById(id);
			String[] seltmArr = Struts2Utils.getRequest().getParameterValues("rightSide");
			List tmpls = new ArrayList();
			int dxt = 0;
			int pdt = 0;
			if(seltmArr != null && seltmArr.length > 0)
			{
				for(String tmpstr : seltmArr)
				{
					Twjwt tmpent = wtService.getById(tmpstr);
					if(tmpent.getWtmc().indexOf("单选题") >= 0)
						dxt++;
					if(tmpent.getWtmc().indexOf("判断题") >= 0)
						pdt++;
					
					tmpent.setId(Integer.parseInt(tmpstr));
					tmpls.add(tmpent);
				}
			}
			
			
			//rw.setDxtsl(String.valueOf(dxt));
			//rw.setPdtsl(String.valueOf(pdt));
			rw.setTmlist(new HashSet(tmpls));
			entityService.update(rw);
			
			Struts2Utils.setPromptInfoToReq("保存成功!!");
			
			return RELOAD;
			
		}
		return RELOAD;
		
	}
	
	/**
	 * 打印试卷
	 */
	public String dysj() throws Exception
	{
		prepareModel();
		//建个map 
		//遍历问题temLIST
		//得到问题。遍历hashMap
		resmap =new HashMap<String, List<Twjwt>>();
		Set<Twjwt> tmset = entity.getTmlist();		
		Iterator<Twjwt> iterator=tmset.iterator();
		while(iterator.hasNext()){
			Twjwt tmpent = (Twjwt)iterator.next();//得到问题
			String wtlx  = tmpent.getWtlx();	
			
			boolean sfcz = false;
			for (String key :resmap.keySet()) {  
				if(key.equals(wtlx)){
					resmap.get(key).add(tmpent);
					sfcz = true;
				}
			 }
			if(!sfcz)
			{
				List<Twjwt> tmpls = new ArrayList<Twjwt>();
				tmpls.add(tmpent);
				resmap.put(wtlx, tmpls);
			}
		}	
		Map map=null;
		if(null!=resmap.get("填空题")){
			map=new HashMap<String, List<Twjwt>>();
			map.put("填空题", resmap.get("填空题"));
			cswt.add(map);
		}
		if(null!=resmap.get("判断题")){
			map=new HashMap<String, List<Twjwt>>();
			map.put("判断题", resmap.get("判断题"));
			cswt.add(map);
		}
		if(null!=resmap.get("单选题")){
			map=new HashMap<String, List<Twjwt>>();
			map.put("单选题", resmap.get("单选题"));
			cswt.add(map);
		}
		if(null!=resmap.get("多选题")){
			map=new HashMap<String, List<Twjwt>>();
			map.put("多选题", resmap.get("多选题"));
			cswt.add(map);
		}
		if(null!=resmap.get("图片题")){
			map=new HashMap<String, List<Twjwt>>();
			map.put("图片题", resmap.get("图片题"));
			cswt.add(map);
		}
		Map<String, List<Jxtm>> mapJx =new HashMap<String, List<Jxtm>>();
		Set<Jxtm> setJxtm=entity.getSetJxtm();
		Iterator<Jxtm> it=setJxtm.iterator();
		while(it.hasNext()){
			Jxtm tmp = (Jxtm)it.next();//得到问题
			String wtlx  = tmp.getType().getTypeName();	
			
			boolean have = false;
			for (String key :mapJx.keySet()) {  
				if(key.equals(wtlx)){
					mapJx.get(key).add(tmp);
					have = true;
				}
			 }
			if(!have)
			{
				List<Jxtm> tmpl = new ArrayList<Jxtm>();
				tmpl.add(tmp);
				mapJx.put(wtlx, tmpl);
			}
		}
		if(StringUtils.isNotBlank(entity.getJxInfo())){
			String[] str=entity.getJxInfo().split(",");
			System.out.println(entity.getJxInfo());
			for(int i=0;i<str.length;i++){
				String[] str1=str[i].split(":");
				System.out.println(mapJx.get(str1[0]));
				if(str1.length>1&&null!=mapJx.get(str1[0])){
					map=new HashMap<String,List<Jxtm>>();
					map.put("机械制图-"+str1[0], mapJx.get(str1[0]));
					cswt.add(map);
				}
			}
		}
		/*if(setJxtm.size()>0&&null!=setJxtm){
			
			map=new HashMap<String,List<Jxtm>>();
			Jxtm tm=setJxtm.iterator().next();
			map.put("机械制图-"+tm.getType().getFparent().getFparent().getTypeName()+"-"+tm.getType().getFparent().getTypeName(), new ArrayList<Jxtm>(setJxtm));
			cswt.add(map);
		}*/
		return "dysj";
	}
	
	private String tmfbjson;
	private String ndfbjson;
	private String zsdfbjson;
	private String dictjson;
	public String getDictjson() {
		return dictjson;
	}

	public void setDictjson(String dictjson) {
		this.dictjson = dictjson;
	}

	public String getTmfbjson() {
		return tmfbjson;
	}

	public void setTmfbjson(String tmfbjson) {
		this.tmfbjson = tmfbjson;
	}

	public String getNdfbjson() {
		return ndfbjson;
	}

	public void setNdfbjson(String ndfbjson) {
		this.ndfbjson = ndfbjson;
	}

	public String getZsdfbjson() {
		return zsdfbjson;
	}

	public void setZsdfbjson(String zsdfbjson) {
		this.zsdfbjson = zsdfbjson;
	}
	
	/**
	 * 开始组卷
	 */
	public String kszj() throws Exception
	{
		prepareModel();
		
		//该任务-课程下题目分布
		List ls = entityService.getFb(entity.getFafl(),entityService.tmfbhql,1);
		tmfbjson = "";
		for(int i=0;i<ls.size();i++){
			Object[] tmpobj = (Object[]) ls.get(i);
			String tmlx = (String)tmpobj[0];
			String tmct = String.valueOf(tmpobj[1]);
			Dict lx=dictDao.findUnique("from Dict a where a.name='"+tmlx+"'");
			lx.setRemark(tmct);
			dict.add(lx);
			tmfbjson += "['"+tmlx+"',"+tmct+"],";
			
		}
		
		//该任务-课程下题目难度分布
		ls = entityService.getFb(entity.getFafl(),entityService.ndfbhql,1);
		ndfbjson = "";
		for(int i=0;i<ls.size();i++){
			Object[] tmpobj = (Object[]) ls.get(i);
			String tmlx = (String)tmpobj[0];
			String tmct = String.valueOf(tmpobj[1]);
			if(tmlx.equals("A"))
				tmlx = "容易";
			if(tmlx.equals("B"))
				tmlx = "一般";
			if(tmlx.equals("C"))
				tmlx = "中等";
			if(tmlx.equals("D"))
				tmlx = "较难";
			if(tmlx.equals("E"))
				tmlx = "困难";
			if(tmlx.equals("NULL"))
				tmlx = "无";
			ndfbjson += "['"+tmlx+"',"+tmct+"],";
			
		}
		
		if(!ndfbjson.equals(""))
			ndfbjson = ndfbjson.substring(0,ndfbjson.length()-1);
		
		//该任务-课程下题目知识点分布
		List<Twjfafl> know=faflService.getALLKnow(entity.getFafl());
		zsdfbjson = "";
		for(int i=0;i<know.size();i++){
			String tmlx = know.get(i).getFparent().getFlmc()+"-"+know.get(i).getFlmc();
			String tmct = String.valueOf(wtService.countKnow(know.get(i),"","cs")) ;
			
			zsdfbjson += "['"+tmlx+"',"+tmct+"],";
			
		}
		
		if(!zsdfbjson.equals(""))
			zsdfbjson = zsdfbjson.substring(0,zsdfbjson.length()-1);
		
		return "kszj";
	}
	
	public String startPaper()throws Exception{
		prepareModel();
		Twjfafl fl=entity.getFafl();
		System.out.println(entityService.tmfbhql);
		List ls = entityService.getFb(entity.getFafl(),entityService.tmfbhql1,2);
			
		//题型，难度，知识点全部以Twjwt为容器转json
		List<Twjwt> list1=new ArrayList<Twjwt>();
		List<Twjwt> list2=new ArrayList<Twjwt>();
		List<Twjwt> list3=new ArrayList<Twjwt>();
		List<Dict> dictlist=new ArrayList<Dict>();
		for(int i=0;i<ls.size();i++){
			Object[] tmpobj = (Object[]) ls.get(i);
			if(tmpobj.length>1){
				String tmlx = (String)tmpobj[0];
				String tmct = String.valueOf(tmpobj[1]);
				Dict lx=dictDao.findUnique("from Dict a where a.name='"+tmlx+"'");
				if(null!=lx){
					lx.setRemark(tmct);
					dict.add(lx);
					Twjwt tw=new Twjwt();
					tw.setWtlx(tmlx);
					tw.setBz(tmct);
					list1.add(tw);
					Dict di=dictDao.findUnique("from Dict a where a.name='"+tmlx+"'");
					di.setRemark(tmct);
					dictlist.add(di);
				}
				if(tmlx.equals("综合题")){
					Dict di=new Dict();
					di.setName("综合题");
					di.setDm("zht");
					di.setRemark(tmct);
					dict.add(di);
					dictlist.add(di);
					Twjwt tw=new Twjwt();
					tw.setWtlx(tmlx);
					tw.setBz(tmct);
					list1.add(tw);
				}
			}
		}
			
			//该任务-课程下题目难度分布
		ls = entityService.getFb(entity.getFafl(),entityService.ndfbhql1,2);
		for(int i=0;i<ls.size();i++){
			Object[] tmpobj = (Object[]) ls.get(i);
			String tmlx = (String)tmpobj[0];
			String tmct = String.valueOf(tmpobj[1]);
			if(StringUtils.isNotBlank(tmlx)&&StringUtils.isNotBlank(tmct)){
				Twjwt tw=new Twjwt();
				tw.setName(tmlx);
				if(tmlx.equals("A"))
					tmlx = "容易";
				if(tmlx.equals("B"))
					tmlx = "一般";
				if(tmlx.equals("C"))
					tmlx = "中等";
				if(tmlx.equals("D"))
					tmlx = "较难";
				if(tmlx.equals("E"))
					tmlx = "困难";
				if(tmlx.equals("NULL"))
					tmlx = "无";
				tw.setWtlx(tmlx);
				tw.setBz(tmct);
				list2.add(tw);
			}
		}
		
		//该任务-课程下题目知识点分布
		List<Twjfafl> know=faflService.getALLKnow(fl);
		for(int i=0;i<know.size();i++){
			String tmlx = know.get(i).getFparent().getFlmc()+"-"+know.get(i).getFlmc();
			String tmct = String.valueOf(entityService.countKnow(entity.getFafl(),know.get(i),entityService.zsdfbhql1,2)) ;
			if(StringUtils.isNotBlank(tmlx)&&StringUtils.isNotBlank(tmct)){
				if(tmct.equals("0")){
					continue;
				}
				Twjwt tw=new Twjwt();
				tw.setName(know.get(i).getId()+"");
				tw.setWtlx(tmlx);
				tw.setBz(tmct);
				list3.add(tw);
			}
		}
		String tmct = String.valueOf(wtService.countKnow1(fl,"","cs"));
		if(!tmct.equals("0")){
			Twjwt tw=new Twjwt();
			tw.setName("ALL");
			tw.setWtlx("全章节知识点");
			tw.setBz(tmct);
			list3.add(tw);
		}
		dxtmlist=list2;
		yxtmlist=list3;
		dictjson="{\"type\":"+Struts2Utils.listToJson(list1, "wtlx","bz")+",\"ny\":"+Struts2Utils.listToJson(list2, "wtlx","bz","name")+",\"know\":"+Struts2Utils.listToJson(list3, "wtlx","bz","name")+",\"lx\":"+dictjson+",\"dictlist\":"+Struts2Utils.listToJson(dictlist, "name","dm","remark")+"}";
		allfl=jxTypeService.getParent();
		jxztNum=jxtmService.countNum("");
		return "kszj";
	}
	
	/**
	 * 获取难易，知识点分布   1为难易  2为知识点
	 * @author  xl 
	 * @date 创建时间：2017年7月10日 上午9:34:51 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public void getDistribute()throws Exception{
		String tx=Struts2Utils.getParameter("tx");
		String flId=Struts2Utils.getParameter("flId");
		String type=Struts2Utils.getParameter("type");
		if(StringUtils.isNotBlank(tx)&&StringUtils.isNotBlank(flId)&&StringUtils.isNotBlank(type)){
			System.out.println(flId+"--------------------------");
			Twjfafl fafl=faflService.getById(Integer.parseInt(flId));
			if(tx.equals("zht")){
				tx="综合题";
			}
			if(tx.equals("duoxt")){
				tx="多选题";
			}
			if(tx.equals("dxt")){
				tx="单选题";
			}
			if(tx.equals("pdt")){
				tx="判断题";
			}
			if(tx.equals("tkt")){
				tx="填空题";
			}
			if(type.equals("1")){
				List list=entityService.getNy(fafl,tx,entityService.getNySql);
				Struts2Utils.renderText(JSON.toJSONString(list));
			}else if(type.equals("2")){
				
			}
		}
	}
	
	/**
	 * 
	 * @author  xl 
	 * @date 创建时间：2017年5月10日 上午10:50:12 
	 * @Description 
	 * @parameter  ndfb 难度分布  A:10,B:10,C:10 字符形式以“,”隔开   难度字母:比例数量 '无'代码为'NULL'
	 * @parameter  zsdfb 知识点分布   知识点1:10,知识点2:10,知识点3:10 字符形式以','隔开  知识点名称:比例数量  '全章节知识点'代码为ALL 数据库代码为'NULL'
	 * @return
	 */
	public void savePaper()throws Exception{
		String tmlx = Struts2Utils.getParameter("tmlx");			//题目类型
		String rwid = Struts2Utils.getParameter("rwid");			//测试任务id
		String tms = Struts2Utils.getParameter("tms");				//数量
		String ndfb = Struts2Utils.getParameter("ndfb");
		String zsdfb = Struts2Utils.getParameter("zsdfb");
		String grade = Struts2Utils.getParameter("grade");
		if(StringUtils.isNotEmpty(tmlx)&&StringUtils.isNotEmpty(rwid)){
			Dict lx=dictDao.findUnique("from Dict a where a.dm='"+tmlx+"'");
			Twjfafl fl=entityService.getById(rwid).getFafl();
			List<Twjwt> tmpls=new ArrayList<Twjwt>();
			List<ContactWebTopic> listTopic=new ArrayList<ContactWebTopic>();
			//常规题
			if(null!=lx){
				if((StringUtils.isNotEmpty(ndfb)&&StringUtils.isEmpty(zsdfb))||StringUtils.isNotBlank(zsdfb)&&StringUtils.isEmpty(ndfb)){
					if(StringUtils.isNotEmpty(ndfb)){
						//知识点为空
						tmpls=wtService.randomTi(fl,lx,ndfb,tms,"");
						
					}else if(StringUtils.isNotBlank(zsdfb)){
						//难度为空
						tmpls=wtService.randomTi(fl,lx,zsdfb,tms,"");
					}
				}else if(StringUtils.isEmpty(ndfb)&&StringUtils.isEmpty(zsdfb)){
					tmpls=wtService.randomTi(fl,lx,"",tms,"");
				}else if(StringUtils.isNotBlank(ndfb)&&StringUtils.isNotBlank(zsdfb)){
					//难易知识点都不为空 
					
					
				}
				if(tmpls.size()>0){
					String data=tmpls.size()+"|"+scale(tmpls,"1")+"|"+scale(tmpls,"2");
					Csrw tmpcsrw = entityService.getById(rwid);
					if(tmpcsrw.getTmlist().size()>0){
						Iterator<Twjwt> it=tmpcsrw.getTmlist().iterator();
						while(it.hasNext()){
							Twjwt wt=it.next();
							if(wt.getWtlx().equals(lx.getName())){
								it.remove();
							}
						}
					}
					for(Twjwt wt:tmpls){
						tmpcsrw.getTmlist().add(wt);
					}
					entityService.update(tmpcsrw);
					Csrw csrw = entityService.getById(rwid);
					List<Object[]> obj=wtService.countNum(rwid);
					String str="";
					String str2="";
					if(obj.size()>0){
						int j=0;
						for(Object[] ob:obj){
							j++;
							if(j==1){
								str+=ob[0].toString()+":"+ob[1].toString();
							}else{
								str+=","+ob[0].toString()+":"+ob[1].toString();
							}
						}
					}
					if(StringUtils.isEmpty(csrw.getScore())){
						str2=lx.getName()+":"+grade;
					}else{
						if(csrw.getScore().indexOf(lx.getName())==-1){
							str2=csrw.getScore()+","+lx.getName()+":"+grade;
						}else{
							String[] arr=csrw.getScore().split(",");
							for(String s:arr){
								String[] arr2=s.split(":");
								if(arr2[0].equals(lx.getName())){
									str2+=arr2[0]+":"+grade+",";
								}else{
									str2+=arr2[0]+":"+arr2[1]+",";
								}
							}
							str2=str2.substring(0,str2.length()-1);
						}
					}
					csrw.setZjxx(str);
					csrw.setScore(str2);
					entityService.update(csrw);
					Struts2Utils.renderText(data);
					
				}else{
					Struts2Utils.renderText("0");
				}
			//接触网题
			}else{
				if((StringUtils.isNotEmpty(ndfb)&&StringUtils.isEmpty(zsdfb))||StringUtils.isNotBlank(zsdfb)&&StringUtils.isEmpty(ndfb)){
					if(StringUtils.isNotEmpty(ndfb)){
						//知识点为空
						listTopic=contactWebTopicService.randomTi(fl,ndfb,tms);
						
					}else{
						//难度为空
						listTopic=contactWebTopicService.randomTi(fl,zsdfb,tms);
					}
				}else if(StringUtils.isEmpty(ndfb)&&StringUtils.isEmpty(zsdfb)){
					listTopic=contactWebTopicService.randomTi(fl,"",tms);
				}else if(StringUtils.isNotBlank(ndfb)&&StringUtils.isNotBlank(zsdfb)){
					//难易知识点都不为空 
					
					
				}
				if(listTopic.size()>0){
					String data=listTopic.size()+"|";
					Csrw csrw = entityService.getById(rwid);
					csrw.setSetContactWebTopic(new HashSet<ContactWebTopic>(listTopic));
					String str="综合题:"+listTopic.size();
					csrw.setTopicInfo(str);
					csrw.setTopicScore(grade);
					entityService.update(csrw);
					Struts2Utils.renderText(data);
					
				}else{
					Struts2Utils.renderText("0");
				}
			}
		}
	}
	public void test()throws Exception{
		entityService.test();
	}
	/**
	 * 获取机械制图题目数量
	 * @author  xl 
	 * @date 创建时间：2017年6月15日 下午3:19:49 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public void getJxNumber()throws Exception{
		String jxztId=Struts2Utils.getParameter("jxztId");
		if(StringUtils.isNotBlank(jxztId)){
			Struts2Utils.renderText(jxtmService.countNum(jxztId));
		}
	}
	
	/**
	 * 保存机械题目
	 * @author  xl 
	 * @date 创建时间：2017年6月15日 下午3:59:35 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public void saveJxzt()throws Exception{
		String type=Struts2Utils.getParameter("type");
		if(StringUtils.isNotBlank(id)&&StringUtils.isNotBlank(type)&&null!=dxtmlist){
			Set<Jxtm> jxtmset=new HashSet<Jxtm>();
			String str1="",str2="";
			for(int i=0,j=dxtmlist.size();i<j;i++){
				if(StringUtils.isNotBlank(dxtmlist.get(i).getWtlx())&&StringUtils.isNotBlank(dxtmlist.get(i).getWtmc())&&StringUtils.isNotBlank(dxtmlist.get(i).getBz())){
					str1+=jxTypeService.getById(dxtmlist.get(i).getWtlx()).getTypeName()+":"+dxtmlist.get(i).getWtmc()+",";
					str2+=jxTypeService.getById(dxtmlist.get(i).getWtlx()).getTypeName()+":"+dxtmlist.get(i).getBz()+",";
					List<Jxtm> jxtmlist = jxtmService.randomNum(dxtmlist.get(i).getWtlx(),dxtmlist.get(i).getWtmc());
					jxtmset.addAll(jxtmlist);
				}
			}
			entity=entityService.getById(id);
			entity.setJxInfo(str1.substring(0,str1.length()-1));
			entity.setJxScore(str2.substring(0,str2.length()-1));
			entity.setSetJxtm(jxtmset);
			entityService.update(entity);
			Struts2Utils.renderText("ok");
		}
	}
	
	/**
	 * 生成难易知识点比例
	 * @author  xl 
	 * @date 创建时间：2017年5月3日 上午9:49:44 
	 * @Description 
	 * @parameter   1为难易  2为知识点
	 * @return
	 */
	public String scale(List<Twjwt> list,String str)throws Exception{
		String data="";
		Map<String, Integer> map = new HashMap<String, Integer>();
		if(str.equals("1")){
			for(int i=0;i<list.size();i++ ){
				String ny=list.get(i).getNycd();
				if(StringUtils.isBlank(ny)){
					ny="无";
				}else{
					ny=ny.replace("A", "容易");
					ny=ny.replace("B", "一般");
					ny=ny.replace("C", "中等");
					ny=ny.replace("D", "较难");
					ny=ny.replace("E", "困难");
					ny=ny.replace("NULL", "无");
				}
				Integer num = map.get(ny);  
				num = null == num ? 1 : num + 1;  
				map.put(ny, num);  
			}
		}else{
			for(int i=0;i<list.size();i++ ){
				String name=list.get(i).getFaflid().getFlmc();
				if(StringUtils.isBlank(name)){
					name="全章节知识点";
				}
				Integer num = map.get(name);  
				num = null == num ? 1 : num + 1;
				map.put(name, num);  
			}
		}
		Set set = map.entrySet();  
        Iterator  it = set.iterator();  
        DecimalFormat df = new DecimalFormat("0.00");
        while (it.hasNext())  
        {  	 
            Entry <String, Integer> entry = (Entry<String, Integer>)it.next();
            data += entry.getKey() + ": " + df.format(Double.parseDouble(entry.getValue()+"")/Double.parseDouble(list.size()+"")*100)+"%("+entry.getValue()+");";  
         }  
		return data;
	}
	
	/**
	 * 提交组卷
	 * @param ndfb 难易分布  字符形式   A:10;B:10;C:10... 以‘;’分开 难易对应字母:比例
	 * @param zsdfb 知识点分布  字符形式  id:10;id:10;id:10... 以‘;’分开 知识点id:比例
	 * @throws Exception
	 */
	public void tjzj() throws Exception
	{
		String tmlx = Struts2Utils.getParameter("tmlx");
		String rwid = Struts2Utils.getParameter("rwid");
		String tms = Struts2Utils.getParameter("tms");
		String ndfb = Struts2Utils.getParameter("ndfb");
		String zsdfb = Struts2Utils.getParameter("zsdfb");
		String grade = Struts2Utils.getParameter("grade");
		if(StringUtils.isNotEmpty(tmlx) && StringUtils.isNotEmpty(rwid))
		{
			Dict lx=dictDao.findUnique("from Dict a where a.dm='"+tmlx+"'");
			if(StringUtils.isNotEmpty(tms))
			{
				//难度占比，知识点分布都不为空的情况----最复杂
				if(StringUtils.isNotEmpty(ndfb) && StringUtils.isNotEmpty(zsdfb))
				{
					//String retstr = entityService.sjndzsdzj(tmlx,rwid,tms,ndfb,zsdfb);
					String retstr = entityService.sjzsdzj(lx,rwid,tms,zsdfb);//暂时按知识点来组卷
					Struts2Utils.renderText(retstr);
				}
				//难度占比不为空，知识点分布为空的情况
				if(StringUtils.isNotEmpty(ndfb) && StringUtils.isEmpty(zsdfb))
				{
					String retstr = entityService.sjndzj(lx,rwid,tms,ndfb);
					Struts2Utils.renderText(retstr);
				}
				//难度占比为空，知识点分布不为空的情况
				if(StringUtils.isEmpty(ndfb) && StringUtils.isNotEmpty(zsdfb))
				{
					String retstr = entityService.sjzsdzj(lx,rwid,tms,zsdfb);
					Struts2Utils.renderText(retstr);
				}
				//难度占比为空，章节分布也为空，则表示随机进行抽题
				if(StringUtils.isEmpty(ndfb) && StringUtils.isEmpty(zsdfb))
				{
					String retstr = entityService.sjzj(lx,rwid,tms);
					Struts2Utils.renderText(retstr);
				}
				Csrw csrw = entityService.getById(rwid);
				List<Object[]> obj=wtService.countNum(rwid);
				String str="";
				String str2="";
				if(obj.size()>0){
					int j=0;
					for(Object[] ob:obj){
						j++;
						if(j==1){
							str+=ob[0].toString()+":"+ob[1].toString();
						}else{
							str+=","+ob[0].toString()+":"+ob[1].toString();
						}
					}
				}
				if(StringUtils.isEmpty(csrw.getScore())){
					str2=lx.getName()+":"+grade;
				}else{
					if(csrw.getScore().indexOf(lx.getName())==-1){
						str2=csrw.getScore()+","+lx.getName()+":"+grade;
					}else{
						String[] arr=csrw.getScore().split(",");
						for(String s:arr){
							String[] arr2=s.split(":");
							if(arr2[0].equals(lx.getName())){
								str2+=arr2[0]+":"+grade+",";
							}else{
								str2+=arr2[0]+":"+arr2[1]+",";
							}
						}
						str2=str2.substring(0,str2.length()-1);
					}
				}
				csrw.setZjxx(str);
				csrw.setScore(str2);
				entityService.update(csrw);
			}
			
		}
		
	}
	
	/**
	 * 测试任务试卷转为word文档
	 * @author  xl 
	 * @date 创建时间：2017年6月13日 下午4:10:29 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public void saveWord() throws Exception{
		Struts2Utils.getRequest().setCharacterEncoding("utf-8");  
		Map<String, Object> map = new HashMap<String, Object>();  
		// 通过循环将表单参数放入键值对映射中  
		for(int i=0;i<csrw.size();i++){
			List<Twjwt> tm=csrw.get(i).getList();
			for(int j=0;j<tm.size();j++){
				if(StringUtils.isNotBlank(tm.get(j).getWtmcFileName())){
					String img=entityService.getImageStr(tm.get(j).getWtmcFileName(),"wtimg");
					tm.get(j).setImgHeight(entityService.getHeight(tm.get(j).getWtmcFileName(),"wtimg"));
					tm.get(j).setImgWidth(entityService.getWidth(tm.get(j).getWtmcFileName(),"wtimg"));
					tm.get(j).setWtmcFileName(img);
				}
				List<Twjwthdx> hdx=tm.get(j).getHdx();
				for(int k=0;k<hdx.size();k++){
					if(StringUtils.isNotBlank(hdx.get(k).getDamcFileName())){
						String img=entityService.getImageStr(hdx.get(k).getDamcFileName(),"daimg");
						hdx.get(k).setImgHeight(entityService.getHeight(hdx.get(k).getDamcFileName(), "daimg"));
						hdx.get(k).setImgWidth(entityService.getWidth(hdx.get(k).getDamcFileName(), "daimg"));
						hdx.get(k).setDamcFileName(img);
					}
				}
			}
		}
		map.put("csrw", csrw);  
		map.put("exam", Struts2Utils.getParameter("exam"));
		// 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整  
		// 否则Freemarker的模板殷勤在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了  
		File file = null;  
		InputStream fin = null;  
		ServletOutputStream out = null;  
		try {  
			// 调用工具类WordGenerator的createDoc方法生成Word文档  
			file=CsrwToWord.createDoc(map, "temple");
			fin = new FileInputStream(file);  
			
			Struts2Utils.getResponse().setCharacterEncoding("utf-8");  
			Struts2Utils.getResponse().setContentType("application/msword");  
			// 设置浏览器以下载的方式处理该文件默认名为resume.doc  
			Struts2Utils.getResponse().addHeader("Content-Disposition", "attachment;filename=csrw.doc");  
			
			out = Struts2Utils.getResponse().getOutputStream();  
			byte[] buffer = new byte[512];  // 缓冲区  
			int bytesToRead = -1;  
			// 通过循环将读入的Word文件的内容输出到浏览器中  
			while((bytesToRead = fin.read(buffer)) != -1) {  
				out.write(buffer, 0, bytesToRead);  
			}  
			
		} finally {  
			if(fin != null) fin.close();
			if(out != null) out.close();  
			if(file != null) file.delete(); // 删除临时文件  
		} 
	}
}
