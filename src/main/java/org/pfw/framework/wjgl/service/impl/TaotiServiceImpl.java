package org.pfw.framework.wjgl.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.pfw.framework.wjgl.dao.TaotiDao;
import org.pfw.framework.wjgl.dao.TwjwtDao;
import org.pfw.framework.wjgl.domain.Taoti;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.service.TaotiService;
import org.pfw.framework.xtgl.domain.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TaotiServiceImpl implements TaotiService {

	@Autowired
	private TaotiDao entityDao;
	@Autowired
	private TwjwtDao wjwtdao;
	
	@Transactional(readOnly=true)
	public Taoti getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Taoti> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	public void save(Taoti entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Taoti entity)
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
	public List<Taoti> findAll()
	{
		return entityDao.find(" from Taoti r order by r.ttmc");
	}

	@Override
	public List<Taoti> getttlist(String flid) {
		// TODO Auto-generated method stub
		int id=Integer.parseInt(flid);
		return entityDao.find(" from Taoti r where r.fafl.id=? order by r.ttmc",id);
	}

	@Override
	public Page findJxPage(Page page, List<String> slist) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		hql.append("from Taoti r ");
		if(slist.size()>0){
			hql.append(" where r.fafl.bz='"+slist.get(2)+"'");
			
			if(slist.get(0)!=null && !"".equals(slist.get(0)) && !"null".equals(slist.get(0)))
			hql.append(" and r.fafl.id='"+slist.get(0)+"'");
			
			if(slist.get(1)!=null && !"".equals(slist.get(1)) && !"null".equals(slist.get(1)))
			hql.append(" and r.ttmc like '%"+slist.get(1)+"%'");
			
		}
		return entityDao.findPage(page, hql.toString());
	}

	@Override
	public Taoti getByName(String mc) {
		// TODO Auto-generated method stub
		return entityDao.findUnique(" from Taoti a where a.ttmc='"+mc+"'");
	}
	
}
