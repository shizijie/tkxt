package org.pfw.framework.wjgl.service;

import java.util.List;

import org.pfw.framework.wjgl.domain.Jxtx;
import org.pfw.framework.modules.orm.Page;
import org.pfw.framework.modules.orm.PropertyFilter;



public interface JxtxService {

	Jxtx getById(String id);
	
	Page findPage(Page page, List<PropertyFilter> filters);
	
	List<Jxtx> find(List<PropertyFilter> filters);
	
	void save(Jxtx entity);
	
	void update(Jxtx entity);

	void deleteById(String id);
	
	void delete(List<String> checks);

	List<Jxtx> findAll();
}
