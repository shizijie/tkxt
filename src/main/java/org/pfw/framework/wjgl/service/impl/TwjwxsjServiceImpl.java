package org.pfw.framework.wjgl.service.impl;

import java.util.List;
import org.pfw.framework.wjgl.dao.TwjwxsjDao;
import org.pfw.framework.wjgl.domain.Twjwxsj;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.service.TwjwxsjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TwjwxsjServiceImpl implements TwjwxsjService {

	@Autowired
	private TwjwxsjDao entityDao;

	@Transactional(readOnly=true)
	public Twjwxsj getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Twjwxsj> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	public void save(Twjwxsj entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Twjwxsj entity)
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

	
}
