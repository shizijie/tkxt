package org.pfw.framework.wjgl.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.wjgl.domain.Banji;
import org.pfw.framework.wjgl.domain.Jiaoshi;
import org.pfw.framework.wjgl.domain.Taoti;
import org.pfw.framework.wjgl.domain.Ttcsjg;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Xuesheng;
import org.pfw.framework.wjgl.service.BanjiService;
import org.pfw.framework.wjgl.service.JiaoshiService;
import org.pfw.framework.wjgl.service.TaotiService;
import org.pfw.framework.wjgl.service.TtcsjgService;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.springframework.beans.factory.annotation.Autowired;

@Results
({ 
	@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
	@Result(name="success",location="/wjgl/ttcsjg-list.ftl",type = "freemarker"),
	@Result(name ="input",location="/wjgl/ttcsjg-input.ftl", type = "freemarker")
})
public class TtcsjgAction extends CrudActionSupport<Ttcsjg>{
	@Autowired
	protected TtcsjgService entityService;
	@Autowired
	protected TwjfaflService faflService;
	@Autowired
	protected TaotiService ttService;
	@Autowired
	protected BanjiService bjService;
	
	private List<Ttcsjg> listjg;
	private List<Twjfafl> fafllist;
	private List<Taoti> listtt;
	private List<Banji> listbj;

	public List<Banji> getListbj() {
		return listbj;
	}

	public void setListbj(List<Banji> listbj) {
		this.listbj = listbj;
	}

	public List<Taoti> getListtt() {
		return listtt;
	}

	public void setListtt(List<Taoti> listtt) {
		this.listtt = listtt;
	}

	public List<Twjfafl> getFafllist() {
		return fafllist;
	}

	public void setFafllist(List<Twjfafl> fafllist) {
		this.fafllist = fafllist;
	}

	public List<Ttcsjg> getListjg() {
		return listjg;
	}

	public void setListjg(List<Ttcsjg> listjg) {
		this.listjg = listjg;
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
		return INPUT;
	}

	@Override
	public String list() throws Exception {
	
		String name=Struts2Utils.getParameter("filter_LIKES_stuname");
		String kcid=Struts2Utils.getParameter("filter_kc");
		String bj=Struts2Utils.getParameter("filter_banji");
		String taoti=Struts2Utils.getParameter("filter_taoti");
		listbj=bjService.findAll();
		fafllist = faflService.findAll();
		if(StringUtils.isNotEmpty(kcid)){
			listtt=ttService.getttlist(kcid);
		}
		if(StringUtils.isEmpty(name)){
			name="";
		}
		//设置默认排序方式
		System.out.println(kcid+"---"+bj+"---"+taoti);
		List<Ttcsjg> list=entityService.getAllby(bj,kcid,taoti);
		if(list.size()==0){
			page.setTotalCount(0);
			List<Xuesheng> xs=new ArrayList<Xuesheng>();
			page.setResult(xs);
			return SUCCESS;
		}
		page.setOrderBy("id");
		page.setOrder(Page.ASC);
		page = entityService.findstu(page,bj, name);
		page.setTotalCount(page.getResult().size());
		DecimalFormat df= new DecimalFormat("######0.00"); 
		for(Ttcsjg tt:list){
			tt.setBz1(Integer.parseInt(tt.getZtkm().getDxtsl())+Integer.parseInt(tt.getZtkm().getPdtsl())+"");
			tt.setBz2(df.format(Double.parseDouble(tt.getZtzl())/Double.parseDouble(tt.getBz1())*100)+"%");
		}
		listjg=list;
		
		
		return SUCCESS;
	}

	@Override
	protected void prepareModel() throws Exception {
		if (!StringUtils.isEmpty(id)) {
			entity = (Ttcsjg) entityService.getById(id);
		} else {
			entity = new Ttcsjg();
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
	 *根据分类id获取套题list 
	 */
	public void gettaoti()throws Exception{
		String kcid=Struts2Utils.getParameter("kcid");
		List<Taoti> list=ttService.getttlist(kcid);
		String html="";
		for(Taoti tt:list){
			html+="<option value=\""+tt.getId()+"\">"+tt.getTtmc()+"</option>";
		}
		Struts2Utils.renderText(html);
	}
	
}
