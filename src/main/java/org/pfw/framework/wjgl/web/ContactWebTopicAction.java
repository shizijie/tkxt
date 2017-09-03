package org.pfw.framework.wjgl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.domain.security.User;
import org.pfw.framework.util.PFWSecurityUtils;
import org.pfw.framework.wjgl.domain.ContactWebTopic;
import org.pfw.framework.wjgl.domain.TopicContent;
import org.pfw.framework.wjgl.domain.TopicOptions;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.wjgl.service.ContactWebTopicService;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.pfw.framework.xtgl.domain.Dict;
import org.pfw.framework.xtgl.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name="success",location="/wjgl/ContactWebTopic-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/wjgl/ContactWebTopic-input.ftl", type = "freemarker"),
		@Result(name ="preview",location="/wjgl/ContactWebTopic-preview.ftl", type = "freemarker")
	})
public class ContactWebTopicAction extends CrudActionSupport<ContactWebTopic> {
	@Autowired
	protected ContactWebTopicService entityService;
	@Autowired
	private DictService dictService;
	@Autowired
	private TwjfaflService twjfaflService;
	
	private List<Twjfafl> allfl;
	private List menus;
	private List<Dict> dict;
	private List<TopicContent> list=new ArrayList<TopicContent>();
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

	public List<TopicContent> getList() {
		return list;
	}

	public void setList(List<TopicContent> list) {
		this.list = list;
	}

	public List<Twjfafl> getAllfl() {
		return allfl;
	}

	public void setAllfl(List<Twjfafl> allfl) {
		this.allfl = allfl;
	}

	public List getMenus() {
		return menus;
	}

	public void setMenus(List menus) {
		this.menus = menus;
	}

	public List<Dict> getDict() {
		return dict;
	}

	public void setDict(List<Dict> dict) {
		this.dict = dict;
	}

	@Override
	public String delete() throws Exception {
		
		if(checks!=null&&checks.size()>0){
			for(String id:checks){
				Iterator<TopicContent> con=entityService.getById(id).getSetTopicContent().iterator();
				while(con.hasNext()){
					TopicContent top=con.next();
					Iterator<TopicOptions> opt=top.getSetTopicOptions().iterator();
					while(opt.hasNext()){
						TopicOptions opts=opt.next();
						if(!opts.getOptionPicture().equals("0")){
							delPicture(opts.getOptionPicture(),2);
						}
					}
					if(!top.getTopicPicture().equals("0")){
						delPicture(top.getTopicPicture(),1);
					}
				}
			}
			entityService.delete(checks);
		}
		Struts2Utils.renderText("删除成功");
		return null;
	}

	@Override
	public String input() throws Exception {
		allfl=twjfaflService.findkc();
		menus = twjfaflService.findAll();
		dict=dictService.findAllByDM("tx");
		if(StringUtils.isNotEmpty(id)){
			if(StringUtils.isNotEmpty(entity.getKnowledgePoint())){
				String[] str=entity.getKnowledgePoint().split(",");
				for(int i=0;i<str.length;i++){
					Twjfafl fl=twjfaflService.getByName1(str[i],entity);
					listKp.add(fl);
				}
				Twjfafl tw=twjfaflService.getById(entity.getTwjfafl().getFparent().getId());
				allKp=twjfaflService.getALLKnow(tw);
			}
			Iterator<TopicContent> it=entity.getSetTopicContent().iterator();
			List<TopicContent> list=new ArrayList<TopicContent>();
			int num=0;
			while(it.hasNext()){
				TopicContent topic=it.next();
				num++;
				if(topic.getTopicType().equals("2")){
					topic.setRemark(topic.getTopicText());
					String string="<input class='span1 topic_"+num+"'>";
					String string1="<input class='span1 topic_"+num+"' value='";
					String answer=topic.getTopicText().replace(string, "___");
					topic.setTopicText(answer);
					String[] str=topic.getRightAnswers().split(";");
					if(answer.indexOf("___")!=-1){
						String[] str1=answer.split("___");
						String newan="";
						for(int i=0;i<str.length;i++){
							newan+=str1[i]+string1+str[i]+"'>";
						}
						topic.setRightAnswers(newan);
					}
				}
				list.add(topic);
			}
			entity.setListTopicContent(list);
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
			entity = (ContactWebTopic) entityService.getById(id);
		} else {
			entity = new ContactWebTopic();
		}
		
	}
	
	public String ioSave(File file,String fileFileName,int type,int number,int isOpt)throws Exception{
		String path = ServletActionContext.getServletContext().getRealPath("");
		InputStream inputStream = new FileInputStream(file);
		String outAddress="";
		String xuhao="";
		if(type==1){
			outAddress=path+ "\\userfiles\\contactWebTopic\\topicImg\\" ;
			xuhao=number+"";
		}
		if(type==2){
			outAddress=path+ "\\userfiles\\contactWebTopic\\optionImg\\" ;
			xuhao=number+"_"+isOpt;
		}
		OutputStream outputStream = new FileOutputStream(outAddress+fileFileName);
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
		String fileName = user.getLoginName() + format.format(new Date())+"_topic_"+xuhao;
		String ext = FilenameUtils.getExtension(fileFileName);
		File file1 = new File(outAddress + fileFileName);
		file1.renameTo(new File(outAddress + fileName + "."+ ext));
		
		return fileName + "." + ext;
	}
	
	@Override
	public String save() throws Exception {
		for(int i=0;i<list.size();i++){
			List<TopicOptions> opt=list.get(i).getListTopicOptions();
			if(null!=opt&&opt.size()>0){
				for(int j=0;j<opt.size();j++){
					if(null!=opt.get(j).getDocument()&&StringUtils.isNotBlank(opt.get(j).getDocumentFileName())){
						if(!StringUtils.isEmpty(id)){
							if(!opt.get(j).getIsHave().equals("0")){
								//删除
								delPicture(opt.get(j).getIsHave(),2);
							}
						}
						opt.get(j).setOptionPicture(ioSave(opt.get(j).getDocument(),opt.get(j).getDocumentFileName(),2,i,j));
						opt.get(j).setOldPicName(opt.get(j).getDocumentFileName());
					}else{
						if(!StringUtils.isEmpty(id)){
							opt.get(j).setOptionPicture(opt.get(j).getIsHave());
							opt.get(j).setOldPicName(opt.get(j).getIsHaveOld());
						}else{
							opt.get(j).setOptionPicture("0");
							opt.get(j).setOldPicName("0");
						}
					}
				}
				list.get(i).setSetTopicOptions(new HashSet<TopicOptions>(opt));
			}
			if(null!=list.get(i).getDocument()&&StringUtils.isNotBlank(list.get(i).getDocumentFileName())){
				if(!StringUtils.isEmpty(id)){
					if(!list.get(i).getIsHave().equals("0")){
						//删除
						delPicture(list.get(i).getIsHave(),1);
					}
				}
				list.get(i).setTopicPicture(ioSave(list.get(i).getDocument(),list.get(i).getDocumentFileName(),1,i,-1));
				list.get(i).setOldPicName(list.get(i).getDocumentFileName());
			}else{
				if(!StringUtils.isEmpty(id)){
					list.get(i).setTopicPicture(list.get(i).getIsHave());
					list.get(i).setOldPicName(list.get(i).getIsHaveOld());
				}else{
					list.get(i).setTopicPicture("0");
					list.get(i).setOldPicName("0");
				}
			}
			if(list.get(i).getTopicType().equals("2")){
				list.get(i).setTopicText(list.get(i).getTopicText().replace("___", "<input class='span1 topic_"+(i+1)+"'>"));
			}
		}
		if (!StringUtils.isEmpty(id))
		{
			List<TopicContent> listCon=new ArrayList<TopicContent>(entity.getSetTopicContent());
			String topIds="",optIds="";
			for(TopicContent top:listCon){
				topIds+=top.getId()+",";
				List<TopicOptions> listOpt=new ArrayList<TopicOptions>(top.getSetTopicOptions());
				for(TopicOptions opts:listOpt){
					optIds+=opts.getId()+",";
				}
			}
			entity.setSetTopicContent(new HashSet<TopicContent>(list));
			entityService.update1(entity,topIds.substring(0,topIds.length()-1),optIds.substring(0,optIds.length()-1));
		}else{
			entity.setSetTopicContent(new HashSet<TopicContent>(list));
			entity.setCreationTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			entityService.save(entity);
		}
		Struts2Utils.setPromptInfoToReq("保存成功!!");
		
		return RELOAD;
	}
	
	/**
	 * 预览
	 * @author  xl 
	 * @date 创建时间：2017年7月6日 下午5:14:08 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public String preview()throws Exception{
		entity=entityService.getById(id);
		return "preview";
	}
	
	/**
	 * 删图
	 * @author  xl 
	 * @date 创建时间：2017年7月6日 下午5:12:36 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public void delPicture(String path,int type)throws Exception{
		String filePath = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
		String wj="";
		if(type==1){
			wj="topicImg/";
		}
		if(type==2){
			wj="optionImg/";
		}
		if (StringUtils.isNotBlank(path)) {
			// 原文件
			File oldDataFile = new File(filePath + "/userfiles/contactWebTopic/"+wj+ path);
			if (oldDataFile.exists()) {
				// 删除旧的数据文件
				FileUtils.deleteQuietly(oldDataFile);
			}
		}
	}
}
