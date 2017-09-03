package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.TopicOptions;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface TopicOptionsService {

	TopicOptions getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<TopicOptions> find(List<PropertyFilter> filters);
	
	void save(TopicOptions entity);
	
	void update(TopicOptions entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	List<TopicOptions> findAll();
}
