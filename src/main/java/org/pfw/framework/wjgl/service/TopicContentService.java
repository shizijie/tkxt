package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.TopicContent;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface TopicContentService {

	TopicContent getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<TopicContent> find(List<PropertyFilter> filters);
	
	void save(TopicContent entity);
	
	void update(TopicContent entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	List<TopicContent> findAll();
}
