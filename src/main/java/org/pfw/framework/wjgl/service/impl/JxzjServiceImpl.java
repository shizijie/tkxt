package org.pfw.framework.wjgl.service.impl;

import java.util.List;

import org.pfw.framework.wjgl.dao.JxzjDao;
import org.pfw.framework.wjgl.domain.Jxzj;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.service.JxzjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class JxzjServiceImpl implements JxzjService {

	@Autowired
	private JxzjDao entityDao;

	@Transactional(readOnly=true)
	public Jxzj getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Jxzj> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	public void save(Jxzj entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Jxzj entity)
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
	public List<Jxzj> findAll()
	{
		return entityDao.find(" from Jxzj r order by r.id");
	}

	
}
