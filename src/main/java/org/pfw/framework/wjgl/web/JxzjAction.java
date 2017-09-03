package org.pfw.framework.wjgl.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.wjgl.domain.JxType;
import org.pfw.framework.wjgl.domain.Jxtm;
import org.pfw.framework.wjgl.domain.Jxtx;
import org.pfw.framework.wjgl.domain.Jxzj;
import org.pfw.framework.wjgl.service.JxTypeService;
import org.pfw.framework.wjgl.service.JxtmService;
import org.pfw.framework.wjgl.service.JxtxService;
import org.pfw.framework.wjgl.service.JxzjService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name="success",location="/wjgl/jxzj-list.ftl",type = "freemarker"),
		@Result(name="dysj",location="/wjgl/jxzj-dysj.ftl",type = "freemarker"),
		@Result(name ="input",location="/wjgl/jxzj-input.ftl", type = "freemarker")
	})
public class JxzjAction extends CrudActionSupport<Jxzj> {
	@Autowired
	protected JxzjService entityService;
	
	@Autowired
	protected JxtmService jxtmService;
	@Autowired
	private JxTypeService jxTypeService;
	
	private List<Jxtx> menus;
	private List<JxType> allfl;
	
	
	public List<JxType> getAllfl() {
		return allfl;
	}

	public void setAllfl(List<JxType> allfl) {
		this.allfl = allfl;
	}
	public List<Jxtx> getMenus() {
		return menus;
	}
	
	private Double zf=0.0;	//传到页面上的总分
	private String xq="";
	private String xn="";
	private String exam;
	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public String getXq() {
		return xq;
	}

	public void setXq(String xq) {
		this.xq = xq;
	}

	public String getXn() {
		return xn;
	}

	public void setXn(String xn) {
		this.xn = xn;
	}

	public Double getZf() {
		return zf;
	}

	public void setZf(Double zf) {
		this.zf = zf;
	}

	public void setMenus(List<Jxtx> menus) {
		this.menus = menus;
	}
	
	private List<Map> reslist= new ArrayList<Map>();
	
	
	public List<Map> getReslist() {
		return reslist;
	}

	public void setReslist(List<Map> reslist) {
		this.reslist = reslist;
	}

	@Autowired
	private JxtxService jxtxService;

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
			if(!StringUtils.isEmpty(id)){
				entity=entityService.getById(id);
				String zjxx=entity.getZjxx();
				System.out.println(zjxx);
				if(StringUtils.isNotEmpty(zjxx)){
					String[] zjxxary=zjxx.split("\\$");
					for(int a=0;a<zjxxary.length;a++){
						//循环找到章节的出题信息
						Map<String, String> tmxxmap = new HashMap<String, String>();
						String[] str=zjxxary[a].split(",");
						List<Jxtm> list=jxtmService.getByTypeId(str[0]);
						tmxxmap.put("txmc", jxTypeService.getById(str[0]).getTypeName());  
						tmxxmap.put("txid", str[0]);
						tmxxmap.put("tmsl", str[1]);
						tmxxmap.put("mtfz", str[2]);
						tmxxmap.put("mhxj", Integer.parseInt(str[1])*Integer.parseInt(str[2])+"");
						tmxxmap.put("sytl", list.size()+"");
						zf+=Integer.parseInt(str[1])*Integer.parseInt(str[2]);
						reslist.add(tmxxmap);
					}
					System.out.println(reslist.size());
				}
			}
			
		allfl=jxTypeService.getParent();
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
			entity = (Jxzj) entityService.getById(id);
		} else {
			entity = new Jxzj();
		}
		
	}

	@Override
	public String save() throws Exception {
		//试卷的结构. 章节id&题目数量&每题分值$章节id&题目数量&每题分值$章节id&题目数量&每题分值
		String sjjg="";
		//用来存题目的
		Set<Jxtm> jxtmset=new HashSet<Jxtm>();
		
		String [] zjids=Struts2Utils.getRequest().getParameterValues("zjid"); //章节ID
		String [] xtsls=Struts2Utils.getRequest().getParameterValues("xtsl");//选题数量
		String [] mtfzs=Struts2Utils.getRequest().getParameterValues("mtfz");//每题分值
			if(mtfzs.length>0 && zjids.length>0 && xtsls.length>0){
				//循环每个章节获取章节对应的出题量和分值
				int i=0;
				for(int lba=0;lba<zjids.length;lba++){
					if(StringUtils.isNotEmpty(mtfzs[lba]) && !"0".equals(mtfzs[lba]) && StringUtils.isNotEmpty(xtsls[lba]) && !"0".equals(xtsls[lba]))
					{	
						i++;
						List<Jxtm> jxtmlist = jxtmService.randomNum(zjids[lba], xtsls[lba]);
						jxtmset.addAll(jxtmlist);
						
						//将出题信息变成一个字符串
						if(i==1){
							sjjg+=zjids[lba]+","+xtsls[lba]+","+mtfzs[lba];	//
						}else{
							sjjg+="$"+zjids[lba]+","+xtsls[lba]+","+mtfzs[lba];
						}
					}
				}
			}
		entity.setTmlist(jxtmset);
		entity.setZjxx(sjjg);
		if(Struts2Utils.getParameter("type.id").equals("null")){
			entity.setType(null);
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
	
	/**
	 * 获取题型
	 * @author  xl 
	 * @date 创建时间：2017年4月12日 下午3:31:06 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public void getInfo()throws Exception{
		String id =Struts2Utils.getParameter("id");
		List<JxType> listtype=new ArrayList<JxType>();
		if(!id.equals("null")){
			listtype=jxTypeService.getListById(id);
		}else{
			listtype=jxTypeService.getAllThree();
		}
		List<String> list=new ArrayList<String>();
		List<PropertyFilter> filters = new ArrayList<PropertyFilter>();
		for(JxType jx:listtype){
			list.add(jx.getId());
		}
		if(list!=null&&list.size()>0){
			PropertyFilter p=new PropertyFilter("INC_id",list);
			filters.add(p);
			List<JxType> typelist=jxTypeService.find(filters);
			if(typelist.size()>0){
				for(JxType ty:typelist){
					ty.setRemark(jxtmService.getByTypeId(ty.getId()).size()+"");
				}
				Struts2Utils.renderText(Struts2Utils.listToJson(typelist, "id","typeName","remark"));
			}else{
				Struts2Utils.renderText("");
			}
		}else{
			Struts2Utils.renderText("");
		}
		
	}

	/**
	 * 打印试卷
	 */
	@SuppressWarnings("unchecked")
	public String dysj() throws Exception
	{
		if (!StringUtils.isEmpty(id)) {
			entity = (Jxzj) entityService.getById(id);
			Set<Jxtm> sxtmset=entity.getTmlist();
			String[] type=entity.getZjxx().split("\\$");
			for(int i=0;i<type.length;i++){
				Map map= new HashMap();
				String[] title=type[i].split(",");
				map.put("txmc",convert(i+1)+"、"+jxTypeService.getById(title[0]).getTypeName());
				map.put("bz", ("（本大题共"+title[1]+"道小题，每小题"+title[2]+"分，共"+Integer.parseInt(title[1])*Integer.parseInt(title[2])+"分。）").replace(".0", ""));
				List<Jxtm> list = new ArrayList<Jxtm>();
				for(Jxtm tm:sxtmset){
					if(tm.getType().getId().equals(title[0])){
						list.add(tm);
					}
				}
				map.put("jtmlist", list);
				reslist.add(map);
			}
		}
		return "dysj";
	}
	  
	  /**
	   * 数字转换为中文简写
	   * @author  xl 
	   * @date 创建时间：2017年4月12日 下午8:44:20 
	   * @Description 
	   * @parameter   
	   * @return
	   */
	  public String convert(int number) {  
	        //数字对应的汉字  
	        String[] num = {"零","一","二","三","四","五","六","七","八","九"};  
	        //单位  
	        String[] unit = {"","十","百","千","万","十","百","千","亿","十","百","千","万亿"};  
	        //将输入数字转换为字符串  
	        String result = String.valueOf(number);  
	        //将该字符串分割为数组存放  
	        char[] ch = result.toCharArray();  
	        //结果 字符串  
	        String str = "";  
	        int length = ch.length;  
	        for (int i = 0; i < length; i++) {  
	            int c = (int)ch[i]-48;  
	            if(c != 0) {  
	                str += num[c]+unit[length-i-1];  
	            } else {  
	                str += num[c];  
	            }  
	        }  
	       return str;
	    }  
}
