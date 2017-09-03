package org.pfw.framework.wjgl.service.impl;

import java.util.List;
import org.pfw.framework.wjgl.dao.TwjlsyjDao;
import org.pfw.framework.wjgl.domain.Twjlsyj;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.service.TwjlsyjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TwjlsyjServiceImpl implements TwjlsyjService {

	@Autowired
	private TwjlsyjDao entityDao;

	@Transactional(readOnly=true)
	public Twjlsyj getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<Twjlsyj> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	public void save(Twjlsyj entity)
	{
		entityDao.save(entity);
	}
	
	public void update(Twjlsyj entity)
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
