package org.pfw.framework.wjgl.service.impl;

import java.util.List;
import org.pfw.framework.wjgl.dao.TopicOptionsDao;
import org.pfw.framework.wjgl.domain.TopicOptions;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;
import org.pfw.framework.wjgl.service.TopicOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class TopicOptionsServiceImpl implements TopicOptionsService {

	@Autowired
	private TopicOptionsDao entityDao;

	@Transactional(readOnly=true)
	public TopicOptions getById(String id) {
		return entityDao.get(id);
	}
	
	@Transactional(readOnly=true)
	public Page findPage(Page page, List<PropertyFilter> filters) {
		return entityDao.findPage(page, filters);
	}	
	
	@Transactional(readOnly=true)
	public List<TopicOptions> find(List<PropertyFilter> filters)
	{
		return entityDao.find(filters);
	}
	
	public void save(TopicOptions entity)
	{
		entityDao.save(entity);
	}
	
	public void update(TopicOptions entity)
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
	public List<TopicOptions> findAll()
	{
		return entityDao.find(" from TopicOptions r order by r.bjmc");
	}
	
}
