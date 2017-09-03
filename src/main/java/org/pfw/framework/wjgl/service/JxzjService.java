package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.Jxzj;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface JxzjService {

	Jxzj getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Jxzj> find(List<PropertyFilter> filters);
	
	void save(Jxzj entity);
	
	void update(Jxzj entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	List<Jxzj> findAll();
	
	
}
