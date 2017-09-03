package org.pfw.framework.wjgl.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.modules.web.struts2.Struts2Utils;
import org.pfw.framework.wjgl.dao.CsrwDao;
import org.pfw.framework.wjgl.dao.TwjwtDao;
import org.pfw.framework.wjgl.domain.Csrw;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.service.CsrwService;
import org.pfw.framework.wjgl.service.TwjfaflService;
import org.pfw.framework.wjgl.service.TwjwtService;
import org.pfw.framework.xtgl.domain.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.CallableStatement;

import sun.jdbc.odbc.ee.ConnectionPool;
import sun.misc.BASE64Encoder;


@Service
@Transactional
public class CsrwServiceImpl implements CsrwService {

	@Autowired
	private CsrwDao entityDao;
	@Autowired
	private TwjwtDao wjwtdao;
	@Autowired
	private TwjfaflService flService;

	@Transactional(readOnly=true)
	public Csrw getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Csrw> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	public void save(Csrw entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Csrw entity)
	{
		entityDao.update(entity);
	}

	public void deleteById(String id)
	{
		entityDao.delete(id);
	}
	
	public void delete(List<String> checks)
	{
		if(checks!=null&&checks.size()>0){
			for (String id : checks) {
				entityDao.delete(id);
			}
		}
	}

	@Override
	public List<Csrw> findAll()
	{
		return entityDao.find(" from Csrw r order by r.bjmc");
	}

	/**
	 * 随机组卷
	 */
	@Override
	public String sjzj(Dict tmlx, String rwid, String tms) throws Exception{
		// TODO Auto-generated method stub
		Csrw tmpcsrw = entityDao.get(rwid);
		Twjfafl fafltmp = tmpcsrw.getFafl();
		subjdjsonstr = "";
		this.getSubJdSql(fafltmp);
		
		String sctl = "";
		String scndzbfb = "";
		String sczsdzbfb = "";
		
		Set<Twjwt> tmpset = tmpcsrw.getTmlist();
		
		//先删除单选题
		Iterator<Twjwt> menuIter = tmpset.iterator();
		while(menuIter.hasNext())
		{
			Twjwt tmpwjwt = menuIter.next();
			
			if(tmpwjwt.getWtlx() != null && tmpwjwt.getWtlx().equals(tmlx.getName()))
			{
				menuIter.remove();
			}
		}
		//生成随机单选题
		if(!subjdjsonstr.equals(""))
			subjdjsonstr = subjdjsonstr.substring(0,subjdjsonstr.length()-1);
		
		String hql = " from Twjwt r where r.faflid.id in ("+subjdjsonstr+")  and r.wtlx = '"+tmlx.getName()+"' order by newId()";
		Query q = entityDao.getSession().createQuery(hql);
		q.setFirstResult(0);
		if(Integer.parseInt(tms) != -1)
			q.setMaxResults(Integer.parseInt(tms));
		List<Twjwt> listdxt = q.list();
		
		//加入到任务问题中去
		for(Twjwt twt : listdxt)
		{
			tmpset.add(twt);
		}
		tmpcsrw.setTmlist(tmpset);
		entityDao.update(tmpcsrw);
		entityDao.getSession().flush();
		
		sctl = String.valueOf(listdxt.size());
		scndzbfb = getscndzb(rwid,tmlx.getName(),listdxt.size());
		scndzbfb=scndzbfb.replaceAll("NULL", "无");
		sczsdzbfb = getzsdfb(rwid,tmlx.getName(),listdxt.size());
		
		return sctl+"|"+scndzbfb+"|"+sczsdzbfb;
		
	}
	

	/**
	 * 难度占比组题
	 */
	@Override
	public String sjndzj(Dict tmlx, String rwid, String tms, String ndfb)
			throws Exception {

		List<String> ndfblst = Struts2Utils.split(ndfb, ";");
		
		Csrw tmpcsrw = entityDao.get(rwid);
		Twjfafl fafltmp = tmpcsrw.getFafl();
		subjdjsonstr = "";
		this.getSubJdSql(fafltmp);
		if(!subjdjsonstr.equals(""))
			subjdjsonstr = subjdjsonstr.substring(0,subjdjsonstr.length()-1);
		
		String sctl = "";
		String scndzbfb = "";
		String sczsdzbfb = "";
		
		int sjtms = 0;
		
		Set<Twjwt> tmpset = tmpcsrw.getTmlist();
		
		//先删除单选题
		Iterator<Twjwt> menuIter = tmpset.iterator();
		while(menuIter.hasNext())
		{
			Twjwt tmpwjwt = menuIter.next();
			
			if(tmpwjwt.getWtlx() != null && tmpwjwt.getWtlx().equals(tmlx.getName()))
			{
				menuIter.remove();
			}
		}
		
		
		for(String tmpstr : ndfblst)
		{
			String nddj = tmpstr.substring(0,tmpstr.indexOf(":"));
			float nddjzb = Float.parseFloat(tmpstr.substring(tmpstr.indexOf(":")+1));
			
			//生成难度比例题目
			int sjsctms = (int)(((Float.parseFloat(tms)*nddjzb)/100)+0.5);//+0.5表示四舍五入
			
			String hql = " from Twjwt r where r.faflid.id in ("+subjdjsonstr+")  and r.wtlx = '"+tmlx.getName()+"' and r.nycd='"+nddj+"' order by newId()";
			Query q = entityDao.getSession().createQuery(hql);
			q.setFirstResult(0);
			if(Integer.parseInt(tms) != -1)
				q.setMaxResults(sjsctms);
			List<Twjwt> listdxt = q.list();
			
			//加入到任务问题中去
			for(Twjwt twt : listdxt)
			{
				tmpset.add(twt);
			}
			tmpcsrw.setTmlist(tmpset);
			entityDao.update(tmpcsrw);
			entityDao.getSession().flush();
			
			sjtms += listdxt.size();
		}
		
		sctl = String.valueOf(sjtms);
		scndzbfb = getscndzb(rwid,tmlx.getName(),sjtms);
		sczsdzbfb = getzsdfb(rwid,tmlx.getName(),sjtms);
		
		return sctl+"|"+scndzbfb+"|"+sczsdzbfb;
		
	}

	/**
	 * 知识点分布组题
	 */
	@Override
	public String sjzsdzj(Dict tmlx, String rwid, String tms, String zsdfb)
			throws Exception {
		//知识点
		List<String> zsdfblst = Struts2Utils.split(zsdfb, ";");
		//试卷 
		Csrw tmpcsrw = entityDao.get(rwid);
		//获得课程
		Twjfafl fafltmp = tmpcsrw.getFafl();
		
		String sctl = "";
		String scndzbfb = "";
		String sczsdzbfb = "";
		
		int sjtms = 0;
		
		//获得题目
		Set<Twjwt> tmpset = tmpcsrw.getTmlist();
		
		//先删除不同类型的题目题目
		Iterator<Twjwt> menuIter = tmpset.iterator();
		while(menuIter.hasNext())
		{
			Twjwt tmpwjwt = menuIter.next();
			if(tmpwjwt.getWtlx() != null && tmpwjwt.getWtlx().equals(tmlx.getName()))
			{
				menuIter.remove();
			}
		}
		
		for(String tmpstr : zsdfblst)
		{
			String zsdid = tmpstr.substring(0,tmpstr.indexOf(":"));  //知识点id
			float  zsdzb = Float.parseFloat(tmpstr.substring(tmpstr.indexOf(":")+1)); //占比
			
			//生成难度比例题目
			int sjsctms = (int)(((Float.parseFloat(tms)*zsdzb)/100)+0.5);//+0.5表示四舍五入
			Twjfafl fl=flService.getById(Integer.parseInt(zsdid));
			String hql = " from Twjwt r where r.knowledgePoint like '%"+fl.getFlmc()+"%' and r.wtlx = '"+tmlx.getName()+"' order by newId()";
			Query q = entityDao.getSession().createQuery(hql);
			q.setFirstResult(0);
			if(Integer.parseInt(tms) != -1)
				q.setMaxResults(sjsctms);
			List<Twjwt> listdxt = q.list();
			
			//加入到任务问题中去
			for(Twjwt twt : listdxt)
			{
				tmpset.add(twt);
			}
			tmpcsrw.setTmlist(tmpset);
			entityDao.update(tmpcsrw);
			entityDao.getSession().flush();
			
			sjtms += listdxt.size();
		}
		
		sctl = String.valueOf(sjtms);
		scndzbfb = getscndzb(rwid,tmlx.getName(),sjtms);
		sczsdzbfb = getzsdfb(rwid,tmlx.getName(),sjtms);
				
		return sctl+"|"+scndzbfb+"|"+sczsdzbfb;
	}
	
	/**
	 * 难度、知识点分布组卷
	 */
	@Override
	public String sjndzsdzj(Dict tmlx, String rwid, String tms, String ndfb,
			String zsdfb) throws Exception 
	{
		List<String> zsdfblst = Struts2Utils.split(zsdfb, ";");
		List<String> ndfblst = Struts2Utils.split(ndfb, ";");
		
		DecimalFormat df = new DecimalFormat("0.00");
		
		Csrw tmpcsrw = entityDao.get(rwid);
		Twjfafl fafltmp = tmpcsrw.getFafl();
		
		String sctl = "";
		String scndzbfb = "";
		String sczsdzbfb = "";
		
		int sjtms = 0;
		
		Set<Twjwt> tmpset = tmpcsrw.getTmlist();
		
		//先删除单选题
		Iterator<Twjwt> menuIter = tmpset.iterator();
		while(menuIter.hasNext())
		{
			Twjwt tmpwjwt = menuIter.next();
			if(tmpwjwt.getWtlx() != null && tmpwjwt.getWtlx().equals(tmlx))
			{
				menuIter.remove();
			}
		}
		
		List<String> zsdndls = new ArrayList<String>();//存储知识点-难度的笛卡尔积的题目数结果
		for(String tmpstr : zsdfblst)
		{
			String zsdid = tmpstr.substring(0,tmpstr.indexOf(":"));
			float  zsdzb = Float.parseFloat(tmpstr.substring(tmpstr.indexOf(":")+1));
			
			//生成知识点题目数
			int sjsctms = (int)(((Float.parseFloat(tms)*zsdzb)/100)+0.5);//+0.5表示四舍五入
			
			for(String tmpndstr : ndfblst)
			{
				String nddj = tmpndstr.substring(0,tmpndstr.indexOf(":"));
				float nddjzb = Float.parseFloat(tmpndstr.substring(tmpndstr.indexOf(":")+1));
				
				double tmpft =  (( sjsctms*nddjzb)/100);
				//int zsdndtms = (int)();//+0.5表示四舍五入
				
				zsdndls.add(zsdid+"-"+nddj+":"+df.format(tmpft));
			}
			
		}
		
		return "";
	}
	
	/**
	 * 组卷完成后，查询出难度分布结果
	 * @param rwid
	 * @param liketx
	 * @param ztl
	 * @return
	 * @throws Exception
	 */
	public String getscndzb(String rwid,String wtlx,int ztl) throws Exception
	{
		String sql = "SELECT nycd,COUNT(*) FROM T_WJGL_RWTM a,t_wjgl_wjwt b  where a.TM_ID=b.ID and b.wtlx = '"+wtlx+"' and a.RW_ID='"+rwid+"' group by nycd order by nycd";
		Query q = entityDao.getSession().createSQLQuery(sql);
		List ls = q.list();
		String retstr = "";
		DecimalFormat df = new DecimalFormat("0.0");
		for(int i=0;i<ls.size();i++)
		{
			Object[] tmpobj = (Object[]) ls.get(i);
			String nycd = (String)tmpobj[0];
			String nytl = String.valueOf(tmpobj[1]);
			String bfb = df.format(Float.parseFloat(nytl)/ztl*100);
			retstr += nycd + ":" + bfb +"%("+nytl+");";
		}
		return retstr;
	}
	
	/**
	 * 组卷完成后，查询出知识点分布结果
	 * @param rwid
	 * @param liketx
	 * @param ztl
	 * @return
	 * @throws Exception
	 */
	public String getzsdfb(String rwid,String wtlx,int ztl) throws Exception
	{
		String sql = "select FLMC,COUNT(*) from T_WJGL_RWTM a,t_wjgl_wjwt b,T_WJGL_WJFAFL c  where a.TM_ID=b.ID and b.FAFLID=c.ID and b.wtlx = '"+wtlx+"'  and a.RW_ID='"+rwid+"' group by FLMC";
		Query q = entityDao.getSession().createSQLQuery(sql);
		List ls = q.list();
		String retstr = "";
		DecimalFormat df = new DecimalFormat("0.0");
		for(int i=0;i<ls.size();i++)
		{
			Object[] tmpobj = (Object[]) ls.get(i);
			String zsd = (String)tmpobj[0];
			String zsdtl = String.valueOf(tmpobj[1]);
			String bfb = df.format(Float.parseFloat(zsdtl)/ztl*100);
			retstr += zsd + ":" + bfb +"%("+zsdtl+");";
		}
		return retstr;
	}
	
	private String subjdjsonstr = "";
	/**
	 * 得到课程下,所有章节知识点id,供组题时sql调用使用
	 * @param fafltmp
	 * @return
	 */
	public String getSubJdSql(Twjfafl fafltmp)
	{
		Set<Twjfafl> menuChildrenSet =  fafltmp.getChildren();
		if(null!=menuChildrenSet&&menuChildrenSet.size()>0){
			Iterator<Twjfafl> menuIter = menuChildrenSet.iterator();
			subjdjsonstr += "'" + fafltmp.getId() + "',";
			while(menuIter.hasNext())
			{
				Twjfafl tmpmenu = menuIter.next();
				subjdjsonstr += "'" + tmpmenu.getId() + "',";
				if(tmpmenu.getChildren().size() > 0)
					getSubJdSql(tmpmenu);
			}
		}
		return subjdjsonstr;
	}

	@Override
	public List getFb(Twjfafl fafl,String hql,int type) throws Exception {
		subjdjsonstr = "";
		getSubJdSql(fafl);
		System.out.println(subjdjsonstr);
		if(!subjdjsonstr.equals(""))
			subjdjsonstr = subjdjsonstr.substring(0,subjdjsonstr.length()-1);
		hql = hql.replace("?", subjdjsonstr);
		Query q=null;
		if(type==1){
			q = entityDao.getSession().createQuery(hql);
		}
		if(type==2){
			q = entityDao.getSession().createSQLQuery(hql);
		}
		//q.setString(0, subjdjsonstr);
		return q.list();
		
	}
	
	/**
	 * 题目类型分布
	 */
	@Override
	public List lxFb(Twjfafl fafl,String hql,String lx) throws Exception {
		subjdjsonstr = "";
		getSubJdSql(fafl);
		if(!subjdjsonstr.equals(""))
			subjdjsonstr = subjdjsonstr.substring(0,subjdjsonstr.length()-1);
		hql = hql.replace("?", subjdjsonstr);
		hql = hql.replace("|", lx);
		Query q = entityDao.getSession().createQuery(hql);
		//q.setString(0, subjdjsonstr);
		return q.list();
		
	}
	
	@Override
	public Object countKnow(Twjfafl fafl, Twjfafl know, String sql, int type) {
		if(type==2){
			subjdjsonstr = "";
			getSubJdSql(fafl);
			if(!subjdjsonstr.equals("")){
				subjdjsonstr = subjdjsonstr.substring(0,subjdjsonstr.length()-1);
			}
			sql=sql.replace("?", subjdjsonstr);
			sql=sql.replace("|", know.getFlmc());
			return entityDao.getSession().createSQLQuery(sql).uniqueResult();
		}
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public List countKnowledgePoint(List<Twjfafl> allKnow) {
		if(allKnow!=null&&allKnow.size()>0){
			StringBuffer hql=new StringBuffer();
			hql.append(" select r.faflid.flmc,count(*) from Twjwt r where r.faflid.id in (");
			hql.append(allKnow.get(0).getId());
			for(int i=1;i<allKnow.size();i++){
				hql.append(","+allKnow.get(i).getId());
			}
			hql.append(") group by r.faflid.flmc");
			return entityDao.getSession().createQuery(hql.toString()).list();
		}else{
			return null;
		}
		
	}
	public void build(String dict,String rwid,String num,String nandu,String zsd){
		Csrw enty = entityDao.get(rwid);
		Twjfafl kc = enty.getFafl();
		//获得题目
		Set<Twjwt> pro = enty.getTmlist();
		String[] nan=nandu.split(";");
		String[] zs=zsd.split(";");
		List<Twjwt> list=new ArrayList<Twjwt>();
		int sum1=0,sum2=0;
		for(int i=0;i<nan.length;i++){
			for(int j=0;j<zs.length;j++){
				String[] nan2=nan[i].split(":");
				String[] zs2=zs[j].split(":");
				int sl=(int) (Double.parseDouble(nan2[1])*Double.parseDouble(zs2[1])*Double.parseDouble(num)/10000);
				sum1+=sl;
				StringBuffer hql=new StringBuffer();
				hql.append(" select count(a) from Twjwt a where a.faflid.fparent.id="+kc.getId());
				Twjfafl fl=flService.getById(Integer.parseInt(zs2[0]));
				hql.append(" and a.nycd='"+nan2[0]+"' and a.knowledgePoint like '%"+fl.getFlmc()+"%'");
				int sj=Integer.parseInt(wjwtdao.findUnique(hql.toString()).toString());
				sum2+=sj;
			}
		}
	}
	
	public Object countNum(Twjfafl kc,String nandu,String know)throws Exception{
		StringBuffer hql=new StringBuffer();
		hql.append(" select count(a) from Twjwt a where a.faflid.fparent.id="+kc.getId());
		hql.append(" and a.wtlx='"+nandu+"' and a.knowledgePoint like '%"+know+"%'");
		return wjwtdao.findUnique(hql.toString());
	}
	
	/**
	 * 图片转64编码
	 * @author  xl 
	 * @date 创建时间：2017年4月1日 下午3:56:38 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public String getImageStr(String imgFile,String type) throws IOException{  
        InputStream in = null;  
        byte[] data = null;  
        try {
        	String fileUrl = Struts2Utils.getSession().getServletContext().getRealPath("/") + "userfiles\\kjzt\\"+type+"\\" + imgFile;
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
	/**
	 * 获取宽度
	 * @author  xl 
	 * @date 创建时间：2017年4月19日 上午10:22:19 
	 * @Description 
	 * @parameter   
	 * @return
	 */
	public String getWidth(String address,String type) throws IOException{
		String fileUrl = Struts2Utils.getSession().getServletContext().getRealPath("/") + "userfiles\\kjzt\\"+type+"\\" + address;
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
	public String getHeight(String address,String type) throws IOException{
		String fileUrl = Struts2Utils.getSession().getServletContext().getRealPath("/") + "userfiles\\kjzt\\"+type+"\\" + address;
		File file = new File(fileUrl);
		FileInputStream fis = new FileInputStream(file);
		BufferedImage bufferedImg = ImageIO.read(fis);
		int imgHeight  = bufferedImg.getHeight();
		return imgHeight +"";
	}

	@Override
	public void test() throws HibernateException, SQLException {
		System.out.println(entityDao.getSession().createSQLQuery(" exec master.dbo.PROC_SELECT_WJWT_BY_FAFLID_COUNT ? ").setString(0, "88").uniqueResult());
	}

	@Override
	public List getNy(Twjfafl fafl, String tx,String sql) {
		subjdjsonstr = "";
		getSubJdSql(fafl);
		if(!subjdjsonstr.equals("")){
			subjdjsonstr = subjdjsonstr.substring(0,subjdjsonstr.length()-1);
		}
		sql=sql.replace("?", subjdjsonstr);
		sql=sql.replace("|", tx);
		return entityDao.getSession().createSQLQuery(sql).list();
	}


}
