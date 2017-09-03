package org.pfw.framework.wjgl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.tools.CsrwToWord;
import org.pfw.framework.tools.TaotiToWord;
import org.pfw.framework.wjgl.dao.TaotiDao;
import org.pfw.framework.wjgl.domain.Csrw;
import org.pfw.framework.wjgl.domain.Taoti;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.domain.Twjwthdx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.wjgl.service.CsrwService;
import org.pfw.framework.wjgl.service.TaotiService;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.pfw.framework.wjgl.service.TwjwtService;
import org.pfw.framework.xtgl.dao.DictDao;
import org.pfw.framework.xtgl.domain.Dict;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name="success",location="/wjgl/taoti-list.ftl",type = "freemarker"),
		@Result(name="dysj",location="/wjgl/taoti-dysj.ftl",type = "freemarker"),
		@Result(name ="input",location="/wjgl/taoti-input.ftl", type = "freemarker")
	})
public class TaotiAction extends CrudActionSupport<Taoti> {
	@Autowired
	protected TaotiService entityService;
	@Autowired
	protected TwjfaflService faflService;
	@Autowired
	protected TwjwtService wtService;
	@Autowired
	protected TaotiDao entityDao;
	@Autowired
	protected CsrwService csrwService;
	@Autowired
	protected DictDao dictDao;
	
	private List<Twjfafl> fafllist;
	
	private Map<String,List<Twjwt>> map=new HashMap<String,List<Twjwt>>();
	
	private List<Map> cswt=new ArrayList<Map>();
	
	private List<Csrw> csrw=new ArrayList<Csrw>();

	public List<Csrw> getCsrw() {
		return csrw;
	}

	public void setCsrw(List<Csrw> csrw) {
		this.csrw = csrw;
	}

	public List<Map> getCswt() {
		return cswt;
	}

	public void setCswt(List<Map> cswt) {
		this.cswt = cswt;
	}

	public Map<String, List<Twjwt>> getMap() {
		return map;
	}

	public void setMap(Map<String, List<Twjwt>> map) {
		this.map = map;
	}

	public List<Twjfafl> getFafllist() {
		return fafllist;
	}

	public void setFafllist(List<Twjfafl> fafllist) {
		this.fafllist = fafllist;
	}

	@Override
	public String delete() throws Exception {
		try {
			if(checks!=null&&checks.size()>0){
				entityService.delete(checks);
			}
			Struts2Utils.renderText("删除成功");
		} catch (Exception e) {
			Struts2Utils.renderText("有学生答题记录，删除失败！");
		}
		
		return null;
	}

	@Override
	public String input() throws Exception {
		fafllist = faflService.findAll();
		return INPUT;
	}

	@Override
	public String list() throws Exception {
		
		fafllist = faflService.findAll();
	
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
			entity = (Taoti) entityService.getById(id);
		} else {
			entity = new Taoti();
		}
		
	}

	@Override
	public String save() throws Exception {
		int flid = entity.getFafl().getId();
		if(flid == 0)
			entity.setFafl(null);
		String dxtsl = Struts2Utils.getParameter("dxtsl");
		String pdtsl = Struts2Utils.getParameter("pdtsl");
		Twjfafl fl=faflService.getById(flid);
		if (!StringUtils.isEmpty(id))
		{
			if(StringUtils.isNotEmpty(dxtsl)&&StringUtils.isNotEmpty(pdtsl))
			{
				
				List<Twjwt> tmpls = wtService.getRandwtForTaoti(fl, Integer.parseInt(dxtsl), Integer.parseInt(pdtsl));
				entity.setTmlist(new HashSet(tmpls));
				
			}
			entityService.update(entity);
		}else{
			entityService.save(entity);
			
			if(StringUtils.isNotEmpty(dxtsl)&&StringUtils.isNotEmpty(pdtsl))
			{
				List<Twjwt> tmpls = wtService.getRandwtForTaoti(fl, Integer.parseInt(dxtsl), Integer.parseInt(pdtsl));
				
				entity.setTmlist(new HashSet(tmpls));
				
			}
			entityService.update(entity);
		}
		Struts2Utils.setPromptInfoToReq("保存成功!!");
		
		return RELOAD;
	}
	
	public void getSytml()
	{
		String faflid = Struts2Utils.getParameter("faflid");
		System.out.println(faflid);
		Twjfafl fl=faflService.getById(Integer.parseInt(faflid));
		String dxtsyl = wtService.getSytlForTaoti(Integer.parseInt(faflid), "1",fl);
		String pdtsyl = wtService.getSytlForTaoti(Integer.parseInt(faflid), "2",fl);
		System.out.println(dxtsyl);
		System.out.println(pdtsyl);
		Struts2Utils.renderText(dxtsyl+"$"+pdtsyl);
	}
	
	/**
	 * 根据课程id调取所有题目
	 * @author  xl 
	 * @date 创建时间：2017年4月19日 上午9:15:28 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public void getTopic()throws Exception{
		String kcid=Struts2Utils.getParameter("kcid");
		Twjfafl fl=faflService.getById(Integer.parseInt(kcid));
		List ls = csrwService.getFb(fl,csrwService.taoTiLxhql,1);
		List<Dict> dict=new ArrayList<Dict>();
		
		//题型，难度，知识点全部以Twjwt为容器转json
		List<Twjwt> list1=new ArrayList<Twjwt>();
		List<Twjwt> list2=new ArrayList<Twjwt>();
		List<Twjwt> list3=new ArrayList<Twjwt>();
		String tmfbjson = "";
		List<Dict> dictlist=new ArrayList<Dict>();
		for(int i=0;i<ls.size();i++){
			Object[] tmpobj = (Object[]) ls.get(i);
			String tmlx = (String)tmpobj[0];
			String tmct = String.valueOf(tmpobj[1]);
			Dict lx=dictDao.findUnique("from Dict a where a.name='"+tmlx+"'");
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
		if(!tmfbjson.equals("")){
			tmfbjson = tmfbjson.substring(0,tmfbjson.length()-1);
		}
		
		//该任务-课程下题目难度分布
		ls = csrwService.getFb(fl,csrwService.taoTinysql,1);
		for(int i=0;i<ls.size();i++){
			Object[] tmpobj = (Object[]) ls.get(i);
			String tmlx = (String)tmpobj[0];
			String tmct = String.valueOf(tmpobj[1]);
			Twjwt tw=new Twjwt();
			if(StringUtils.isBlank(tmlx)){
				tw.setName("NULL");
				tw.setWtlx("无");
			}else{
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
			}
			tw.setBz(tmct);
			list2.add(tw);
		}
		
		//该任务-课程下题目知识点分布
		List<Twjfafl> know=faflService.getALLKnow(fl);
		for(int i=0;i<know.size();i++){
			String tmlx = know.get(i).getFparent().getFlmc()+"-"+know.get(i).getFlmc();
			String tmct = String.valueOf(wtService.countKnow(know.get(i),"","")) ;
			if(tmct.equals("0")){
				continue;
			}
			Twjwt tw=new Twjwt();
			tw.setName(know.get(i).getId()+"");
			tw.setWtlx(tmlx);
			tw.setBz(tmct);
			list3.add(tw);
		}
		Twjwt tw=new Twjwt();
		String tmct = String.valueOf(wtService.countKnow1(fl,"",""));
		tw.setName("ALL");
		tw.setWtlx("全章节知识点");
		tw.setBz(tmct);
		list3.add(tw);
		
		String dictjson=Struts2Utils.listToJson(dict, "name","dm","remark");
		String json="{\"type\":"+Struts2Utils.listToJson(list1, "wtlx","bz")+",\"ny\":"+Struts2Utils.listToJson(list2, "wtlx","bz","name")+",\"know\":"+Struts2Utils.listToJson(list3, "wtlx","bz","name")+",\"lx\":"+dictjson+",\"dictlist\":"+Struts2Utils.listToJson(dictlist, "name","dm","remark")+"}";
		Struts2Utils.renderText(json);
		
	}
	
	/**
	 * 获得对应课程，题目类型的难度与知识点
	 * @author  xl 
	 * @date 创建时间：2017年4月21日 下午1:05:05 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public void getPercent()throws Exception{
		String kcid=Struts2Utils.getParameter("kc");
		String lx=Struts2Utils.getParameter("tmlx");
		Twjfafl fl=faflService.getById(Integer.parseInt(kcid));
		//难度，知识点全部以Twjwt为容器转json
		List<Twjwt> list1=new ArrayList<Twjwt>();
		List<Twjwt> list2=new ArrayList<Twjwt>();
		//难度
		List ls = csrwService.lxFb(fl,csrwService.coursenysql,lx);
		for(int i=0;i<ls.size();i++){
			Object[] tmpobj = (Object[]) ls.get(i);
			String tmlx = (String)tmpobj[0];
			String tmct = String.valueOf(tmpobj[1]);
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
			list1.add(tw);
		}
		List<Twjfafl> know=faflService.getALLKnow(fl);
		for(int i=0;i<know.size();i++){
			String tmlx = know.get(i).getFparent().getFlmc()+"-"+know.get(i).getFlmc();
			String tmct = String.valueOf(wtService.countKnow(know.get(i),lx,"")) ;
			if(tmct.equals("0")){
				continue;
			}
			Twjwt tw=new Twjwt();
			tw.setName(know.get(i).getId()+"");
			tw.setWtlx(tmlx);
			tw.setBz(tmct);
			list2.add(tw);
		}
		Twjwt tw=new Twjwt();
		String tmct = String.valueOf(wtService.countKnow1(fl,lx,"")) ;
		tw.setName("ALL");
		tw.setWtlx("全章节知识点");
		tw.setBz(tmct);
		list2.add(tw);
		
		String json="{\"ny\":"+Struts2Utils.listToJson(list1, "wtlx","bz","name")+",\"know\":"+Struts2Utils.listToJson(list2, "wtlx","bz","name")+"}";
		System.out.println(json);
		Struts2Utils.renderText(json);
	}
	/**
	 * 保存套题
	 * @author  xl 
	 * @date 创建时间：2017年5月3日 上午9:50:51 
	 * @Description 
	 * @parameter  ndfb 难度分布  A:10,B:10,C:10 字符形式以“,”隔开   难度字母:比例数量 '无'代码为'NULL'
	 * @parameter  zsdfb 知识点分布   知识点1:10,知识点2:10,知识点3:10 字符形式以','隔开  知识点名称:比例数量  '全章节知识点'代码为ALL 数据库代码为'NULL'
	 * @return
	 */
	public void saveTaoTi()throws Exception{
		String tmlx = Struts2Utils.getParameter("tmlx");
		String ttid = Struts2Utils.getParameter("ttid");
		String tms = Struts2Utils.getParameter("tms");
		String ndfb = Struts2Utils.getParameter("ndfb");
		String zsdfb = Struts2Utils.getParameter("zsdfb");
		String kcid=Struts2Utils.getParameter("kc");
		String mc=Struts2Utils.getParameter("ttmc");
		if(StringUtils.isNotEmpty(tmlx)&&StringUtils.isNotEmpty(kcid)){
			Dict lx=dictDao.findUnique("from Dict a where a.dm='"+tmlx+"'");
			Twjfafl fl=faflService.getById(Integer.parseInt(kcid));
			//知识点，难度有一个为空一个不为空
			if((StringUtils.isNotEmpty(ndfb)&&StringUtils.isEmpty(zsdfb))||StringUtils.isNotBlank(zsdfb)&&StringUtils.isEmpty(ndfb)){
				List<Twjwt> tmpls;
				if(StringUtils.isNotEmpty(ndfb)){
					//知识点为空
					tmpls=wtService.randomTi(fl,lx,ndfb,tms,"taoti");
				}else{
					//难度为空
					tmpls=wtService.randomTi(fl,lx,zsdfb,tms,"taoti");
				}
				System.out.println(tmpls.size());
				if(tmpls!=null&&tmpls.size()>0){
					String data=tmpls.size()+"|"+scale(tmpls,"1")+"|"+scale(tmpls,"2");
					//Taoti topic=entityService.getByName(mc);
					if(StringUtils.isEmpty(ttid)){
						Taoti ti=new Taoti();
						ti.setTtmc(mc);
						ti.setFafl(fl);
						ti.setTmlist(new HashSet<Twjwt>(tmpls));
						ti.setRemark(lx.getName()+"|"+tmpls.size()+"|"+scale(tmpls,"1")+"|"+scale(tmpls,"2"));		//存入备注
						entityService.save(ti);
						data+="|"+ti.getId();
					}else{
						Taoti topic=entityService.getById(ttid);
						Iterator<Twjwt> it=topic.getTmlist().iterator();
						while(it.hasNext()){
							Twjwt wt=it.next();
							if(wt.getWtlx().equals(lx.getName())){
								it.remove();
							}
						}
						for(Twjwt tw:tmpls){
							topic.getTmlist().add(tw);
						}
						topic.setTtmc(mc);
						topic.setFafl(fl);
						String[] str=topic.getRemark().split(",");
						String str2="";
						for(int i=0;i<str.length;i++){
							String[] arr=str[i].split("\\|");
							if(!arr[0].equals(lx.getName())){
								str2+=str[i]+",";
							}
						}
						str2+=lx.getName()+"|"+tmpls.size()+"|"+scale(tmpls,"1")+"|"+scale(tmpls,"2");
						topic.setRemark(str2);
						entityService.update(topic);
					}
					
					Struts2Utils.renderText(data);
				}else{
					Struts2Utils.renderText("0");
				}
			}
			if(StringUtils.isEmpty(ndfb)&&StringUtils.isEmpty(zsdfb)){
				List<Twjwt> tmpls=wtService.randomTi(fl,lx,"",tms,"taoti");
				if(tmpls!=null&&tmpls.size()>0){
					String data=tmpls.size()+"|"+scale(tmpls,"1")+"|"+scale(tmpls,"2");
					if(StringUtils.isEmpty(ttid)){
						Taoti ti=new Taoti();
						ti.setTtmc(mc);
						ti.setFafl(fl);
						ti.setTmlist(new HashSet<Twjwt>(tmpls));
						ti.setRemark(lx.getName()+"|"+tmpls.size()+"|"+scale(tmpls,"1")+"|"+scale(tmpls,"2"));		//存入备注
						entityService.save(ti);
						data+="|"+ti.getId();
					}else{
						Taoti topic=entityService.getById(ttid);
						Iterator<Twjwt> it=topic.getTmlist().iterator();
						while(it.hasNext()){
							Twjwt wt=it.next();
							if(wt.getWtlx().equals(lx.getName())){
								it.remove();
							}
						}
						for(Twjwt tw:tmpls){
							topic.getTmlist().add(tw);
						}
						topic.setTtmc(mc);
						topic.setFafl(fl);
						String[] str=topic.getRemark().split(",");
						String str2="";
						for(int i=0;i<str.length;i++){
							String[] arr=str[i].split("\\|");
							if(!arr[0].equals(lx.getName())){
								str2+=str[i]+",";
							}
						}
						str2+=lx.getName()+"|"+tmpls.size()+"|"+scale(tmpls,"1")+"|"+scale(tmpls,"2");
						topic.setRemark(str2);
						entityService.update(topic);
					}
					
					Struts2Utils.renderText(data);
				}else{
					Struts2Utils.renderText("0");
				}
			}
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
	 * 打印试卷
	 */
	public String dysj() throws Exception
	{
		prepareModel();
		List<Twjwt> list=new ArrayList<Twjwt>(entity.getTmlist());
		List<Dict> dict=dictDao.getSession().createQuery("from Dict a where a.parent.dm='tx'").list();
		List<Twjwt> listwt=null;
		for(Dict di:dict){
			listwt=new ArrayList<Twjwt>();
			for(Twjwt wt:list){
				if(wt.getWtlx().equals(di.getName())){
					listwt.add(wt);
				}
			}
			if(listwt.size()>0){
				map.put(di.getName(), listwt);
			}
		}
		Map mapset=null;
		if(null!=map.get("填空题")){
			mapset=new HashMap<String, List<Twjwt>>();
			mapset.put("填空题", map.get("填空题"));
			cswt.add(mapset);
		}
		if(null!=map.get("判断题")){
			mapset=new HashMap<String, List<Twjwt>>();
			mapset.put("判断题", map.get("判断题"));
			cswt.add(mapset);
		}
		if(null!=map.get("单选题")){
			mapset=new HashMap<String, List<Twjwt>>();
			mapset.put("单选题", map.get("单选题"));
			cswt.add(mapset);
		}
		if(null!=map.get("多选题")){
			mapset=new HashMap<String, List<Twjwt>>();
			mapset.put("多选题", map.get("多选题"));
			cswt.add(mapset);
		}
		if(null!=map.get("图片题")){
			mapset=new HashMap<String, List<Twjwt>>();
			mapset.put("图片题", map.get("图片题"));
			cswt.add(mapset);
		}
		return "dysj";
	}
	
	/**
	 * 套题转为word
	 * @author  xl 
	 * @date 创建时间：2017年6月15日 上午10:17:43 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public String saveWord() throws Exception{
		Struts2Utils.getRequest().setCharacterEncoding("utf-8");  
        Map<String, Object> map = new HashMap<String, Object>();  
        System.out.println(csrw);
        // 通过循环将表单参数放入键值对映射中  
        for(int i=0;i<csrw.size();i++){
        	List<Twjwt> tm=csrw.get(i).getList();
        	for(int j=0;j<tm.size();j++){
        		if(StringUtils.isNotBlank(tm.get(j).getWtmcFileName())){
        			String img=csrwService.getImageStr(tm.get(j).getWtmcFileName(),"wtimg");
        			tm.get(j).setImgHeight(csrwService.getHeight(tm.get(j).getWtmcFileName(),"wtimg"));
        			tm.get(j).setImgWidth(csrwService.getWidth(tm.get(j).getWtmcFileName(),"wtimg"));
        			tm.get(j).setWtmcFileName(img);
        		}
        		List<Twjwthdx> hdx=tm.get(j).getHdx();
        		for(int k=0;k<hdx.size();k++){
        			if(StringUtils.isNotBlank(hdx.get(k).getDamcFileName())){
        				String img=csrwService.getImageStr(hdx.get(k).getDamcFileName(),"daimg");
        				hdx.get(k).setImgHeight(csrwService.getHeight(hdx.get(k).getDamcFileName(), "daimg"));
        				hdx.get(k).setImgWidth(csrwService.getWidth(hdx.get(k).getDamcFileName(), "daimg"));
        				hdx.get(k).setDamcFileName(img);
        			}
        		}
        	}
        }
        map.put("csrw", csrw);  
        map.put("exam", Struts2Utils.getParameter("exam"));
        map.put("title", Struts2Utils.getParameter("title"));
        // 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整  
        // 否则Freemarker的模板殷勤在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了  
        File file = null;  
        InputStream fin = null;  
        ServletOutputStream out = null;  
        try {  
            // 调用工具类WordGenerator的createDoc方法生成Word文档
            file=TaotiToWord.createDoc(map, "temple");
            fin = new FileInputStream(file);  
              
            Struts2Utils.getResponse().setCharacterEncoding("utf-8");  
            Struts2Utils.getResponse().setContentType("application/msword");  
            // 设置浏览器以下载的方式处理该文件默认名为resume.doc  
            Struts2Utils.getResponse().addHeader("Content-Disposition", "attachment;filename=taoti.doc");  
              
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
        return null;
	}
	
}
