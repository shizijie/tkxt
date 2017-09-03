package org.pfw.framework.wjgl.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.pfw.framework.wjgl.dao.JxTypeDao;
import org.pfw.framework.wjgl.domain.JxType;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.service.JxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class JxTypeServiceImpl implements JxTypeService {

	@Autowired
	private JxTypeDao entityDao;

	@Transactional(readOnly=true)
	public JxType getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<JxType> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	public void save(JxType entity)
	{
		entityDao.save(entity);
	}
	
	public void update(JxType entity)
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
	public List<JxType> findAll()
	{
		return entityDao.find(" from JxType r order by r.bjmc");
	}

	@Override
	public List<JxType> getParent() {
		// TODO Auto-generated method stub
		return entityDao.find(" from JxType a where a.fparent is null");
	}

	@Override
	public List<JxType> getListById(String type) {
		// TODO Auto-generated method stub
		Query qry=entityDao.getSession().createQuery(" from JxType a where a.fparent.id =?");
		qry.setParameter(0, type);
		return qry.list();
	}

	@Override
	public List<JxType> getAllThree() {
		// TODO Auto-generated method stub
		return entityDao.find(" from JxType a where a.jdtype ='3'");
	}
	
}
