package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.Banji;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface BanjiService {

	Banji getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Banji> find(List<PropertyFilter> filters);
	
	void save(Banji entity);
	
	void update(Banji entity);

	void deleteById(String id);
	
	void delete(List<String> checks);
	
	List<Banji> findAll();

	
}
