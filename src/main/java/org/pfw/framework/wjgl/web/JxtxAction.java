package org.pfw.framework.wjgl.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.pfw.framework.base.web.CrudActionSupport;
import org.pfw.framework.tools.WordGenerator;
import org.pfw.framework.wjgl.domain.Jxtm;
import org.pfw.framework.wjgl.domain.Jxtx;
import org.pfw.framework.wjgl.domain.Jxzj;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.orm.hibernate.HibernateWebUtils;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.wjgl.service.JxtxService;
import org.springframework.beans.factory.annotation.Autowired;

import sun.misc.BASE64Encoder;

/**
 * 功能管理Action.
 */
@Results
	({ 
		@Result(name =CrudActionSupport.RELOAD,location="/util/winopen.ftl" , type="freemarker"),
		@Result(name="success",location="/wjgl/jxtx-list.ftl",type = "freemarker"),
		@Result(name ="input",location="/wjgl/jxtx-input.ftl", type = "freemarker")
	})
@SuppressWarnings("all")
public class JxtxAction extends CrudActionSupport<Jxtx> {
	@Autowired
	protected JxtxService entityService;
	
	private List<Jxzj> testPaper;

	
	public List<Jxzj> getTestPaper() {
		return testPaper;
	}

	public void setTestPaper(List<Jxzj> testPaper) {
		this.testPaper = testPaper;
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
			entity = (Jxtx) entityService.getById(id);
		} else {
			entity = new Jxtx();
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
	
	public String saveWord() throws Exception{
		Struts2Utils.getRequest().setCharacterEncoding("utf-8");  
        Map<String, Object> map = new HashMap<String, Object>();  
        // 通过循环将表单参数放入键值对映射中  
        for(int i=0;i<testPaper.size();i++){
        	List<Jxtm> tm=testPaper.get(i).getList();
        	for(int j=0;j<tm.size();j++){
        		String img=getImageStr(tm.get(j).getTmFileName());
        		testPaper.get(i).getList().get(j).setWidth(getWidth(tm.get(j).getTmFileName()));
        		testPaper.get(i).getList().get(j).setHeight(getHeight(tm.get(j).getTmFileName()));
        		testPaper.get(i).getList().get(j).setTmFileName(img);
        	}
        	List<Jxtm> xtm=new ArrayList<Jxtm>();
        	for(int k=0;k<Math.floor(tm.size()/2)*2;k+=2){
        		Jxtm t=new Jxtm();
        		if(Integer.parseInt(tm.get(k).getWidth())>300||k==Math.floor(tm.size()/2)*2-1){
        			t.setDan(tm.get(k).getTmms());
        			t.setDanwidth(tm.get(k).getWidth());
        			t.setDanheight(tm.get(k).getHeight());
        			t.setDantu(tm.get(k).getTmFileName());
        			t.setShuang("");
            		t.setShuangtu("");
            		xtm.add(t);
            		k=k-1;
        		}else{
        			t.setDan(tm.get(k).getTmms());
        			t.setShuang(tm.get(k+1).getTmms());
        			t.setDanwidth(tm.get(k).getWidth());
        			t.setDanheight(tm.get(k).getHeight());
        			t.setShuangwidth(tm.get(k+1).getWidth());
        			t.setShuangheight(tm.get(k+1).getHeight());
        			t.setDantu(tm.get(k).getTmFileName());
        			t.setShuangtu(tm.get(k+1).getTmFileName());
        			xtm.add(t);
        		}
        	}
        	if(tm.size()%2==1){
        		Jxtm t1=new Jxtm();
        		t1.setDan(tm.get(tm.size()-1).getTmms());
        		t1.setDantu(tm.get(tm.size()-1).getTmFileName());
        		t1.setDanwidth(tm.get(tm.size()-1).getWidth());
        		t1.setDanheight(tm.get(tm.size()-1).getHeight());
        		t1.setShuang("");
        		t1.setShuangtu("");
        		xtm.add(t1);
        	}
        	testPaper.get(i).setList(xtm);;
        }
        map.put("testPaper", testPaper);  
        map.put("exam", Struts2Utils.getParameter("exam"));
        map.put("xn", Struts2Utils.getParameter("xn"));
        map.put("xq", Struts2Utils.getParameter("xq"));
        // 提示：在调用工具类生成Word文档之前应当检查所有字段是否完整  
        // 否则Freemarker的模板殷勤在处理时可能会因为找不到值而报错 这里暂时忽略这个步骤了  
        File file = null;  
        InputStream fin = null;  
        ServletOutputStream out = null;  
        try {  
            // 调用工具类WordGenerator的createDoc方法生成Word文档  
            file=WordGenerator.createDoc(map, "temple");
            fin = new FileInputStream(file);  
              
            Struts2Utils.getResponse().setCharacterEncoding("utf-8");  
            Struts2Utils.getResponse().setContentType("application/msword");  
            // 设置浏览器以下载的方式处理该文件默认名为resume.doc  
            Struts2Utils.getResponse().addHeader("Content-Disposition", "attachment;filename=exam.doc");  
              
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
	
	/**
	 * 获取宽度
	 * @author  xl 
	 * @date 创建时间：2017年4月19日 上午10:22:19 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public String getWidth(String address) throws IOException{
		String fileUrl = Struts2Utils.getSession().getServletContext().getRealPath("/") + "userfiles\\kjzt\\wtimg\\" + address;
		File file = new File(fileUrl);
		FileInputStream fis = new FileInputStream(file);
		BufferedImage bufferedImg = ImageIO.read(fis);
		int imgWidth = bufferedImg.getWidth();
		return imgWidth+"";
	}
	
	/**
	 * 获取高度
	 * @author  xl 
	 * @date 创建时间：2017年4月19日 上午10:23:14 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public String getHeight(String address) throws IOException{
		String fileUrl = Struts2Utils.getSession().getServletContext().getRealPath("/") + "userfiles\\kjzt\\wtimg\\" + address;
		File file = new File(fileUrl);
		FileInputStream fis = new FileInputStream(file);
		BufferedImage bufferedImg = ImageIO.read(fis);
		int imgHeight  = bufferedImg.getHeight();
		return imgHeight +"";
	}
	
	/**
	 * 图片转64编码
	 * @author  xl 
	 * @date 创建时间：2017年4月1日 下午3:56:38 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public String getImageStr(String imgFile) {  
        InputStream in = null;  
        byte[] data = null;  
        try {
        	String fileUrl = Struts2Utils.getSession().getServletContext().getRealPath("/") + "userfiles\\kjzt\\wtimg\\" + imgFile;
            in = new FileInputStream(fileUrl);  
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);  
    }  
}
