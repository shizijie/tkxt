package org.pfw.framework.wjgl.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.dao.TwjfaflDao;
import org.pfw.framework.wjgl.dao.TwjwtDao;
import org.pfw.framework.wjgl.domain.Taoti;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.wjgl.service.TwjwtService;
import org.pfw.framework.xtgl.domain.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TwjwtServiceImpl implements TwjwtService {
	@Autowired
	private TwjwtDao wjwtdao;

	public Map<String,String> getJfgzMap() {
		Map<String,String> jfgzMap = new TreeMap<String, String>();
		jfgzMap.put("1", "根据正确答案的分值计总分");
		jfgzMap.put("2", "(多选题)全答对得几分");
		jfgzMap.put("3", "(多选题)根据选择的答案正确数，选对几个得几分");
		jfgzMap.put("4", "填空单位计分");
		jfgzMap.put("5", "人工主观计分");
		return jfgzMap;
	}


	public void deleteById(String id) {
		wjwtdao.delete(Integer.parseInt(id));
	}

	public boolean exists(String attName,String value) {
		List<Twjwt> ls = wjwtdao.findBy(attName, value);
		if(ls!=null&&ls.size()>0){
			return true;
		}
		return false;
	}

	public Page findPage(Page page, List<PropertyFilter> filters) {
		return wjwtdao.findPage(page, filters);
	}

	public Twjwt getById(String id) {
		return wjwtdao.get(Integer.parseInt(id));
	}

	public void save(Twjwt entity) {
		wjwtdao.save(entity);
	}

	public void delete(List<String> checks) throws Exception {
		if(checks!=null&&checks.size()>0){
			for (String id : checks) {
				wjwtdao.delete(Integer.parseInt(id));
			}
		}
	}

	public void update(Twjwt entity) {
		wjwtdao.update(entity);
	}

	public List<Twjwt> listByFaid(String faid) {
		List<Twjwt> ls = wjwtdao.getSession().createQuery("select r from Twjwt r join r.faflid a join a.faid b where b.id= '"+faid+"'").list();
		return ls;
	}
	
	@SuppressWarnings("unchecked")
	public List findAll() {
		List ls = wjwtdao.getSession().createQuery("from SupervisionItem r where r.parent is null order by r.lbdm,r.id").list();
		return ls;
	}

	/**
	 * 根据课程提取题目
	 */
	@Override
	public List<Twjwt> getRandwt(Twjfafl fl) {
		String[] str1=fl.getTiliang().split("/");
		List<Twjwt> retList = new ArrayList<Twjwt>();
		for(int i=0;i<str1.length;i++){
			String[] str2=str1[i].split(":");
			StringBuffer hql=new StringBuffer();
			hql.append(" from Twjwt r where r.wtlx like '%"+str2[0]+"%' and r.faflid.id in ( ");
			int num=0;
			for(Twjfafl wtfl:fl.getChildren()){
				num++;
				if(num==fl.getChildren().size()){
					hql.append(wtfl.getId());
				}else{
					hql.append(wtfl.getId()+",");
				}
			}
			hql.append(") order by newId() ");
			System.out.println(hql.toString());
			Query q=wjwtdao.getSession().createQuery(hql.toString());
			q.setFirstResult(0);
			if(Integer.parseInt(str2[1])!=-1){
				q.setMaxResults(Integer.parseInt(str2[1]));
			}
			List<Twjwt> list=q.list();
			for(Twjwt tmp : list){
				retList.add(tmp);
			}
		}
		return retList;
	}


	@Override
	public List tmfltj() {
		String hql = "select t.faflid.flmc,count(*) from Twjwt t group by t.faflid.flmc";
		Query q = wjwtdao.getSession().createQuery(hql);
		return q.list();
	}


	@Override
	public List<Twjwt> getWtByfl(String flid) {
		String hql = " from Twjwt r where r.faflid.id = ?";
		Query q = wjwtdao.getSession().createQuery(hql);
		q.setString(0, flid);
		q.setFirstResult(0);
		List<Twjwt> list = q.list();
		return list;
	}


	@Override
	public String getRandwtId(int wts) {
		String hql = " from Twjwt r order by newId() ";
		Query q = wjwtdao.getSession().createQuery(hql);
		q.setFirstResult(0);
		if(wts != -1)
			q.setMaxResults(wts);
		List<Twjwt> list = q.list();
		String retStr = "";
		if(list != null && list.size() > 0)
		{
			for(Twjwt tmpent : list)
			{
				retStr+= "'"+tmpent.getId()+"',";
			}
		}
		if(!retStr.equals(""))
			retStr = retStr.substring(0,retStr.length()-1);
		
		return retStr;
	}


	@Override
	public List<Twjwt> getRandwt(int faflid, int dxtsl, int pdtsl) {
		//单选题
		String hql = " from Twjwt r where r.faflid.id = ? and r.wtmc like '%单选题%' order by newId()";
		Query q = wjwtdao.getSession().createQuery(hql);
		q.setInteger(0, faflid);
		q.setFirstResult(0);
		if(dxtsl != -1)
			q.setMaxResults(dxtsl);
		List<Twjwt> listdxt = q.list();
		
		//判断题
		hql = " from Twjwt r where r.faflid.id = ? and r.wtmc like '%判断题%' order by newId()"; 
		Query q2 = wjwtdao.getSession().createQuery(hql);
		q2.setInteger(0, faflid);
		q2.setFirstResult(0);
		if(pdtsl != -1)
			q2.setMaxResults(pdtsl);
		List<Twjwt> listpdt = q2.list();
		
		List<Twjwt> retList = new ArrayList<Twjwt>();
		for(Twjwt tmp : listdxt)
			retList.add(tmp);
		for(Twjwt tmp : listpdt)
			retList.add(tmp);
		
		return retList;
	}
	
	@Override
	public List<Twjwt> randomTi(Twjfafl fl,Dict lx, String ndfb, String tms,String taoti) {
		StringBuffer hql=new StringBuffer();
		if(StringUtils.isNotBlank(taoti)){
			hql.append(" select c from Twjwt c where c.id not in (select distinct r.id from Twjwt r,Taoti t  where r in elements(t.tmlist)) and  c.wtlx= '"+lx.getName()+"'  ");
		}else{
			hql.append(" select c from Twjwt c where c.wtlx= '"+lx.getName()+"' ");
		}
		if(fl.getChildren().size()>0&&fl.getChildren()!=null){
			hql.append(" and c.faflid.id in(");
			int i=0;
			for(Twjfafl wtfl:fl.getChildren()){
				i++;
				if(i==fl.getChildren().size()){
					hql.append(wtfl.getId());
				}else{
					hql.append(wtfl.getId()+",");
				}
			}
			hql.append(") ");
		}
		List<Twjwt> retList = new ArrayList<Twjwt>();
		if(StringUtils.isNotBlank(ndfb)){
			String[] ny=ndfb.split(",");
			for(int i=0;i<ny.length;i++){
				StringBuffer hql2=new StringBuffer();
				hql2.append(hql);
				String[] nystr=ny[i].split(":");
				int num = (int)(((Float.parseFloat(tms)*Float.parseFloat(nystr[1]))/100)+0.5);
				if(nystr[0].matches("^[a-zA-Z]*")){
					if(nystr[0].equals("ALL")){
						hql2.append(" and (c.knowledgePoint='' or c.knowledgePoint='NULL' or c.knowledgePoint is null) order by newid()");
					}else{
						hql2.append(" and c.nycd='"+nystr[0]+"' order by newid()");
					}
				}else{
					hql2.append(" and c.knowledgePoint like '%"+nystr[0]+"%' order by newid()");
				}
				Query q=wjwtdao.getSession().createQuery(hql2.toString());
				System.out.println(hql2.toString());
				q.setFirstResult(0);
				if(num!=-1){
					q.setMaxResults(num);
				}
				List<Twjwt> list=q.list();
				for(Twjwt wt:list){
					retList.add(wt);
				}
			}
		}else{
			hql.append(" order by newid() ");
			Query q=wjwtdao.getSession().createQuery(hql.toString());
			q.setMaxResults(Integer.parseInt(tms));
			retList=q.list();
		}
		return retList;
		
		
	}

	@Override
	public List<Twjwt> getRandwtForTaoti(Twjfafl fl,int dxtsl,int pdtsl) {
		//单选题
		StringBuffer hql1=new StringBuffer();
		hql1.append( "select c from Twjwt c where c.id not in (select distinct r.id from Twjwt r,Taoti t where r in elements(t.tmlist))  and c.wtmc like '%单选题%' ");
		//String hql = "select distinct r from Twjwt r,Taoti";
		hql1.append(" and c.faflid.id in (");
		int i=0;
		for(Twjfafl wtfl:fl.getChildren()){
			i++;
			if(i==fl.getChildren().size()){
				hql1.append(wtfl.getId());
			}else{
				hql1.append(wtfl.getId()+",");
			}
		}
		hql1.append(" ) order by newid() ");
		Query q = wjwtdao.getSession().createQuery(hql1.toString());
		q.setFirstResult(0);
		if(dxtsl != -1)
			q.setMaxResults(dxtsl);
		List<Twjwt> listdxt = q.list();
		
		//判断题
		StringBuffer hql2=new StringBuffer();
		hql2.append( "select c from Twjwt c where c.id not in (select distinct r.id from Twjwt r,Taoti t  where r in elements(t.tmlist)) and  c.wtmc like '%判断题%' "); 
		hql2.append(" and c.faflid.id in (");
		int j=0;
		for(Twjfafl wtfl:fl.getChildren()){
			j++;
			if(j==fl.getChildren().size()){
				hql2.append(wtfl.getId());
			}else{
				hql2.append(wtfl.getId()+",");
			}
		}
		hql2.append(" ) order by newid() ");
		Query q2 = wjwtdao.getSession().createQuery(hql2.toString());
		q2.setFirstResult(0);
		if(pdtsl != -1)
			q2.setMaxResults(pdtsl);
		List<Twjwt> listpdt = q2.list();
		
		List<Twjwt> retList = new ArrayList<Twjwt>();
		for(Twjwt tmp : listdxt)
			retList.add(tmp);
		for(Twjwt tmp : listpdt)
			retList.add(tmp);
		
		return retList;
	}


	/**
	 * /lx表示题型传参,1表示单选、2表示判断
	 */
	@Override
	public String getSytlForTaoti(int faflid, String lx,Twjfafl fl) {
		
		List<Taoti> fatt = wjwtdao.find(" from Taoti r where r.fafl.id = ?", faflid);
		if(lx.equals("1"))
		{	
			long tmpl=0;
			if(fatt != null && fatt.size() > 0)
			{	
				for(Twjfafl wtfl:fl.getChildren()){
					long tmpl1 = wjwtdao.findUnique("select count(c) from Twjwt c where c.id not in (select r.id from Twjwt r ,Taoti t where r in elements(t.tmlist)) and c.faflid.id = ? and c.wtmc like '%单选题%' ",wtfl.getId());
					tmpl+=tmpl1;
				}
				return String.valueOf(tmpl);
			}else{
				for(Twjfafl wtfl:fl.getChildren()){
					tmpl += wjwtdao.countHqlResult("select r from Twjwt r  where r.faflid.id = ? and r.wtmc like '%单选题%' ", wtfl.getId());
				}
				return String.valueOf(tmpl);
			}
			
		}
		if(lx.equals("2"))
		{	
			long tmpl=0;
			if(fatt != null && fatt.size() > 0)
			{	
				for(Twjfafl wtfl:fl.getChildren()){
					long tmpl1 = wjwtdao.findUnique("select count(c) from Twjwt c where c.id not in (select r.id from Twjwt r ,Taoti t where r in elements(t.tmlist)) and c.faflid.id = ? and c.wtmc like '%判断题%' ",wtfl.getId());
					tmpl+=tmpl1;
				}
				return String.valueOf(tmpl);
			}else{
				for(Twjfafl wtfl:fl.getChildren()){
					tmpl += wjwtdao.countHqlResult("select r from Twjwt r  where r.faflid.id = ? and r.wtmc like '%判断题%' ", wtfl.getId());
				}
				return String.valueOf(tmpl);
			}
		}
		
		return null;
	}


	@Override
	public Object getdx(int id) {
		// TODO Auto-generated method stub
		return wjwtdao.findUnique(" select count(c) from Twjwt c where c.faflid.id = "+id+" and c.wtmc like '%单选题%'");
	}


	@Override
	public Object getpd(int id) {
		// TODO Auto-generated method stub
		return wjwtdao.findUnique(" select count(c) from Twjwt c where c.faflid.id = "+id+" and c.wtmc like '%判断题%'");
	}


	@Override
	public Page findlist(Page page,  List<String> slist) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		hql.append("from Twjwt r ");
		if(slist.size()>0){
			hql.append(" where r.faflid.bz='"+slist.get(2)+"'");
			
			if(slist.get(0)!=null && !"".equals(slist.get(0)) && !"null".equals(slist.get(0)))
			hql.append(" and r.faflid.id='"+slist.get(0)+"'");
			
			if(slist.get(1)!=null && !"".equals(slist.get(1)) && !"null".equals(slist.get(1)))
			hql.append(" and r.wtmc like '%"+slist.get(1)+"%'");
		}
		return wjwtdao.findPage(page, hql.toString());
	}


	@Override
	public Object countKnow(Twjfafl tw,String lx,String cs) {
		StringBuffer hql=new StringBuffer();
		hql.append(" select count(c) from Twjwt c where c.faflid.fparent.id = "+tw.getFparent().getFparent().getId());
		hql.append(" and c.knowledgePoint like '%"+tw.getFlmc()+"%'");
		if(StringUtils.isBlank(cs)){
			hql.append(" and c.id not in (select distinct a.id from Twjwt a,Taoti t  where a in elements(t.tmlist))");
		}
		if(StringUtils.isNotEmpty(lx)){
			hql.append(" and c.wtlx='"+lx+"'");
		}
		return wjwtdao.findUnique(hql.toString());
	}


	@Override
	public Object countCourse(Twjfafl tw) {
		StringBuffer hql=new StringBuffer();
		hql.append(" select count(c) from Twjwt c where c.faflid.fparent.id = "+tw.getId());
		return wjwtdao.findUnique(hql.toString());
	}

	/**
	 * 根据测试任务id计算各问题类型数量
	 */
	@Override
	public List<Object[]> countNum(String id) {
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT b.wtlx,COUNT(1) FROM T_WJGL_RWTM a,T_WJGL_WJWT b where a.TM_ID=b.ID and a.RW_ID=? group by b.WTLX ");
		return wjwtdao.getSession().createSQLQuery(sql.toString()).setString(0, id).list();
	}

	/**
	 * 根据章节id计算各问题类型数量
	 */
	@Override
	public List<Object[]> countDict(Twjfafl wtfl) {
		StringBuffer hql=new StringBuffer();
		hql.append(" select b.wtlx,count(1) from T_WJGL_WJFAFL a,T_WJGL_WJWT b where b.faflid=a.id and a.id=? group by b.wtlx");
		Query qry=wjwtdao.getSession().createSQLQuery(hql.toString());
		qry.setInteger(0, wtfl.getId());
		return qry.list();
	}


	@Override
	public Object countKnow1(Twjfafl fl,String lx,String cs) {
		StringBuffer hql=new StringBuffer();
		hql.append(" select count(c) from Twjwt c where c.faflid.fparent.id ='"+fl.getId()+"'");
		hql.append(" and (c.knowledgePoint is null or c.knowledgePoint='' or c.knowledgePoint='NULL')");
		if(StringUtils.isBlank(cs)){
			hql.append(" and c.id not in (select distinct a.id from Twjwt a,Taoti t  where a in elements(t.tmlist))");
		}
		if(StringUtils.isNotEmpty(lx)){
			hql.append(" and c.wtlx='"+lx+"'");
		}
		return wjwtdao.findUnique(hql.toString());
	}


	
	

}
