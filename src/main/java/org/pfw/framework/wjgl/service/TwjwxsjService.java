package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.Twjwxsj;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface TwjwxsjService {

	Twjwxsj getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Twjwxsj> find(List<PropertyFilter> filters);
	
	void save(Twjwxsj entity);
	
	void update(Twjwxsj entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	
}
