package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.Jiaoshi;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface JiaoshiService {

	Jiaoshi getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Jiaoshi> find(List<PropertyFilter> filters);
	
	void save(Jiaoshi entity);
	
	void update(Jiaoshi entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	
}
