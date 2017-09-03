package org.pfw.framework.wjgl.service.impl;

import java.util.List;
import org.pfw.framework.wjgl.dao.JxtxDao;
import org.pfw.framework.wjgl.domain.Jxtx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.service.JxtxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class JxtxServiceImpl implements JxtxService {

	@Autowired
	private JxtxDao entityDao;

	@Transactional(readOnly=true)
	public Jxtx getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Jxtx> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	public void save(Jxtx entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Jxtx entity)
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
	public List<Jxtx> findAll()
	{
		return entityDao.find(" from Jxtx r order by r.id");
	}
	
}
