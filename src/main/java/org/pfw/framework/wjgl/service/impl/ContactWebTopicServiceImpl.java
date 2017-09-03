package org.pfw.framework.wjgl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.pfw.framework.wjgl.dao.ContactWebTopicDao;
import org.pfw.framework.wjgl.domain.ContactWebTopic;
import org.pfw.framework.wjgl.domain.Twjfafl;
import org.pfw.framework.wjgl.domain.Twjwt;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.service.ContactWebTopicService;
import org.pfw.framework.xtgl.domain.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class ContactWebTopicServiceImpl implements ContactWebTopicService {

	@Autowired
	private ContactWebTopicDao entityDao;

	@Transactional(readOnly=true)
	public ContactWebTopic getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<ContactWebTopic> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	public void save(ContactWebTopic entity)
	{
		entityDao.save(entity);
	}
	
	public void update(ContactWebTopic entity)
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
	public List<ContactWebTopic> findAll()
	{
		return entityDao.find(" from ContactWebTopic r order by r.bjmc");
	}

	@Override
	public void update1(ContactWebTopic entity, String substring,
			String substring2) {
		String sql1=" delete T_WJGL_TOPIC_CONTENT  where T_WJGL_TOPIC_CONTENT.id in ('"+substring.replaceAll(",", "','")+"') ";
		String sql2=" delete T_WJGL_TOPIC_OPTIONS  where T_WJGL_TOPIC_OPTIONS.id in ('"+substring2.replaceAll(",", "','")+"') ";
		entityDao.update(entity);
		entityDao.getSession().createSQLQuery(sql2+sql1).executeUpdate();
	}

	@Override
	public List<ContactWebTopic> randomTi(Twjfafl fl, String ndfb, String tms) {
		StringBuffer hql=new StringBuffer();
		hql.append(" from ContactWebTopic a where a.topicType= '综合题' ");
		if(fl.getChildren().size()>0&&fl.getChildren()!=null){
			hql.append(" and a.twjfafl.id in(");
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
		List<ContactWebTopic> retList = new ArrayList<ContactWebTopic>();
		if(StringUtils.isNotBlank(ndfb)){
			String[] ny=ndfb.split(",");
			for(int i=0;i<ny.length;i++){
				StringBuffer hql2=new StringBuffer();
				hql2.append(hql);
				String[] nystr=ny[i].split(":");
				int num = (int)(((Float.parseFloat(tms)*Float.parseFloat(nystr[1]))/100)+0.5);
				if(nystr[0].matches("^[a-zA-Z]*")){
					if(nystr[0].equals("ALL")){
						hql2.append(" and (a.knowledgePoint='' or a.knowledgePoint='NULL' or a.knowledgePoint is null) order by newid()");
					}else{
						hql2.append(" and a.complexity='"+nystr[0]+"' order by newid()");
					}
				}else{
					hql2.append(" and c.knowledgePoint like '%"+nystr[0]+"%' order by newid()");
				}
				Query q=entityDao.getSession().createQuery(hql2.toString());
				System.out.println(hql2.toString());
				q.setFirstResult(0);
				if(num!=-1){
					q.setMaxResults(num);
				}
				List<ContactWebTopic> list=q.list();
				for(ContactWebTopic wt:list){
					retList.add(wt);
				}
			}
		}else{
			hql.append(" order by newid() ");
			Query q=entityDao.getSession().createQuery(hql.toString());
			q.setMaxResults(Integer.parseInt(tms));
			retList=q.list();
		}
		return retList;
	}

	
}
